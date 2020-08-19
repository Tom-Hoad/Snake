import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Snake {
    private final Rectangle snake;
    private final static int width = 16;
    private int speed;
    private int score;
    private Timeline snakeMovement;

    public Snake() {
        this.snake = new Rectangle(width, width, Color.BLACK);
        this.speed = 50;
        this.score = 0;

        defaultPosition();
    }

    // Gets the snake shape.
    public Rectangle getSnake() {
        return snake;
    }

    // Gets the snake score.
    public int getScore() {
        return score;
    }

    // Gets the width of the snake.
    public int getWidth() {
        return width;
    }

    // Sets the initial position of the snake.
    public void defaultPosition() {
        snake.setX(width * width);
        snake.setY(width * width);
    }

    // The snake eats the food.
    public void eatFood() {
        if (speed > 50) {
            speed = speed - 10;
        }
        score++;
    }

    public void move(int xChange, int yChange, int boardWidth) {
        // Resets the direction the snake is going in.
        if (snakeMovement != null) {
            snakeMovement.stop();
        }

        // Moves the snake in the current direction.
        snakeMovement = new Timeline(
                new KeyFrame(
                        Duration.millis(speed),
                        event -> {
                            snake.setX(snake.getX() + xChange);
                            snake.setY(snake.getY() + yChange);

                            // Stops the game if the snake goes off the board.
                            if (snake.getX() >= boardWidth || snake.getX() < 0 || snake.getY() >= boardWidth || snake.getY() < 0) {
                                gameOver();
                            }
                        }
                )
        );
        snakeMovement.setCycleCount(Timeline.INDEFINITE);
        snakeMovement.play();
    }

    // Ends the game.
    public void gameOver() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over!");
        alert.setHeaderText("You finished with a score of: " + score);
        alert.show();

        defaultPosition();
        snakeMovement.stop();
    }
}
