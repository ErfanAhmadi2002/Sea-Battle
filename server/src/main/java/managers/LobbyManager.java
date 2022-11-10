package managers;

import game.Game;
import shared.models.Board;

import java.util.ArrayList;

public class LobbyManager {
    public ClientManager waiting = null;
    private final ArrayList<Game> gamesInProgress;

    public LobbyManager(ArrayList<Game> gamesInProgress) {
        this.gamesInProgress = gamesInProgress;
    }

    public ArrayList<Game> getGamesInProgress() {
        return gamesInProgress;
    }

    public void setWaiting(ClientManager waiting) {
        this.waiting = waiting;
    }

    public synchronized void newGameRequest (ClientManager clientManager){
        if (waiting == null && clientManager.game == null){
            waiting = clientManager;
        }
        else if (waiting != null && waiting != clientManager){
            Board board1 = waiting.getSelectedBoard();
            Board board2 = clientManager.getSelectedBoard();
            waiting.setPlayerNumber(1);
            clientManager.setPlayerNumber(2);
            Game game = new Game(board1 , board2 , gamesInProgress);
            gamesInProgress.add(game);
            waiting.setGame(game);
            clientManager.setGame(game);
            setWaiting(null);
        }
    }
}
