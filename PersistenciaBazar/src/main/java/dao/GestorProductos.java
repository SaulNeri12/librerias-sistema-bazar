package dao;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
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
            Document query = new Document("codigoInterno", productoDTO.getCodigoInterno());
            Document productoDoc = productosCollection.find(query).first();

            if (productoDoc == null) {
                throw new DAOException("No se encontró el producto a actualizar");
            }

            productoDoc.put("nombre", productoDTO.getNombre());
            productoDoc.put("precio", productoDTO.getPrecio());

            productosCollection.replaceOne(query, productoDoc);
        } catch (Exception ex) {
            throw new DAOException("Error al actualizar el producto", ex);
        }
    }

    public ProductoDTO consultarPorCodigoInterno(String codigoInterno) throws DAOException {
        try {
            Bson filtro = Filters.eq("codigoInterno", codigoInterno);

            Document productoDoc = productosCollection.find(filtro).first();

            if (productoDoc == null) {
                throw new DAOException("No se encontró ningún producto con el código interno especificado");
            }

            ProductoDTO productoDTO = convertidor.convertirDocumentoAProductoDTO(productoDoc);
            return productoDTO;

        } catch (Exception ex) {
            throw new DAOException("Error al consultar el producto por su código interno", ex);
        }
    }

    public List<ProductoDTO> consultarPorNombre(String nombre) throws DAOException {
        List<ProductoDTO> productosEncontrados = new ArrayList<>();
        try {
            String nombreNormalizado = nombre.trim().toLowerCase();

            Bson filtro = Filters.eq("nombre", nombreNormalizado);

            FindIterable<Document> productosDocs = productosCollection.find(filtro);

            if (!productosDocs.iterator().hasNext()) {
                throw new DAOException("No se encontró ningún producto con el nombre especificado");
            }

            for (Document productoDoc : productosDocs) {
                ProductoDTO productoDTO = convertidor.convertirDocumentoAProductoDTO(productoDoc);
                productosEncontrados.add(productoDTO);
            }
        } catch (Exception ex) {
            throw new DAOException("Error al consultar el producto por su nombre", ex);
        }
        return productosEncontrados;
    }

    public List<ProductoDTO> consultarPorPrecioAscendente(float precioLimite) throws DAOException {
        List<ProductoDTO> productosEncontrados = new ArrayList<>();
        try {

            Bson filtro = Filters.lte("precio", precioLimite);


            Bson ordenAscendente = Sorts.ascending("precio");


            FindIterable<Document> productosDocs = productosCollection.find(filtro).sort(ordenAscendente);


            for (Document productoDoc : productosDocs) {
                ProductoDTO productoDTO = convertidor.convertirDocumentoAProductoDTO(productoDoc);
                productosEncontrados.add(productoDTO);
            }
        } catch (Exception ex) {
            throw new DAOException("Error al consultar productos por precio ascendente", ex);
        }
        return productosEncontrados;
    }

    public List<ProductoDTO> consultarPorPrecioDescendente(float precioLimite) throws DAOException {
        List<ProductoDTO> productosEncontrados = new ArrayList<>();
        try {

            Bson filtro = Filters.gte("precio", precioLimite);

            Bson ordenDescendente = Sorts.descending("precio");

            FindIterable<Document> productosDocs = productosCollection.find(filtro).sort(ordenDescendente);

            for (Document productoDoc : productosDocs) {
                ProductoDTO productoDTO = convertidor.convertirDocumentoAProductoDTO(productoDoc);
                productosEncontrados.add(productoDTO);
            }
        } catch (Exception ex) {
            throw new DAOException("Error al consultar productos por precio descendente", ex);
        }
        return productosEncontrados;
    }

    public List<ProductoDTO> consultarPorFechaAscendente(LocalDateTime fechaLimite) throws DAOException {
        List<ProductoDTO> productosEncontrados = new ArrayList<>();
        try {

            Bson filtro = Filters.gte("fechaRegistro",
                    Date.from(fechaLimite.atZone(ZoneId.systemDefault()).toInstant()));

            Bson ordenAscendente = Sorts.ascending("fechaRegistro");

            FindIterable<Document> productosDocs = productosCollection.find(filtro).sort(ordenAscendente);

            for (Document productoDoc : productosDocs) {
                ProductoDTO productoDTO = convertidor.convertirDocumentoAProductoDTO(productoDoc);
                productosEncontrados.add(productoDTO);
            }
        } catch (Exception ex) {
            throw new DAOException("Error al consultar productos por fecha ascendente", ex);
        }
        return productosEncontrados;
    }

    public List<ProductoDTO> consultarPorFechaDescendente(LocalDateTime fechaLimite) throws DAOException {
        List<ProductoDTO> productosEncontrados = new ArrayList<>();
        try {

            Bson filtro = Filters.lte("fechaRegistro",
                    Date.from(fechaLimite.atZone(ZoneId.systemDefault()).toInstant()));

            Bson ordenDescendente = Sorts.descending("fechaRegistro");

            FindIterable<Document> productosDocs = productosCollection.find(filtro).sort(ordenDescendente);

            for (Document productoDoc : productosDocs) {
                ProductoDTO productoDTO = convertidor.convertirDocumentoAProductoDTO(productoDoc);
                productosEncontrados.add(productoDTO);
            }
        } catch (Exception ex) {
            throw new DAOException("Error al consultar productos por fecha descendente", ex);
        }
        return productosEncontrados;
    }

}
