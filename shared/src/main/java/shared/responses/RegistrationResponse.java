package shared.responses;

import shared.models.User;

public class RegistrationResponse extends Response{
    private User user;
    private int authToken;

    public RegistrationResponse(User user, int authToken) {
        this.user = user;
        this.authToken = authToken;
    }

    public User getUser() {
        return user;
    }

    public int getAuthToken() {
        return authToken;
    }

    public void setAuthToken(int authToken) {
        this.authToken = authToken;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
