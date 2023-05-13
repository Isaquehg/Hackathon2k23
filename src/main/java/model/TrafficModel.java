package model;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.Document;
import org.bson.Bson

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public List<DataModel> getProtocolDataByPeriod(LocalDateTime startTime, LocalDateTime endTime) {

        // Exemplo de implementação:
        List<DataModel> protocolDataList = new ArrayList<>();

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
            DataModel protocolData = convertDocumentToProtocolData(document);
            protocolDataList.add(protocolData);
        }

        return protocolDataList;
    }

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