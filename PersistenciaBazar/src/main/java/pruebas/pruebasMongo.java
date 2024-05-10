package pruebas;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import conexion.MongoDBConexion;
import dao.GestorProductos;
import dao.excepciones.DAOException;
import objetosNegocio.ProductoDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class pruebasMongo {

    public static void main(String[] args) throws DAOException {

        // TODO: registrar - listo
        // modificar/editar - listo
        // eliminar - listo
        // consultarTodos - listo
        // falta consultar por codigoInterno, por nombre, por precio en Ascendente y en
        // descendente.
        // falta consultar por fecha de registro en Ascendente y en descendente.

        GestorProductos gestorProductos = new GestorProductos();

        // Crear algunos productos de prueba y registrarlos
        ProductoDTO producto1 = new ProductoDTO();
        producto1.setCodigoInterno("PROD001");
        producto1.setCodigoBarras(4444444444L);
        producto1.setNombre("Producto A");
        producto1.setPrecio(10.0);
        producto1.setFechaRegistro(LocalDateTime.now());
        gestorProductos.registrarProducto(producto1);

        ProductoDTO producto2 = new ProductoDTO();
        producto2.setCodigoInterno("PROD002");
        producto2.setCodigoBarras(0000000000L);
        producto2.setNombre("Producto B");
        producto2.setPrecio(15.0);
        producto2.setFechaRegistro(LocalDateTime.now().minusDays(1));
        gestorProductos.registrarProducto(producto2);

        ProductoDTO producto3 = new ProductoDTO();
        producto3.setCodigoInterno("PROD003");
        producto3.setCodigoBarras(777777777L);
        producto3.setNombre("Producto C");
        producto3.setPrecio(20.0);
        producto3.setFechaRegistro(LocalDateTime.now().minusDays(2));
        gestorProductos.registrarProducto(producto3);

        try {
            // Crear un nuevo producto para insertar en la base de datos
            ProductoDTO productoNuevo = new ProductoDTO();
            productoNuevo.setCodigoBarras(123456789L);
            productoNuevo.setCodigoInterno("PROD004");
            productoNuevo.setNombre("Producto de Prueba");
            productoNuevo.setPrecio(20.5f);
            productoNuevo.setFechaRegistro(LocalDateTime.now());
    
            // Insertar el nuevo producto en la base de datos
            gestorProductos.registrarProducto(productoNuevo);
            System.out.println("Producto registrado exitosamente.");
    
            // Consultar el producto recién insertado
            ProductoDTO productoConsultado = gestorProductos.consultarPorCodigoInterno("PROD001");
            System.out.println("Producto consultado: " + productoConsultado);
    
            // Modificar el nombre del producto consultado
            productoConsultado.setNombre("Producto modificado");
            // Actualizar el producto en la base de datos
            gestorProductos.actualizarProducto(productoConsultado);
            System.out.println("Producto actualizado exitosamente.");
    
            // Consultar el producto nuevamente para verificar la actualización
            ProductoDTO productoActualizado = gestorProductos.consultarPorCodigoInterno("PROD001");
            System.out.println("Producto actualizado: " + productoActualizado);
    
        } catch (DAOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        gestorProductos.eliminarProducto("PROD001");

        try {
    
            // Ejecutar pruebas de consultas

            List<ProductoDTO> productos = gestorProductos.consultarTodos();

            for (ProductoDTO producto : productos) {
                System.out.println(producto);
            }

            probarConsultaPorFechaAscendente(gestorProductos);
            probarConsultaPorFechaDescendente(gestorProductos);
            probarConsultaPorPrecioAscendente(gestorProductos);
            probarConsultaPorPrecioDescendente(gestorProductos);
            probarConsultaPorNombre(gestorProductos);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public static void probarConsultaPorFechaAscendente(GestorProductos gestorProductos) throws DAOException {
        System.out.println("Consulta por fecha ascendente:");
        List<ProductoDTO> productos = gestorProductos.consultarPorFechaAscendente(LocalDateTime.now().minusDays(2));
        for (ProductoDTO producto : productos) {
            System.out.println(producto);
        }
    }
    
    public static void probarConsultaPorFechaDescendente(GestorProductos gestorProductos) throws DAOException {
        System.out.println("Consulta por fecha descendente:");
        List<ProductoDTO> productos = gestorProductos.consultarPorFechaDescendente(LocalDateTime.now().minusDays(2));
        for (ProductoDTO producto : productos) {
            System.out.println(producto);
        }
    }

    public static void probarConsultaPorPrecioAscendente(GestorProductos gestorProductos) throws DAOException {
        System.out.println("Consulta por precio ascendente:");
        List<ProductoDTO> productos = gestorProductos.consultarPorPrecioAscendente(30);
        for (ProductoDTO producto : productos) {
            System.out.println(producto);
        }
    }
    
    public static void probarConsultaPorPrecioDescendente(GestorProductos gestorProductos) throws DAOException {
        System.out.println("Consulta por precio descendente:");
        List<ProductoDTO> productos = gestorProductos.consultarPorPrecioDescendente(30);
        for (ProductoDTO producto : productos) {
            System.out.println(producto);
        }
    }
    
    public static void probarConsultaPorNombre(GestorProductos gestorProductos) throws DAOException {
        System.out.println("Consulta por nombre:");
        List<ProductoDTO> productos = gestorProductos.consultarPorNombre("Producto");
        for (ProductoDTO producto : productos) {
            System.out.println(producto);
        }
    }
    

}
