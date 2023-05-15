package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.model.AppModel;
import com.hackathon.flowwatcher.model.HostModel;
import com.hackathon.flowwatcher.model.ProtocolModel;

// Interface for tracking and sending real-time data usage to the UI from Threads
public interface TrafficUIListener {
    AppModel onAppTrafficUpdated(String data);
    ProtocolModel onProtocolTrafficUpdated(String data);
    HostModel onHostTrafficUpdated(String data);
}
