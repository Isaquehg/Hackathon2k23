package com.hackathon.flowwatcher.controller;

import com.hackathon.flowwatcher.model.AppModel;
import com.hackathon.flowwatcher.model.HostModel;
import com.hackathon.flowwatcher.model.ProtocolModel;
import com.hackathon.flowwatcher.model.TrafficModel;
import java.time.LocalDateTime;
import java.util.List;

public class TrafficDataRetriever {
    TrafficModel model = new TrafficModel();

    public TrafficDataRetriever(TrafficModel model) {
        this.model = model;
    }

    // Function to retrieve data by source, by period
    public void retrieveDataByPeriod(LocalDateTime startTime, LocalDateTime endTime, int source) {
        // Data by App
        if(source == 0){
            TrafficModel trafficModel = new TrafficModel();
            List<AppModel> appModelList;
            appModelList = trafficModel.getAppDataByPeriod(startTime, endTime);
        }
        // Data by Protocol
        else if(source == 1){
            TrafficModel trafficModel = new TrafficModel();
            List<ProtocolModel> protocolModelList;
            protocolModelList = trafficModel.getProtocolDataByPeriod(startTime, endTime);
        }
        // Data by Host
        else if(source == 2){
            TrafficModel trafficModel = new TrafficModel();
            List<HostModel> hostModelList;
            hostModelList = trafficModel.getHostDataByPeriod(startTime, endTime);
        }
        // All the data!
        else{

        }
    }
}
