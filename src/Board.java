import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {
    // Board size is a 33 * 33 grid, each tile being 16px * 16px.
    private final Rectangle board;
    private final static int width = 528;

    public Board() {
        board = new Rectangle(width, width, Color.LIGHTGRAY);
    }

    // Gets the board.
    public Rectangle getBoard() {
        return board;
    }

    // Gets the width of the board.
    public int getWidth() {
        return width;
    }
}
