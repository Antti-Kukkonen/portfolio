public interface FileSystemElement {
    void accept(FileSystemVisitor visitor);
    String getName();
}
