package controller;

import clientStarter.ClientStarter;
import listeners.GeneralListeners;
import network.ResponseVisitor;
import network.SocketEventSender;
import shared.events.GetGamesListEvent;
import shared.events.GetLiveGameStateEvent;
import shared.responses.GameListResponse;
import shared.responses.LiveGameStateResponse;
import view.ExtraViewManager;
import view.pages.LiveGamePage;
import view.pages.StreamListPage;

public class StreamController {
    public void getStreamList (StreamListPage streamListPage , boolean isOnPage){
        SocketEventSender socketEventSender = ClientStarter.socketEventSender;
        GetGamesListEvent getGamesListEvent = new GetGamesListEvent(ClientStarter.authToken);
        Runnable runnable = () -> {
                while (isOnPage) {
                    GameListResponse gameListResponse = (GameListResponse) socketEventSender.request(getGamesListEvent);
                    streamListPage.updatePage(gameListResponse.getAllGamesData());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }

        };
        ResponseVisitor responseVisitor = new ResponseVisitor(runnable);
        responseVisitor.start();
    }

    public void goToStreamPage (int streamNumber){
        ExtraViewManager.loadStreamPage(streamNumber);
    }

    public void getLiveGame (LiveGamePage liveGamePage){
        SocketEventSender socketEventSender = ClientStarter.socketEventSender;
        GetLiveGameStateEvent getLiveGameStateEvent = new GetLiveGameStateEvent(ClientStarter.authToken , liveGamePage.getStreamNumber());
        Runnable runnable = () -> {
            try {
                while (true){
                    LiveGameStateResponse liveGameStateResponse = (LiveGameStateResponse) socketEventSender.request(getLiveGameStateEvent);
                    liveGamePage.update(liveGameStateResponse.getPlayer1Board() , liveGameStateResponse.getPlayer2Board() , liveGameStateResponse.getSecondsLeft());
                    Thread.sleep(400);
                }
            }catch (Throwable throwable){
                GeneralListeners generalListeners = new GeneralListeners();
                generalListeners.goBackToMainMenu();
            }
        };
        ResponseVisitor responseVisitor = new ResponseVisitor(runnable);
        responseVisitor.start();
    }
}
