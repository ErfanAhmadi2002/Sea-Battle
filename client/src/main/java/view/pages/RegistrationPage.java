package view.pages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import listeners.SignUpPageListeners;
import shared.Forms.RegistrationForm;
import view.errors.ExistingUserNameError;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationPage implements Initializable {
    private SignUpPageListeners signUpPageListeners;
    private ExistingUserNameError existingUserNameError;

    @FXML
    private TextArea username;

    @FXML
    private TextArea password;

    @FXML
    private Button submit;

    @FXML
    private Text error;

    @FXML
    private void submit() throws IOException {
        RegistrationForm registrationForm = new RegistrationForm(username.getText() , password.getText());
        signUpPageListeners.registerRequest(registrationForm , existingUserNameError);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.signUpPageListeners =  new SignUpPageListeners();
        this.existingUserNameError = new ExistingUserNameError(error);
    }
}
