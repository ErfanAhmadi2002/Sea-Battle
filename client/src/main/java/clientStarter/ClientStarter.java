package clientStarter;

import javafx.stage.Stage;
import network.SocketEventSender;
import view.ViewManager;

import java.io.IOException;
import java.net.Socket;

public class ClientStarter {
    public static SocketEventSender socketEventSender;
    public static ViewManager viewManager;
    public static int authToken;
    public static int myId;
    private final Stage primaryStage;

    public ClientStarter(Stage primaryStage , Socket socket) throws IOException {
        socketEventSender = new SocketEventSender(socket);
        authToken = -1;
        this.primaryStage = primaryStage;
        viewManager = new ViewManager(primaryStage);
    }

    public void start (){
        primaryStage.setTitle("SeaBattle");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(900);
        primaryStage.setResizable(false);
        primaryStage.show();
        viewManager.start();
    }
}
