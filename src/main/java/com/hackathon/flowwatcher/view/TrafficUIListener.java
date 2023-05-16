package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.model.AppModel;
import com.hackathon.flowwatcher.model.HostModel;
import com.hackathon.flowwatcher.model.ProtocolModel;

import java.util.List;

// Interface for tracking and sending real-time data usage to the UI from Threads
public interface TrafficUIListener {
    void onAppTrafficUpdated(List<AppModel> appModelList);
    void onProtocolTrafficUpdated(List<ProtocolModel> protocolModelList);
    void onHostTrafficUpdated(List<HostModel> hostModelList);
}
