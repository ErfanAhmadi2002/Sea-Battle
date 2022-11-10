package shared.responses;

import shared.models.User;

public class LoginResponse extends Response{
    private User user;
    private int authToken;

    public LoginResponse(User user, int authToken) {
        this.user = user;
        this.authToken = authToken;
    }

    public User getUser() {
        return user;
    }

    public int getAuthToken() {
        return authToken;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthToken(int authToken) {
        this.authToken = authToken;
    }
}
