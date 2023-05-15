package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.model.AppModel;
import com.hackathon.flowwatcher.model.HostModel;
import com.hackathon.flowwatcher.model.ProtocolModel;

// Interface for tracking and sending real-time data usage to the UI from Threads
public interface TrafficUIListener {
    void onAppTrafficUpdated(AppModel appModel);
    void onProtocolTrafficUpdated(ProtocolModel protocolModel);
    void onHostTrafficUpdated(HostModel hostModel);
}
