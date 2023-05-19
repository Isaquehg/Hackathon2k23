package com.hackathon.flowwatcher;

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
    }

    public static void main(String[] args) {
        launch();
    }
}