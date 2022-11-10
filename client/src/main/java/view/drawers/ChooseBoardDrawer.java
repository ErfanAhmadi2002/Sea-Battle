package view.drawers;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import shared.models.Board;
import shared.models.Cell;
import shared.models.Ship;

public class ChooseBoardDrawer {
    private Board board;
    private Canvas canvas;
    private final int CELL_SIZE = 70;

    public ChooseBoardDrawer(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void draw (){
        GraphicsContext g = canvas.getGraphicsContext2D();
        Image shipImage = new Image(getClass().getResource("/images/myBoat.png").toString());
        for (Ship ship:board.getShips()) {
            Cell start = ship.getFirstCell();
            if (ship.isVertical()){
                int y = start.getX() * CELL_SIZE;
                int x = start.getY() * CELL_SIZE;
                g.drawImage(shipImage , x , y , CELL_SIZE * ship.getLength() , CELL_SIZE);
            }
            else {
                ImageView imageView = new ImageView(shipImage);
                imageView.setRotate(90);
                SnapshotParameters parameters = new SnapshotParameters();
                parameters.setFill(Color.TRANSPARENT);
                Image shipImage2 = imageView.snapshot(parameters,null);
                int y = start.getX() * CELL_SIZE;
                int x = start.getY() * CELL_SIZE;
                g.drawImage(shipImage2 , x , y , CELL_SIZE , CELL_SIZE * ship.getLength());
            }
        }
    }
}
