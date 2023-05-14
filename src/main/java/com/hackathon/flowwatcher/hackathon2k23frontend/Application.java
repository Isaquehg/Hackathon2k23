package com.hackathon.flowwatcher.hackathon2k23frontend;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("pizza.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Controller controller = new Controller();
        fxmlLoader.setController(controller);
        stage.setTitle("FloWatcher");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}