package dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import conexion.MongoDBConexion;
import dao.excepciones.DAOException;
import objetosNegocio.ProductoDTO;

public class GestorProductos {

public List<ProductoDTO> consultarTodos() throws DAOException {
    try {
        // Obtener la base de datos MongoDB
        MongoDatabase database = MongoDBConexion.getDatabase();

        // Obtener la colección de productos
        MongoCollection<Document> productosCollection = database.getCollection("productos");

        // Consultar todos los documentos de la colección de productos
        MongoCursor<Document> cursor = productosCollection.find().iterator();

        // Convertir los documentos de productos a DTOs
        List<ProductoDTO> productosDTO = new ArrayList<>();
        ConvertidorBazarDTO convertidor = new ConvertidorBazarDTO();

        while (cursor.hasNext()) {
            Document productoDoc = cursor.next();
            ProductoDTO productoDTO = convertidor.convertirDocumentoAProductoDTO(productoDoc);
            productosDTO.add(productoDTO);
        }

        cursor.close();

        return productosDTO;
    } catch (Exception ex) {
        throw new DAOException("Error al consultar todos los productos", ex);
    }
}
}
