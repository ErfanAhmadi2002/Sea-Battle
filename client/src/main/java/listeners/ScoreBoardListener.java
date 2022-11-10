package listeners;

import controller.ScoreBoardController;
import view.pages.ScoreBoard;

public class ScoreBoardListener {
    private final ScoreBoardController scoreBoardController = new ScoreBoardController();

    public void startPage (ScoreBoard scoreBoard , boolean isOnPage){
        scoreBoardController.getLiveScore(scoreBoard , isOnPage);
    }
}
