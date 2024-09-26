public class Main {
    private static final SortContext strategy = new SortContext();
    public static void main(String[] args) {
        // Bubble Sort is okay for small data sets, but it's not practical for large data sets.
        strategy.setStrategy(new BubbleSort());
        int[] test_set_a = generateRandomArray(2000);
        int[] test_set_b = generateRandomArray(50_000);

        testSort(test_set_a);
        testSort(test_set_b);

        // Quick Sort is a great choice for large and small data sets alike.
        strategy.setStrategy(new QuickSort());
        test_set_a = generateRandomArray(2000);
        test_set_b = generateRandomArray(500_000);

        testSort(test_set_a);
        testSort(test_set_b);

        // Bogo Sort is not practical for any data set.
        // The chance that it will sort any data set is the factorial of the size of the data set.
        // For an array of size 10, the chance is 10! = 3,628,800.
        // For an array of size 100, the chance is 100! = ~9.3 x 10^157.
        // We might be here for a while...
        strategy.setStrategy(new BogoSort());
        test_set_a = generateRandomArray(10);
        test_set_b = generateRandomArray(100);

        testSort(test_set_a);
        testSort(test_set_b);
    }


    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        return arr;
    }

    private static void testSort(int[] arr) {
        long start = System.currentTimeMillis();
        strategy.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println(strategy.getStrategy() + " took " + (end - start) + "ms to sort the array of size " + arr.length + ".");
    }
}
