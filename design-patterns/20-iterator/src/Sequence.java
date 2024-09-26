import java.util.Iterator;

public interface Sequence extends Iterator<Integer> {
    boolean hasNext();
    Integer next();
}
