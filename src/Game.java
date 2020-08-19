import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game extends Application {
    private final Pane layout = new Pane();

    @Override
    public void start(Stage stage) {
        stage.setTitle("The Snake Game");

        // Creates the board and snake objects.
        Snake snake = new Snake();
        Board board = new Board(snake);

        // Creates the window.
        layout.setPrefSize(board.getWidth(), board.getWidth());
        Scene scene = new Scene(layout, board.getWidth(), board.getWidth());

        // Creates the game area.
        Rectangle gameArea = new Rectangle(board.getWidth(), board.getWidth(), Color.LIGHTGRAY);
        layout.getChildren().addAll(gameArea, snake.getShape());

        // Changes the snakes direction based on a key press.
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                snake.move(0, -snake.getWidth(), board.getWidth());
            } else if (e.getCode() == KeyCode.DOWN) {
                snake.move(0, snake.getWidth(), board.getWidth());
            } else if (e.getCode() == KeyCode.RIGHT) {
                snake.move(snake.getWidth(), 0, board.getWidth());
            } else if (e.getCode() == KeyCode.LEFT) {
                snake.move(-snake.getWidth(), 0, board.getWidth());
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
