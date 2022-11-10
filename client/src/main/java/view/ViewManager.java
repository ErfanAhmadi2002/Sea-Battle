package view;

import config.Config;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public final class ViewManager {
    private final Stage mainStage;
    private final Config fxmlConfig;

    public ViewManager(Stage stage) {
        this.fxmlConfig = Config.getConfig("fxmlAddress");
        mainStage = stage;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void start(){
        LoadScene("signUpPage");
        mainStage.show();
    }

    public void LoadScene(String name) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String url = fxmlConfig.getProperty(String.class , name);
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource(url));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert root != null;
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
            }
        });
    }

    public Config getFxmlConfig() {
        return fxmlConfig;
    }
}
