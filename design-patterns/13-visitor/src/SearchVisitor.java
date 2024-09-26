import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SearchVisitor implements FileSystemVisitor {
    private final List<FileSystemElement> matches = new ArrayList<>();
    private final String search;

    public SearchVisitor(String search) {
        this.search = search;
    }
    @Override
    public void visit(Directory directory) {
        for (FileSystemElement child : directory.getChildren()) {
            child.accept(this);
        }
    }
    @Override
    public void visit(File file) {
        if (file.getName().contains(search)) {
            matches.add(file);
        }
    }

    public List<FileSystemElement> getMatches() {
        return matches;
    }

    public void printMatches() {
        matches.sort(Comparator.comparing(FileSystemElement::getName));
        for (FileSystemElement match : getMatches()) {
            System.out.println(match.getName());
        }
    }
}
