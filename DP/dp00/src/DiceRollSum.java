import java.util.HashMap;

public class DiceRollSum {

    // Runtime: O(n)
    // Space: O(n)
    public static int diceRollSum(int N) {
        HashMap<Integer, Integer> memo = new HashMap<>();
        return rec(N, memo);
    }

    private static int rec(int n, HashMap<Integer, Integer> memo) {
        int sum = 0;
        // base cases
        if (n < 0) return 0;
        if (n == 0) return 1;
        //have we already solved this subproblem
        else if (memo.containsKey(n)) return memo.get(n);
        // Guess: how many sequences sum to six previous amounts? (six because 1-6 values per side of die)
        else {// DP[i] = rec(i-1) + rec(i-2) + ... + rec(i-6)
            for (int i = 1; i <= 6; i++) {
                sum += rec(n-i, memo);// Recurse
            }
            memo.put(n, sum);// Memoize
        }
        return sum;// Solve/return answer
    }
}