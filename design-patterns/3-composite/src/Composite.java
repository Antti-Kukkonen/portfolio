import java.util.ArrayList;

public abstract class Composite implements Component {
    protected String name;
    protected ArrayList<Composite> children = new ArrayList<>();

    public Composite(String name) {
        this.name = name;
    }

    public void add(Composite composite) {
        this.children.add(composite);
    }


    public void remove(Composite composite) {
        this.children.remove(composite);
    }

    public Composite[] getChildren() {
        return this.children.toArray(new Composite[0]);
    }
}
