package shared.responses;

import shared.models.GameData;

import java.util.ArrayList;

public class GameListResponse extends Response{
    private final ArrayList<GameData> allGamesData;

    public GameListResponse(ArrayList<GameData> allGamesData) {
        this.allGamesData = allGamesData;
    }

    public ArrayList<GameData> getAllGamesData() {
        return allGamesData;
    }
}
