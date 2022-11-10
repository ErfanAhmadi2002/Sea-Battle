package shared.models;

import java.util.ArrayList;

public class Board {
    private Cell[][] cells;
    private ArrayList<Ship> ships;

    public Board() {
    }

    public Board(Cell[][] cells, ArrayList<Ship> ships) {
        this.cells = cells;
        this.ships = ships;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

}
