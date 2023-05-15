package com.hackathon.flowwatcher.controller;

import com.hackathon.flowwatcher.model.AppModel;
import com.hackathon.flowwatcher.model.HostModel;
import com.hackathon.flowwatcher.model.ProtocolModel;
import com.hackathon.flowwatcher.model.TrafficModel;
import java.time.LocalDateTime;
import java.util.List;

public class TrafficDataRetriever {

    // Function to retrieve data by period from Apps
    public List<AppModel> retrieveAppDataByPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        // Data by App
        TrafficModel trafficModel = new TrafficModel();
        List<AppModel> appModelList;
        appModelList = trafficModel.getAppDataByPeriod(startTime, endTime);

        return appModelList;
    }

    // Function to retrieve data by period from Protocols
    public List<ProtocolModel> retrieveProtocolDataByPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        TrafficModel trafficModel = new TrafficModel();
        List<ProtocolModel> protocolModelList;
        protocolModelList = trafficModel.getProtocolDataByPeriod(startTime, endTime);

        return protocolModelList;
    }

    // By Host
    public List<HostModel> retrieveHostDataByPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        TrafficModel trafficModel = new TrafficModel();
        List<HostModel> hostModelList;
        hostModelList = trafficModel.getHostDataByPeriod(startTime, endTime);

        return hostModelList;
    }
}
