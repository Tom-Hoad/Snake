import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Food {
    private final Rectangle food;
    private final int width;
    private int x;
    private int y;

    public Food(int width) {
        this.food = new Rectangle(width, width, Color.LIGHTGREEN);
        this.width = width;

        positionFood();
    }

    // Gets the food shape.
    public Rectangle getShape() {
        return food;
    }

    // Gets the x co-ord.
    public int getX() {
        return x;
    }

    // Gets the y co-ord.
    public int getY() {
        return y;
    }

    // Randomly positions food.
    public void positionFood() {
        Random rand = new Random();
        x = width * rand.nextInt(33);
        y = width * rand.nextInt(33);

        food.setX(x);
        food.setY(y);
    }
}
