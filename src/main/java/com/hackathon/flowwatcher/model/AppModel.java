package com.hackathon.flowwatcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;
import com.fasterxml.jackson.databind.ObjectMapper;

// Class for generating objects with data usage
public class AppModel {
    private Document document;

    // Data usage details
    @JsonProperty("name")
    private String source;
    @JsonProperty("total")
    private double total;
    @JsonProperty("download")
    private double download;
    @JsonProperty("upload")
    private double upload;

    // Constructor for Model usage when converting a BSON to show history
    public AppModel(Document document){
        this.document = document;
    }

    // Constructor used when receiving real-time data
    public AppModel(String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AppModel dataModel = objectMapper.readValue(json, AppModel.class);

            System.out.println("App: " + dataModel.getSource());
            System.out.println("Total: " + dataModel.getTotal());
            System.out.println("Download: " + dataModel.getDownload());
            System.out.println("Upload: " + dataModel.getUpload());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Convert from BSON
    public void convertBSON(){
        source = (String) this.document.get("name");
        total = (double) this.document.get("total");
        download = (double) this.document.get("download");
        upload = (double) this.document.get("upload");
    }

    // Getters & Setters
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDownload() {
        return download;
    }

    public void setDownload(double download) {
        this.download = download;
    }

    public double getUpload() {
        return upload;
    }

    public void setUpload(double upload) {
        this.upload = upload;
    }
}
