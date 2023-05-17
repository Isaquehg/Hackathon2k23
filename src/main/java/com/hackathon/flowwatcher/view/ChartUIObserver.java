package com.hackathon.flowwatcher.view;

import javafx.scene.chart.PieChart;

public interface ChartUIObserver {
    public void realChartValue(PieChart.Data data);
}
