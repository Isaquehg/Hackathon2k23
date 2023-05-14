package com.hackathon.flowwatcher.controller;

import com.hackathon.flowwatcher.model.TrafficModel;
import com.hackathon.flowwatcher.view.TrafficUIListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
                        model.updateAppTraffic(data);
                        //send data to UI for real-time monitoring
                        uiListener.onAppTrafficUpdated(data);
                    }
                    // Protocol
                    else if (socket.getPort() == 50001) {
                        model.updateProtocolTraffic(data);
                        //send data to UI for real-time monitoring
                        uiListener.onProtocolTrafficUpdated(data);
                    }
                    //Host
                    else if (socket.getPort() == 50002) {
                        model.updateHostTraffic(data);
                        //send data to UI for real-time monitoring
                        uiListener.onHostTrafficUpdated(data);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error in connection to port " + socket.getPort() + ": " + e);
            }
        }
    }
}