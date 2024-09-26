import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Mediator {
    private static Mediator instance;

    private final List<Message> messages = new ArrayList<>();
    private final Map<User, Client> users = new HashMap<>();

    private Mediator() {
    }

    public static Mediator getInstance() {
        if (instance == null) {
            instance = new Mediator();
        }
        return instance;
    }

    public void sendMessage(String message, User sender, String recipient_name) {
        User recipient = getUser(recipient_name);
        messages.add(new Message(message, sender, recipient));
    }

    public User addUser(String user, Client client) {
        User newUser = new User(user);
        users.put(newUser, client);
        return newUser;
    }

    public Map<User, Client> getUsers() {
        return users;
    }

    public User getUser(String name) {
        return users.keySet().stream()
                .filter(user -> user.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public String getMessages(User user) {
        StringBuilder sb = new StringBuilder();
        for (Message message : messages) {
            if (message.recipient().equals(user) || message.sender().equals(user)) {
                sb.append(message.sender()).append(": ").append(message.message()).append("\n");
            }
        }
        return sb.toString();
    }
}
