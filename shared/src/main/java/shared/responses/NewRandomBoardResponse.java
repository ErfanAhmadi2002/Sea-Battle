package shared.responses;

import shared.models.Board;
import shared.models.Timer;

public class NewRandomBoardResponse extends Response{
    private final Board board;
    private final Timer timer;

    public NewRandomBoardResponse(Board board, Timer timer) {
        this.board = board;
        this.timer = timer;
    }

    public Board getBoard() {
        return board;
    }

    public Timer getTimer() {
        return timer;
    }
}
