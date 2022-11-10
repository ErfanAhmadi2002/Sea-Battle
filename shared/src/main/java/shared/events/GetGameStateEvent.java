package shared.events;

import shared.responses.Response;

public class GetGameStateEvent extends Event{
    private final int authToken;

    public GetGameStateEvent(int authToken) {
        this.authToken = authToken;
    }

    public int getAuthToken() {
        return authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getGameState(this);
    }


}
