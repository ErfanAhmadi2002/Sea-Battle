package view.pages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import listeners.SignUpPageListeners;
import shared.Forms.LoginForm;
import view.errors.WrongPasswordError;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {
    private SignUpPageListeners signUpPageListeners;
    private WrongPasswordError wrongPasswordError;

    @FXML
    private TextArea username;

    @FXML
    private TextArea password;

    @FXML
    private Text error;

    @FXML
    private Button submit;

    @FXML
    private void submit() throws IOException {
        LoginForm loginForm = new LoginForm(username.getText() , password.getText());
        signUpPageListeners.logInRequest(loginForm , wrongPasswordError);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signUpPageListeners = new SignUpPageListeners();
        wrongPasswordError = new WrongPasswordError(error);
    }

    public WrongPasswordError getWrongPasswordError() {
        return wrongPasswordError;
    }
}
