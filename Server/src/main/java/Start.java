import server.ServerConnection;

public class Start {
    public static void main(String[] args) {
        ServerConnection serverConnection = new ServerConnection();
        serverConnection.startServer();
        serverConnection.connectNewClientInToServer();
        serverConnection.closeAll();
    }
}
