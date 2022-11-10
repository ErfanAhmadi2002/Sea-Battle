package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;
import shared.models.User;
import shared.models.UserCopy;

import java.io.*;
import java.util.*;

public class UserDataBase {

    public static User Load(int id) throws IOException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "UserDirectory") + "\\" + id + ".json");
        FileReader fileReader = new FileReader(file);
        Gson gson = new Gson();
        User user = gson.fromJson(fileReader, User.class);
        fileReader.close();
        return user;
    }


    public static void update(User user) {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "UserDirectory") + "\\" + user.getId() + ".json" );
        while (true) {
            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                FileWriter fileWriter = new FileWriter(file , false);
                gson.toJson(user, fileWriter);
                fileWriter.flush();
                fileWriter.close();
                break;
            } catch (Exception e) {
                try {
                    file.createNewFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public static void delete(int id) {
        try {
            Config config = Config.getConfig("dataBaseAddress");
            File file = new File(config.getProperty(String.class , "UserDirectory") + "\\" + id + ".json");
            file.delete();
        }catch (Throwable throwable){
        }
    }

    public static int getLastId() throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File initialize = new File(config.getProperty(String.class , "initializeUser"));
        Scanner scanner = new Scanner(initialize);
        scanner.next();
        int LastUserId = scanner.nextInt();
        scanner.close();
        PrintStream printStream = new PrintStream(new FileOutputStream(initialize) , false);
        Integer LastUserId2 = LastUserId + 1;
        printStream.println("NumberOfUsers: " + LastUserId2);
        printStream.flush();
        printStream.close();
        return LastUserId;
    }

    public static HashMap<String , Integer> loadAllUserNames () throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "AllUserNames"));
        Scanner scanner = new Scanner(file);
        HashMap<String , Integer> AllUsernames = new HashMap<>();
        while (scanner.hasNext()){
            String username = scanner.next();
            int id = scanner.nextInt();
            AllUsernames.put(username , id);
        }
        scanner.close();
        return AllUsernames;
    }

    public static void saveNewAllUserNames (HashMap<String , Integer> allUserNames) throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "AllUserNames"));
        PrintStream printStream = new PrintStream(new FileOutputStream(file) , false);
        for (String username:allUserNames.keySet()) {
            printStream.println(username + " " + allUserNames.get(username));
        }
        printStream.flush();
        printStream.close();
    }

    public static HashMap<UserCopy , Integer> getAllUsersCopy (HashMap<User , Integer> onlineUsers) throws IOException {
        Config config = Config.getConfig("dataBaseAddress");
        File directory = new File(config.getProperty(String.class , "UserDirectory"));
        HashMap<UserCopy , Integer> allUsersCopy = new HashMap<>();
        for (File file:directory.listFiles()) {
            FileReader fileReader = new FileReader(file);
            Gson gson = new Gson();
            User user = gson.fromJson(fileReader, User.class);
            fileReader.close();
            String name = user.getName();
            boolean isOnline = isUserOnline(user , onlineUsers);
            int score = user.getNumberOfWins() - user.getNumberOfLoses();
            allUsersCopy.put(new UserCopy(name , isOnline) , score);
        }
        return allUsersCopy;
    }

    private static boolean isUserOnline (User user , HashMap<User , Integer> onlineUsers){
        for (User user1:onlineUsers.keySet()) {
            if (user.getId() == user1.getId()){
                return true;
            }
        }
        return false;
    }


}
