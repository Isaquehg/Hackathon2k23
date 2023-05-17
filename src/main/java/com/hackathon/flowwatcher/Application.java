package com.hackathon.flowwatcher;

import com.hackathon.flowwatcher.controller.TrafficController;
import com.hackathon.flowwatcher.view.ChartUIObserver;
import com.hackathon.flowwatcher.view.TrafficUIListener;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import com.hackathon.flowwatcher.view.PizzaController;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("pizza.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        PizzaController controller = new PizzaController();
        fxmlLoader.setController(controller);
        stage.setTitle("FloWatcher");
        stage.setScene(scene);
        stage.show();
        TrafficController trafficController = new TrafficController();
        trafficController.startTrafficCapture();
        ChartUIObserver chartUIObserver;
        Platform.runLater(() -> {

        });
    }

    public static void main(String[] args) {
        launch();
        while(true){

        }
    }
}