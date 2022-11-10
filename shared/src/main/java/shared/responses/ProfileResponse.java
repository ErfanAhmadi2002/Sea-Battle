package shared.responses;

import shared.models.User;

public class ProfileResponse extends Response{
    private User user;

    public ProfileResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
