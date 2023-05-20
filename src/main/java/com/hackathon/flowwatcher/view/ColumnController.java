package com.hackathon.flowwatcher.view;

import com.hackathon.flowwatcher.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ColumnController implements Initializable {
    @FXML
    StackedBarChart stakedBarChart;
    private Stage stage;
    @FXML
    private ChoiceBox<String> mode;
    @FXML
    private ChoiceBox<String> time;
    @FXML
    private ChoiceBox<String> dataType;
    @FXML
    private Label dataUsage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializa os botões
        mode.getItems().addAll("App", "Host", "Protocol");
        time.getItems().addAll("Realtime", "Last_24h", "Last_Week");
        dataType.getItems().addAll("Total", "Download", "Upload");

        // Set default value to checkBoxes and graph
        mode.setValue("Select type…");
        time.setValue("Select time…");
        dataType.setValue("Select data type…");
        dataUsage.setText("SET PARAMETERS!");
        // Grafico
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        stakedBarChart.getData().addAll(series1);

        // Set the methods to checkBoxes
        mode.setOnAction(this::updateChart);
        time.setOnAction(this::updateChart);
        dataType.setOnAction(this::updateChart);
    }

    public void changeGraphic(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("pizza.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        stage.setScene(scene);
        stage.show();
    }

    public void updateChart(javafx.event.ActionEvent event) {
        String selectedMode = mode.getValue();
        String selectedTime = time.getValue();
        String selectedDataType = dataType.getValue();

        // LAST 24H MODE
        if (selectedTime.equals("Last_24h")) {
            if (selectedMode.equals("App")) {
                dataUsage.setText("APP USAGE");
                if (selectedDataType.equals("Total")) {
                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                    series1.getData().add(new XYChart.Data<>("MSTeams", 30.78));
                    series1.getData().add(new XYChart.Data<>("Code", 25));
                    series1.getData().add(new XYChart.Data<>("Chrome", 14.12));
                    series1.getData().add(new XYChart.Data<>("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }

                if (selectedDataType.equals("Download")) {
                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("MSTeams", 10));
                            series1.getData().add(new XYChart.Data<>("Code", 13));
                            series1.getData().add(new XYChart.Data<>("Chrome", 8));
                            series1.getData().add(new XYChart.Data<>("WhatsApp", 0.55));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }

                if (selectedDataType.equals("Upload")) {
                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                    series1.getData().add(new XYChart.Data<>("MSTeams", 20)); 
                    series1.getData().add(new XYChart.Data<>("Code", 14));
                    series1.getData().add(new XYChart.Data<>("Chrome", 6));
                    series1.getData().add(new XYChart.Data<>("WhatsApp", 0.14));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }
                // Protocol
            } else if (mode.getValue().equals("Protocol")) {
                // Cleaning previous data
                stakedBarChart.getData().clear();
                
                dataUsage.setText("PROTOCOL USAGE");

                if (selectedDataType.equals("Total")) {
                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("https", 30.78));
                            series1.getData().add(new XYChart.Data<>("mdbs_daemon", 1.75));
                            series1.getData().add(new XYChart.Data<>("domain", 4.88));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }

                if (selectedDataType.equals("Download")) {
                    // Cleaning previous data
                    stakedBarChart.getData().clear();
                    stakedBarChart.setTitle("MB");

                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("https", 16.5));
                            series1.getData().add(new XYChart.Data<>("mdbs_daemon", 0.14));
                            series1.getData().add(new XYChart.Data<>("domain", 2.55));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }

                if (selectedDataType.equals("Upload")) {
                    // Cleaning previous data
                    stakedBarChart.getData().clear();
                    stakedBarChart.setTitle("MB");

                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("https", 14.5));
                            series1.getData().add(new XYChart.Data<>("mdbs_daemon", 0.8));
                            series1.getData().add(new XYChart.Data<>("domain", 2.45));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }
            } else if (mode.getValue().equals("Host")) {
                dataUsage.setText("HOST USAGE");
                if (selectedDataType.equals("Total")) {
                    // Cleaning previous data
                    stakedBarChart.getData().clear();
                    stakedBarChart.setTitle("MB");

                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("host", 687));
                            series1.getData().add(new XYChart.Data<>("host", 455));
                            series1.getData().add(new XYChart.Data<>("host", 210));
                            series1.getData().add(new XYChart.Data<>("host", 32));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }
                if (selectedDataType.equals("Download")) {
                    // Cleaning previous data
                    stakedBarChart.getData().clear();
                    stakedBarChart.setTitle("MB");
                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("host", 321));
                            series1.getData().add(new XYChart.Data<>("host", 200));
                            series1.getData().add(new XYChart.Data<>("host", 114));
                            series1.getData().add(new XYChart.Data<>("host", 12));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }
                if (selectedDataType.equals("Upload")) {
                    // Cleaning previous data
                    stakedBarChart.getData().clear();

                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("host", 222));
                            series1.getData().add(new XYChart.Data<>("host", 195));
                            series1.getData().add(new XYChart.Data<>("host", 57));
                            series1.getData().add(new XYChart.Data<>("host", 20));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
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
                    stakedBarChart.getData().clear();
                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("MSTeams", 30.78));
                            series1.getData().add(new XYChart.Data<>("Code", 25));
                            series1.getData().add(new XYChart.Data<>("Chrome", 14.12));
                            series1.getData().add(new XYChart.Data<>("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }
                if (selectedDataType.equals("Download")) {
                    // Cleaning previous data
                    stakedBarChart.getData().clear();

                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("MSTeams", 30.78));
                            series1.getData().add(new XYChart.Data<>("Code", 25));
                            series1.getData().add(new XYChart.Data<>("Chrome", 14.12));
                            series1.getData().add(new XYChart.Data<>("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }
                if (selectedDataType.equals("Upload")) {
                    // Cleaning previous data
                    stakedBarChart.getData().clear();
                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("MSTeams", 30.78));
                            series1.getData().add(new XYChart.Data<>("Code", 25));
                            series1.getData().add(new XYChart.Data<>("Chrome", 14.12));
                            series1.getData().add(new XYChart.Data<>("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }
            }
            // Protocol
            else if (mode.getValue().equals("Protocol")) {
                dataUsage.setText("PROTOCOL USAGE");
                if (selectedDataType.equals("Total")) {
                    // Cleaning previous data
                    stakedBarChart.getData().clear();
                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("https", 333));
                            series1.getData().add(new XYChart.Data<>("mdbs_daemon", 25));
                            series1.getData().add(new XYChart.Data<>("domain", 48));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                } else if (selectedDataType.equals("Download")) {
                    // Cleaning previous data
                    stakedBarChart.getData().clear();

                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("https", 100));
                            series1.getData().add(new XYChart.Data<>("mdbs_daemon", 12));
                            series1.getData().add(new XYChart.Data<>("domain", 24));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                } else if (selectedDataType.equals("Upload")) {
                    dataUsage.setText("HOST USAGE");
                    // Cleaning previous data
                    stakedBarChart.getData().clear();
                }
                XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                        series1.getData().add(new XYChart.Data<>("https", 221));
                        series1.getData().add(new XYChart.Data<>("mdbs_daemon", 16));
                        series1.getData().add(new XYChart.Data<>("domain", 22));
                // Create PieChart.Data objects using the appModelList
                stakedBarChart.getData().addAll(series1);
                // Host
            } else if (mode.getValue().equals("Host")) {
                if (selectedDataType.equals("Total")) {
                    dataUsage.setText("HOST USAGE");
                    // Cleaning previous data
                    stakedBarChart.getData().clear();

                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("MSTeams", 30.78));
                            series1.getData().add(new XYChart.Data<>("Code", 25));
                            series1.getData().add(new XYChart.Data<>("Chrome", 14.12));
                            series1.getData().add(new XYChart.Data<>("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                } else if (mode.getValue().equals("Host") && selectedDataType.equals("Download")) {
                    dataUsage.setText("HOST USAGE");
                    // Cleaning previous data
                    stakedBarChart.getData().clear();

                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("MSTeams", 30.78));
                            series1.getData().add(new XYChart.Data<>("Code", 25));
                            series1.getData().add(new XYChart.Data<>("Chrome", 14.12));
                            series1.getData().add(new XYChart.Data<>("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                } else if (selectedDataType.equals("Upload")) {
                    // Cleaning previous data
                    stakedBarChart.getData().clear();

                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("MSTeams", 30.78));
                            series1.getData().add(new XYChart.Data<>("Code", 25));
                            series1.getData().add(new XYChart.Data<>("Chrome", 14.12));
                            series1.getData().add(new XYChart.Data<>("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }
            }
        }
        // REAL-TIME MODE
        else if (selectedTime.equals("Realtime")) {
            if (mode.getValue().equals("App")) {
                dataUsage.setText("APP USAGE");
                if (dataType.getValue().equals("Total")) {
                    // Cleaning previous data
                    stakedBarChart.getData().clear();
                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("MSTeams", 238));
                            series1.getData().add(new XYChart.Data<>("Code", 88.5));
                            series1.getData().add(new XYChart.Data<>("Chrome", 555.14));
                            series1.getData().add(new XYChart.Data<>("WhatsApp", 15.5));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }
                if (dataType.getValue().equals("Download")) {
                    dataUsage.setText("APP USAGE");
                    // Cleaning previous data
                    stakedBarChart.getData().clear();

                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("MSTeams", 30.78));
                            series1.getData().add(new XYChart.Data<>("Code", 25));
                            series1.getData().add(new XYChart.Data<>("Chrome", 14.12));
                            series1.getData().add(new XYChart.Data<>("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }
                if (dataType.getValue().equals("Upload")) {
                    dataUsage.setText("APP USAGE");
                    // Cleaning previous data
                    stakedBarChart.getData().clear();

                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                            series1.getData().add(new XYChart.Data<>("MSTeams", 30.78));
                            series1.getData().add(new XYChart.Data<>("Code", 25));
                            series1.getData().add(new XYChart.Data<>("Chrome", 14.12));
                            series1.getData().add(new XYChart.Data<>("WhatsApp", 3.05));
                    // Create PieChart.Data objects using the appModelList
                    stakedBarChart.getData().addAll(series1);
                }
            }
        }
    }
}
