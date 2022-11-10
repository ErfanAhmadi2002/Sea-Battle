package shared.models;

public class User {
    private String name;
    private String password;
    private final int id;
    private int numberOfWins;
    private int numberOfLoses;


    public User(String name, String password , int lastId) {
        lastId++;
        this.id = lastId;
        this.name = name;
        this.password = password;
        this.numberOfLoses = 0;
        this.numberOfWins = 0;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public int getNumberOfLoses() {
        return numberOfLoses;
    }

    public void setNumberOfLoses(int numberOfLoses) {
        this.numberOfLoses = numberOfLoses;
    }
}
