
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameView extends Application {
    private static final int TILE_SIZE = 32;
    // Change the boolean value of the GameController constructor to switch between city and wilderness maps
    private final GameController controller = new GameController(true);

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game");

        Canvas canvas = new Canvas(TILE_SIZE * controller.getGridWidth(), TILE_SIZE * controller.getGridHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (int i = 0; i < controller.getGridWidth(); i++) {
            for (int j = 0; j < controller.getGridHeight(); j++) {
                Image image = controller.getTileImage(i, j);
                gc.drawImage(image, i * TILE_SIZE, j * TILE_SIZE);
            }
        }

        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
