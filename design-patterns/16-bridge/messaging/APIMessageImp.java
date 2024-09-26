package bridge.messaging;

public class APIMessageImp implements MessageImp {
    @Override
    public void sendMessage(String message) {
        // Assume some code here to interact with the messaging API
        // This could involve HTTP requests, authentication, etc.
        // For demonstration purposes, let's just print the message
        System.out.println("Sending a request to *** with the message: " + message);
    }
}


