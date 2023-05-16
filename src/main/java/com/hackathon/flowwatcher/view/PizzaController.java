package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PizzaController implements Initializable {

    @FXML
    private PieChart pieChart;
    @FXML
    private ChoiceBox<String> mode;
    @FXML
    private ChoiceBox<String> time;
    @FXML
    private Label dataUsage;
    @FXML
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddModeTimeData();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Apples", 2),
                        new PieChart.Data("Oranges", 25),
                        new PieChart.Data("Grapes", 50),
                        new PieChart.Data("Melons", 3));

        pieChart.getData().addAll(pieChartData);
    }


    public void changeGraphic(javafx.scene.input.MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("column.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        stage.setScene(scene);
        stage.show();
    }

    public void getParms(javafx.event.ActionEvent event) {
        if(mode.getValue().equals("App")){
            dataUsage.setText("APP USAGE");
        }
        if(mode.getValue().equals("Host")){
            dataUsage.setText("HOST USAGE");
        }
        if(mode.getValue().equals("Protocol")){
            dataUsage.setText("PROTOCOL USAGE");
        }
    }
    public void AddModeTimeData(){
        mode.getItems().addAll("App", "Host", "Protocol");
        time.getItems().addAll("Realtime", "Last_24h", "Last_Week");
        mode.setValue("App");
        time.setValue("Realtime");
        dataUsage.setText("APP USAGE");
        mode.setOnAction(this::getParms);
        time.setOnAction(this::getParms);
    }

}