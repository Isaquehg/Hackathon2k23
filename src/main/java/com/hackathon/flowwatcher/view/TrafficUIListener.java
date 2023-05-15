package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.model.AppModel;
import com.hackathon.flowwatcher.model.HostModel;
import com.hackathon.flowwatcher.model.ProtocolModel;

// Interface for tracking and sending real-time data usage to the UI from Threads
public interface TrafficUIListener {
    void onAppTrafficUpdated(String data);
    void onProtocolTrafficUpdated(String data);
    void onHostTrafficUpdated(String data);
}
