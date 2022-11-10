package shared.models;

public class GameState {
    private boolean gameFinished;
    private int turnsPlayed;
    private final Board board1,board2;
    private int healthyShips1,healthyShips2;
    private int bombsInTargets1,bombsInTargets2;
    private Timer timer;
    private int turn;

    public GameState(Board board1, Board board2, Timer timer, int turn) {
        this.gameFinished = false;
        this.board1 = board1;
        this.board2 = board2;
        this.timer = timer;
        this.turn = turn;
        this.turnsPlayed = 0;
        this.healthyShips1 = 10;
        this.healthyShips2 = 10;
        this.bombsInTargets1 = 0;
        this.bombsInTargets2 = 0;
    }

    public Board getBoard(int num) {
        if (num == 1) {
            return board1;
        }else {return board2;}
    }

    public int getBombsInTargets(int num){
        if (num == 1){
            return bombsInTargets1;
        }else {return bombsInTargets2;}
    }

    public int getHealthyShips(int num) {
        if (num == 1){
            return healthyShips1;
        }else {return healthyShips2;}
    }

    public Timer getTimer() {
        return timer;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurnsPlayed() {
        return turnsPlayed;
    }

    public void decreaseHealthyShips (int num){
        if (num == 1){
            healthyShips1--;
        }else {
            healthyShips2--;
        }
    }

    public void increaseBombsInTarget (int num){
        if (num == 1){
            bombsInTargets1++;
        }else {
            bombsInTargets2++;
        }
    }

    public Board getCopyOfBoard (int num){
        Board board = new Board();
        if (num == 1){
            board.setShips(board1.getShips());
            board.setCells(board1.getCells());
        }else {
            board.setShips(board2.getShips());
            board.setCells(board2.getCells());
        }
        return board;
    }

    public void nextTurn (){
        if (turn == 1){
            turn = 2;
        }
        else {
            turn = 1;
        }
        turnsPlayed++;
    }

}
