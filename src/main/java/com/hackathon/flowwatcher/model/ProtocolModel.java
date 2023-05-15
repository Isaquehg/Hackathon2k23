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
    public ProtocolModel(String total, String download, String upload){
        this.total = total;
        this.download = download;
        this.upload = upload;
    }

    public static Map<String, ProtocolModel> fromJson(String json) {
        Map<String, ProtocolModel> protocolMap = new HashMap<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);

            // Iterate over the fields in the JSON object
            for (JsonNode field : rootNode) {
                String protocol = field.fieldNames().next(); // Get the protocol name
                JsonNode protocolNode = field.get(protocol); // Get the protocol data

                // Extract the data from the protocol node
                String total = protocolNode.get("total").asText();
                String download = protocolNode.get("download").asText();
                String upload = protocolNode.get("upload").asText();

                ProtocolModel protocolModel = new ProtocolModel(total, download, upload);
                protocolMap.put(protocol, protocolModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return protocolMap;
    }

    // Convert from BSON
    public ProtocolModel convertBSON(){
        total = (String) this.document.get("total");
        download = (String) this.document.get("download");
        upload = (String) this.document.get("upload");
        ProtocolModel protocolModel = new ProtocolModel(total, download, upload);
        return protocolModel;
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
