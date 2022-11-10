package view.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import listeners.GeneralListeners;
import shared.models.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Profile implements Initializable {
    private User user;
    private GeneralListeners generalListeners;

    @FXML
    private Text username;

    @FXML
    private Text totalScore;

    @FXML
    private Text loses;

    @FXML
    private Text wins;

    @FXML
    private Button back;

    @FXML
    void back(ActionEvent event) throws IOException {
        generalListeners.goBackToMainMenu();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generalListeners = new GeneralListeners();
    }

    public void startPage (){
        if (user!=null) {
            username.setText(user.getName());
            totalScore.setText(String.valueOf(user.getNumberOfWins() - user.getNumberOfLoses()));
            wins.setText(String.valueOf(user.getNumberOfWins()));
            loses.setText(String.valueOf(user.getNumberOfLoses()));
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
