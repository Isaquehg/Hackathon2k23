package com.hackathon.flowwatcher.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class GraphController implements Initializable {
    @FXML
    private ChoiceBox<String> mode;
    @FXML
    private ChoiceBox<String> time;
    @FXML
    private Label dataUsage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddModeTimeData();
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

}
