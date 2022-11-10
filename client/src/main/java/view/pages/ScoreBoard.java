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
import listeners.ScoreBoardListener;
import shared.models.UserCopy;
import view.ViewManager;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ScoreBoard implements Initializable {
    private ScoreBoardListener scoreBoardListener;
    private boolean isOnPage;
    private GeneralListeners generalListeners;
    private VBox vBox;

    @FXML
    private Button back;

    @FXML
    private ScrollPane list;

    @FXML
    void back(ActionEvent event) throws InterruptedException {
        isOnPage = false;
        Thread.sleep(2);
        generalListeners.goBackToMainMenu();
    }

    public void updatePage (HashMap<UserCopy , Integer> allUsers){
        Platform.runLater(() -> {
            vBox.getChildren().clear();
            for (UserCopy userCopy:allUsers.keySet()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(ViewManager.class.getResource(ClientStarter.viewManager.getFxmlConfig().getProperty(String.class , "scorePane")));
                try {
                    AnchorPane pane = fxmlLoader.load();
                    ScorePane scorePane = fxmlLoader.getController();
                    scorePane.loadPane(userCopy , allUsers.get(userCopy));
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
        generalListeners = new GeneralListeners();
        scoreBoardListener = new ScoreBoardListener();
        vBox = new VBox();
        list.setContent(vBox);
        scoreBoardListener.startPage(this , isOnPage);
    }
}
