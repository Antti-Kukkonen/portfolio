import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Gui extends Application {
    private final Controller controller = new Controller();
    private GraphicsContext gc;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pixel Art");
        VBox root = new VBox();
        Scene scene = new Scene(root);
        stage.setResizable(false);

        Canvas canvas = new Canvas((controller.MAX_X + 1) * 100, (controller.MAX_Y + 1) * 100);

        // Draw grid
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x <= controller.MAX_X; x++) {
            for (int y = 0; y <= controller.MAX_Y; y++) {
                drawPixel(x, y);
                drawPixel(x, y);
            }
        }
        highlightPixel(controller.x, controller.y);

        scene.setOnKeyPressed(e -> {
            redrawNeighbours(controller.x, controller.y);
            switch (e.getCode()) {
                case UP:
                    new MoveCursorUpCommand(controller).execute();
                    break;
                case DOWN:
                    new MoveCursorDownCommand(controller).execute();
                    break;
                case LEFT:
                    new MoveCursorLeftCommand(controller).execute();
                    break;
                case RIGHT:
                    new MoveCursorRightCommand(controller).execute();
                    break;
                case SPACE:
                    new TogglePixelCommand(controller).execute();
                    break;
            }
            drawPixel(controller.x, controller.y);
            highlightPixel(controller.x, controller.y);
        });

        Button generateCodeButton = new Button("Generate Code");
        generateCodeButton.setOnAction(e -> new GenerateCodeCommand(controller).execute());
        generateCodeButton.setFocusTraversable(false);

        root.getChildren().addAll(canvas, generateCodeButton);
        stage.setScene(scene);
        stage.show();
    }

    private void drawPixel(int x, int y) {
        gc.setFill(controller.getPixels()[x][y].isOn() ? Color.web("#444") : Color.web("#ccc"));
        gc.fillRect(x * 100, y * 100, 99, 99);

    }

    private void highlightPixel(int x, int y) {
        gc.setFill(controller.getPixels()[x][y].isOn() ? Color.web("#333") : Color.web("#aaa"));
        gc.fillRect(x * 100, y * 100, 99, 99);

    }

    private void redrawNeighbours(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x + i >= controller.MIN_X && x + i <= controller.MAX_X && y + j >= controller.MIN_Y && y + j <= controller.MAX_Y) {
                    drawPixel(x + i, y + j);
                }
            }
        }
    }
}
