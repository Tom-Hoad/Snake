import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Snake extends Application {
    private final static int boardWidth = 512;
    private final static int snakeWidth = 16;

    private Timeline snakeMovement;
    private int snakeSpeed = 150;

    @Override
    public void start(Stage stage) {
        stage.setTitle("The Snake Game");

        // Creates the window.
        Pane pane = new Pane();
        pane.setPrefSize(boardWidth, boardWidth);
        Scene scene = new Scene(pane, boardWidth, boardWidth);

        // Creates the game area and the snake.
        Rectangle snake = new Rectangle(snakeWidth, snakeWidth, Color.BLACK);
        Rectangle gameArea = new Rectangle(boardWidth, boardWidth, Color.LIGHTGRAY);
        pane.getChildren().addAll(gameArea, snake);

        // Changes the snakes direction based on a key press.
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                move(snake, 0, -snakeWidth);
            } else if (e.getCode() == KeyCode.DOWN) {
                move(snake, 0, snakeWidth);
            } else if (e.getCode() == KeyCode.RIGHT) {
                move(snake, snakeWidth, 0);
            } else if (e.getCode() == KeyCode.LEFT) {
                move(snake, -snakeWidth, 0);
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public void move(Rectangle snake, int xChange, int yChange) {
        // Resets the direction the snake is going in.
        if (snakeMovement != null) {
            snakeMovement.stop();
        }

        // Moves the snake in the current direction.
        snakeMovement = new Timeline(
                new KeyFrame(
                        Duration.millis(snakeSpeed),
                        event -> {
                            snake.setX(snake.getX() + xChange);
                            snake.setY(snake.getY() + yChange);

                            // Stops the game if the snake goes off the board.
                            if (snake.getX() >= boardWidth || snake.getX() < 0 || snake.getY() >= boardWidth || snake.getY() < 0) {
                                System.out.println("Game Over!");
                                snake.setX(snake.getX() - xChange);
                                snake.setY(snake.getY() - yChange);
                                snakeMovement.stop();
                            }
                        }
                )
        );
        snakeMovement.setCycleCount(Timeline.INDEFINITE);
        snakeMovement.play();
    }

    // Launches the game.
    public static void main(String[] args) {
        launch(args);
    }
}
