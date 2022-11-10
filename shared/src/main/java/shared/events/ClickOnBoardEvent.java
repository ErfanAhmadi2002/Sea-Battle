package shared.events;

import shared.responses.Response;

public class ClickOnBoardEvent extends Event{
    private final int authToken;
    private final int cell_X;
    private final int cell_Y;

    public ClickOnBoardEvent(int authToken, int cell_x, int cell_y) {
        this.authToken = authToken;
        cell_X = cell_x;
        cell_Y = cell_y;
    }

    public int getAuthToken() {
        return authToken;
    }

    public int getCell_X() {
        return cell_X;
    }

    public int getCell_Y() {
        return cell_Y;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.clickOnBoard(this);
    }
}
