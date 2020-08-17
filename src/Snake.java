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
    private Timeline snakeMovement;

    @Override
    public void start(Stage stage) {
        stage.setTitle("The Snake Game");

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 400);

        Rectangle snake = new Rectangle(15, 15, Color.BLACK);
        pane.getChildren().add(snake);

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
        if (snakeMovement != null) {
            snakeMovement.stop();
        }

        snakeMovement = new Timeline(
                new KeyFrame(
                        Duration.millis(100),
                        event -> {
                            snake.setX(snake.getX() + xChange);
                            snake.setY(snake.getY() + yChange);
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
