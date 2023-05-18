package com.hackathon.flowwatcher.view;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class StartJson {
    public static void initializeJson() {
        try {
            List<SocketConnectionHandler> handlers = new ArrayList<>();
            // Para utilizar esse exemplo com o traffic_analyzer_v2.py, remova as linhas 13-14.
            handlers.add(handleSocketConnection(50000)); 
            handlers.add(handleSocketConnection(50001));
            handlers.add(handleSocketConnection(50002));
            for (SocketConnectionHandler handler : handlers) {
                handler.join();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e);
        }
    }

    public static SocketConnectionHandler handleSocketConnection(int port) throws IOException {
        Socket socket = new Socket("localhost", port);
        System.out.println("Connected to port " + port + ".");
        SocketConnectionHandler handler = new SocketConnectionHandler(socket);
        handler.start();
        return handler;
    }

    public static class SocketConnectionHandler extends Thread {
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
                }
            } catch (IOException e) {
                System.out.println("Error in connection to port " + socket.getPort() + ": " + e);
            }
        }
    }
}
