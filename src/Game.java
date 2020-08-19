import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Game {
    private final Rectangle board;
    private final int width = 528;
    private final Snake snake;
    private Food food;
    private Label endLabel;

    public Game() {
        board = new Rectangle(width, width, Color.LIGHTGRAY);
        this.snake = new Snake();
        this.food = new Food(snake.getWidth());
    }

    // Gets the board.
    public Rectangle getBoard() {
        return board;
    }

    // Gets the snake.
    public Snake getSnake() {
        return snake;
    }

    // Gets the food.
    public Food getFood() {
        return food;
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
        if (endLabel.isVisible()) {
            endLabel.setVisible(false);
        }

        // Changes the snakes direction based on a key press.
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
