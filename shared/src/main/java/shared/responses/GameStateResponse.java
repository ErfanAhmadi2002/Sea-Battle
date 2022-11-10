package shared.responses;

import shared.models.Board;

public class GameStateResponse extends Response{
    private final Board myBoard;
    private final Board opponentBoard;
    private final boolean isMyTurn;
    private final int secondsLeft;
    private final int gameCondition; //0 -> running , 1 -> win , 2 -> lose

    public GameStateResponse(Board myBoard, Board opponentBoard, boolean isMyTurn, int secondsLeft, int gameCondition) {
        this.myBoard = myBoard;
        this.opponentBoard = opponentBoard;
        this.isMyTurn = isMyTurn;
        this.secondsLeft = secondsLeft;
        this.gameCondition = gameCondition;
    }

    public Board getMyBoard() {
        return myBoard;
    }

    public Board getOpponentBoard() {
        return opponentBoard;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

    public int getGameCondition() {
        return gameCondition;
    }
}
