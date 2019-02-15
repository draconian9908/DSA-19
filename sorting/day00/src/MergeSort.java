
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private InsertionSort insertsort = new InsertionSort();

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(1)
     * Worst-case runtime: O(N^2)
     * Average-case runtime:O(NlogN)
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        if (array.length <= INSERTION_THRESHOLD) {
            return insertsort.sort(array);
        }
        int[] left_sort = new int[array.length/2];
        int[] right_sort = new int[array.length-left_sort.length];
        System.arraycopy(array,0,left_sort,0,array.length/2);
        System.arraycopy(array,array.length/2,right_sort,0,array.length-left_sort.length);
        int[] left_sorted = sort(left_sort);
        int[] right_sorted = sort(right_sort);
        return merge(left_sorted,right_sorted);
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int[] merged = new int[a.length+b.length];
        int pos = 0;
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                merged[pos] = a[i];
                pos++;
                i++;
            }
            else if (b[j] < a[i]) {
                merged[pos] = b[j];
                pos++;
                j++;
            }
        }
        if (i >= a.length && j < b.length) {
            for (int k = j; k < b.length; k++) {
                merged[pos] = b[k];
                pos++;
            }
        }
        else if (j >= b.length && i < a.length) {
            for (int k = i; k < a.length; k++) {
                merged[pos] = a[k];
                pos++;
            }
        }
        return merged;
    }

}
