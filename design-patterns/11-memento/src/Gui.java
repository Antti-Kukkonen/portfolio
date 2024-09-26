import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;


public class Gui extends Application {

    // create a GUI with three adjacent ColorBoxes and one CheckBox below them
    Controller controller;
    ColorBox colorBox1;
    ColorBox colorBox2;
    ColorBox colorBox3;
    CheckBox checkBox;
    ListView<String> listView = new ListView<>();

    public void start(Stage stage) {

        controller = new Controller(this);

        // Insets for margin and padding
        Insets insets = new Insets(10, 10, 10, 10);

        // Create three ColorBoxes
        colorBox1 = new ColorBox(1, controller);
        colorBox2 = new ColorBox(2, controller);
        colorBox3 = new ColorBox(3, controller);

        // Create a CheckBox
        checkBox = new CheckBox("Click me!");
        checkBox.setPadding(insets);

        // Add the ColorBoxes and CheckBox to a HBox
        HBox hBox = new HBox(colorBox1.getRectangle(), colorBox2.getRectangle(), colorBox3.getRectangle());
        hBox.setSpacing(10);

        hBox.setMargin(colorBox1.getRectangle(), insets);
        hBox.setMargin(colorBox2.getRectangle(), insets);
        hBox.setMargin(colorBox3.getRectangle(), insets);


        Label label = new Label("Press Ctrl-Z to undo the last change.");
        Label label2 = new Label("Press Ctrl-Y to redo the last change.");
        label.setPadding(insets);
        label2.setPadding(insets);

        // create a VBox that contains the HBox and the CheckBox
        VBox vBox = new VBox(hBox, checkBox, label, label2);
        // call controller when the CheckBox is clicked
        checkBox.setOnAction(event -> {
            controller.setIsSelected(checkBox.isSelected());
        });

        // update the history list when the scene is clicked
        vBox.setOnMouseClicked(event -> {
            listView.setItems(FXCollections.observableArrayList(controller.getHistory()));
        });

        // Set the HBox to be the root of the Scene
        Scene scene = new Scene(vBox);
        scene.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.Z) {
                // Ctrl-Z: undo
                System.out.println("Undo key combination pressed");
                controller.undo();
            }
            if (event.isControlDown() && event.getCode() == KeyCode.Y) {
                // Ctrl-Y: redo
                System.out.println("Redo key combination pressed");
                controller.redo();
            }
            listView.setItems(FXCollections.observableArrayList(controller.getHistory()));
        });

        // Open a new window for the history list
        listView.setItems(FXCollections.observableArrayList(controller.getHistory()));
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(listView);
        Scene secondScene = new Scene(secondaryLayout, 230, 800);
        Stage newWindow = new Stage();
        newWindow.setTitle("History");
        newWindow.setScene(secondScene);
        newWindow.show();

        // Clicking on the history items will restore the state
        listView.setOnMouseClicked(event -> {
            int index = listView.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                controller.restoreState(index);
            }
        });

        stage.setScene(scene);
        stage.setTitle("Memento Pattern Example");
        stage.show();
    }

    public void updateGui() {
        // called after restoring state from a Memento
        colorBox1.setColor(controller.getOption(1));
        colorBox2.setColor(controller.getOption(2));
        colorBox3.setColor(controller.getOption(3));
        checkBox.setSelected(controller.getIsSelected());
        listView.setItems(FXCollections.observableArrayList(controller.getHistory()));
    }
}
