package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.model.AppModel;
import com.hackathon.flowwatcher.model.HostModel;
import com.hackathon.flowwatcher.model.ProtocolModel;

import java.util.List;

// Class to provide real-time usage for UI
public class RealTimeTrafficUI implements TrafficUIListener {
    @Override
    public void onAppTrafficUpdated(List<AppModel> appModelList) {
        // App List usage to UI
    }

    @Override
    public void onProtocolTrafficUpdated(List<ProtocolModel> protocolModelList) {

    }

    @Override
    public void onHostTrafficUpdated(List<HostModel> hostModelList) {

    }
}

