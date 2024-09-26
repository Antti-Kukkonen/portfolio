import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Custom wrapper around javafx.stage.Stage to keep track of the different clients' chat areas
public class Client extends javafx.stage.Stage {
    private final TextArea chatArea;

    // Constructor
    public Client(String user_name) {
        User user = Mediator.getInstance().addUser(user_name, this);
        Stage stage = new Stage();
        stage.setTitle("Chat window - " + user_name);
        VBox root = new VBox();
        chatArea = new TextArea(Mediator.getInstance().getMessages(user));
        chatArea.setEditable(false);

        TextField messageField = new TextField();
        TextField recipientField = new TextField();
        Label messageLabel = new Label("Message:");
        Label recipientLabel = new Label("Recipient:");

        Button sendButton = getSendButton(messageField, recipientField, user);

        root.getChildren().addAll(chatArea, recipientLabel, recipientField, messageLabel, messageField, sendButton);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private Button getSendButton(TextField messageField, TextField recipientField, User user) {
        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> {
            String message = messageField.getText();
            String recipient = recipientField.getText();
            try {
                Mediator.getInstance().sendMessage(message, user, recipient);
            } catch (IllegalArgumentException error) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error sending message: User not found");
                alert.showAndWait();
            }

            update(user.name(), recipient);
            messageField.clear();
            recipientField.clear();
        });
        return sendButton;
    }

    private void update(String sender, String recipient) {
        // Get the clients from the mediator for the sender and recipient
        Mediator mediator = Mediator.getInstance();
        try {
            Client senderUser = mediator.getUsers().get(mediator.getUser(sender));
            Client recipientUser = mediator.getUsers().get(mediator.getUser(recipient));

            // Update the chat area for the sender and recipient
            senderUser.chatArea.setText(mediator.getMessages(mediator.getUser(sender)));
            recipientUser.chatArea.setText(mediator.getMessages(mediator.getUser(recipient)));
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to update chat area: User not found");
        }
    }
}
