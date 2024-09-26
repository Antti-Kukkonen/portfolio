import java.util.HashMap;

public class AccessControlService {
    private final HashMap<User, Integer> userPermission = new HashMap<>();

    private static AccessControlService instance = null;

    private AccessControlService() {
    }

    public static AccessControlService getInstance() {
        if (instance == null) {
            instance = new AccessControlService();
        }
        return instance;
    }
    public boolean checkPermission(User user, DocumentInterface document) {
        return userPermission.getOrDefault(user, Integer.MIN_VALUE) == document.getId();
    }

    public void giveAccess(User user, int documentId) {
        userPermission.put(user, documentId);
    }

    public void giveAccess(User user, DocumentInterface document) {
        giveAccess(user, document.getId());
    }
}
