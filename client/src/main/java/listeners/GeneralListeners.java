package listeners;

import clientStarter.ClientStarter;

public class GeneralListeners {
    public void goBackToMainMenu () {
        ClientStarter.viewManager.LoadScene("mainMenuPage");
    }
}
