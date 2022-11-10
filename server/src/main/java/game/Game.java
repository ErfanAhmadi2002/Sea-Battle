package game;

import shared.models.*;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public final Object syncTitle = new Object();
    private final TimerThread timerThread;
    private final ArrayList<Game> gamesInProgress;
    private int winnerNumber;
    private final GameState gameState;

    public Game(Board board1 , Board board2 , ArrayList<Game> gamesInProgress) {
        this.gamesInProgress = gamesInProgress;
        Timer timer = new Timer(35);
        Random random = new Random();
        int turn = random.nextInt(2)+1;
        this.gameState = new GameState(board1 , board2 , timer , turn);
        this.winnerNumber = 0;
        timerThread = new TimerThread(new Timer(25) , gameState.isGameFinished() , gameState);
        timerThread.start();
    }

    public GameState getGameState() {
        return gameState;
    }

    public TimerThread getTimerThread() {
        return timerThread;
    }

    public int getWinnerNumber() {
        return winnerNumber;
    }

    public void setWinnerNumber(int winnerNumber) {
        this.winnerNumber = winnerNumber;
    }

    public void clickOnBoard (int x , int y , int playerNumber){
        synchronized (syncTitle) {
            int opponentNumber = 0;
            if (playerNumber == 1){opponentNumber = 2;}
            else {opponentNumber = 1;}
            if (gameState.getTurn() == playerNumber) {
                Cell clicked = gameState.getBoard(opponentNumber).getCells()[x][y];
                if (!clicked.isExploded()){
                    clicked.setExploded(true);
                    timerThread.getTimer().reset();
                    if (!clicked.isHasShip()){
                        gameState.nextTurn();
                    }
                    else {
                        Ship ship = getShipSelected(clicked , opponentNumber);
                        gameState.increaseBombsInTarget(playerNumber);
                        assert ship != null;
                        handleExplosion(ship , opponentNumber);
                        int winnerNumber = getWinner();
                        if (winnerNumber != 0){
                            gameState.setGameFinished(true);
                            synchronized (gamesInProgress) {
                                gamesInProgress.remove(this);
                            }
                        }
                        this.winnerNumber = winnerNumber;
                    }
                }
            }
        }
    }

    private Ship getShipSelected (Cell cell , int opponentNumber){
        for (Ship ship:gameState.getBoard(opponentNumber).getShips()) {
            for (Cell shipCell:ship.getCells()) {
                if (cell.getX() == shipCell.getX() && cell.getY() == shipCell.getY()){
                    return ship;
                }
            }
        }
        return null;
    }

    private void handleExplosion (Ship ship , int opponentNumber){
        boolean isExploded = true;
        for (Cell shipCell:ship.getCells()) {
            Cell cell = gameState.getBoard(opponentNumber).getCells()[shipCell.getX()][shipCell.getY()];
            if (!cell.isExploded()){
                isExploded = false;
                break;
            }
        }
        if (isExploded){
            for (Cell shipCell:ship.getAdjacentCells()) {
                Cell cell = gameState.getBoard(opponentNumber).getCells()[shipCell.getX()][shipCell.getY()];
                cell.setExploded(true);
            }
            gameState.decreaseHealthyShips(opponentNumber);
        }
    }

    private int getWinner (){
        int winner = 1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = gameState.getBoard(2).getCells()[i][j];
                if (cell.isHasShip() && !cell.isExploded()){
                    winner = 0;
                }
            }
        }
        if (winner == 1){return winner;}
        winner = 2;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = gameState.getBoard(1).getCells()[i][j];
                if (cell.isHasShip() && !cell.isExploded()){
                    winner = 0;
                }
            }
        }
        return winner;
    }

}
