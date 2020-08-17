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
    private final static int width = 400;

    private Timeline snakeMovement;

    @Override
    public void start(Stage stage) {
        stage.setTitle("The Snake Game");

        // Creates the window.
        Pane pane = new Pane();
        pane.setPrefSize(width, width);
        Scene scene = new Scene(pane, width, width);

        // Creates the game area and the snake.
        Rectangle snake = new Rectangle(16, 16, Color.BLACK);
        Rectangle gameArea = new Rectangle(width, width, Color.LIGHTGRAY);
        pane.getChildren().addAll(gameArea, snake);

        // Changes the snakes direction based on a key press.
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                move(snake, 0, -10);
            } else if (e.getCode() == KeyCode.DOWN) {
                move(snake, 0, 10);
            } else if (e.getCode() == KeyCode.RIGHT) {
                move(snake, 10, 0);
            } else if (e.getCode() == KeyCode.LEFT) {
                move(snake, -10, 0);
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
                        Duration.millis(100),
                        event -> {
                            snake.setX(snake.getX() + xChange);
                            snake.setY(snake.getY() + yChange);

                            // Checks if the snake is on the board.
                            if (snake.getX() > 400 || snake.getX() < 0 || snake.getY() > 400 || snake.getY() < 0) {
                                System.out.println("The snake is off the board.");
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
