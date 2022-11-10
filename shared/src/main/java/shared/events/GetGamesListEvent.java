package shared.events;

import shared.responses.Response;

public class GetGamesListEvent extends Event{
    private final int authToken;

    public GetGamesListEvent(int authToken) {
        this.authToken = authToken;
    }

    public int getAuthToken() {
        return authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getGamesList(this);
    }


}
