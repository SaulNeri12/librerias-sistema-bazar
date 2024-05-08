package dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

import conexion.MongoDBConexion;
import dao.excepciones.DAOException;
import objetosNegocio.ProductoDTO;

public class GestorProductos {
    private final ConvertidorBazarDTO convertidor = new ConvertidorBazarDTO();
    private final MongoDatabase database;
    private final MongoCollection<Document> productosCollection;

    public GestorProductos() throws DAOException {
        // Obtener la base de datos MongoDB
        this.database = MongoDBConexion.getDatabase();
        // Obtener la colección de productos
        this.productosCollection = database.getCollection("productos");
    }

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

    public void registrarProducto(ProductoDTO producto) throws DAOException {
        if (producto == null) {
            throw new DAOException("El producto dado es null");
        }

        // Convertir el DTO a un documento MongoDB
        Document productoDoc = convertidor.convertirProductoDTOADocumento(producto);

        // Insertar el documento en la colección de productos
        productosCollection.insertOne(productoDoc);
    }

    public void eliminarProducto(String codigoInterno) throws DAOException {
        try {
            // Obtener la colección de productos
            MongoCollection<Document> collection = MongoDBConexion.getDatabase().getCollection("productos");

            // Crear el filtro para encontrar el producto por su código interno
            Bson filtro = Filters.eq("codigoInterno", codigoInterno);

            // Ejecutar la operación de eliminación
            DeleteResult result = collection.deleteOne(filtro);

            // Verificar si se eliminó algún documento
            if (result.getDeletedCount() == 0) {
                throw new DAOException("No se encontró ningún producto con el código interno especificado");
            }
        } catch (Exception ex) {
            throw new DAOException("Error al eliminar el producto", ex);
        }
    }

    

    /**
     * Actualiza un producto en la base de datos.
     *
     * @param productoDTO El DTO del producto con los datos actualizados.
     * @throws DAOException Si el producto dado es null, si no se encontró el
     *                      producto en el sistema o si no se pudieron modificar los
     *                      datos del producto.
     */
    public void actualizarProducto(ProductoDTO productoDTO) throws DAOException {
        if (productoDTO == null) {
            throw new DAOException("El producto dado es null");
        }
        try {
            // Buscar el producto en la base de datos por su código interno
            Document query = new Document("codigoInterno", productoDTO.getCodigoInterno());
            Document productoDoc = productosCollection.find(query).first();

            // Verificar si el producto existe
            if (productoDoc == null) {
                throw new DAOException("No se encontró el producto a actualizar");
            }

            // Actualizar los campos del producto con los nuevos valores
            productoDoc.put("nombre", productoDTO.getNombre());
            productoDoc.put("precio", productoDTO.getPrecio());

            // Actualizar el documento en la colección
            productosCollection.replaceOne(query, productoDoc);
        } catch (Exception ex) {
            throw new DAOException("Error al actualizar el producto", ex);
        }
    }
}
