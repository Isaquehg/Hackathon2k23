package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.Application;
import com.hackathon.flowwatcher.controller.TrafficDataRetriever;
import com.hackathon.flowwatcher.model.AppModel;
import com.hackathon.flowwatcher.model.HostModel;
import com.hackathon.flowwatcher.model.ProtocolModel;
import javafx.application.Platform;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class PizzaController implements TrafficUIListener, Initializable{
    private static ObservableList<PieChart.Data> pieChartData;

    @FXML
    private PieChart pieChart;
    @FXML
    private Stage stage;
    @FXML
    private ChoiceBox<String> mode;
    @FXML
    private ChoiceBox<String> time;
    @FXML
    private ChoiceBox<String> dataType;
    @FXML
    private Label dataUsage;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddModeTimeData();
        pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Apples", 2),
                        new PieChart.Data("Oranges", 25),
                        new PieChart.Data("Grapes", 50),
                        new PieChart.Data("Melons", 3));

        pieChart.getData().addAll(pieChartData);
    }

    public void AddModeTimeData(){
        // Add items to checkBoxes
        mode.getItems().addAll("App", "Host", "Protocol");
        time.getItems().addAll("Realtime", "Last_24h", "Last_Week");
        dataType.getItems().addAll("Total", "Download", "Upload");

        // Set default value to checkBoxes and dataUsage
        mode.setValue("App");
        time.setValue("Realtime");
        dataType.setValue("Total");
        dataUsage.setText("APP USAGE");

        // Set the methods to checkBoxes
        mode.setOnAction(this::updateChart);
        time.setOnAction(this::updateChart);
        dataType.setOnAction(this::updateChart);
    }

    public void changeGraphic(javafx.scene.input.MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("column.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        stage.setScene(scene);
        stage.show();
    }

    // ------------------------------------PAST DATA IMPLEMENTATION---------------------------------------
    public void updateChart(javafx.event.ActionEvent event) {
        TrafficDataRetriever trafficDataRetriever = new TrafficDataRetriever();
        List<AppModel> appModelList;
        List<ProtocolModel> protocolModelList;
        List<HostModel> hostModelList;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twentyFourHoursAgo = now.minusHours(24);
        LocalDateTime lastWeek = now.minusWeeks(1);

        String selectedMode = mode.getValue();
        String selectedTime = time.getValue();
        String selectedDataType = dataType.getValue();

        ChartUIObserver chartUIObserver;

        // If NON-Real-time mode selected -> 24H
        if(selectedTime.equals("Last_24h")) {
            System.out.println("Searching last 24 hours data");

            // App
            if (selectedTime.equals("App") && selectedDataType.equals("Total")) {
                appModelList = trafficDataRetriever.retrieveAppDataByPeriod(twentyFourHoursAgo, now);
                // Cleaning previous data
                pieChartData.clear();

                // Create PieChart.Data objects using the appModelList
                for (AppModel appModel : appModelList) {
                    PieChart.Data data = new PieChart.Data(appModel.getSource(), appModel.getTotal());
                    chartUIObserver.realChartValue(data);
                }

                // Update the pie chart data
                pieChart.setData(pieChartData);
            }
            else if (selectedTime.equals("App") && selectedDataType.equals("Download")) {
                appModelList = trafficDataRetriever.retrieveAppDataByPeriod(twentyFourHoursAgo, now);
                // Cleaning previous data
                pieChartData.clear();

                // Create PieChart.Data objects using the appModelList
                for (AppModel appModel : appModelList) {
                    PieChart.Data data = new PieChart.Data(appModel.getSource(), appModel.getDownload());
                    pieChartData.add(data);
                }

                // Update the pie chart data
                pieChart.setData(pieChartData);
            }
            else if (selectedTime.equals("App") && selectedDataType.equals("Upload")) {
                appModelList = trafficDataRetriever.retrieveAppDataByPeriod(twentyFourHoursAgo, now);
                // Cleaning previous data
                pieChartData.clear();

                // Create PieChart.Data objects using the appModelList
                for (AppModel appModel : appModelList) {
                    PieChart.Data data = new PieChart.Data(appModel.getSource(), appModel.getUpload());
                    pieChartData.add(data);
                }

                // Update the pie chart data
                pieChart.setData(pieChartData);
            }

            // Protocol
            else if (mode.getValue().equals("Protocol") && selectedDataType.equals("Total")) {
                protocolModelList = trafficDataRetriever.retrieveProtocolDataByPeriod(twentyFourHoursAgo, now);
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
            else if (mode.getValue().equals("Protocol") && selectedDataType.equals("Download")) {
                protocolModelList = trafficDataRetriever.retrieveProtocolDataByPeriod(twentyFourHoursAgo, now);
                // Cleaning previous data
                pieChartData.clear();

                // Create PieChart.Data objects using the appModelList
                for (ProtocolModel protocolModel : protocolModelList) {
                    PieChart.Data data = new PieChart.Data(protocolModel.getProtocol(), protocolModel.getDownload());
                    pieChartData.add(data);
                }

                // Update the pie chart data
                pieChart.setData(pieChartData);
            }
            else if (mode.getValue().equals("Protocol") && selectedDataType.equals("Upload")) {
                protocolModelList = trafficDataRetriever.retrieveProtocolDataByPeriod(twentyFourHoursAgo, now);
                // Cleaning previous data
                pieChartData.clear();

                // Create PieChart.Data objects using the appModelList
                for (ProtocolModel protocolModel : protocolModelList) {
                    PieChart.Data data = new PieChart.Data(protocolModel.getProtocol(), protocolModel.getUpload());
                    pieChartData.add(data);
                }

                // Update the pie chart data
                pieChart.setData(pieChartData);
            }

            // Host
            else if (mode.getValue().equals("Host") && selectedDataType.equals("Total")) {
                hostModelList = trafficDataRetriever.retrieveHostDataByPeriod(twentyFourHoursAgo, now);
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
            else if (mode.getValue().equals("Host") && selectedDataType.equals("Download")) {
                hostModelList = trafficDataRetriever.retrieveHostDataByPeriod(twentyFourHoursAgo, now);
                // Cleaning previous data
                pieChartData.clear();

                // Create PieChart.Data objects using the appModelList
                for (HostModel hostModel : hostModelList) {
                    PieChart.Data data = new PieChart.Data(hostModel.getSource(), hostModel.getDownload());
                    pieChartData.add(data);
                }

                // Update the pie chart data
                pieChart.setData(pieChartData);
            }
            else if (mode.getValue().equals("Host") && selectedDataType.equals("Upload")) {
                hostModelList = trafficDataRetriever.retrieveHostDataByPeriod(twentyFourHoursAgo, now);
                // Cleaning previous data
                pieChartData.clear();

                // Create PieChart.Data objects using the appModelList
                for (HostModel hostModel : hostModelList) {
                    PieChart.Data data = new PieChart.Data(hostModel.getSource(), hostModel.getUpload());
                    pieChartData.add(data);
                }

                // Update the pie chart data
                pieChart.setData(pieChartData);
            }
        }
        // If NON-Real-time mode selected -> Last Week
        else if(selectedTime.equals("Last_Week")){
            System.out.println("Searching last week data...");
            // App
            if (selectedTime.equals("App") && selectedDataType.equals("Total")) {
                appModelList = trafficDataRetriever.retrieveAppDataByPeriod(lastWeek, now);
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
            else if (selectedTime.equals("App") && selectedDataType.equals("Download")) {
                appModelList = trafficDataRetriever.retrieveAppDataByPeriod(lastWeek, now);
                // Cleaning previous data
                pieChartData.clear();

                // Create PieChart.Data objects using the appModelList
                for (AppModel appModel : appModelList) {
                    PieChart.Data data = new PieChart.Data(appModel.getSource(), appModel.getDownload());
                    pieChartData.add(data);
                }

                // Update the pie chart data
                pieChart.setData(pieChartData);
            }
            else if (selectedTime.equals("App") && selectedDataType.equals("Upload")) {
                appModelList = trafficDataRetriever.retrieveAppDataByPeriod(lastWeek, now);
                // Cleaning previous data
                pieChartData.clear();

                // Create PieChart.Data objects using the appModelList
                for (AppModel appModel : appModelList) {
                    PieChart.Data data = new PieChart.Data(appModel.getSource(), appModel.getUpload());
                    pieChartData.add(data);
                }

                // Update the pie chart data
                pieChart.setData(pieChartData);
            }

            // Protocol
            else if (mode.getValue().equals("Protocol") && selectedDataType.equals("Total")) {
                protocolModelList = trafficDataRetriever.retrieveProtocolDataByPeriod(lastWeek, now);
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
            else if (mode.getValue().equals("Protocol") && selectedDataType.equals("Download")) {
                protocolModelList = trafficDataRetriever.retrieveProtocolDataByPeriod(lastWeek, now);
                // Cleaning previous data
                pieChartData.clear();

                // Create PieChart.Data objects using the appModelList
                for (ProtocolModel protocolModel : protocolModelList) {
                    PieChart.Data data = new PieChart.Data(protocolModel.getProtocol(), protocolModel.getDownload());
                    pieChartData.add(data);
                }

                // Update the pie chart data
                pieChart.setData(pieChartData);
            }
            else if (mode.getValue().equals("Protocol") && selectedDataType.equals("Upload")) {
                protocolModelList = trafficDataRetriever.retrieveProtocolDataByPeriod(lastWeek, now);
                // Cleaning previous data
                pieChartData.clear();

                // Create PieChart.Data objects using the appModelList
                for (ProtocolModel protocolModel : protocolModelList) {
                    PieChart.Data data = new PieChart.Data(protocolModel.getProtocol(), protocolModel.getUpload());
                    pieChartData.add(data);
                }

                // Update the pie chart data
                pieChart.setData(pieChartData);
            }

            // Host
            else if (mode.getValue().equals("Host") && selectedDataType.equals("Total")) {
                hostModelList = trafficDataRetriever.retrieveHostDataByPeriod(lastWeek, now);
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
            else if (mode.getValue().equals("Host") && selectedDataType.equals("Download")) {
                hostModelList = trafficDataRetriever.retrieveHostDataByPeriod(lastWeek, now);
                // Cleaning previous data
                pieChartData.clear();

                // Create PieChart.Data objects using the appModelList
                for (HostModel hostModel : hostModelList) {
                    PieChart.Data data = new PieChart.Data(hostModel.getSource(), hostModel.getDownload());
                    pieChartData.add(data);
                }

                // Update the pie chart data
                pieChart.setData(pieChartData);
            }
            else if (mode.getValue().equals("Host") && selectedDataType.equals("Upload")) {
                hostModelList = trafficDataRetriever.retrieveHostDataByPeriod(lastWeek, now);
                // Cleaning previous data
                pieChartData.clear();

                // Create PieChart.Data objects using the appModelList
                for (HostModel hostModel : hostModelList) {
                    PieChart.Data data = new PieChart.Data(hostModel.getSource(), hostModel.getUpload());
                    pieChartData.add(data);
                }

                // Update the pie chart data
                pieChart.setData(pieChartData);
            }
        }
    }

    // ------------------------------------REAL-TIME IMPLEMENTATION---------------------------------------
    // Sending App usage to graph
    @Override
    public void onAppTrafficUpdated(List<AppModel> appModelList) {
        if (time.getValue().equals("Realtime") && mode.getValue().equals("App")) {
            if (dataType.getValue().equals("Total")) {
                updateChartBasedOnAppAndTotalRealtime(appModelList);
            } else if (dataType.getValue().equals("Download")) {
                updateChartBasedOnAppAndDownloadRealtime(appModelList);
            } else if (dataType.getValue().equals("Upload")) {
                updateChartBasedOnAppAndUploadRealtime(appModelList);
            }
        }
    }

    @Override
    public void onProtocolTrafficUpdated(List<ProtocolModel> protocolModelList) {
        if (time.getValue().equals("Realtime") && mode.getValue().equals("Protocol")) {
            if (dataType.getValue().equals("Total")) {
                updateChartBasedOnProtocolAndTotalRealtime(protocolModelList);
            } else if (dataType.getValue().equals("Download")) {
                updateChartBasedOnProtocolAndDownloadRealtime(protocolModelList);
            } else if (dataType.getValue().equals("Upload")) {
                updateChartBasedOnProtocolAndUploadRealtime(protocolModelList);
            }
        }
    }

    @Override
    public void onHostTrafficUpdated(List<HostModel> hostModelList) {
        if (time.getValue().equals("Realtime") && mode.getValue().equals("Host")) {
            if (dataType.getValue().equals("Total")) {
                updateChartBasedOnHostAndTotalRealtime(hostModelList);
            } else if (dataType.getValue().equals("Download")) {
                updateChartBasedOnHostAndDownloadRealtime(hostModelList);
            } else if (dataType.getValue().equals("Upload")) {
                updateChartBasedOnHostAndUploadRealtime(hostModelList);
            }
        }
    }

    // -------------------------------------UPDATING REAL-TIME-------------------------------------------
    // APP
    private void updateChartBasedOnAppAndTotalRealtime(List<AppModel> appModelList) {
        System.out.println("Updating App Realtime Chart...");

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

    private void updateChartBasedOnAppAndDownloadRealtime(List<AppModel> appModelList) {
        System.out.println("Updating Download App Realtime Chart...");
        // Implement the logic to update the chart based on App and Download in Realtime
        // Cleaning previous data
        pieChartData.clear();

        // Create PieChart.Data objects using the appModelList
        for (AppModel appModel : appModelList) {
            PieChart.Data data = new PieChart.Data(appModel.getSource(), appModel.getDownload());
            pieChartData.add(data);
        }

        // Update the pie chart data
        pieChart.setData(pieChartData);
    }

    private void updateChartBasedOnAppAndUploadRealtime(List<AppModel> appModelList) {
        System.out.println("Updating Upload App Realtime Chart...");
        // Implement the logic to update the chart based on App and Upload in Realtime
        // Cleaning previous data
        pieChartData.clear();

        // Create PieChart.Data objects using the appModelList
        for (AppModel appModel : appModelList) {
            PieChart.Data data = new PieChart.Data(appModel.getSource(), appModel.getUpload());
            pieChartData.add(data);
        }

        // Update the pie chart data
        pieChart.setData(pieChartData);
    }

    // PROTOCOL
    private void updateChartBasedOnProtocolAndTotalRealtime(List<ProtocolModel> protocolModelList) {
        System.out.println("Updating Protocol Realtime Chart...");
        // Implement the logic to update the chart based on Protocol and Total in Realtime
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

    private void updateChartBasedOnProtocolAndDownloadRealtime(List<ProtocolModel> protocolModelList) {
        System.out.println("Updating Download Protocol Realtime Chart...");
        // Implement the logic to update the chart based on Protocol and Download in Realtime
        // Cleaning previous data
        pieChartData.clear();

        // Create PieChart.Data objects using the appModelList
        for (ProtocolModel protocolModel : protocolModelList) {
            PieChart.Data data = new PieChart.Data(protocolModel.getProtocol(), protocolModel.getDownload());
            pieChartData.add(data);
        }

        // Update the pie chart data
        pieChart.setData(pieChartData);
    }

    private void updateChartBasedOnProtocolAndUploadRealtime(List<ProtocolModel> protocolModelList) {
        System.out.println("Updating Upload Protocol Realtime Chart...");
        // Implement the logic to update the chart based on Protocol and Upload in Realtime
        // Cleaning previous data
        pieChartData.clear();

        // Create PieChart.Data objects using the appModelList
        for (ProtocolModel protocolModel : protocolModelList) {
            PieChart.Data data = new PieChart.Data(protocolModel.getProtocol(), protocolModel.getUpload());
            pieChartData.add(data);
        }

        // Update the pie chart data
        pieChart.setData(pieChartData);
    }

    // HOST
    private void updateChartBasedOnHostAndTotalRealtime(List<HostModel> hostModelList) {
        System.out.println("Updating Host Realtime Chart...");
        // Implement the logic to update the chart based on Host and Total in Realtime
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
    private void updateChartBasedOnHostAndUploadRealtime(List<HostModel> hostModelList) {
        System.out.println("Updating Upload Host Realtime Chart...");
        // Implement the logic to update the chart based on Host and Upload in Realtime
        // Cleaning previous data
        pieChartData.clear();

        // Create PieChart.Data objects using the appModelList
        for (HostModel hostModel : hostModelList) {
            PieChart.Data data = new PieChart.Data(hostModel.getSource(), hostModel.getUpload());
            pieChartData.add(data);
        }

        // Update the pie chart data
        pieChart.setData(pieChartData);
    }
    private void updateChartBasedOnHostAndDownloadRealtime(List<HostModel> hostModelList) {
        System.out.println("Updating Download Host Realtime Chart...");
        // Implement the logic to update the chart based on Host and download in Realtime
        // Cleaning previous data
        pieChartData.clear();

        // Create PieChart.Data objects using the appModelList
        for (HostModel hostModel : hostModelList) {
            PieChart.Data data = new PieChart.Data(hostModel.getSource(), hostModel.getDownload());
            pieChartData.add(data);
        }

        // Update the pie chart data
        pieChart.setData(pieChartData);
    }
}