package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.model.AppModel;
import com.hackathon.flowwatcher.model.HostModel;
import com.hackathon.flowwatcher.model.ProtocolModel;

// Class to provide real-time usage for UI
public class RealTimeTrafficUI implements TrafficUIListener {

    @Override
    public void onAppTrafficUpdated(String data) {
        // Update UI from App data usage thread
        AppModel appModel = new AppModel(data);
        ColumnController columnController = new ColumnController();
        columnController.appmodel = appModel;
    }

    @Override
    public void onProtocolTrafficUpdated(String data) {
        // Update UI from Protocol data usage thread
        ProtocolModel protocolModel = new ProtocolModel(data);
    }

    @Override
    public void onHostTrafficUpdated(String data) {
        HostModel hostModel = new HostModel(data);
    }
}

