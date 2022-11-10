package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;
import shared.models.Board;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BoardDataBase {

    public static Board Load(int id) throws IOException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "BoardDirectory") + "\\" + id + ".json");
        FileReader fileReader = new FileReader(file);
        Gson gson = new Gson();
        Board board = gson.fromJson(fileReader, Board.class);
        fileReader.close();
        return board;
    }

    public static void update(Board board, int id) throws IOException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "BoardDirectory") + "\\" + id + ".json" );
        while (true) {
            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                FileWriter fileWriter = new FileWriter(file , false);
                gson.toJson(board, fileWriter);
                fileWriter.flush();
                fileWriter.close();
                break;
            } catch (Exception e) {
                file.createNewFile();
            }
        }
    }

}
