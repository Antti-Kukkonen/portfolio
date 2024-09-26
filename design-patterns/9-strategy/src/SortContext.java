public class SortContext {
    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void sort(int[] arr) {
        strategy.sort(arr);
    }

    public String getStrategy() {
        return strategy.getClass().getSimpleName();
    }
}
