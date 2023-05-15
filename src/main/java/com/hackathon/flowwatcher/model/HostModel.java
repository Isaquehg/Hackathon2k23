package com.hackathon.flowwatcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;

public class HostModel {
    Document document;
    @JsonProperty("total")
    private double total;
    @JsonProperty("download")
    private double download;
    @JsonProperty("upload")
    private double upload;

    public HostModel(Document document){
        this.document = document;
    }
    public HostModel(String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            HostModel dataModel = objectMapper.readValue(json, HostModel.class);

            System.out.println("Host:");
            System.out.println("Total: " + dataModel.getTotal());
            System.out.println("Download: " + dataModel.getDownload());
            System.out.println("Upload: " + dataModel.getUpload());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Convert from BSON
    public void convertBSON(){
        total = (double) this.document.get("total");
        download = (double) this.document.get("download");
        upload = (double) this.document.get("upload");
    }

    // Getters & Setters
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
