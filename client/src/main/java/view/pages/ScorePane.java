package view.pages;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import shared.models.UserCopy;

public class ScorePane {

    @FXML
    private Text name;

    @FXML
    private Text score;

    @FXML
    private Text state;

    public void loadPane (UserCopy userCopy , int userScore){
        name.setText(userCopy.getName());
        if (userCopy.isOnline()){
            state.setText("online");
        }else {state.setText("offline");}
        score.setText(String.valueOf(userScore));
    }
}
