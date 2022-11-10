package shared.events;

import shared.models.Board;
import shared.responses.Response;

public class BoardIsReadyEvent extends Event{
    private final int authToken;
    private final Board board;

    public BoardIsReadyEvent(int authToken, Board board) {
        this.authToken = authToken;
        this.board = board;
    }

    public int getAuthToken() {
        return authToken;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.boardIsReady(this);
    }
}
