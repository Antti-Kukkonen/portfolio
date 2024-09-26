import java.util.ArrayList;
import java.util.List;

public class Computer {
    private final List<Component> components = new ArrayList<>();

    public void addComponent(Component component) {
        components.add(component);
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Computer components:\n");
        for (Component component : components) {
            result.append(component.getName()).append("\n");
        }
        return result.toString();
    }
}
