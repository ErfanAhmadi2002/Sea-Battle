package shared.events;

import shared.responses.Response;

public class WatchProfileEvent extends Event{
    private int authToken;

    public WatchProfileEvent(int authToken) {
        this.authToken = authToken;
    }

    public int getAuthToken() {
        return authToken;
    }

    public void setAuthToken(int authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) { return eventVisitor.watchProfile(authToken);}
}
