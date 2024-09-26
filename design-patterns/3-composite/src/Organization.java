import java.util.Arrays;

public class Organization extends Composite {

    public Organization(String name) {
        super(name);
    }

    @Override
    public void execute() {
        System.out.println("<organization>");
        System.out.println("\t<name>" + this.name + "</name>");
        Arrays.stream(this.getChildren()).forEach(Component::execute);
        System.out.println("</organization>");
    }
}
