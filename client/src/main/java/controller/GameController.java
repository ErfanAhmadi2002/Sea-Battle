package controller;

import clientStarter.ClientStarter;
import network.ResponseVisitor;
import network.SocketEventSender;
import shared.events.ClickOnBoardEvent;
import shared.events.GetGameStateEvent;
import shared.responses.GameStateResponse;
import view.pages.GamePage;

public class GameController {

    public void getGameState (GamePage gamePage) {
        SocketEventSender socketEventSender = ClientStarter.socketEventSender;
        GetGameStateEvent getGameStateEvent = new GetGameStateEvent(ClientStarter.authToken);
        Runnable runnable = () -> {
            boolean isGameFinished = false;
            while (!isGameFinished){
                GameStateResponse gameStateResponse = (GameStateResponse) socketEventSender.request(getGameStateEvent);
                gamePage.update(gameStateResponse.getMyBoard() , gameStateResponse.getOpponentBoard() , gameStateResponse.getSecondsLeft() , gameStateResponse.isMyTurn());
                if (gameStateResponse.getGameCondition() == 1){
                    isGameFinished = true;
                    gamePage.showGameResult(true);
                }
                if (gameStateResponse.getGameCondition() == 2){
                    isGameFinished = true;
                    gamePage.showGameResult(false);
                }
                    try {
                    Thread.sleep(300);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        };
        ResponseVisitor responseVisitor = new ResponseVisitor(runnable);
        responseVisitor.start();
    }

    public void clickOnBoard (double canvas_X , double canvas_Y){
        SocketEventSender socketEventSender = ClientStarter.socketEventSender;
        int cell_X = (int) (canvas_Y/50);
        int cell_Y = (int) (canvas_X/50);
        ClickOnBoardEvent clickOnBoardEvent = new ClickOnBoardEvent(ClientStarter.authToken , cell_X , cell_Y);
        socketEventSender.request(clickOnBoardEvent);
    }
}
