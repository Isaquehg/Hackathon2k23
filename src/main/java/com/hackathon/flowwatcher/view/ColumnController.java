package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.Application;
import com.hackathon.flowwatcher.model.AppModel;
import com.hackathon.flowwatcher.model.HostModel;
import com.hackathon.flowwatcher.model.ProtocolModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ColumnController {
    @FXML
    StackedBarChart stackedBarChart;
    private Stage stage;
    RealTimeTrafficUI realTimeTrafficUI = new RealTimeTrafficUI();
    AppModel appmodel;
    ProtocolModel protocolModel;
    HostModel hostModel;

    public void changeGraphic(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("pizza.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
