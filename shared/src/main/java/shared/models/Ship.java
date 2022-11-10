package shared.models;

import java.util.ArrayList;

public class Ship {
    private int length;
    private boolean isVertical;
    private Cell firstCell,lastCell;
    private ArrayList<Cell> cells;
    private ArrayList<Cell> adjacentCells;
    private boolean isExploded;

    public Ship(int length, boolean isVertical, ArrayList<Cell> cells , ArrayList<Cell> adjacentCells) {
        this.length = length;
        this.isVertical = isVertical;
        this.cells = cells;
        this.adjacentCells = adjacentCells;
        this.firstCell = cells.get(0);
        this.lastCell = cells.get(cells.size()-1);
        this.isExploded = false;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }

    public Cell getFirstCell() {
        return firstCell;
    }

    public void setFirstCell(Cell firstCell) {
        this.firstCell = firstCell;
    }

    public Cell getLastCell() {
        return lastCell;
    }

    public void setLastCell(Cell lastCell) {
        this.lastCell = lastCell;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public boolean isExploded() {
        return isExploded;
    }

    public void setExploded(boolean exploded) {
        isExploded = exploded;
    }

    public ArrayList<Cell> getAdjacentCells() {
        return adjacentCells;
    }

    public void setAdjacentCells(ArrayList<Cell> adjacentCells) {
        this.adjacentCells = adjacentCells;
    }

}
