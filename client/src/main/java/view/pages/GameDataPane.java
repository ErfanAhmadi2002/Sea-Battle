package view.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import listeners.StreamListPageListener;
import shared.models.GameData;

import java.net.URL;
import java.util.ResourceBundle;

public class GameDataPane implements Initializable {
    private StreamListPageListener streamListPageListener;
    private int gameNumber;

    @FXML
    private Text healthyShips1;

    @FXML
    private Text healthyShips2;

    @FXML
    private Text bombs1;

    @FXML
    private Text bombs2;

    @FXML
    private Text turns;

    @FXML
    private Button watchGame;

    @FXML
    void watchGame(ActionEvent event) {
        streamListPageListener.goToStreamPage(gameNumber);
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void loadPane (GameData gameData){
        this.gameNumber = gameData.getGameNumber();
        healthyShips1.setText(String.valueOf(gameData.getHealthyShips1()));
        healthyShips2.setText(String.valueOf(gameData.getHealthyShips2()));
        bombs1.setText(String.valueOf(gameData.getBombsInTargets1()));
        bombs2.setText(String.valueOf(gameData.getBombsInTargets2()));
        turns.setText(String.valueOf(gameData.getTurns()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        streamListPageListener = new StreamListPageListener();
    }


}
