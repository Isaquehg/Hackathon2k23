package controller;

import model.TrafficModel;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketHandler extends Thread {
    private Socket socket;
    private TrafficModel trafficModel;
    private boolean running;

    public SocketHandler(Socket socket, TrafficModel trafficModel) {
        this.socket = socket;
        this.trafficModel = trafficModel;
        this.running = true;
    }

    public void stopHandler() {
        running = false;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while (running && (bytesRead = inputStream.read(buffer)) != -1) {
                String data = new String(buffer, 0, bytesRead);
                // Update Model when it receives data
                trafficModel.updateAppTraffic(data);
            }
        } catch (IOException e) {
            System.out.println("Error in connection to port " + socket.getPort() + ": " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error closing socket: " + e);
            }
        }
    }
}
