public class Game {
    private Board board;
    private Snake snake;

    public Game() {
        board = new Board();
        snake = new Snake();
    }

    // Gets the board.
    public Board getBoard() {
        return board;
    }

    // Gets the snake.
    public Snake getSnake() {
        return snake;
    }
}
