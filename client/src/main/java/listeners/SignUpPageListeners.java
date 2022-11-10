package listeners;

import clientStarter.ClientStarter;
import controller.SignUpController;
import shared.Forms.LoginForm;
import shared.Forms.RegistrationForm;
import view.errors.ExistingUserNameError;
import view.errors.WrongPasswordError;

import java.io.IOException;

public class SignUpPageListeners {
    private final SignUpController signUpController = new SignUpController();

    public void goToRegistrationPage () throws IOException {
        ClientStarter.viewManager.LoadScene("registrationPage");
    }

    public void goToLoginPage () throws IOException {
        ClientStarter.viewManager.LoadScene("loginPage");
    }

    public void logInRequest (LoginForm loginForm , WrongPasswordError wrongPasswordError) throws IOException {
        signUpController.login(loginForm , wrongPasswordError);
    }

    public void registerRequest (RegistrationForm registrationForm , ExistingUserNameError existingUserNameError) throws IOException {
        signUpController.register(registrationForm , existingUserNameError);
    }

}
