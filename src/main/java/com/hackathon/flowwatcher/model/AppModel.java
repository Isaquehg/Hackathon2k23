package com.hackathon.flowwatcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.Document;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Class for generating objects with data usage
public class AppModel {
    private Document document;
    public List<AppModel> appModels;

    // Data usage details
    private String source;
    private double total;
    private double download;
    private double upload;

    // Constructor for Model usage when converting a BSON to show history
    public AppModel(Document document){
        this.document = document;
    }

    // Constructor used when receiving real-time data
    public AppModel() throws IOException {

    }

    // Creating Object List
    public static List<AppModel> getAppModelsFromJson(String jsonString) {
        List<AppModel> appList = new ArrayList<>();

        try {
            int jsonStart = jsonString.indexOf('{');
            int jsonEnd = jsonString.lastIndexOf('}') + 1;
            String jsonData = jsonString.substring(jsonStart, jsonEnd);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonData);

            Iterator<String> keys = jsonNode.fieldNames();

            while (keys.hasNext()) {
                String key = keys.next();
                JsonNode appNode = jsonNode.get(key);

                AppModel app = new AppModel();
                app.setSource(appNode.get("name").asText());
                app.setDownload(appNode.get("download").asDouble());
                app.setUpload(appNode.get("upload").asDouble());
                app.setTotal(appNode.get("upload_speed").asDouble());

                appList.add(app);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return appList;
    }

    // Convert from BSON
    public void convertBSON(){
        source = (String) this.document.get("name");
        total = (double) this.document.get("total");
        download = (double) this.document.get("download");
        upload = (double) this.document.get("upload");
    }

    // Getters & Setters
    @JsonProperty("name")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("total")
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @JsonProperty("download")
    public double getDownload() {
        return download;
    }

    public void setDownload(double download) {
        this.download = download;
    }

    @JsonProperty("upload")
    public double getUpload() {
        return upload;
    }

    public void setUpload(double upload) {
        this.upload = upload;
    }
}