public class Employee extends Composite {
    protected double salary;

    public Employee(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    @Override
    public void execute() {
        System.out.println("\t\t<employee>");
        System.out.println("\t\t\t<name>" + this.name + "</name>");
        System.out.println("\t\t\t<salary>" + this.salary + "</salary>");
        System.out.println("\t\t</employee>");
    }

    @Override
    public void add(Composite composite) {
        throw new UnsupportedOperationException("This node does not support children.");
    }

    @Override
    public void remove(Composite composite) {
        throw new UnsupportedOperationException("This node does not support children.");
    }

    @Override
    public Composite[] getChildren() {
        throw new UnsupportedOperationException("This node does not support children.");
    }
}
