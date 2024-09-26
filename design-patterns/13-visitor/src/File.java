public class File implements FileSystemElement {
    private final String name;
    private final byte[] content;
    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }

    public File(String name, byte[] content) {
        this.name = name;
        this.content = content;

    }

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }

    public int getSize() {
        return content.length;
    }
}
