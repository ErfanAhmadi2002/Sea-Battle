package view.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import listeners.ChooseBoardListeners;
import view.drawers.ChooseBoardDrawer;
import view.drawers.TimerDrawer;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseBoardPage implements Initializable {
    private ChooseBoardListeners chooseBoardListeners;
    private ChooseBoardDrawer chooseBoardDrawer;
    private TimerDrawer timerDrawer;

    @FXML
    private Button anotherBoard;

    @FXML
    private Canvas board;

    @FXML
    private Button ready;

    @FXML
    private Text secondsLeft;

    public ChooseBoardDrawer getBoardDrawer() {
        return chooseBoardDrawer;
    }

    public TimerDrawer getTimerDrawer() {
        return timerDrawer;
    }

    public ChooseBoardListeners getChooseBoardListeners() {
        return chooseBoardListeners;
    }

    @FXML
    void anotherBoard(ActionEvent event) {
        chooseBoardListeners.anotherBoardButtonPressed(chooseBoardDrawer, timerDrawer);
     }

    @FXML
    void ready(ActionEvent event) {
        chooseBoardListeners.readyButtonPressed(chooseBoardDrawer.getBoard() , timerDrawer.getTimerThread().getTimer());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.chooseBoardDrawer = new ChooseBoardDrawer(board);
        this.timerDrawer = new TimerDrawer(secondsLeft);
        this.chooseBoardListeners = new ChooseBoardListeners();
    }


}
