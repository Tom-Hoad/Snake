import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SnakeGame extends Application {
    private final Pane layout = new Pane();

    @Override
    public void start(Stage stage) {
        stage.setTitle("The Snake Game");

        // Creates the board and snake objects.
        Game game = new Game();

        // Creates the window.
        layout.setPrefSize(game.getBoard().getWidth(), game.getBoard().getWidth());
        layout.getChildren().addAll(game.getBoard().getBoard(), game.getSnake().getSnake());
        Scene scene = new Scene(layout, game.getSnake().getWidth(), game.getSnake().getWidth());

        // Changes the snakes direction based on a key press.
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                game.getSnake().move(0, -game.getSnake().getWidth(), game.getBoard().getWidth());
            } else if (e.getCode() == KeyCode.DOWN) {
                game.getSnake().move(0, game.getSnake().getWidth(), game.getBoard().getWidth());
            } else if (e.getCode() == KeyCode.RIGHT) {
                game.getSnake().move(game.getSnake().getWidth(), 0, game.getBoard().getWidth());
            } else if (e.getCode() == KeyCode.LEFT) {
                game.getSnake().move(-game.getSnake().getWidth(), 0, game.getBoard().getWidth());
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
