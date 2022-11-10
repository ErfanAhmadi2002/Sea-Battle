package listeners;

import controller.GameController;
import javafx.scene.input.MouseEvent;
import view.pages.GamePage;


public class GamePageListeners {
    private final GameController gameController = new GameController();

    public void getGameState (GamePage gamePage){
        gameController.getGameState(gamePage);
    }

    public void clickOnBoard (MouseEvent event){
        gameController.clickOnBoard(event.getX() , event.getY());
    }
}
