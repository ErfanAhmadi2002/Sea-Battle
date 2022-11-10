package controller;

import clientStarter.ClientStarter;
import network.ResponseVisitor;
import network.SocketEventSender;
import shared.events.BoardIsReadyEvent;
import shared.events.CreateNewBoardEvent;
import shared.models.Board;
import shared.models.Timer;
import shared.responses.GameStateResponse;
import shared.responses.NewRandomBoardResponse;
import view.ExtraViewManager;
import timerHandler.TimerThread;


public class ChooseBoardController {

    public void boardIsOkRequest(Board board, Timer timer) {
        timer.stopTimer();
        ClientStarter.viewManager.LoadScene("waitingPage");
        SocketEventSender socketEventSender = ClientStarter.socketEventSender;
        BoardIsReadyEvent boardIsReadyEvent = new BoardIsReadyEvent(ClientStarter.authToken, board);
        Runnable runnable = () -> {
            boolean isOpponentFound = false;
            while (!isOpponentFound) {
                GameStateResponse gameStateResponse = (GameStateResponse) socketEventSender.request(boardIsReadyEvent);
                if (gameStateResponse == null) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                } else {
                    isOpponentFound = true;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
            ClientStarter.viewManager.LoadScene("gamePage");
        };
        ResponseVisitor responseVisitor = new ResponseVisitor(runnable);
        responseVisitor.start();
    }

    public void generateNewBoardRequest(Board board, Timer timer) {
        timer.stopTimer();
        SocketEventSender socketEventSender = ClientStarter.socketEventSender;
        CreateNewBoardEvent createNewBoardEvent = new CreateNewBoardEvent(ClientStarter.authToken, timer);
        NewRandomBoardResponse newRandomBoardResponse = (NewRandomBoardResponse) socketEventSender.request(createNewBoardEvent);
        if (newRandomBoardResponse.getBoard() != null) {
            TimerThread timerThread = new TimerThread(newRandomBoardResponse.getTimer());
            timerThread.start();
            ExtraViewManager.loadChooseBoardPage(newRandomBoardResponse.getBoard(), timerThread);
        } else {
            boardIsOkRequest(board, timer);
        }
    }
}
