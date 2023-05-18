package com.hackathon.flowwatcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class ProtocolModel {
    Document document;
    private String protocol;
    private double total;
    private double download;
    private double upload;

    public ProtocolModel(Document document){
        this.document = document;
    }
    public ProtocolModel(){
    }

    public static List<ProtocolModel> getProtocolModelsFromJson(String json) throws IOException {
        // Removing unwanted text
        int jsonStart = json.indexOf('{');
        int jsonEnd = json.lastIndexOf('}') + 1;
        String jsonSubstring = json.substring(jsonStart, jsonEnd);

        // Processing JSON
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, ProtocolModel>> typeReference = new TypeReference<Map<String, ProtocolModel>>() {};

        Map<String, ProtocolModel> map = objectMapper.readValue(jsonSubstring, typeReference);
        List<ProtocolModel> protocolModels = new ArrayList<>(map.values());

        return protocolModels;
    }

    public void convertBSON(){
        protocol = (String) this.document.get("name");
        total = (double) this.document.get("total");
        download = (double) this.document.get("download");
        upload = (double) this.document.get("upload");
    }

    // Getters & Setters
    @JsonProperty("protocol")
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
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