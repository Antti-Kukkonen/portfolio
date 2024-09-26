import java.util.Arrays;

public class Department extends Composite {
    public Department(String name) {
        super(name);
    }

    @Override
    public void execute() {
        System.out.println("\t<department>");
        System.out.println("\t\t<name>" + this.name + "</name>");
        Arrays.stream(this.getChildren()).forEach(Component::execute);
        System.out.println("\t</department>");
    }
}
