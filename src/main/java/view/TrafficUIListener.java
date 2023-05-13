package view;

// Interface for tracking and sending real-time data usage to the UI
public interface TrafficUIListener {
    void onAppTrafficUpdated(String data);
    void onProtocolTrafficUpdated(String data);
    void onHostTrafficUpdated(String data);
}
