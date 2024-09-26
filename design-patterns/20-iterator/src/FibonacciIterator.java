public class FibonacciIterator implements Sequence {

    private int n;

    public FibonacciIterator() {
        n = 0;
    }

    public FibonacciIterator(int start) {
        this.n = start;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        return FibonacciSequence.get(n++);
    }
}
