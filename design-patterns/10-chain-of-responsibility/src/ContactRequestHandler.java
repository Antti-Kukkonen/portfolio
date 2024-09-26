public class ContactRequestHandler extends MessageHandler {
    @Override
    public void handleRequest(Message message) {
        if (message.getType() == MessageType.CONTACT_REQUEST) {
            System.out.println("Contact request has been forwarded to the appropriate department.");
        } else {
            super.handleRequest(message);
        }
    }
}
