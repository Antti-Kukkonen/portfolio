public class DevelopmentSuggestionHandler extends MessageHandler {
    @Override
    public void handleRequest(Message message) {
        if (message.getType() == MessageType.DEVELOPMENT_SUGGESTION) {
            System.out.println("Your development suggestion has logged and will be ignored.");
        } else {
            super.handleRequest(message);
        }
    }
}
