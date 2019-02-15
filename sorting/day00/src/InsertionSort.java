
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * TODO
     * Best-case runtime: O(N)
     * Worst-case runtime: O(N^2)
     * Average-case runtime:
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        if (array.length == 1 || array.length == 0) {
            return array;
        }
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (array[j] < array[j-1]) {
                if (j == 1) {
                    swap(array, j, j-1);
                    break;
                }
                swap(array, j, j-1);
                j--;
            }
        }
        return array;
    }
}