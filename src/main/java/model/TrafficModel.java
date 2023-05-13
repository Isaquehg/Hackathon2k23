package model;

import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.Document;

public class TrafficModel {
    private MongoDBConnection dbConnection;
    private MongoCollection<Document> appTrafficCollection;
    private MongoCollection<Document> hostTrafficCollection;
    private MongoCollection<Document> protocolTrafficCollection;

    public TrafficModel() {
        // Instantiating MongoDB and connecting to it
        this.dbConnection = new MongoDBConnection();
        MongoDatabase database = dbConnection.getDatabase();

        // Getting the collections to store
        this.appTrafficCollection = database.getCollection("apptraffic");
        this.hostTrafficCollection = database.getCollection("hosttraffic");
        this.protocolTrafficCollection = database.getCollection("protocoltraffic");
    }

    // Update app data traffic
    public void updateAppTraffic(String data) {
        Document document = Document.parse(data);
        appTrafficCollection.insertOne(document);
    }

    // Update host data traffic
    public void updateHostTraffic(String data) {
        Document document = Document.parse(data);
        hostTrafficCollection.insertOne(document);
    }

    // Update protocol data traffic
    public void updateProtocolTraffic(String data) {
        Document document = Document.parse(data);
        protocolTrafficCollection.insertOne(document);
    }

    // Close MongoDB connection
    public void close() {
        dbConnection.close();
    }
}