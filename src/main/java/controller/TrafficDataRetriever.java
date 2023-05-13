package controller;

import model.TrafficModel;
import java.time.LocalDateTime;

public class TrafficDataRetriever {
    TrafficModel model = new TrafficModel();

    public TrafficDataRetriever(TrafficModel model) {
        this.model = model;
    }

    // Function to retrieve data by source, by period
    public void retrieveDataByPeriod(LocalDateTime startTime, LocalDateTime endTime, int source) {
        // Data by App
        if(source == 0){

        }
        // Data by Protocol
        else if(source == 1){

        }
        // Data by Host
        else if(source == 2){

        }
        // All the data!
        else{

        }
    }
}
