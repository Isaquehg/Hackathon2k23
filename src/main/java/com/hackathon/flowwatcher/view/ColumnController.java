package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.Application;
import com.hackathon.flowwatcher.controller.TrafficController;
import com.hackathon.flowwatcher.model.AppModel;
import com.hackathon.flowwatcher.model.HostModel;
import com.hackathon.flowwatcher.model.ProtocolModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ColumnController extends GraphController{
    @FXML
    StackedBarChart stakedBarChart;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializa os botões
        super.AddModeTimeData();
        // Grafico
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("MB");
        series1.getData().add(new XYChart.Data<>("Telegram", 2000));
        series1.getData().add(new XYChart.Data<>("WhatsApp", 3000));
        series1.getData().add(new XYChart.Data<>("Teams", 2500));
        series1.getData().add(new XYChart.Data<>("Microsoft Edge", 4000));
        stakedBarChart.getData().addAll(series1);
    }

    public void changeGraphic(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("pizza.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        stage.setScene(scene);
        stage.show();
    }
}
