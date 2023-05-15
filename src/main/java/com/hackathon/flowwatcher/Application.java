package com.hackathon.flowwatcher;

import com.hackathon.flowwatcher.controller.TrafficController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.hackathon.flowwatcher.view.PizzaController;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("pizza.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        PizzaController controller = new PizzaController();
        fxmlLoader.setController(controller);
        stage.setTitle("FloWatcher");
        stage.setScene(scene);
        stage.show();
        // Ao iniciar a aplicação, lembrar de instanciar a classe startTrafficCapture() para inciar as 3 threads
        // Podemos dividir a aplicação entre mostrar em tempo real e por período específico
    }

    public static void main(String[] args) {
        launch();
    }
}