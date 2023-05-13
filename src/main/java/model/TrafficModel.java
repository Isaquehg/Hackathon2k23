package model;

public class TrafficModel {
    private Thread appTrafficThread;
    private Thread hostTrafficThread;
    private Thread protocolTrafficThread;

    public TrafficModel() {
        // Inicializar as threads
        appTrafficThread = new Thread(() -> {
            // Lógica para capturar o tráfego de aplicativos
            // Atualizar os dados de tráfego relacionados aos aplicativos
        });

        hostTrafficThread = new Thread(() -> {
            // Lógica para capturar o tráfego por host
            // Atualizar os dados de tráfego relacionados aos hosts
        });

        protocolTrafficThread = new Thread(() -> {
            // Lógica para capturar o tráfego por protocolo
            // Atualizar os dados de tráfego relacionados aos protocolos
        });
    }

    public void startThreads() {
        // Iniciar as threads
        appTrafficThread.start();
        hostTrafficThread.start();
        protocolTrafficThread.start();
    }

    public void stopThreads() {
        // Parar as threads
        appTrafficThread.interrupt();
        hostTrafficThread.interrupt();
        protocolTrafficThread.interrupt();
    }
}
