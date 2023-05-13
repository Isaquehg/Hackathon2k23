package controller;

import model.TrafficModel;

public class TrafficController {
    private TrafficModel trafficModel;
    private SocketHandler socketHandler;

    public TrafficController() {
        this.trafficModel = new TrafficModel();
        this.socketHandler = new SocketHandler(trafficModel);
    }

    public void start() {
        // Iniciar a thread para o socket handler
        socketHandler.start();
    }

    public void stop() {
        // Parar a thread do socket handler
        socketHandler.stopHandler();
    }
}