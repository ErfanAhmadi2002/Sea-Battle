package shared.responses;

import shared.models.Board;

public class LiveGameStateResponse extends Response{
    private final Board player1Board;
    private final Board player2Board;
    private final int secondsLeft;

    public LiveGameStateResponse(Board player1Board, Board player2Board, int secondsLeft) {
        this.player1Board = player1Board;
        this.player2Board = player2Board;
        this.secondsLeft = secondsLeft;
    }

    public Board getPlayer1Board() {
        return player1Board;
    }

    public Board getPlayer2Board() {
        return player2Board;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }
}
