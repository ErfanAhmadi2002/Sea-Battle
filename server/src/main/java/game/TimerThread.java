package game;


import shared.models.GameState;
import shared.models.Timer;

public class TimerThread extends Thread{
    private final Timer timer;
    private boolean gameIsFinished;
    private GameState gameState;

    public TimerThread(Timer timer , boolean gameIsFinished , GameState gameState) {
        this.timer = timer;
        this.gameIsFinished = gameIsFinished;
        this.gameState = gameState;
    }

    public boolean isGameIsFinished() {
        return gameIsFinished;
    }
    public void setGameIsFinished(boolean gameIsFinished) {
        this.gameIsFinished = gameIsFinished;
    }
    public GameState getGameState() {
        return gameState;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public void run(){
        while (!gameIsFinished) {
            timer.start();
            timer.reset();
            gameState.nextTurn();
        }
    }


}
