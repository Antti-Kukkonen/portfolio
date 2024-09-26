public class SizeCalculatorVisitor implements FileSystemVisitor {
    private int size;

    @Override
    public void visit(Directory directory) {
        for (FileSystemElement child : directory.getChildren()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(File file) {
        size += file.getSize();
    }

    public int getSize() {
        return size;
    }

    public void printSize() {
        System.out.println(getSize());
    }
}
