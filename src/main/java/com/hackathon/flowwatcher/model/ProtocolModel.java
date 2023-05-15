package com.hackathon.flowwatcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class ProtocolModel {
    Document document;
    @JsonProperty("total")
    private String total;
    @JsonProperty("download")
    private String download;
    @JsonProperty("upload")
    private String upload;

    public ProtocolModel(Document document){
        this.document = document;
    }
    public ProtocolModel(String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProtocolModel protocolModel = objectMapper.readValue(json, ProtocolModel.class);

            System.out.println("Total: " + protocolModel.getTotal());
            System.out.println("Download: " + protocolModel.getDownload());
            System.out.println("Upload: " + protocolModel.getUpload());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getters & Setters
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }
}
