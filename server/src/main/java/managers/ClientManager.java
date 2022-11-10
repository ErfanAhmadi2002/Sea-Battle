package managers;

import database.BoardDataBase;
import database.UserDataBase;
import game.Game;
import network.SocketResponseSender;
import shared.Forms.LoginForm;
import shared.Forms.RegistrationForm;
import shared.events.*;
import shared.models.*;
import shared.responses.*;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ClientManager extends Thread implements EventVisitor {
    private final SocketResponseSender socketResponseSender;
    private final LobbyManager lobbyManager;
    private final HashMap<String, Integer> allUserNames;
    private final HashMap<User, Integer> onlineUsers;
    private final HashMap<Integer, Boolean> availableBoardsList;
    private Board selectedBoard;
    private int playerNumber;
    public Game game;
    private int authToken;
    private final SecureRandom secureRandom;

    public ClientManager(SocketResponseSender socketResponseSender, LobbyManager lobbyManager, HashMap<String, Integer> allUserNames, SecureRandom secureRandom, HashMap<User, Integer> onlineUsers) {
        this.lobbyManager = lobbyManager;
        this.secureRandom = secureRandom;
        this.allUserNames = allUserNames;
        this.socketResponseSender = socketResponseSender;
        this.onlineUsers = onlineUsers;
        this.availableBoardsList = new HashMap<>();
        availableBoardsList.put(1, true);
        availableBoardsList.put(2, true);
        availableBoardsList.put(3, true);
        this.game = null;
        this.selectedBoard = null;
    }

    public Board getSelectedBoard() {
        return selectedBoard;
    }

    public void setSelectedBoard(Board selectedBoard) {
        this.selectedBoard = selectedBoard;
    }

    public LobbyManager getLobbyManager() {
        return lobbyManager;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }


    @Override
    public void run() {
        try {
            while (true) {
                socketResponseSender.sendResponse(socketResponseSender.getEvent().visit(this));
            }
        } catch (Throwable disconnected) {
            socketResponseSender.closeResponseSender();
            disconnectUser();
        }
    }

    private void disconnectUser() {
        if (lobbyManager.waiting == this) {
            lobbyManager.setWaiting(null);
        }
        User user = getUserByAuthToken(authToken);
        onlineUsers.remove(user);
    }

    private User getUserByAuthToken(int authToken) {
        for (User user : onlineUsers.keySet()) {
            if (onlineUsers.get(user) == authToken) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Response checkLogin(LoginEvent loginEvent) {
        try {
            LoginForm loginForm = loginEvent.getLoginForm();
            User user = UserDataBase.Load(allUserNames.get(loginForm.getUsername()));
            if (user.getPassword().equals(loginForm.getPassword())) {
                int authToken;
                synchronized (secureRandom) {
                    authToken = secureRandom.nextInt(Integer.MAX_VALUE);
                }
                synchronized (onlineUsers) {
                    onlineUsers.put(user, authToken);
                }
                this.authToken = authToken;
                return new LoginResponse(user, authToken);
            } else {
                return null;
            }
        } catch (Throwable throwable) {
            return null;
        }
    }

    @Override
    public Response createAccount(RegistrationEvent registrationEvent) {
        RegistrationForm registrationForm = registrationEvent.getRegistrationForm();
        if (allUserNames.containsKey(registrationForm.getUsername())) {
            return null;
        } else {
            try {
                int lastId = UserDataBase.getLastId();
                User user = new User(registrationForm.getUsername(), registrationForm.getPassword(), lastId);
                synchronized (allUserNames) {
                    allUserNames.put(user.getName(), user.getId());
                }
                UserDataBase.update(user);
                UserDataBase.saveNewAllUserNames(allUserNames);
                int authToken = secureRandom.nextInt(1000);
                synchronized (onlineUsers) {
                    onlineUsers.put(user, authToken);
                }
                this.authToken = authToken;
                return new RegistrationResponse(user, authToken);
            } catch (IOException e) {
                return null;
            }
        }
    }

    @Override
    public Response watchProfile(int authToken) {
        if (this.authToken == authToken) {
            User user = getUserByAuthToken(authToken);
            return new ProfileResponse(user);
        }
        return null;
    }

    @Override
    public Response createNewBoard(CreateNewBoardEvent createNewBoardEvent) {
        if (this.authToken == createNewBoardEvent.getAuthToken()) {
            Random random = new Random();
            Board board = null;
            boolean found = false;
            boolean exist = false;
            for (Integer number : availableBoardsList.keySet()) {
                if (availableBoardsList.get(number)) {
                    exist = true;
                }
            }
            if (exist) {
                do {
                    int type = random.nextInt(3) + 1;
                    if (availableBoardsList.get(type)) {
                        try {
                            found = true;
                            board = BoardDataBase.Load(type);
                            availableBoardsList.put(type, false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } while (!found);
            }
            if (createNewBoardEvent.getTimer() == null) {
                return new NewRandomBoardResponse(board, new Timer(30));
            } else {
                Timer timer = createNewBoardEvent.getTimer();
                timer.increaseTime(10);
                return new NewRandomBoardResponse(board, timer);
            }
        } else {
            return null;
        }
    }

    @Override
    public Response boardIsReady(BoardIsReadyEvent boardIsReadyEvent) {
        if (this.authToken == (boardIsReadyEvent.getAuthToken())) {
            this.selectedBoard = boardIsReadyEvent.getBoard();
            lobbyManager.newGameRequest(this);
            if (game == null) {
                return null;
            } else {
                return new GameStateResponse(null, null, false, 0, 0);
            }
        }
        return null;
    }

    @Override
    public Response getGameState(GetGameStateEvent getGameStateEvent) {
        synchronized (game.syncTitle) {
            if (this.authToken == (getGameStateEvent.getAuthToken())) {
                int opponentNumber;
                if (playerNumber == 1) {
                    opponentNumber = 2;
                } else {
                    opponentNumber = 1;
                }
                Board myBoard = game.getGameState().getCopyOfBoard(playerNumber);
                Board opponentBoard = game.getGameState().getCopyOfBoard(opponentNumber);
                opponentBoard.setShips(new ArrayList<>());
                boolean isMyTurn = (playerNumber == game.getGameState().getTurn());
                int secondsLeft = game.getTimerThread().getTimer().getSecondsLeft();
                int gameCondition = 0;
                User user = getUserByAuthToken(authToken);
                if (game.getWinnerNumber() == 0) {
                    gameCondition = 0;
                } else if (game.getWinnerNumber() == playerNumber) {
                    this.game = null;
                    gameCondition = 1;
                    assert user != null;
                    int per = user.getNumberOfWins();
                    user.setNumberOfWins(per + 1);
                    UserDataBase.update(user);
                } else if (game.getWinnerNumber() != playerNumber) {
                    this.game = null;
                    gameCondition = 2;
                    assert user != null;
                    int per = user.getNumberOfLoses();
                    user.setNumberOfLoses(per + 1);
                    UserDataBase.update(user);
                }
                return new GameStateResponse(myBoard, opponentBoard, isMyTurn, secondsLeft, gameCondition);
            } else {
                return null;
            }
        }
    }

    @Override
    public Response getLiveGameState(GetLiveGameStateEvent getLiveGameStateEvent) {
        if (this.authToken == (getLiveGameStateEvent.getAuthToken())) {
            try {
                int streamNumber = getLiveGameStateEvent.getStreamNumber();
                synchronized (lobbyManager.getGamesInProgress()) {
                    Game game = lobbyManager.getGamesInProgress().get(streamNumber);
                    Board player1Board = game.getGameState().getCopyOfBoard(1);
                    player1Board.setShips(new ArrayList<>());
                    Board player2Board = game.getGameState().getCopyOfBoard(2);
                    player2Board.setShips(new ArrayList<>());
                    return new LiveGameStateResponse(player1Board, player2Board, game.getTimerThread().getTimer().getSecondsLeft());
                }
            } catch (Throwable throwable) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Response clickOnBoard(ClickOnBoardEvent clickOnBoardEvent) {
        if (this.authToken == (clickOnBoardEvent.getAuthToken())) {
            game.clickOnBoard(clickOnBoardEvent.getCell_X(), clickOnBoardEvent.getCell_Y(), playerNumber);
        }
        return null;
    }

    @Override
    public Response getGamesList(GetGamesListEvent getGamesListEvent) {
        if (this.authToken == (getGamesListEvent.getAuthToken())) {
            synchronized (lobbyManager.getGamesInProgress()) {
                ArrayList<GameData> allGamesData = new ArrayList<>();
                for (int i = 0; i < lobbyManager.getGamesInProgress().size(); i++) {
                    Game game = lobbyManager.getGamesInProgress().get(i);
                    int turnsPlayed = game.getGameState().getTurnsPlayed();
                    int healthyShips1 = game.getGameState().getHealthyShips(1);
                    int healthyShips2 = game.getGameState().getHealthyShips(2);
                    int bombs1 = game.getGameState().getBombsInTargets(1);
                    int bombs2 = game.getGameState().getBombsInTargets(2);
                    allGamesData.add(new GameData(i, turnsPlayed, healthyShips1, healthyShips2, bombs1, bombs2));
                }
                return new GameListResponse(allGamesData);
            }
        }
        return null;
    }

    @Override
    public Response getLiveScore(GetLiveScoreEvent getLiveScoreEvent) {
        if (this.authToken == getLiveScoreEvent.getAuthToken()){
            try {
                HashMap<UserCopy , Integer> allUserData = UserDataBase.getAllUsersCopy(onlineUsers);
                ArrayList<UserCopy> allUsers = new ArrayList<>();
                ArrayList<Integer> allScores = new ArrayList<>();
                for (UserCopy userCopy:allUserData.keySet()) {
                    allUsers.add(userCopy);
                    allScores.add(allUserData.get(userCopy));
                }
                return new LiveScoreResponse(allUsers , allScores);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
