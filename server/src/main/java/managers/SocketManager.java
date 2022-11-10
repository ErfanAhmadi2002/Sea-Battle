package managers;

import config.Config;
import database.UserDataBase;
import game.Game;
import network.SocketResponseSender;
import shared.models.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class SocketManager extends Thread{

    public void run() {
        try {
            Config config = Config.getConfig("network");
            ArrayList<Game> gamesInProgress = new ArrayList<>();
            LobbyManager lobbyManager = new LobbyManager(gamesInProgress);
            ServerSocket serverSocket = new ServerSocket(config.getProperty(Integer.class , "port"));
            HashMap<String , Integer> allUserNames = UserDataBase.loadAllUserNames();
            HashMap<User , Integer> onlineUsers = new HashMap<>();
            SecureRandom secureRandom = new SecureRandom();
            while (true) {
                Socket socket = serverSocket.accept();
                SocketResponseSender socketResponseSender = new SocketResponseSender(socket);
                ClientManager clientManager = new ClientManager(socketResponseSender , lobbyManager, allUserNames , secureRandom , onlineUsers);
                clientManager.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}