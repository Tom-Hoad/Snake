import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Game {
    private final Rectangle board;
    private final int width = 528;
    private final Snake snake;
    private Label endLabel;

    public Game() {
        board = new Rectangle(width, width, Color.LIGHTGRAY);
        this.snake = new Snake();
    }

    // Gets the board.
    public Rectangle getBoard() {
        return board;
    }

    // Gets the snake.
    public Snake getSnake() {
        return snake;
    }

    // Gets the width of the board.
    public int getWidth() {
        return width;
    }

    // Sets the game over label.
    public void setLabel(Label endLabel) {
        this.endLabel = endLabel;
    }

    // Moves the snake.
    public void move(KeyCode code) {
        if (code == KeyCode.UP) {
            snake.move(0, -snake.getWidth(), this);
        } else if (code == KeyCode.DOWN) {
            snake.move(0, snake.getWidth(), this);
        } else if (code == KeyCode.RIGHT) {
            snake.move(snake.getWidth(), 0, this);
        } else if (code == KeyCode.LEFT) {
            snake.move(-snake.getWidth(), 0, this);
        }
    }

    // Ends the game.
    public void gameOver() {
        endLabel.setVisible(!endLabel.isVisible());
        snake.defaultPosition();
        snake.getMovement().stop();
    }
}
