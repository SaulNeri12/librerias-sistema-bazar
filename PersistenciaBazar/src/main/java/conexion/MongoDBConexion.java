package conexion;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import dao.excepciones.DAOException;

public class MongoDBConexion {

    private static final String DATABASE_NAME = "bazar";
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    // Método para obtener el cliente de MongoDB
    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            // Configuración del cliente de MongoDB
            ConnectionString connectionString = new ConnectionString(CONNECTION_STRING);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();

            // Creación del cliente de MongoDB
            mongoClient = MongoClients.create(settings);
        }
        return mongoClient;
    }

    public static MongoDatabase getDatabase() throws DAOException {
    try {
        // Obtener el cliente de MongoDB
        MongoClient client = getMongoClient();

        // Obtener la base de datos
        return client.getDatabase(DATABASE_NAME);
    } catch (Exception ex) {
        throw new DAOException("Error al obtener la base de datos MongoDB", ex);
    }
}

    // Método para cerrar la conexión
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}