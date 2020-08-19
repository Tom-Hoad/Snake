import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Game {
    private final Rectangle board;
    private final int width = 528;
    private final Snake snake;
    private final Food food;
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
            try {
                Thread.sleep(1000);
                endLabel.setVisible(false);
                snake.resetSnake();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        endLabel.setText("Game Over! Your score was: " + snake.getScore());
        endLabel.setVisible(!endLabel.isVisible());

        snake.defaultPosition();
        snake.getMovement().stop();
    }
}
