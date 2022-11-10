package view.drawers;

import javafx.application.Platform;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import shared.models.Board;
import shared.models.Cell;
import shared.models.Ship;


public class GameStateBoardDrawer {
    private final Canvas canvas;
    private final int CELL_SIZE = 50;

    public GameStateBoardDrawer(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void draw(Board board) {
        Platform.runLater(() -> {
            GraphicsContext g = canvas.getGraphicsContext2D();
            //draw ships
            javafx.scene.image.Image shipImage = new javafx.scene.image.Image(getClass().getResource("/images/myBoat.png").toString());
            for (Ship ship : board.getShips()) {
                Cell start = ship.getFirstCell();
                if (ship.isVertical()) {
                    int y = start.getX() * CELL_SIZE;
                    int x = start.getY() * CELL_SIZE;
                    g.drawImage(shipImage, x, y, CELL_SIZE * ship.getLength(), CELL_SIZE);
                } else {
                    ImageView imageView = new ImageView(shipImage);
                    imageView.setRotate(90);
                    SnapshotParameters parameters = new SnapshotParameters();
                    parameters.setFill(Color.TRANSPARENT);
                    Image shipImage2 = imageView.snapshot(parameters, null);
                    int y = start.getX() * CELL_SIZE;
                    int x = start.getY() * CELL_SIZE;
                    g.drawImage(shipImage2, x, y, CELL_SIZE, CELL_SIZE * ship.getLength());
                }
            }

            //draw checkmarks and xMarks
            javafx.scene.image.Image checkImage = new javafx.scene.image.Image(getClass().getResource("/images/checkmark.png").toString());
            javafx.scene.image.Image XImage = new javafx.scene.image.Image(getClass().getResource("/images/xmark.png").toString());
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    Cell cell = board.getCells()[i][j];
                    int y = cell.getX() * CELL_SIZE;
                    int x = cell.getY() * CELL_SIZE;
                    if (cell.isHasShip() && cell.isExploded()) {
                        g.drawImage(checkImage, x, y, CELL_SIZE, CELL_SIZE);
                    }
                    if (!cell.isHasShip() && cell.isExploded()){
                        g.drawImage(XImage, x, y, CELL_SIZE, CELL_SIZE);
                    }
                }
            }
        });
    }
}
