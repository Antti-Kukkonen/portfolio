public class MessageHandler {
    private MessageHandler nextHandler;
    public void setNextHandler(MessageHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(Message message) {
        if (nextHandler != null) {
            nextHandler.handleRequest(message);
        }
    }
}
