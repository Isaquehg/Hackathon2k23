package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.Application;
import com.hackathon.flowwatcher.model.AppModel;
import com.hackathon.flowwatcher.model.HostModel;
import com.hackathon.flowwatcher.model.ProtocolModel;
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
import java.util.List;
import java.util.ResourceBundle;

public class PizzaController extends GraphController implements TrafficUIListener{
    private static ObservableList<PieChart.Data> pieChartData;

    @FXML
    private PieChart pieChart;
    @FXML
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.AddModeTimeData();
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

    private void createPieChartAppDataRealTime() {
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Apples", 2),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Grapes", 50),
                new PieChart.Data("Melons", 3));

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(data.getName(), " Total: ", data.pieValueProperty())
                )
        );

        pieChart.setData(pieChartData);
    }

    // ------------------------------------REAL-TIME IMPLEMENTATION---------------------------------------
    // Sending App usage to graph
    @Override
    public void onAppTrafficUpdated(List<AppModel> appModelList) {
        // Cleaning previous data
        pieChartData.clear();

        // Create PieChart.Data objects using the appModelList
        for (AppModel appModel : appModelList) {
            PieChart.Data data = new PieChart.Data(appModel.getSource(), appModel.getTotal());
            pieChartData.add(data);
        }

        // Update the pie chart data
        pieChart.setData(pieChartData);
    }

    // Sending Data usage by Protocol to graph
    @Override
    public void onProtocolTrafficUpdated(List<ProtocolModel> protocolModelList) {
        // Cleaning previous data
        pieChartData.clear();

        // Create PieChart.Data objects using the appModelList
        for (ProtocolModel protocolModel : protocolModelList) {
            PieChart.Data data = new PieChart.Data(protocolModel.getProtocol(), protocolModel.getTotal());
            pieChartData.add(data);
        }

        // Update the pie chart data
        pieChart.setData(pieChartData);
    }

    // Sending Data usage by Host to Graph
    @Override
    public void onHostTrafficUpdated(List<HostModel> hostModelList) {
        // Cleaning previous data
        pieChartData.clear();

        // Create PieChart.Data objects using the appModelList
        for (HostModel hostModel : hostModelList) {
            PieChart.Data data = new PieChart.Data(hostModel.getSource(), hostModel.getTotal());
            pieChartData.add(data);
        }

        // Update the pie chart data
        pieChart.setData(pieChartData);
    }

    // ------------------------------------PAST DATA IMPLEMENTATION---------------------------------------


}