package shared.events;

import shared.models.Timer;
import shared.responses.Response;

public class CreateNewBoardEvent extends Event{
    private final int authToken;
    private final Timer timer;

    public CreateNewBoardEvent(int authToken , Timer timer) {
        this.authToken = authToken;
        this.timer = timer;
    }

    public int getAuthToken() {
        return authToken;
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.createNewBoard(this);
    }
}
