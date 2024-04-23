package pruebas;

import conexion.EntityManagerSingleton;
import dao.GestorProductos;
import dao.GestorProveedores;
import dao.GestorUsuarios;
import dao.GestorVentas;
import java.time.LocalDateTime;
import java.util.List;
import objetosNegocio.DireccionDTO;
import objetosNegocio.ProductoDTO;
import objetosNegocio.ProveedorDTO;

import subsistemas.interfaces.*;
import subsistemas.excepciones.DAOException;

/**
 *
 * @author saul
 */
public class PersistenciaListasBazar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GestorProveedores gestorProveedores = GestorProveedores.getInstance();
        GestorProductos gestorProductos = GestorProductos.getInstance();
        /*
        pruebaConsultarTodos(gestorProductos);
        pruebaConsultarProductosPorNombre(gestorProductos);
        pruebaConsultarProductoPorCodigoInterno(gestorProductos);
        pruebaConsultarProductoPorCodigoBarras(gestorProductos);
         */
        
        // pruebaActualizarProducto(gestorProductos); 
        pruebaEliminarProducto(gestorProductos);
        
        // insertarProveedores(gestorProveedores);
        // TODO: CUANDO SE HAGAN LAS CONVERSIONES EN LOS DAO, EMPIEZEN POR HACER
        //       BUSQUEDA, INSERCION, ACTUALIZACION Y ELMININACION CON LOS SUBSISTEMAS
        //       NECESARIOS, NO HABRAN UNA NUEVA CONEXION. PARA ESO SON LOS 
        //       DAO...
    }

    public static void insertarProveedores(GestorProveedores gestorProveedores) {
        try {
            // Obtener la fecha y hora actual
            LocalDateTime fechaActual = LocalDateTime.now();

            // Crear direcciones
            DireccionDTO direccionProveedor1 = new DireccionDTO();
            direccionProveedor1.setCiudad("Ciudad 1");
            direccionProveedor1.setNumeroEdificio("123");
            direccionProveedor1.setCalle("Calle 1");
            direccionProveedor1.setColonia("Colonia 1");
            direccionProveedor1.setCodigoPostal("12345");

            DireccionDTO direccionProveedor2 = new DireccionDTO();
            direccionProveedor2.setCiudad("Ciudad 2");
            direccionProveedor2.setNumeroEdificio("456");
            direccionProveedor2.setCalle("Calle 2");
            direccionProveedor2.setColonia("Colonia 2");
            direccionProveedor2.setCodigoPostal("54321");

            // Crear proveedores
            ProveedorDTO proveedor1 = new ProveedorDTO();
            proveedor1.setNombre("Proveedor 1");
            proveedor1.setTelefono("123456789");
            proveedor1.setEmail("proveedor1@example.com");
            proveedor1.setDescripcion("Proveedor número 1");
            proveedor1.setDireccion(direccionProveedor1);
            proveedor1.setFechaRegistro(fechaActual);

            ProveedorDTO proveedor2 = new ProveedorDTO();
            proveedor2.setNombre("Proveedor 2");
            proveedor2.setTelefono("987654321");
            proveedor2.setEmail("proveedor2@example.com");
            proveedor2.setDescripcion("Proveedor número 2");
            proveedor2.setDireccion(direccionProveedor2);
            proveedor2.setFechaRegistro(fechaActual);

            // Registrar proveedores
            gestorProveedores.registrarProveedor(proveedor1);
            gestorProveedores.registrarProveedor(proveedor2);

            System.out.println("Proveedores registrados correctamente.");
        } catch (DAOException e) {
            System.err.println("Error al registrar proveedores: " + e.getMessage());
        }
    }

    public static void pruebaConsultarTodos(GestorProductos gestorProductos) {
        try {
            // Consultar todos los productos
            List<ProductoDTO> productos = gestorProductos.consultarTodos();

            // Mostrar los productos consultados
            System.out.println("Productos registrados en el sistema:");
            for (ProductoDTO producto : productos) {
                System.out.println("Nombre: " + producto.getNombre() + ", Código Interno: " + producto.getCodigoInterno());
            }
        } catch (DAOException e) {
            System.out.println("Error al consultar todos los productos: " + e.getMessage());
        }
    }

    public static void pruebaConsultarProductosPorNombre(GestorProductos gestorProductos) {
        try {
            // Consultar productos cuyo nombre contiene la cadena "prueba"
            String nombreABuscar = "prueba";
            List<ProductoDTO> productos = gestorProductos.consultarProductosPorNombre(nombreABuscar);

            // Mostrar los productos consultados
            System.out.println("Productos cuyo nombre contiene '" + nombreABuscar + "':");
            for (ProductoDTO producto : productos) {
                System.out.println("Nombre: " + producto.getNombre() + ", Código Interno: " + producto.getCodigoInterno());
            }
        } catch (DAOException e) {
            System.out.println("Error al consultar productos por nombre: " + e.getMessage());
        }
    }

    public static void pruebaConsultarProductoPorCodigoInterno(GestorProductos gestorProductos) {
        String codigoInterno = "ABC123"; // Supongamos que este es el código interno que queremos buscar
        try {
            ProductoDTO producto = gestorProductos.consultarProducto(codigoInterno);
            if (producto != null) {
                System.out.println("Producto encontrado por código interno:");
                System.out.println("Nombre: " + producto.getNombre() + ", Código Interno: " + producto.getCodigoInterno());
            } else {
                System.out.println("No se encontró ningún producto con el código interno: " + codigoInterno);
            }
        } catch (DAOException e) {
            System.err.println("Error al consultar el producto por código interno: " + e.getMessage());
        }
    }

    public static void pruebaConsultarProductoPorCodigoBarras(GestorProductos gestorProductos) {
        Long codigoBarras = 123456789L; // Supongamos que este es el código de barras que queremos buscar
        try {
            ProductoDTO producto = gestorProductos.consultarProducto(codigoBarras);
            if (producto != null) {
                System.out.println("Producto encontrado por código de barras:");
                System.out.println("Nombre: " + producto.getNombre() + ", Código Interno: " + producto.getCodigoInterno());
            } else {
                System.out.println("No se encontró ningún producto con el código de barras: " + codigoBarras);
            }
        } catch (DAOException e) {
            System.err.println("Error al consultar el producto por código de barras: " + e.getMessage());
        }
    }

    public static void pruebaActualizarProducto(GestorProductos gestorProductos) {
        try {
            // Crear un nuevo producto para insertarlo
            ProductoDTO nuevoProducto = new ProductoDTO();
            nuevoProducto.setCodigoBarras(2222111166L);
            nuevoProducto.setCodigoInterno("BQZ123");
            nuevoProducto.setNombre("Producto de Prueba");
            nuevoProducto.setPrecio(50.0f);
            nuevoProducto.setFechaRegistro(LocalDateTime.now());

            gestorProductos.registrarProducto(nuevoProducto);

            nuevoProducto.setNombre("Nuevo Nombre");
            nuevoProducto.setPrecio(99.99f);
            gestorProductos.actualizarProducto(nuevoProducto);

            System.out.println("Producto actualizado correctamente.");
        } catch (DAOException e) {
            System.err.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    public static void pruebaEliminarProducto(GestorProductos gestorProductos) {
        try {
            String codigoInterno = "ABC123";

            gestorProductos.eliminarProducto(codigoInterno);

            System.out.println("Producto eliminado correctamente.");
        } catch (DAOException e) {
            System.err.println("Error al eliminar el producto: " + e.getMessage());
        }
    }
}
