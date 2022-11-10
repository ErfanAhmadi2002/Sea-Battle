package listeners;

import controller.ChooseBoardController;
import shared.models.Board;
import shared.models.Timer;
import view.drawers.ChooseBoardDrawer;
import view.drawers.TimerDrawer;


public class ChooseBoardListeners {
    private final ChooseBoardController chooseBoardController = new ChooseBoardController();


    public void readyButtonPressed (Board board , Timer timer){
        chooseBoardController.boardIsOkRequest(board , timer);
    }

    public void anotherBoardButtonPressed (ChooseBoardDrawer chooseBoardDrawer, TimerDrawer timerDrawer){
        Board board = chooseBoardDrawer.getBoard();
        Timer timer = timerDrawer.getTimerThread().getTimer();
        chooseBoardController.generateNewBoardRequest(board , timer);
    }
}
