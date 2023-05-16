package com.hackathon.flowwatcher.controller;

import com.hackathon.flowwatcher.model.AppModel;
import com.hackathon.flowwatcher.model.HostModel;
import com.hackathon.flowwatcher.model.ProtocolModel;
import com.hackathon.flowwatcher.model.TrafficModel;
import com.hackathon.flowwatcher.view.TrafficUIListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// Class for retrieving real-time data traffic from sockets
public class TrafficController {
    private TrafficModel model;
    private TrafficUIListener uiListener;

    public TrafficController(TrafficUIListener uiListener) {
        this.model = new TrafficModel();
        this.uiListener = uiListener;
    }

    public void startTrafficCapture() {
        // Starting data traffic tracking
        try {
            List<SocketConnectionHandler> handlers = new ArrayList<>();
            handlers.add(handleSocketConnection(50000));
            handlers.add(handleSocketConnection(50001));
            handlers.add(handleSocketConnection(50002));
            for (SocketConnectionHandler handler : handlers) {
                handler.join();
            }
        } catch (IOException | InterruptedException e){
            System.out.println("Error: " + e);
        }
    }

    // Stop capturing packages
    public void stopTrafficCapture() {

    }

    public SocketConnectionHandler handleSocketConnection(int port) throws IOException {
        Socket socket = new Socket("localhost", port);
        System.out.println("Connected to port " + port + ".");
        SocketConnectionHandler handler = new SocketConnectionHandler(socket);
        return handler;
    }

    // Thread for package capture
    public class SocketConnectionHandler extends Thread {
        private Socket socket;

        public SocketConnectionHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    String data = new String(buffer, 0, bytesRead);
                    System.out.println("Received from port " + socket.getPort() + ": " + data);

                    // Selecting which source of data will deal with
                    // User
                    if(socket.getPort() == 50000){
                        // Saving to DB
                        model.updateAppTraffic(data);
                        // Convert JSON to Object
                        List<AppModel> appModelList = AppModel.getAppModelsFromJson(data);
                        // Send data to UI for real-time monitoring
                        uiListener.onAppTrafficUpdated(appModelList);
                    }
                    // Protocol
                    else if (socket.getPort() == 50001) {
                        // Saving to DB
                        model.updateProtocolTraffic(data);
                        // Convert JSON to Object
                        List<ProtocolModel> protocolModelList = ProtocolModel.getProtocolModelsFromJson(data);
                        // Send data to UI for real-time monitoring
                        uiListener.onProtocolTrafficUpdated(protocolModelList);
                    }
                    //Host
                    else if (socket.getPort() == 50002) {
                        // Saving to DB
                        model.updateHostTraffic(data);
                        // Convert JSON to objects
                        List<HostModel> hostModelList = HostModel.getHostModelsFromJson(data);
                        // Send data to UI for real-time monitoring
                        uiListener.onHostTrafficUpdated(hostModelList);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error in connection to port " + socket.getPort() + ": " + e);
            }
        }
    }
}