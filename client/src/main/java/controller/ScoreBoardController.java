package controller;

import clientStarter.ClientStarter;
import network.ResponseVisitor;
import network.SocketEventSender;
import shared.events.GetLiveScoreEvent;
import shared.models.UserCopy;
import shared.responses.LiveScoreResponse;
import view.pages.ScoreBoard;

import java.util.*;

public class ScoreBoardController {

    public void getLiveScore(ScoreBoard scoreBoard , boolean isOnPage) {
        SocketEventSender socketEventSender = ClientStarter.socketEventSender;
        GetLiveScoreEvent getLiveScoreEvent = new GetLiveScoreEvent(ClientStarter.authToken);
        Runnable runnable = () -> {
            while (isOnPage){
                LiveScoreResponse liveScoreResponse = (LiveScoreResponse) socketEventSender.request(getLiveScoreEvent);
                HashMap<UserCopy , Integer> allScoreBoardData = new HashMap<>();
                for (int i = 0; i < liveScoreResponse.getAllUsers().size(); i++) {
                    UserCopy userCopy = liveScoreResponse.getAllUsers().get(i);
                    allScoreBoardData.put(userCopy , liveScoreResponse.getAllScores().get(i));
                }
                allScoreBoardData = sortByValue(allScoreBoardData);
                scoreBoard.updatePage(allScoreBoardData);
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

    public static HashMap<UserCopy, Integer> sortByValue(HashMap<UserCopy, Integer> hm) {
        LinkedHashMap<UserCopy, Integer> sortedMap = new LinkedHashMap<>();
        hm.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        return sortedMap;
    }

}
