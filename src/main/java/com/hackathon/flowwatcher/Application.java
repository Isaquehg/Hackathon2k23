package com.hackathon.flowwatcher;

import com.hackathon.flowwatcher.controller.TrafficController;
import com.hackathon.flowwatcher.view.TrafficUIListener;
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
        PizzaController pizzaController = new PizzaController();
        fxmlLoader.setController(pizzaController);
        stage.setTitle("FloWatcher");
        stage.setScene(scene);
        stage.show();
        TrafficController trafficController = new TrafficController();
        trafficController.registerListener(new TrafficUIListener() {
            @Override
            public void onAppDataCaptured(List<DataItem> data) {
                Platform.runLater(() -> {
                    // Pass data to Pizza Chart
                    pizzaController.updateChartData(data);
                });
            }
        });
        trafficController.startTrafficCapture();
    }

    public static void main(String[] args) {
        launch();
    }
}