import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game extends Application {
    // Board size is a 33 * 33 grid, each tile being 16px * 16px.
    private final static int boardWidth = 528;
    private final static int snakeWidth = 16;
    private final Pane layout = new Pane();

    @Override
    public void start(Stage stage) {
        stage.setTitle("The Snake Game");

        // Creates the window.
        layout.setPrefSize(boardWidth, boardWidth);
        Scene scene = new Scene(layout, boardWidth, boardWidth);

        // Creates the game area and the snake.
        Snake snake = new Snake(snakeWidth);
        Rectangle gameArea = new Rectangle(boardWidth, boardWidth, Color.LIGHTGRAY);
        layout.getChildren().addAll(gameArea, snake.getSnake());
        snake.defaultPosition();

        // Changes the snakes direction based on a key press.
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                snake.move(0, -snakeWidth);
            } else if (e.getCode() == KeyCode.DOWN) {
                snake.move(0, snakeWidth);
            } else if (e.getCode() == KeyCode.RIGHT) {
                snake.move(snakeWidth, 0);
            } else if (e.getCode() == KeyCode.LEFT) {
                snake.move(-snakeWidth, 0);
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    // Launches the game.
    public static void main(String[] args) {
        launch(args);
    }
}
