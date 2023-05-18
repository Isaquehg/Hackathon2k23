package com.hackathon.flowwatcher;

import com.hackathon.flowwatcher.controller.TrafficController;
import com.hackathon.flowwatcher.view.TrafficUIListener;
import com.hackathon.flowwatcher.view.UISync;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.hackathon.flowwatcher.view.PizzaController;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("pizza.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);

        // Adapting PizzaController to work with the interface
        PizzaController pizzaController = fxmlLoader.getController();

        fxmlLoader.setController(pizzaController);
        stage.setTitle("FloWatcher");
        stage.setScene(scene);
        stage.show();

        // Setting what to show first for thread synchronization
        UISync.APP = true;
        UISync.HOST = false;
        UISync.PROTOCOL = false;
        UISync.PIE = true;
        UISync.COLUMN = false;
        UISync.DOWNLOAD = false;
        UISync.UPLOAD = false;
        UISync.TOTAL = true;
        UISync.REALTIME = true;
        UISync.LAST_WEEK = false;
        UISync.LAST_24H = false;

        // Instantiating Interface like Observer with UI controller
        TrafficUIListener trafficUIListener = pizzaController;
        TrafficController trafficController = new TrafficController(trafficUIListener);
        trafficController.startTrafficCapture();
    }

    public static void main(String[] args) {
        launch();
    }
}