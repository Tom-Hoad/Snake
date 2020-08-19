import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;
import java.util.Queue;

public class Game {
    private final Rectangle board;
    private final int width = 528;
    private final Snake snake;
    private final Food food;
    private Label endLabel;
    private Group tailGroup;
    private Queue<Rectangle> snakeTail;

    public Game(Group tailGroup) {
        board = new Rectangle(width, width, Color.LIGHTGRAY);
        this.snake = new Snake();
        this.food = new Food(snake.getWidth());
        this.tailGroup = tailGroup;
        this.snakeTail = new LinkedList<>();
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
        // Hides the game over message.
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

    // Moves the snake tail.
    public void moveTail(int x, int y, boolean extend) {
        if (snakeTail.size() > 0 || extend) {
            // Creates more of the snake.
            if (extend) {
                createTail(x, y);
                if (snakeTail.size() == 1) {
                    createTail(x, y);
                }
            }
            createTail(x, y);

            snakeTail.remove();

            // Removes the visual tail.
            Rectangle lastElement = (Rectangle) tailGroup.getChildren().get(tailGroup.getChildren().size() - 1);
            lastElement.toBack();
            tailGroup.getChildren().remove(tailGroup.getChildren().size() - 1);
        }
    }

    // Creates a tail part.
    public void createTail(int x, int y) {
        Rectangle tailPart = new Rectangle(snake.getWidth(), snake.getWidth(), Color.BLACK);
        tailPart.relocate(x, y);
        tailGroup.getChildren().add(tailPart);
        snakeTail.add(tailPart);
    }

    // Ends the game.
    public void gameOver() {
        // Shows the user a message.
        endLabel.setText("Game Over! Your score was: " + snake.getScore());
        endLabel.setVisible(!endLabel.isVisible());

        // Clears the tail.
        for (int i = 0; i < tailGroup.getChildren().size() - 1; i++) {
            Rectangle tailPart = (Rectangle) tailGroup.getChildren().get(i);
            tailPart.toBack();
        }
        tailGroup.getChildren().clear();
        snakeTail = new LinkedList<>();

        // Stops the animation.
        snake.defaultPosition();
        snake.getMovement().stop();
    }
}
