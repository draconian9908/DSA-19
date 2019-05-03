public class EditDistance {

    // Bottom-Up Approach
    public static int minEditDist(String a, String b) {
        //base cases

        // if either string empty, cost equals length of non-empty string
        if (a.isEmpty() && !b.isEmpty()) return b.length();
        if (b.isEmpty() && !a.isEmpty()) return a.length();
        // if strings equal, cost is 0
        if (a.equals(b)) return 0;

        // otherwise, dp
        int[][] dp = new int[a.length()+1][b.length()+1];
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i==0) dp[i][j] = j;
                else if (j==0) dp[i][j] = i;
                else if (a.charAt(i-1) == b.charAt(j-1)) dp[i][j] = dp[i-1][j-1];// if prev chars same, ignore and move on; no cost
                else {// if prev chars differ, which operation costs less (insert, delete, replace), add 1 because you're performing the change
                    int insert = dp[i][j-1];
                    int delete = dp[i-1][j];
                    int replace = dp[i-1][j-1];
                    dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        // return final answer, cost determined when you reached the end of the strings
        return dp[a.length()][b.length()];
    }
}