package view.pages;

import clientStarter.ClientStarter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import listeners.GeneralListeners;
import listeners.StreamListPageListener;
import shared.models.GameData;
import view.ViewManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StreamListPage implements Initializable {
    private GeneralListeners generalListeners;
    private StreamListPageListener streamListPageListener;
    private boolean isOnPage;
    private VBox vBox;

    @FXML
    private Button back;

    @FXML
    private ScrollPane list;

    @FXML
    void back(ActionEvent event) {
        generalListeners.goBackToMainMenu();
    }

    public void updatePage (ArrayList<GameData> allGameData){
        Platform.runLater(() -> {
            vBox.getChildren().clear();
        for (GameData gameData:allGameData) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(ViewManager.class.getResource(ClientStarter.viewManager.getFxmlConfig().getProperty(String.class , "gameDataPane")));
                try {
                    AnchorPane pane = fxmlLoader.load();
                    GameDataPane gameDataPane = fxmlLoader.getController();
                    gameDataPane.loadPane(gameData);
                    vBox.getChildren().add(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isOnPage = true;
        streamListPageListener = new StreamListPageListener();
        generalListeners = new GeneralListeners();
        vBox = new VBox();
        list.setContent(vBox);
        streamListPageListener.getStreamList(this , isOnPage);
    }
}
