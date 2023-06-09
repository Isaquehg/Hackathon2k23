package com.hackathon.flowwatcher.model;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;

public class TrafficModel {
    private MongoDBConnection dbConnection;
    private MongoCollection<Document> appTrafficCollection;
    private MongoCollection<Document> hostTrafficCollection;
    private MongoCollection<Document> protocolTrafficCollection;
    private MongoDatabase database;

    public TrafficModel() {
        // Instantiating MongoDB and connecting to it
        this.dbConnection = new MongoDBConnection();
        this.database = dbConnection.getDatabase();

        // Getting the collections to store
        this.appTrafficCollection = database.getCollection("apptraffic");
        this.hostTrafficCollection = database.getCollection("hosttraffic");
        this.protocolTrafficCollection = database.getCollection("protocoltraffic");
    }

    // Update app data traffic in database
    public void updateAppTraffic(String data) {
        // Removing HTTP headers
        int jsonStart = data.indexOf('{');
        int jsonEnd = data.lastIndexOf('}') + 1;
        String jsonData = data.substring(jsonStart, jsonEnd);

        // Parse and set the document in MongoDB
        Document document = Document.parse(jsonData);
        appTrafficCollection.insertOne(document);
        System.out.println("Successfully saved App Traffic!");
    }

    // Update host data traffic
    public void updateHostTraffic(String data) {
        // Removing HTTP headers
        int jsonStart = data.indexOf('{');
        int jsonEnd = data.lastIndexOf('}') + 1;
        String jsonData = data.substring(jsonStart, jsonEnd);

        // Parse and set the document in MongoDB
        Document document = Document.parse(jsonData);
        hostTrafficCollection.insertOne(document);
        System.out.println("Successfully saved Host Traffic!");
    }

    // Update protocol data traffic
    public void updateProtocolTraffic(String data) {
        // Removing HTTP headers
        int jsonStart = data.indexOf('{');
        int jsonEnd = data.lastIndexOf('}') + 1;
        String jsonData = data.substring(jsonStart, jsonEnd);

        // Parse and set the document in MongoDB
        Document document = Document.parse(jsonData);
        protocolTrafficCollection.insertOne(document);
        System.out.println("Successfully saved Protocol Traffic!");
    }

    // Function to get data usage from Protocol in a specific period
    public List<ProtocolModel> getProtocolDataByPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        System.out.println("Searching Protocol Traffic...");

        MongoCollection<Document> collection = database.getCollection("protocoltraffic");

        // Defining query filter
        Bson filter = Filters.and(
                Filters.gte("timestamp", startTime),
                Filters.lte("timestamp", endTime)
        );

        // Query
        Flux<Document> result = Flux.from(collection.find(filter));

        // Converting query to objects List
        List<ProtocolModel> protocolDataList = result.map(this::convertDocumentToProtocolData)
                .collectList()
                .block();

        return protocolDataList;
    }

    public List<AppModel> getAppDataByPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        System.out.println("Searching Apps Traffic...");

        MongoCollection<Document> collection = database.getCollection("apptraffic");

        // Defining query filter
        Bson filter = Filters.and(
                Filters.gte("timestamp", startTime),
                Filters.lte("timestamp", endTime)
        );

        // Query
        Flux<Document> result = Flux.from(collection.find(filter));

        // Converting query to objects List
        List<AppModel> appDataList = result.map(this::convertDocumentToAppData)
                .collectList()
                .block();

        return appDataList;
    }

    public List<HostModel> getHostDataByPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        System.out.println("Searching Host Traffic...");

        MongoCollection<Document> collection = database.getCollection("hosttraffic");

        // Defining query filter
        Bson filter = Filters.and(
                Filters.gte("timestamp", startTime),
                Filters.lte("timestamp", endTime)
        );

        // Query
        Flux<Document> result = Flux.from(collection.find(filter));

        // Converting query to objects List
        List<HostModel> hostDataList = result.map(this::convertDocumentToHostData)
                .collectList()
                .block();

        return hostDataList;
    }

    // Conversions from BSON to Model object
    private ProtocolModel convertDocumentToProtocolData(Document document) {
        ProtocolModel protocolModel = new ProtocolModel(document);
        return protocolModel;
    }
    private AppModel convertDocumentToAppData(Document document) {
        AppModel appModel = new AppModel(document);
        return appModel;
    }
    private HostModel convertDocumentToHostData(Document document) {
        HostModel hostModel = new HostModel(document);
        return hostModel;
    }

    // Close MongoDB connection
    public void close() {
        dbConnection.close();
    }
}