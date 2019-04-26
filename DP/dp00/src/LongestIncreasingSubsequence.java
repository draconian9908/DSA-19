import java.util.Arrays;
import java.util.HashMap;

public class LongestIncreasingSubsequence {

    // Runtime: TODO
    // Space: TODO
    public static int LIS(int[] A) {
        if (A.length == 0) return 0;
        if (A.length == 1) return 1;
        int[] memo = new int[A.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = 1;
        }
        memo[0] = 1;
        for (int idx = 1; idx < memo.length; idx++) {
            for (int prev = 0; prev < idx; prev++) {
                if (A[idx] > A[prev] && memo[idx] < memo[prev]+1) {
                    memo[idx] = memo[prev] + 1;
                }
            }
        }
        return Arrays.stream(memo).max().getAsInt();
    }
}