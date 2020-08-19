import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Snake {
    private final Rectangle snake;
    private final int width = 16;
    private int speed;
    private int score;
    private Timeline movement;

    public Snake() {
        this.snake = new Rectangle(width, width, Color.BLACK);
        this.speed = 120;
        this.score = 0;

        defaultPosition();
    }

    // Gets the snake shape.
    public Rectangle getShape() {
        return snake;
    }

    // Gets the width of the snake.
    public int getWidth() {
        return width;
    }

    // Gets the score.
    public int getScore() {
        return score;
    }

    // Resets the score and speed.
    public void resetSnake() {
        speed = 120;
        score = 0;
    }

    // Gets the snake animation.
    public Timeline getMovement() {
        return movement;
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

    public void move(int xChange, int yChange, Game game) {
        // Resets the direction the snake is going in.
        if (movement != null) {
            movement.stop();
        }

        // Moves the snake in the current direction.
        movement = new Timeline(
                new KeyFrame(
                        Duration.millis(speed),
                        event -> {
                            snake.setX(snake.getX() + xChange);
                            snake.setY(snake.getY() + yChange);

                            // Checks if the snail goes off the board, eats food or just moves normally.
                            if (snake.getX() >= game.getWidth() || snake.getX() < 0 || snake.getY() >= game.getWidth() || snake.getY() < 0) {
                                game.gameOver();
                            } else if (snake.getX() == game.getFood().getX() && snake.getY() == game.getFood().getY()) {
                                eatFood();
                                game.moveTail((int) snake.getX(), (int) snake.getY(), true);
                                game.getFood().positionFood();
                            } else {
                                game.checkPath((int) snake.getX(), (int) snake.getY());
                                game.moveTail((int) snake.getX(), (int) snake.getY(), false);
                            }
                        }
                )
        );
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.play();
    }
}
