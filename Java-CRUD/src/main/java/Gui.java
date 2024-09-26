import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.bson.Document;

import java.util.Objects;


public class Gui extends Application {
    private final Controller controller = new Controller();
    private final String css = Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm();

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();

        Label idLabel = new Label("ID:");
        Label nameLabel = new Label("Name:");
        Label ageLabel = new Label("Age:");
        Label cityLabel = new Label("City:");

        TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField ageField = new TextField();
        TextField cityField = new TextField();

        Button addButton = new Button("Add");
        Button readButton = new Button("Read");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        root.add(idLabel, 0, 0);
        root.add(nameLabel, 0, 1);
        root.add(ageLabel, 0, 2);
        root.add(cityLabel, 0, 3);

        root.add(idField, 1, 0);
        root.add(nameField, 1, 1);
        root.add(ageField, 1, 2);
        root.add(cityField, 1, 3);

        root.add(addButton, 0, 4);
        root.add(readButton, 1, 4);
        root.add(updateButton, 0, 5);
        root.add(deleteButton, 1, 5);

        // Filter out non-numeric characters from the age field
        ageField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                ageField.setText(newValue.replaceAll("\\D", ""));
            }
        });

        // Add a document
        addButton.setOnAction(event -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText().isEmpty() ? "-1" : ageField.getText());
            String city = cityField.getText();
            if (name.isEmpty() || city.isEmpty() || age <= 0) {
                createWindow("Error", "Please enter a name, age and city");
                return;
            }
            boolean successful = controller.createDocument(name, age, city);

            if (successful) {
                createWindow("Success", "Document added successfully");
            } else {
                createWindow("Error", "Failed to add document");
            }
        });

        // Read a document by ID or name
        readButton.setOnAction(event -> {
            String id = idField.getText();
            String name = nameField.getText();
            Document document;


            if (!id.isEmpty()) {
                document = controller.readDocumentById(id);
            } else if (!name.isEmpty()) {
                document = controller.readDocumentByName(name);
            } else {
                createWindow("Error", "Please enter an ID or name");
                return;
            }

            if (document != null) {
                nameField.setText(document.getString("name"));
                ageField.setText(String.valueOf(document.getInteger("age")));
                cityField.setText(document.getString("city"));
                createWindow("Success", "Document found: " + document.toJson());
            } else {
                createWindow("Error", "Document not found");
            }
        });

        // Update a document by ID or name
        updateButton.setOnAction(event -> {
            String id = idField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String city = cityField.getText();
            boolean successful;

            if (!id.isEmpty()) {
                successful = controller.updateDocument(id, name, age, city);
            } else if (!name.isEmpty()) {
                successful = controller.updateDocument(name, age, city);
            } else {
                createWindow("Error", "Please enter an ID or name");
                return;
            }

            if (successful) {
                createWindow("Success", "Document updated successfully");
            } else {
                createWindow("Error", "Failed to update document");
            }
        });

        // Delete a document by ID or name
        deleteButton.setOnAction(event -> {
            String id = idField.getText();
            String name = nameField.getText();
            boolean successful;

            if (!id.isEmpty()) {
                successful = controller.deleteDocumentById(id);
            } else if (!name.isEmpty()) {
                successful = controller.deleteDocumentByName(name);
            } else {
                createWindow("Error", "Please enter an ID or name");
                return;
            }

            if (successful) {
                createWindow("Success", "Document deleted successfully");
            } else {
                createWindow("Error", "Failed to delete document");
            }
        });

        Scene scene = new Scene(root, 300, 150);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("MongoDB CRUD");
        stage.show();
    }

    public void createWindow(String title, String message) {
        Stage stage = new Stage();
        stage.setTitle(title);
        VBox root = new VBox();
        root.getStyleClass().add("notice-root");

        Label label = new Label(message);
        label.getStyleClass().add("notice-label");

        Button button = new Button("Close");
        button.getStyleClass().add("notice-button");
        button.setOnAction(event -> stage.close());

        root.getChildren().addAll(label, button);

        // Dynamically adjust the window width based on the message length
        Scene scene = new Scene(root, computeTextWidth(message, label.getFont()) + 132, 100);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    private double computeTextWidth(String text, javafx.scene.text.Font font) {
        javafx.scene.text.Text tempText = new javafx.scene.text.Text(text);
        tempText.setFont(font);
        return tempText.getLayoutBounds().getWidth();
    }
}
