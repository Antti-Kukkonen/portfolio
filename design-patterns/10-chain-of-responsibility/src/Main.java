public class Main {
    public static void main(String[] args) {
        MessageHandler compensation = new CompensationClaimsHandler();
        MessageHandler contact = new ContactRequestHandler();
        MessageHandler development = new DevelopmentSuggestionHandler();
        MessageHandler feedback = new GeneralFeedbackHandler();

        compensation.setNextHandler(contact);
        contact.setNextHandler(development);
        development.setNextHandler(feedback);

        Message message = new Message(MessageType.COMPENSATION_CLAIM, "I am not satisfied with the service. giv money$$", "jeff@amazon.com");
        compensation.handleRequest(message);

        message = new Message(MessageType.GENERAL_FEEDBACK, "I think you should delete your website", "john.mann@hotmail.com");
        compensation.handleRequest(message);
    }
}
