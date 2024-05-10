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

       //TODO: registrar - listo
       //      modificar/editar - listo
       //      eliminar - listo
       //      consultarTodos - listo
       //      falta consultar por codigoInterno, por nombre, por precio en Ascendente y en descendente.
       //      falta consultar por fecha de registro en Ascendente y en descendente.

        GestorProductos gestorProductos = new GestorProductos();
        gestorProductos.consultarPorCodigoInterno("PROD001");
    }

    // Método para insertar un producto en la base de datos MongoDB
    public static void insertarProducto(ProductoDTO producto) throws DAOException {
        // Obtener la colección de productos
        MongoCollection<Document> coleccionProductos = MongoDBConexion.getDatabase().getCollection("productos");

        // Crear un documento a partir del producto
        Document documentoProducto = new Document("codigoBarras", producto.getCodigoBarras())
                .append("codigoInterno", producto.getCodigoInterno())
                .append("nombre", producto.getNombre())
                .append("precio", producto.getPrecio())
                .append("fechaRegistro", producto.getFechaRegistro().toString());

        // Insertar el documento en la colección
        coleccionProductos.insertOne(documentoProducto);
        System.out.println("Producto insertado en MongoDB.");
    }

    // Método para consultar todos los productos de la base de datos MongoDB
    public static List<ProductoDTO> consultarTodosProductos() throws DAOException {
        // Obtener la colección de productos
        MongoCollection<Document> coleccionProductos = MongoDBConexion.getDatabase().getCollection("productos");

        // Crear una lista para almacenar los productos consultados
        List<ProductoDTO> productos = new ArrayList<>();

        // Consultar todos los documentos de la colección
        MongoCursor<Document> cursor = coleccionProductos.find().iterator();
        while (cursor.hasNext()) {
            Document productoDoc = cursor.next();

            // Crear un ProductoDTO a partir del documento
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setCodigoBarras(productoDoc.getLong("codigoBarras"));
            productoDTO.setCodigoInterno(productoDoc.getString("codigoInterno"));
            productoDTO.setNombre(productoDoc.getString("nombre"));
            productoDTO.setPrecio(productoDoc.getDouble("precio").floatValue());
            productoDTO.setFechaRegistro(LocalDateTime.parse(productoDoc.getString("fechaRegistro")));

            // Agregar el producto a la lista
            productos.add(productoDTO);
        }
        cursor.close();

        return productos;
    }
}
