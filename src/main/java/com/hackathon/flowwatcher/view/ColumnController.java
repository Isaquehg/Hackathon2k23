package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ColumnController extends PizzaController implements Initializable {
    @FXML
    StackedBarChart stackedBarChart;
    @FXML
    private ChoiceBox<String> mode;
    @FXML
    private ChoiceBox<String> time;
    private Stage stage;
    @FXML
    private Label dataUsage;

    public void changeGraphic(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("pizza.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataUsage.setText("APP USAGE");
        mode.getItems().addAll("App", "Host", "Protocol");
        time.getItems().addAll("Realtime", "Last_24h", "Last_Week");
        mode.setValue("App");
        time.setValue("Realtime");
        mode.setOnAction(this::getParms);
    }
}
