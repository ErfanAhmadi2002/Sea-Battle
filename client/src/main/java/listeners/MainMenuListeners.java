package listeners;

import clientStarter.ClientStarter;
import controller.MainMenuController;

import java.io.IOException;

public class MainMenuListeners {
    private final MainMenuController mainMenuController = new MainMenuController();

    public void profileRequest () throws IOException {
        mainMenuController.profileRequest();
    }

    public void newRandomBoardRequest () {
        mainMenuController.newGameRequest();
    }

    public void loadStreamPageRequest () {
        ClientStarter.viewManager.LoadScene("streamListPage");
    }

    public void loadScoreBoardPage (){
        ClientStarter.viewManager.LoadScene("scoreBoard");
    }
}
