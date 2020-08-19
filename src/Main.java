import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    private final Group mainGroup = new Group();
    private final Group tailGroup = new Group();
    private final Pane layout = new Pane();

    @Override
    public void start(Stage stage) {
        // Creates the scene and organises groups.
        mainGroup.getChildren().addAll(layout, tailGroup);
        Scene scene = new Scene(mainGroup);
        stage.setTitle("The Snake Game");

        // Creates the board and snake objects.
        Game game = new Game(tailGroup);

        // Creates the game over text.
        Label endLabel = new Label("Game Over!");
        endLabel.setFont(new Font("Arial", 32));
        endLabel.setTextFill(Color.RED);
        endLabel.relocate(10, 10);
        endLabel.setVisible(false);
        game.setLabel(endLabel);

        // Creates the window.
        layout.setPrefSize(game.getWidth(), game.getWidth());
        layout.getChildren().addAll(game.getBoard(), game.getSnake().getShape(), game.getFood().getShape(), endLabel);

        // Responds to key presses.
        scene.setOnKeyPressed(e -> game.move(e.getCode()));

        stage.setScene(scene);
        stage.show();
    }

    // Launches the game.
    public static void main(String[] args) {
        launch(args);
    }
}
