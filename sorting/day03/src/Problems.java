import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] = A[i]+100;
        }
        int k = getMax(A);
        int w = (int) Math.ceil(Math.log(Math.abs(k)) / Math.log(10)); // w = log base b of k, word length of numbers
        for (int i = 0; i < w; i++) {
            countingSortByDigit(A,i);
        }
        for (int i = 0; i < A.length; i++) {
            A[i] = A[i]-100;
        }
    }

    private static int getNthDigit(int number, int n) {
        return number / ((int) Math.pow(10, n)) % 10;
    }

    private static void countingSortByDigit(int[] A, int n) {
        LinkedList<Integer>[] L = new LinkedList[10];
        for (int i = 0; i < 10; i++)
            L[i] = new LinkedList<>();
        for (int i : A) {
            int d = getNthDigit(i,n);
            L[d].add(i);
        }
        int j = 0; // index in A to place numbers
        for (LinkedList<Integer> list : L) {
            while (!list.isEmpty()) {
                A[j] = list.poll();
                j++;
            }
        }
    }

    private static int getMax(int[] a) {
        int k = a[0] + 1;
        for (int el : a) {
            k = (el+1 > k) ? el : k;
        }
        return k;
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        LinkedList<String>[] L = new LinkedList[26];
        for (int i = 0; i < 26; i++) {
            L[i] = new LinkedList<>();
        }
        for (String s : A) {
            int d = getNthCharacter(s,n);
            L[d].add(s);
        }
        int j = 0; // index in A to place strings
        for (LinkedList<String> list : L) {
            while (!list.isEmpty()) {
                A[j] = list.poll();
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        if (stringLength == 0 || stringLength == 1) {
            return;
        }
        for (int i = 0; i < stringLength; i++) {
            countingSortByCharacter(S,i);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

}