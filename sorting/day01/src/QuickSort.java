import java.util.concurrent.ThreadLocalRandom;

public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }

    /**
     * TODO
     * Best-case runtime: O(nlogn)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        shuffleArray(array);
        quickSort(array,0,array.length-1);
        return array;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int lo, int hi) {
        if (a.length == 0 || a.length == 1) {// if array is negligible/already sorted, return
            return;
        }
        if (lo < 0 || lo >= a.length || hi < 0 || hi >= a.length) {// check if indices are legal
            return;
        }
        if (lo < hi) {
            int p = partition(a, lo, hi);// partition; initial sort
            quickSort(a,lo,p-1);// sort left subarray; recursive
            quickSort(a,p+1,hi);// sort right subarray; recursive
        }
    }


    /**
     * Given an array, choose the array[lo] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int lo, int hi) {
        int pivot = array[lo];
        int j = lo;
        int i = hi;
        while (!(i<=j) && i>=lo) {
            if (array[i] < pivot) {
                int temp = array[i];
                array[i] = array[j+1];
                swap(array,j,j+1);
                array[j] = temp;
                j++;
                continue;
            }
            i--;
        }
        return j;
    }

}
