package shared.responses;

import shared.models.UserCopy;

import java.util.ArrayList;

public class LiveScoreResponse extends Response{
    private final ArrayList<UserCopy> allUsers;
    private final ArrayList<Integer> allScores;

    public LiveScoreResponse(ArrayList<UserCopy> allUsers, ArrayList<Integer> allScores) {
        this.allUsers = allUsers;
        this.allScores = allScores;
    }


    public ArrayList<UserCopy> getAllUsers() {
        return allUsers;
    }

    public ArrayList<Integer> getAllScores() {
        return allScores;
    }
}
