package model;

import com.mongodb.ConnectionString;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoDatabase;
import com.mongodb.reactivestreams.client.MongoClients;

public class MongoDBConnection {
    private static final String DATABASE_NAME = "mydatabase";

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBConnection() {
        // Configuração da conexão com o MongoDB
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        this.mongoClient = MongoClients.create(connectionString);

        // Obtenção de uma referência ao banco de dados
        this.database = mongoClient.getDatabase(DATABASE_NAME);
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        mongoClient.close();
    }
}