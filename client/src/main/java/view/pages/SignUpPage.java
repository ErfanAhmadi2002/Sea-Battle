package view.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import listeners.SignUpPageListeners;

import java.io.IOException;

public class SignUpPage {
    private final SignUpPageListeners signUpPageListeners = new SignUpPageListeners();

    @FXML
    private Button register;

    @FXML
    private Button login;

    @FXML
    void login(ActionEvent event) throws IOException {
        signUpPageListeners.goToLoginPage();
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        signUpPageListeners.goToRegistrationPage();
    }

}
