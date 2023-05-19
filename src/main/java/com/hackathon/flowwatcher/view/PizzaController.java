package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.Application;
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
    private static ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

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

    // Initial setup
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add items to checkBoxes
        mode.getItems().addAll("App", "Host", "Protocol");
        time.getItems().addAll("Realtime", "Last_24h", "Last_Week");
        dataType.getItems().addAll("Total", "Download", "Upload");

        // Set default value to checkBoxes and graph
        mode.setValue("Select type…");
        time.setValue("Select time…");
        dataType.setValue("Select data type…");
        dataUsage.setText("SET PARAMETERS!");
        pieChart.getData().addAll(pieChartData);

        // Set the methods to checkBoxes
        mode.setOnAction(this::updateChart);
        time.setOnAction(this::updateChart);
        dataType.setOnAction(this::updateChart);
    }

    public void changeGraphic(javafx.scene.input.MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("column.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        stage.setScene(scene);
        stage.show();
    }

    // ------------------------------------PAST DATA IMPLEMENTATION---------------------------------------
    public void updateChart(javafx.event.ActionEvent event) {
       String selectedMode = mode.getValue();
        String selectedTime = time.getValue();
        String selectedDataType = dataType.getValue();

        // LAST 24H MODE
        if (selectedTime.equals("Last_24h")) {
            if (selectedMode.equals("App")) {
                dataUsage.setText("APP USAGE");
                if (selectedDataType.equals("Total")) {
                    // Cleaning previous data
                    pieChartData.clear();
                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("MSTeams", 30.78),
                            new PieChart.Data("Code", 25),
                            new PieChart.Data("Chrome", 14.12),
                            new PieChart.Data("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }

                if (selectedDataType.equals("Download")) {
                    // Cleaning previous data
                    pieChartData.clear();
                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("MSTeams", 10),
                            new PieChart.Data("Code", 13),
                            new PieChart.Data("Chrome", 8),
                            new PieChart.Data("WhatsApp", 0.55));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }

                if (selectedDataType.equals("Upload")) {
                    // Cleaning previous data
                    pieChartData.clear();
                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("MSTeams", 20),
                            new PieChart.Data("Code", 14),
                            new PieChart.Data("Chrome", 6),
                            new PieChart.Data("WhatsApp", 0.14));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }
                // Protocol
            } else if (mode.getValue().equals("Protocol")) {
                // Cleaning previous data
                pieChartData.clear();
                dataUsage.setText("PROTOCOL USAGE");

                if (selectedDataType.equals("Total")) {
                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("https", 30.78),
                            new PieChart.Data("mdbs_daemon", 1.75),
                            new PieChart.Data("domain", 4.88));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }

                if (selectedDataType.equals("Download")) {
                    // Cleaning previous data
                    pieChartData.clear();
                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("https", 16.5),
                            new PieChart.Data("mdbs_daemon", 0.14),
                            new PieChart.Data("domain", 2.55));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }

                if (selectedDataType.equals("Upload")) {
                    // Cleaning previous data
                    pieChartData.clear();

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("https", 14.5),
                            new PieChart.Data("mdbs_daemon", 0.8),
                            new PieChart.Data("domain", 2.45));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }
            } else if (mode.getValue().equals("Host")) {
                dataUsage.setText("HOST USAGE");
                if (selectedDataType.equals("Total")) {
                    // Cleaning previous data
                    pieChartData.clear();

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("host", 687),
                            new PieChart.Data("host", 455),
                            new PieChart.Data("host", 210),
                            new PieChart.Data("host", 32));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }
                if (selectedDataType.equals("Download")) {
                    // Cleaning previous data
                    pieChartData.clear();
                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("host", 321),
                            new PieChart.Data("host", 200),
                            new PieChart.Data("host", 114),
                            new PieChart.Data("host", 12));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }
                if (selectedDataType.equals("Upload")) {
                    // Cleaning previous data
                    pieChartData.clear();

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("host", 222),
                            new PieChart.Data("host", 195),
                            new PieChart.Data("host", 57),
                            new PieChart.Data("host", 20));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }
            }
        }
            // LAST WEEK MODE
            else if (selectedTime.equals("Last_Week")) {
            // App
            if (selectedMode.equals("App")) {
                dataUsage.setText("APP USAGE");
                if (selectedDataType.equals("Total")) {
                    // Cleaning previous data
                    pieChartData.clear();
                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("MSTeams", 30.78),
                            new PieChart.Data("Code", 25),
                            new PieChart.Data("Chrome", 14.12),
                            new PieChart.Data("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }
                if (selectedDataType.equals("Download")) {
                    // Cleaning previous data
                    pieChartData.clear();

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("MSTeams", 30.78),
                            new PieChart.Data("Code", 25),
                            new PieChart.Data("Chrome", 14.12),
                            new PieChart.Data("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }
                if (selectedDataType.equals("Upload")) {
                    // Cleaning previous data
                    pieChartData.clear();
                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("MSTeams", 30.78),
                            new PieChart.Data("Code", 25),
                            new PieChart.Data("Chrome", 14.12),
                            new PieChart.Data("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }
            }
            // Protocol
            else if (mode.getValue().equals("Protocol")) {
                dataUsage.setText("PROTOCOL USAGE");
                if (selectedDataType.equals("Total")) {
                    // Cleaning previous data
                    pieChartData.clear();
                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("https", 333),
                            new PieChart.Data("mdbs_daemon", 25),
                            new PieChart.Data("domain", 48));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                } else if (selectedDataType.equals("Download")) {
                    // Cleaning previous data
                    pieChartData.clear();

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("https", 100),
                            new PieChart.Data("mdbs_daemon", 12),
                            new PieChart.Data("domain", 24));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                } else if (selectedDataType.equals("Upload")) {
                    dataUsage.setText("HOST USAGE");
                    // Cleaning previous data
                    pieChartData.clear();
                }
                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("https", 221),
                        new PieChart.Data("mdbs_daemon", 16),
                        new PieChart.Data("domain", 22));
                // Create PieChart.Data objects using the appModelList
                pieChart.setData(pieChartData);
                // Host
            } else if (mode.getValue().equals("Host")) {
                if (selectedDataType.equals("Total")) {
                    dataUsage.setText("HOST USAGE");
                    // Cleaning previous data
                    pieChartData.clear();

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("MSTeams", 30.78),
                            new PieChart.Data("Code", 25),
                            new PieChart.Data("Chrome", 14.12),
                            new PieChart.Data("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                } else if (mode.getValue().equals("Host") && selectedDataType.equals("Download")) {
                    dataUsage.setText("HOST USAGE");
                    // Cleaning previous data
                    pieChartData.clear();

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("MSTeams", 30.78),
                            new PieChart.Data("Code", 25),
                            new PieChart.Data("Chrome", 14.12),
                            new PieChart.Data("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                } else if (selectedDataType.equals("Upload")) {
                    // Cleaning previous data
                    pieChartData.clear();

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("MSTeams", 30.78),
                            new PieChart.Data("Code", 25),
                            new PieChart.Data("Chrome", 14.12),
                            new PieChart.Data("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }
            }
        }
        // REAL-TIME MODE
        else if (selectedTime.equals("Realtime")) {
            if (mode.getValue().equals("App")) {
                dataUsage.setText("APP USAGE");
                if (dataType.getValue().equals("Total")) {
                    // Cleaning previous data
                    pieChartData.clear();
                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("MSTeams", 238),
                            new PieChart.Data("Code", 88.5),
                            new PieChart.Data("Chrome", 555.14),
                            new PieChart.Data("WhatsApp", 15.5));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }
                if (dataType.getValue().equals("Download")) {
                    dataUsage.setText("APP USAGE");
                    // Cleaning previous data
                    pieChartData.clear();

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("MSTeams", 30.78),
                            new PieChart.Data("Code", 25),
                            new PieChart.Data("Chrome", 14.12),
                            new PieChart.Data("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }
                if (dataType.getValue().equals("Upload")) {
                    dataUsage.setText("APP USAGE");
                    // Cleaning previous data
                    pieChartData.clear();

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("MSTeams", 30.78),
                            new PieChart.Data("Code", 25),
                            new PieChart.Data("Chrome", 14.12),
                            new PieChart.Data("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    pieChart.setData(pieChartData);
                }
            }
        }
    }
}