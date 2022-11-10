package shared.events;

import shared.Forms.LoginForm;
import shared.responses.Response;

public class LoginEvent extends Event{
    private LoginForm loginForm;

    public LoginEvent(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.checkLogin(this);
    }

}
