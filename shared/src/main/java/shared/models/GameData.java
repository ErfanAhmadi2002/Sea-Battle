package shared.models;

public class GameData {
    private final int gameNumber;
    private final int turns;
    private final int healthyShips1,healthyShips2;
    private final int bombsInTargets1,bombsInTargets2;

    public GameData(int gameNumber, int turns, int healthyShips1, int healthyShips2, int bombsInTargets1, int bombsInTargets2) {
        this.gameNumber = gameNumber;
        this.turns = turns;
        this.healthyShips1 = healthyShips1;
        this.healthyShips2 = healthyShips2;
        this.bombsInTargets1 = bombsInTargets1;
        this.bombsInTargets2 = bombsInTargets2;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public int getHealthyShips1() {
        return healthyShips1;
    }

    public int getBombsInTargets1() {
        return bombsInTargets1;
    }

    public int getBombsInTargets2() {
        return bombsInTargets2;
    }

    public int getHealthyShips2() {
        return healthyShips2;
    }

    public int getTurns() {
        return turns;
    }
}
