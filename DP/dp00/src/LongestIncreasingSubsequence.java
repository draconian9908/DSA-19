import java.util.Arrays;

public class LongestIncreasingSubsequence {

    // Runtime: O(n^2)
    // Space: O(n)
    public static int LIS(int[] A) {
        // "base cases"
        if (A.length == 0) return 0;
        if (A.length == 1) return 1;
        int[] memo = new int[A.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = 1;// everything is part of a subsequence of at least length 1 (itself)
        }
        for (int idx = 1; idx < memo.length; idx++) {// first one can only be part of subsequence of len 1, so start at second one
            for (int prev = 0; prev < idx; prev++) {// check against all numbers before it in sequence
                if (A[idx] > A[prev] && memo[idx] < memo[prev]+1) {// if increasing, and length of new subsequence longer than current one...
                    memo[idx] = memo[prev] + 1;// save it as the new max subsequence as of this index
                }
            }
        }
        return Arrays.stream(memo).max().getAsInt();
    }
}