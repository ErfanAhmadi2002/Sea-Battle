package extra;

import shared.models.Board;
import shared.models.Cell;
import shared.models.Ship;

import java.util.ArrayList;

public class BoardGenerator {

    public BoardGenerator() {
    }

    public Board generateNewBoard (){
        Cell[][] cells = new Cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell(i , j);
            }
        }

        ////////////////////////////
        cells[0][0].setHasShip(true);
        cells[0][3].setHasShip(true);
        cells[0][4].setHasShip(true);
        cells[0][5].setHasShip(true);
        cells[0][6].setHasShip(true);
        cells[0][9].setHasShip(true);
        cells[9][0].setHasShip(true);
        cells[9][2].setHasShip(true);
        cells[9][3].setHasShip(true);
        cells[9][4].setHasShip(true);
        cells[9][9].setHasShip(true);
        cells[2][0].setHasShip(true);
        cells[3][0].setHasShip(true);
        cells[6][0].setHasShip(true);
        cells[7][0].setHasShip(true);
        cells[2][9].setHasShip(true);
        cells[3][9].setHasShip(true);
        cells[4][9].setHasShip(true);
        cells[6][9].setHasShip(true);
        cells[7][9].setHasShip(true);
        /////////////////////////////

        ArrayList<Ship> ships = new ArrayList<>();
        Ship ship1 = getShip4(cells);
        Ship ship2 = getShip3_1(cells);
        Ship ship3 = getShip3_2(cells);
        Ship ship4 = getShip2_1(cells);
        Ship ship5 = getShip2_2(cells);
        Ship ship6 = getShip2_3(cells);
        Ship ship7 = getShip4_1(cells);
        Ship ship8 = getShip4_2(cells);
        Ship ship9 = getShip4_3(cells);
        Ship ship10 = getShip4_4(cells);
        ships.add(ship1);
        ships.add(ship2);
        ships.add(ship3);
        ships.add(ship4);
        ships.add(ship5);
        ships.add(ship6);
        ships.add(ship7);
        ships.add(ship8);
        ships.add(ship9);
        ships.add(ship10);
        return new Board(cells , ships);
    }

    private Ship getShip4 (Cell[][] cells){
        ArrayList<Cell> cellsShip = new ArrayList<>();
        cellsShip.add(cells[0][3]);
        cellsShip.add(cells[0][4]);
        cellsShip.add(cells[0][5]);
        cellsShip.add(cells[0][6]);
        ArrayList<Cell> adjacentCells = new ArrayList<>();
        adjacentCells.add(cells[0][2]);
        adjacentCells.add(cells[0][7]);
        adjacentCells.add(cells[1][2]);
        adjacentCells.add(cells[1][3]);
        adjacentCells.add(cells[1][4]);
        adjacentCells.add(cells[1][5]);
        adjacentCells.add(cells[1][6]);
        adjacentCells.add(cells[1][7]);
        return new Ship(4 , true , cellsShip , adjacentCells);
    }

    private Ship getShip3_1 (Cell[][] cells){
        ArrayList<Cell> cellsShip = new ArrayList<>();
        cellsShip.add(cells[9][2]);
        cellsShip.add(cells[9][3]);
        cellsShip.add(cells[9][4]);
        ArrayList<Cell> adjacentCells = new ArrayList<>();
        adjacentCells.add(cells[9][1]);
        adjacentCells.add(cells[9][5]);
        adjacentCells.add(cells[8][1]);
        adjacentCells.add(cells[8][2]);
        adjacentCells.add(cells[8][3]);
        adjacentCells.add(cells[8][4]);
        adjacentCells.add(cells[8][5]);
        return new Ship(3 , true , cellsShip , adjacentCells);
    }

    private Ship getShip3_2 (Cell[][] cells){
        ArrayList<Cell> cellsShip = new ArrayList<>();
        cellsShip.add(cells[2][9]);
        cellsShip.add(cells[3][9]);
        cellsShip.add(cells[4][9]);
        ArrayList<Cell> adjacentCells = new ArrayList<>();
        adjacentCells.add(cells[1][9]);
        adjacentCells.add(cells[5][9]);
        adjacentCells.add(cells[1][8]);
        adjacentCells.add(cells[2][8]);
        adjacentCells.add(cells[3][8]);
        adjacentCells.add(cells[4][8]);
        adjacentCells.add(cells[5][8]);
        return new Ship(3 , false , cellsShip , adjacentCells);
    }

    private Ship getShip2_1 (Cell[][] cells){
        ArrayList<Cell> cellsShip = new ArrayList<>();
        cellsShip.add(cells[2][0]);
        cellsShip.add(cells[3][0]);
        ArrayList<Cell> adjacentCells = new ArrayList<>();
        adjacentCells.add(cells[1][0]);
        adjacentCells.add(cells[4][0]);
        adjacentCells.add(cells[1][1]);
        adjacentCells.add(cells[2][1]);
        adjacentCells.add(cells[3][1]);
        adjacentCells.add(cells[4][1]);
        return new Ship(2 , false , cellsShip , adjacentCells);
    }

    private Ship getShip2_2(Cell[][] cells){
        ArrayList<Cell> cellsShip = new ArrayList<>();
        cellsShip.add(cells[6][0]);
        cellsShip.add(cells[7][0]);
        ArrayList<Cell> adjacentCells = new ArrayList<>();
        adjacentCells.add(cells[5][0]);
        adjacentCells.add(cells[8][0]);
        adjacentCells.add(cells[5][1]);
        adjacentCells.add(cells[6][1]);
        adjacentCells.add(cells[7][1]);
        adjacentCells.add(cells[8][1]);
        return new Ship(2 , false , cellsShip , adjacentCells);
    }

    private Ship getShip2_3(Cell[][] cells){
        ArrayList<Cell> cellsShip = new ArrayList<>();
        cellsShip.add(cells[6][9]);
        cellsShip.add(cells[7][9]);
        ArrayList<Cell> adjacentCells = new ArrayList<>();
        adjacentCells.add(cells[5][9]);
        adjacentCells.add(cells[8][9]);
        adjacentCells.add(cells[5][8]);
        adjacentCells.add(cells[6][8]);
        adjacentCells.add(cells[7][8]);
        adjacentCells.add(cells[8][8]);
        return new Ship(2 , false , cellsShip , adjacentCells);
    }

    private Ship getShip4_1(Cell[][] cells){
        ArrayList<Cell> cellsShip = new ArrayList<>();
        cellsShip.add(cells[0][0]);
        ArrayList<Cell> adjacentCells = new ArrayList<>();
        adjacentCells.add(cells[0][1]);
        adjacentCells.add(cells[1][0]);
        adjacentCells.add(cells[1][1]);
        return new Ship(1 , false , cellsShip , adjacentCells);
    }

    private Ship getShip4_2(Cell[][] cells){
        ArrayList<Cell> cellsShip = new ArrayList<>();
        cellsShip.add(cells[0][9]);
        ArrayList<Cell> adjacentCells = new ArrayList<>();
        adjacentCells.add(cells[0][8]);
        adjacentCells.add(cells[1][8]);
        adjacentCells.add(cells[1][9]);
        return new Ship(1 , false , cellsShip , adjacentCells);
    }

    private Ship getShip4_3(Cell[][] cells){
        ArrayList<Cell> cellsShip = new ArrayList<>();
        cellsShip.add(cells[9][0]);
        ArrayList<Cell> adjacentCells = new ArrayList<>();
        adjacentCells.add(cells[9][1]);
        adjacentCells.add(cells[8][0]);
        adjacentCells.add(cells[8][1]);
        return new Ship(1 , false , cellsShip , adjacentCells);
    }

    private Ship getShip4_4(Cell[][] cells){
        ArrayList<Cell> cellsShip = new ArrayList<>();
        cellsShip.add(cells[9][9]);
        ArrayList<Cell> adjacentCells = new ArrayList<>();
        adjacentCells.add(cells[9][8]);
        adjacentCells.add(cells[8][8]);
        adjacentCells.add(cells[8][9]);
        return new Ship(1 , false , cellsShip , adjacentCells);
    }
}
