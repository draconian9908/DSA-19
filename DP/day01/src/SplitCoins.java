public class SplitCoins {

    public static int splitCoins(int[] coins) {
        int difference = 0;
        int sum = 0;
        for (int c : coins) sum += c;
        boolean[][] memo = new boolean[coins.length+1][sum+1];
        for (int i = 0; i <= coins.length; i++) {// first column
            memo[i][0] = true;
        }
        for (int j = 1; j <= sum; j++) {// first row
            memo[0][j] = false;
        }

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= sum; j++) {
                memo[i][j] = memo[i - 1][j];// true if can make current sum without current coin,
                if (coins[i - 1] <= j) {// true if can make current sum with coin
                    memo[i][j] |= memo[i - 1][j - coins[i - 1]];
                }
            }
        }

        for (int j = sum/2; j >= 0; j--) {
            if (memo[coins.length][j]) {
                difference = sum - 2*j;// find difference
                break;
            }
        }
        return difference;
    }
}
