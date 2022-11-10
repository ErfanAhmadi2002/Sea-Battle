package controller;

import clientStarter.ClientStarter;
import network.SocketEventSender;
import shared.Forms.LoginForm;
import shared.Forms.RegistrationForm;
import shared.events.LoginEvent;
import shared.events.RegistrationEvent;
import shared.responses.LoginResponse;
import shared.responses.RegistrationResponse;
import view.errors.ExistingUserNameError;
import view.errors.WrongPasswordError;

import java.io.IOException;

public class SignUpController {

    public void login (LoginForm loginForm , WrongPasswordError wrongPasswordError) {
        SocketEventSender socketEventSender = ClientStarter.socketEventSender;
        LoginEvent loginEvent = new LoginEvent(loginForm);
        LoginResponse loginResponse = (LoginResponse) socketEventSender.request(loginEvent);
        if (loginResponse!= null){
            ClientStarter.authToken = loginResponse.getAuthToken();
            ClientStarter.myId = loginResponse.getUser().getId();
            ClientStarter.viewManager.LoadScene("mainMenuPage");
        }
        else {
            wrongPasswordError.errorOccurred();
        }
    }

    public void register (RegistrationForm registrationForm , ExistingUserNameError existingUserNameError) throws IOException {
        SocketEventSender socketEventSender = ClientStarter.socketEventSender;
        RegistrationEvent registrationEvent = new RegistrationEvent(registrationForm);
        RegistrationResponse registrationResponse = (RegistrationResponse) socketEventSender.request(registrationEvent);
        if (registrationResponse != null){
            ClientStarter.authToken = registrationResponse.getAuthToken();
            ClientStarter.myId = registrationResponse.getUser().getId();
            ClientStarter.viewManager.LoadScene("mainMenuPage");
        }
        else {
            existingUserNameError.errorOccurred();
        }
    }
}
