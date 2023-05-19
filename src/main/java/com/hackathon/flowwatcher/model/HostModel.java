package com.hackathon.flowwatcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.Document;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

// Class for generating objects with data usage
public class HostModel {
    private Document document;
    public List<HostModel> hostModelsList;

    // Data usage details
    private String source;
    private double total;
    private double download;
    private double upload;

    // Constructor for Model usage when saving to DB to show history
    public HostModel(Document document){
        this.document = document;
    }

    // Constructor used when receiving real-time data
    public HostModel() throws IOException {
    }

    // Creating Object List
    public static List<HostModel> getHostModelsFromJson(String json) throws IOException {
        List<HostModel> hostModels = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        Iterator<String> keys = jsonNode.fieldNames();

        while (keys.hasNext()) {
            String key = keys.next();
            JsonNode hostNode = jsonNode.get(key);

            HostModel hostModel = new HostModel();
            hostModel.setSource(hostNode.get("name").asText());
            hostModel.setDownload(hostNode.get("download").asDouble());
            hostModel.setUpload(hostNode.get("upload").asDouble());
            hostModel.setTotal(hostNode.get("total").asDouble());

            hostModels.add(hostModel);
        }

        return hostModels;
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