package shared.events;

import shared.responses.Response;

public class GetLiveScoreEvent extends Event{
    private final int authToken;

    public GetLiveScoreEvent(int authToken) {
        this.authToken = authToken;
    }

    public int getAuthToken() {
        return authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getLiveScore(this);
    }


}
