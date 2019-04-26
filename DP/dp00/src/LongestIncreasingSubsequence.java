import java.util.Arrays;
import java.util.HashMap;

public class LongestIncreasingSubsequence {

    // Runtime: TODO
    // Space: TODO
    public static int LIS(int[] A) {
        HashMap<int[], Integer> memo = new HashMap<>();
//        int[] memo = new int[A.length];
//        memo[0] = 1;
        return rec(A, memo);
    }

    private static int rec(int[] a, HashMap<int[], Integer> memo) {
//        for (int i = 1; i < a.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if (a[j] < a[i] && memo[j] > memo[i]) {// a: element greater than prev element, memo: subproblem LIS greater than current LIS
//                    memo[i] = memo[j] + 1;
//                }
//            }
//        }
//        return Arrays.stream(memo).max().getAsInt();

//        int[] lens = new int[a.length];
//        if (a.length == 0) return 0;
//        else if (a.length == 1) return 1;
//        else if (memo.containsKey(a)) return memo.get(a);
//        else {
//            int idx = 0;
//            for (int i = 2; i <= a.length; i++) {
//                if (a[a.length-1] > a[a.length-i]) {
//                    int[] b = Arrays.copyOf(a, a.length - i);
//                    lens[idx] = rec(b, memo);
//                    memo.put(a, lens[idx]);
//                    idx++;
//                }
//            }
//        }
//        return Arrays.stream(lens).max().getAsInt();

        int len = 1;
        if (a.length == 0) return 0;
        else if (a.length == 1) return 1;
//        else {
//            int j = 2;
//            while (a[a.length - 1] <= a[a.length - j] && j < a.length-1) {
//                j++;
//            }
//            int[] b = Arrays.copyOf(a, a.length-j);
//            len += rec(b, memo);
//            memo.put(a, len);
//        }
        else {
            if (a[a.length-1] > a[a.length-2]) {
                int[] b = Arrays.copyOf(a, a.length - 1);
                len += rec(b, memo);
                memo.put(a, len);
            }
        }
        return len;
    }
}