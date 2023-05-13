package controller;

public class UserTraffic {
    private TrafficModel model;

    public UserTraffic() {
        this.model = new UserTraffic();
    }

    public void startTrafficCapture() {
        model.startThreads();
    }

    public void stopTrafficCapture() {
        model.stopThreads();
    }

    // Outros métodos do controlador...

    public void close() {
        model.close();
    }
}
