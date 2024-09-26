import javafx.application.Application;
import javafx.stage.Stage;


public class Gui extends Application {


    @Override
    public void start(Stage stage) {
        new Client("Alice");
        new Client("Bob");
        new Client("Charlie");
    }
}
