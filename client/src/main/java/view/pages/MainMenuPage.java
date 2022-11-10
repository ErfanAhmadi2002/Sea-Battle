package view.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import listeners.MainMenuListeners;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuPage implements Initializable {
    private MainMenuListeners mainMenuListeners;


    @FXML
    private Button newGame;

    @FXML
    private Button liveGame;

    @FXML
    private Button liveScore;

    @FXML
    private Button profile;

    @FXML
    void liveGame(ActionEvent event) {
        mainMenuListeners.loadStreamPageRequest();
    }

    @FXML
    void liveScore(ActionEvent event) {
        mainMenuListeners.loadScoreBoardPage();
    }

    @FXML
    void newGame(ActionEvent event) {
        mainMenuListeners.newRandomBoardRequest();
    }

    @FXML
    void profile(ActionEvent event) throws IOException {
        mainMenuListeners.profileRequest();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenuListeners = new MainMenuListeners();
    }
}
