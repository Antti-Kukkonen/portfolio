import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemElement {

    private final String name;
    private final List<FileSystemElement> children = new ArrayList<>();

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }

    public Directory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addChild(FileSystemElement child) {
        children.add(child);
    }

    public List<FileSystemElement> getChildren() {
        return children;
    }
}
