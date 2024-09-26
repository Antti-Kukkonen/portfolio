public class BogoSort implements SortStrategy {
    @Override
    public void sort(int[] arr) {
        while (!isSorted(arr)) {
            shuffle(arr);
        }
    }

    private boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int randomIndex = (int) (Math.random() * arr.length);
            swap(arr, i, randomIndex);
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
