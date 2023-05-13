package model;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    // Function to get data usage from Protocol in a specific period
    public List<ProtocolModel> getProtocolDataByPeriod(LocalDateTime startTime, LocalDateTime endTime) {
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
        List<AppModel> appDataList = new ArrayList<>();

        // Consultar os documentos no MongoDB que correspondem ao período de tempo especificado
        // Utilize os métodos adequados do driver do MongoDB para executar a consulta
        // Aqui está um exemplo simplificado de como fazer a consulta:
        MongoCollection<Document> collection = database.getCollection("protocoltraffic");
        Bson filter = Filters.and(
                Filters.gte("timestamp", startTime),
                Filters.lte("timestamp", endTime)
        );
        FindIterable<Document> result = collection.find(filter);

        // Iterar sobre os documentos retornados e converter em objetos ProtocolData
        for (Document document : result) {
            AppModel appData = convertDocumentToAppData(document);
            appDataList.add(appData);
        }

        return appDataList;
    }

    public List<HostModel> getHostDataByPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        List<HostModel> hostDataList = new ArrayList<>();

        MongoCollection<Document> collection = database.getCollection("protocoltraffic");
        Bson filter = Filters.and(
                Filters.gte("timestamp", startTime),
                Filters.lte("timestamp", endTime)
        );
        FindIterable<Document> result = collection.find(filter);

        // Iterar sobre os documentos retornados e converter em objetos ProtocolData
        for (Document document : result) {
            HostModel hostData = convertDocumentToHostData(document);
            hostDataList.add(hostData);
        }

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