package com.hackathon.flowwatcher.view;

// Class to provide real-time usage for UI
public class RealTimeTrafficUI implements TrafficUIListener {

    @Override
    public void onAppTrafficUpdated(String data) {
        // Update UI from App data usage thread
    }

    @Override
    public void onProtocolTrafficUpdated(String data) {
        // Update UI from Protocol data usage thread
    }

    @Override
    public void onHostTrafficUpdated(String data) {
        // Update UI from Host data usage thread
    }
}

