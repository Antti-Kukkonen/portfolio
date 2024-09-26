public class GeneralFeedbackHandler extends MessageHandler {
    @Override
    public void handleRequest(Message message) {
        if (message.getType() == MessageType.GENERAL_FEEDBACK) {
            System.out.println("Thank you for your feedback! We will take it into account and come back to you soon.");
        } else {
            super.handleRequest(message);
        }
    }
}
