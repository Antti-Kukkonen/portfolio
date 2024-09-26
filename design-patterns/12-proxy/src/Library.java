import java.util.HashMap;

public class Library {
    private HashMap<Integer, DocumentInterface> documents = new HashMap<>();
    private AccessControlService accessControlService = AccessControlService.getInstance();

    public void addDocument(Document document) {
        documents.put(document.getId(), document);
        documents.put(document.getId(), new DocumentProxy(document));
    }

    public DocumentInterface getDocument(User user, int documentId) throws AccessDeniedException {
        if (accessControlService.checkPermission(user, documents.get(documentId))) {
            System.out.println("User " + user.getUsername() + " accessed document " + documents.get(documentId).getId());
            return documents.get(documentId);
        }
        throw new AccessDeniedException("Access denied");
    }
}
