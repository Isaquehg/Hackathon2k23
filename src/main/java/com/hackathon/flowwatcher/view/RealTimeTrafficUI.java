package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.model.AppModel;
import com.hackathon.flowwatcher.model.HostModel;
import com.hackathon.flowwatcher.model.ProtocolModel;

// Class to provide real-time usage for UI
public class RealTimeTrafficUI implements TrafficUIListener {

    @Override
    public void onAppTrafficUpdated(AppModel appModel) {
        // Update UI from App data usage thread
    }

    @Override
    public void onProtocolTrafficUpdated(ProtocolModel protocolModel) {
        // Update UI from Protocol data usage thread
    }

    @Override
    public void onHostTrafficUpdated(HostModel hostModel) {
        // Update UI from Host data usage thread
    }
}

