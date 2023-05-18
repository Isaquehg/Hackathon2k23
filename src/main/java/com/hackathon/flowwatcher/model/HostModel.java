package com.hackathon.flowwatcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import org.bson.Document;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        // Removing unwanted text
        int jsonStart = json.indexOf('{');
        int jsonEnd = json.lastIndexOf('}') + 1;
        String jsonSubstring = json.substring(jsonStart, jsonEnd);

        // Processing JSON
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, HostModel>> typeReference = new TypeReference<Map<String, HostModel>>() {};

        Map<String, HostModel> map = objectMapper.readValue(jsonSubstring, typeReference);
        List<HostModel> hostModels = new ArrayList<>(map.values());

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
