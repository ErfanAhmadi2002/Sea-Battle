package view.pages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import listeners.GamePageListeners;
import listeners.GeneralListeners;
import shared.models.Board;
import view.drawers.GameStateBoardDrawer;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class GamePage implements Initializable {
    private GamePageListeners gamePageListeners;
    private GameStateBoardDrawer myBoardDrawer;
    private GameStateBoardDrawer opponentBoardDrawer;
    private GeneralListeners generalListeners;

    @FXML
    private Canvas myBoard;

    @FXML
    private Text secondsLeft;

    @FXML
    private Canvas opponentBoard;

    @FXML
    private Text turnText;

    @FXML
    void click(MouseEvent event) {
        gamePageListeners.clickOnBoard(event);
    }

    public void update(Board myBoard , Board opponentBoard , int secondsLeft , boolean isMyTurn){
        myBoardDrawer.draw(myBoard);
        opponentBoardDrawer.draw(opponentBoard);
        this.secondsLeft.setText(String.valueOf(secondsLeft));
        if (isMyTurn)turnText.setText("Your Turn");
        else turnText.setText("Opponent's Turn");
    }

    public void showGameResult (boolean win){
        if (win){
            JOptionPane.showMessageDialog(null , "You won!" , "Result" , JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null , "You Lost" , "Result" , JOptionPane.INFORMATION_MESSAGE);
        }
        generalListeners.goBackToMainMenu();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gamePageListeners = new GamePageListeners();
        myBoardDrawer = new GameStateBoardDrawer(myBoard);
        opponentBoardDrawer = new GameStateBoardDrawer(opponentBoard);
        generalListeners = new GeneralListeners();
        gamePageListeners.getGameState(this);
    }
}
