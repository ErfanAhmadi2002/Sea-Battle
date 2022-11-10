package shared.models;

public class UserCopy {
    private final String name;
    private final boolean isOnline;


    public UserCopy(String name , boolean isOnline) {
        this.name = name;
        this.isOnline = isOnline;
    }

    public String getName() {
        return name;
    }

    public boolean isOnline() {
        return isOnline;
    }

}
