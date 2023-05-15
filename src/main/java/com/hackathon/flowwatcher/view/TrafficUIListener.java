package com.hackathon.flowwatcher.view;

// Interface for tracking and sending real-time data usage to the UI from Threads
public interface TrafficUIListener {
    void onAppTrafficUpdated(String data);
    void onProtocolTrafficUpdated(String data);
    void onHostTrafficUpdated(String data);
}
