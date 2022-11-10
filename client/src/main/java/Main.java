import clientStarter.ClientStarter;
import config.Config;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.Socket;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Config config = Config.getConfig("network");
        Socket socket = new Socket(config.getProperty(String.class , "ip") , config.getProperty(Integer.class , "port"));
        ClientStarter clientStarter = new ClientStarter(primaryStage , socket);
        clientStarter.start();
    }
}
