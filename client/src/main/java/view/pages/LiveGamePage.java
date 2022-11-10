package view.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import listeners.GeneralListeners;
import listeners.StreamListPageListener;
import shared.models.Board;
import view.drawers.GameStateBoardDrawer;

import java.net.URL;
import java.util.ResourceBundle;

public class LiveGamePage implements Initializable {
    private GeneralListeners generalListeners;
    private StreamListPageListener streamListPageListener;
    private GameStateBoardDrawer player1BoardDrawer;
    private GameStateBoardDrawer player2BoardDrawer;
    private int streamNumber;

    public int getStreamNumber() {
        return streamNumber;
    }

    public void setStreamNumber(int streamNumber) {
        this.streamNumber = streamNumber;
    }

    @FXML
    private Canvas player1Board;

    @FXML
    private Canvas player2Board;

    @FXML
    private Button back;

    @FXML
    private Text secondsLeft;

    @FXML
    void back(ActionEvent event) {
        generalListeners.goBackToMainMenu();
    }

    public void startPage(){
        streamListPageListener.getLiveGame(this);
    }

    public void update(Board player1Board , Board player2Board , int secondsLeft){
        player1BoardDrawer.draw(player1Board);
        player2BoardDrawer.draw(player2Board);
        this.secondsLeft.setText(String.valueOf(secondsLeft));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generalListeners = new GeneralListeners();
        streamListPageListener = new StreamListPageListener();
        player1BoardDrawer = new GameStateBoardDrawer(player1Board);
        player2BoardDrawer = new GameStateBoardDrawer(player2Board);
    }
}
