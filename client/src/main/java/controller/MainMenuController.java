package controller;

import clientStarter.ClientStarter;
import network.SocketEventSender;
import shared.events.CreateNewBoardEvent;
import shared.events.WatchProfileEvent;
import shared.responses.NewRandomBoardResponse;
import shared.responses.ProfileResponse;
import view.ExtraViewManager;
import timerHandler.TimerThread;

import java.io.IOException;

public class MainMenuController {

    public void profileRequest() throws IOException {
        SocketEventSender socketEventSender = ClientStarter.socketEventSender;
        WatchProfileEvent watchProfileEvent = new WatchProfileEvent(ClientStarter.authToken);
        ProfileResponse profileResponse = (ProfileResponse) socketEventSender.request(watchProfileEvent);
        ExtraViewManager.loadProfileScene(profileResponse.getUser());
    }

    public void newGameRequest() {
        SocketEventSender socketEventSender = ClientStarter.socketEventSender;
        CreateNewBoardEvent createNewBoardEvent = new CreateNewBoardEvent(ClientStarter.authToken, null);
        NewRandomBoardResponse newRandomBoardResponse = (NewRandomBoardResponse) socketEventSender.request(createNewBoardEvent);
        TimerThread timerThread = new TimerThread(newRandomBoardResponse.getTimer());
        timerThread.start();
        ExtraViewManager.loadChooseBoardPage(newRandomBoardResponse.getBoard(), timerThread);
    }

}
