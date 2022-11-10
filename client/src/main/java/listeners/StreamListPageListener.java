package listeners;

import controller.StreamController;
import view.pages.LiveGamePage;
import view.pages.StreamListPage;

public class StreamListPageListener {
    private final StreamController streamController =  new StreamController();

    public void getStreamList (StreamListPage streamListPage , boolean isOnPage){
        streamController.getStreamList(streamListPage , isOnPage);
    }

    public void goToStreamPage (int streamNumber){
        streamController.goToStreamPage(streamNumber);
    }

    public void getLiveGame (LiveGamePage liveGamePage){
        streamController.getLiveGame (liveGamePage);
    }
}
