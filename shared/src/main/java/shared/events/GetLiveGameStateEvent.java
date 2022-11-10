package shared.events;

import shared.responses.Response;

public class GetLiveGameStateEvent extends Event{
    private final int authToken;
    private final int streamNumber;

    public GetLiveGameStateEvent(int authToken, int streamNumber) {
        this.authToken = authToken;
        this.streamNumber = streamNumber;
    }

    public int getAuthToken() {
        return authToken;
    }

    public int getStreamNumber() {
        return streamNumber;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getLiveGameState(this);
    }



}
