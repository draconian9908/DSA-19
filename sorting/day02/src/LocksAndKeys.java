import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class LocksAndKeys {

    // Given, much thanks
    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    // Taken from the QuickSort HW, much thanks
    private static void shuffleArray(char[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }

    // main, important function
    static char[][] locksAndKeys(char[] locks, char[] keys) {
        char[][] result = new char[2][];
        if (locks.length == 0 || locks.length == 1) {
            result[0] = locks;
            result[1] = keys;
            return result;
        }
        sort(keys,locks);
        result[0] = locks;
        result[1] = keys;
        return result;
    }

    // made to make the keys match the current order of the locks
    private static void reOrder(char[] a, char[] b) {
        HashMap<Character, Integer> mapping = new HashMap<>();
        for (int i = 0; i < b.length; i++) {
            mapping.put(b[i],i);
        }
        for (int i = 0; i < a.length; i++) {
            int temp = mapping.get(a[i]);
            swap(a,i,temp);
        }
        for (int i = 0; i < a.length; i++) {
            int temp = mapping.get(a[i]);
            swap(a,i,temp);
        }
    }

    // Everything from here down is adapted from my QuickSort code
    private static void sort(char[] checker, char[] checkee) {
        reOrder(checker,checkee);
        quickSort(checker, checkee, 0, checker.length-1);
    }

    public static void quickSort(char[] a, char[] b, int lo, int hi) {
        if (a.length == 0 || a.length == 1) {
            return;
        }
        if (lo < 0 || lo >= a.length || hi < 0 || hi >= a.length) {
            return;
        }
        if (lo<hi) {
            // sort the locks based on the keys
            int p = partition(a, b, lo, hi);

            // sort the keys based on the locks, using a hashmap
            reOrder(a, b);

            // recursively sort locks with updated keys
            quickSort(a,b,lo,p-1);
            quickSort(a,b,p+1,hi);
        }
    }

    public static int partition(char[] checker, char[] checkee, int lo, int hi) {
        char pivot = checker[lo];
        int j = lo;
        int i = hi;
        while (!(i<=j) && i>=lo) {
            if (checkee[i] < pivot) {
                swap(checkee,i,j+1);
                swap(checkee,j,j+1);
                j++;
                continue;
            }
            i--;
        }
        return j;
    }
}




