public class BubbleSort implements SortStrategy {
    // Implementation (slightly modified) from https://www.geeksforgeeks.org/bubble-sort/

    // An optimized version of Bubble Sort
    public void sort(int[] arr) {
        int i, j;
        boolean swapped;
        for (i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {

                    // Swap arr[j] and arr[j+1]
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (!swapped)
                break;
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
