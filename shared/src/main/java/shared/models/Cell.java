package shared.models;

public class Cell {
    private final int x;
    private final int y;
    private boolean exploded;
    private boolean hasShip;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.exploded = false;
        this.hasShip = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public boolean isHasShip() {
        return hasShip;
    }

    public void setHasShip(boolean hasShip) {
        this.hasShip = hasShip;
    }

}
