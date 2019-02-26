
public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: O(n+k)
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int k = 0;
        for (int i = 0; i < A.length; i++) {
            if (i == 0) {
                k = A[i];
            }
            else if (A[i] > k) {
                k = A[i];
            }
        }

        int[] counts = new int[k+1];
        for (int el : A) {
            counts[el]++;
        }
        int j = 0;
        for (int i = 0; i < k+1; i++) {
            while (counts[i] > 0) {
                A[j] = i;
                j++;
                counts[i]--;
            }
        }
    }

}
