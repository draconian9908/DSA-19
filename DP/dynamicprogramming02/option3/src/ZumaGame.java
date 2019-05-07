import java.util.ArrayList;
import java.util.HashMap;

public class ZumaGame {

    public static int zumaGame(char[] board, char[] hand) {
        ArrayList<Character> boardList = new ArrayList<>(board.length);
        char[] possible = new char[]{'g','w','r','b','y'};
        for (char c : board) {
            boardList.add(c);
        }
        HashMap<Character, Integer> handMap = new HashMap<>();
        for (char c : possible) {
            handMap.put(c, 0);
        }
        for (char c : hand) {
            int temp = handMap.get(c);
            handMap.put(c, temp+1);
        }
        return solver(boardList, handMap);
    }

    private static int solver(ArrayList<Character> board, HashMap<Character, Integer> hand) {

        // Clean Board
        removeAdjacent(board);

        // Base Cases
        if (board.size() == 0) return 0;// emptied the board, go back up the stack
        if (outOfTurns(hand)) return -1;// no more moves can be made, impossible attempt

        // Main Function
        int count = 0;
        int mini = 10000;
        for (int i = 0; i < board.size(); i++) {
            char c = board.get(i);
            count++;
            if (i == board.size()-1 || board.get(i+1) != c) {
                int needed = 3 - count;
                if (hand.get(c) >= needed) {
                    hand.put(c, hand.get(c) - needed);
                    ArrayList<Character> subBoard = (ArrayList<Character>) board.clone();
                    // adjust
                    for (int j = 0; j < count; j++) {
                        subBoard.remove(i-j);// couldn't get backtracking to work, so worked around it...
                    }
                    // recurse
                    int subSol = solver(subBoard, hand);
                    if (subSol != -1) mini = Math.min(subSol + needed, mini);
                    // backtrack
                    hand.put(c, hand.get(c) + needed);
                }
                count = 0;
            }
        }

        // return result
        if (mini == 10000) return -1;
        return mini;
    }

    private static void removeAdjacent(ArrayList<Character> board) {
        int count = 0;
        boolean adjs = false;// have adjacencies been found
        for (int i = 0; i < board.size(); i++) {
            char c = board.get(i);
            count++;
            if (i == board.size()-1 || board.get(i+1) != c) {
                if (count >= 3) {
                    for (int j = 0; j < count; j++) {
                        board.remove(i-j);
                    }
                    adjs = true;
                    break;
                }
                count = 0;
            }
        }
        if (adjs) removeAdjacent(board);// catch that accordion effect!
    }

    private static boolean outOfTurns(HashMap<Character, Integer> hand) {
        for (int value : hand.values()) {
            if (value != 0) return false;
        }
        return true;
    }
}
