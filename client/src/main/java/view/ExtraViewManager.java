package view;

import clientStarter.ClientStarter;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import shared.models.Board;
import shared.models.User;
import timerHandler.ChooseBoardTimerHandler;
import timerHandler.TimerThread;
import view.pages.ChooseBoardPage;
import view.pages.LiveGamePage;
import view.pages.Profile;

import java.io.IOException;

public class ExtraViewManager {

    public static void loadProfileScene(User user) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(ClientStarter.viewManager.getFxmlConfig().getProperty(String.class , "profile")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            Profile profile = fxmlLoader.getController();
            profile.setUser(user);
            profile.startPage();
            ClientStarter.viewManager.getMainStage().setScene(scene);
        });
    }

    public static void loadChooseBoardPage (Board board , TimerThread timerThread) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(ClientStarter.viewManager.getFxmlConfig().getProperty(String.class , "chooseBoardPage")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            ChooseBoardPage chooseBoardPage = fxmlLoader.getController();
            chooseBoardPage.getBoardDrawer().setBoard(board);
            chooseBoardPage.getTimerDrawer().setTimerThread(timerThread);
            chooseBoardPage.getBoardDrawer().draw();
            chooseBoardPage.getTimerDrawer().start();
            ChooseBoardTimerHandler chooseBoardTimerHandler = new ChooseBoardTimerHandler(board , timerThread.getTimer() , chooseBoardPage.getChooseBoardListeners());
            chooseBoardTimerHandler.start();
            ClientStarter.viewManager.getMainStage().setScene(scene);
        });
    }

    public static void loadStreamPage (int streamNumber){
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(ClientStarter.viewManager.getFxmlConfig().getProperty(String.class , "liveGamePage")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            LiveGamePage liveGamePage = fxmlLoader.getController();
            liveGamePage.setStreamNumber(streamNumber);
            liveGamePage.startPage();
            ClientStarter.viewManager.getMainStage().setScene(scene);
        });
    }
}
