package conexion;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

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

    // Método para obtener la base de datos MongoDB
    public static MongoDatabase getDatabase() {
        if (database == null) {
            // Obtener el cliente de MongoDB
            MongoClient client = getMongoClient();

            // Obtener la base de datos
            database = client.getDatabase(DATABASE_NAME);
        }
        return database;
    }

    // Método para cerrar la conexión
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}