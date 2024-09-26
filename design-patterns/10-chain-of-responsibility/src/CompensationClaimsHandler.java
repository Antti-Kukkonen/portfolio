public class CompensationClaimsHandler extends MessageHandler {
    @Override
    public void handleRequest(Message message) {
        if (message.getType() == MessageType.COMPENSATION_CLAIM) {
            System.out.println("Compensation claim received and sent for review.");
        } else {
            super.handleRequest(message);
        }
    }
}
