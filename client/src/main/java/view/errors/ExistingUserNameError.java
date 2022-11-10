package view.errors;

import config.Config;
import javafx.scene.text.Text;

public class ExistingUserNameError {
    private Text text;
    private final Config config = Config.getConfig("errorMessages");
    private final String ERROR_TEXT = config.getProperty(String.class , "userNameExist");

    public ExistingUserNameError(Text text) {
        this.text = text;
    }

    public void errorOccurred (){
        text.setText(ERROR_TEXT);
    }
}
