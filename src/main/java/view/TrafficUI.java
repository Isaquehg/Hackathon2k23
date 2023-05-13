package view;

public class TrafficUI implements TrafficUIListener {

    @Override
    public void onAppTrafficUpdated(String data) {
        // Atualizar a UI com os dados de tráfego de aplicativos
    }

    @Override
    public void onProtocolTrafficUpdated(String data) {
        // Atualizar a UI com os dados de tráfego de protocolos
    }

    @Override
    public void onHostTrafficUpdated(String data) {
        // Atualizar a UI com os dados de tráfego de hosts
    }
}

