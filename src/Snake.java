import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Snake extends Application {
    // Board size is a 33 * 33 grid, each tile being 16px * 16px.
    private final static int boardWidth = 528;
    private final static int snakeWidth = 16;

    private Timeline snakeMovement;
    private Pane layout = new Pane();
    private int snakeSpeed = 150;
    private int snakeScore = 0;

    @Override
    public void start(Stage stage) {
        stage.setTitle("The Snake Game");

        // Creates the window.
        layout.setPrefSize(boardWidth, boardWidth);
        Scene scene = new Scene(layout, boardWidth, boardWidth);

        // Creates the game area and the snake.
        Rectangle snake = new Rectangle(snakeWidth, snakeWidth, Color.BLACK);
        Rectangle gameArea = new Rectangle(boardWidth, boardWidth, Color.LIGHTGRAY);
        layout.getChildren().addAll(gameArea, snake);
        defaultPosition(snake);

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

    // Sets the initial position of the snake.
    public void defaultPosition(Rectangle snake) {
        snake.setX(snakeWidth * snakeWidth);
        snake.setY(snakeWidth * snakeWidth);
    }

    // Changes the speed of the snake.
    public void changeSpeed(int newSpeed) {
        snakeSpeed = newSpeed;
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
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Game Over!");
                                alert.setHeaderText("You finished with a score of: " + snakeScore);
                                alert.show();

                                defaultPosition(snake);
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
