import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        HashMap<List<Integer>, Integer> dp = new HashMap<>();
        return rec(0, 0, map, dp);// int h =
//        return Math.abs(h) + 1;
    }

    public static int rec(int i, int j, int[][] map, HashMap<List<Integer>, Integer> dp) {
        int h;
        List<Integer> temp = new ArrayList<>();
        temp.add(i);
        temp.add(j);
        if (i == map.length-1 && j == map[0].length-1) {// check if at end of map
            return -map[map.length-1][map[0].length-1] + 1;
        }
        else if (dp.containsKey(temp)) {// check if memoized
            return dp.get(temp);
        }
        else if (i == map.length-1) {// at bottom of map
            int choice = rec(i, j+1, map, dp);
            h = Math.max(-map[i][j] + choice, 1);// take positive health rooms into account
            dp.put(temp, h);
        }
        else if (j == map[0].length-1) {// at right edge of map
            int choice = rec(i+1, j, map, dp);
            h = Math.max(-map[i][j] + choice, 1);
            dp.put(temp, h);
        }
        else {
            int down = rec(i+1, j, map, dp);
            int right = rec(i, j+1, map, dp);
            int choice = Math.min(down, right);
            h = Math.max(-map[i][j] + choice, 1);
            dp.put(temp, h);
        }
        return h;
    }
}

//    int[][][] dp = new int[map.length+1][map[0].length+1][2];
//    int[] prev;
//        if (map[0][0] <= 0) {
//                dp[0][0][0] = map[0][0];
//                dp[0][0][1] = 0;
//                }
//                else {
//                dp[0][0][0] = map[0][0];
//                dp[0][0][1] = map[0][0];
//                }
//                for (int i = 0; i <= map.length; i++) {
//                prev = dp[i-1][0];
//                if (map[i][0] <= 0) {
//                int cost = prev[1] - (map[i][0] + prev[0]);
//                dp[i][0][0] = cost;
//                if (cost <= 0) {
//                dp[i][0][1] = 0;
//                }
//                else dp[i][0][1] = prev[1] - cost;
//                }
//                else {
//                dp[0][0][0] = map[0][0];
//                dp[0][0][1] = map[0][0];
//                }
//                }
//                dp[0][0] = map[0][0];
//                int h = 0;
//                int down,right;
//                for (int i = 0; i <= map.length; i++) {
//                for (int j = 0; j <= map.length; j++) {
//                if (i==0) {
//                dp[i][j] = dp[i][j-1] + map[i][j];// first row
//                }
//                else if (j==0) dp[i][j] = dp[i-1][j] + map[i][j];// first column
//                else if (map[j][j] == 0) {
//                down = dp[i][j-1];
//                right = dp[i-1][j];
//                dp[i][j] = Math.min(down, right);
//                }
//                else if (map[i][j] > 0) {
//                h += map[i][j];
//                down = dp[i][j-1];
//                right = dp[i-1][j];
//                dp[i][j] = Math.min(down, right);
//                }
//                else if (map[i][j] < 0) {
//        down = dp[i][j-1];
//        right = dp[i-1][j];
//        int move = h - (map[i][j] + Math.min(down, right));
//        h = move;
//        if (h < 0) h = 0;
//        dp[i][j] = move;
//        }
//        }
//        }
//        return dp[map.length][map[0].length];
