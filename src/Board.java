public class Board {
    // Board size is a 33 * 33 grid, each tile being 16px * 16px.
    private final static int width = 528;
    Snake snake;

    public Board(Snake snake) {
        this.snake = snake;
    }

    // Gets the width of the board.
    public int getWidth() {
        return width;
    }
}
