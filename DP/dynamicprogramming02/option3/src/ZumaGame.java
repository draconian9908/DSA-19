import java.util.ArrayList;
import java.util.HashMap;

public class ZumaGame {

    public static int zumaGame(char[] board, char[] hand) {
        ArrayList<Character> boardList = new ArrayList<>(board.length);
        for (char c : board) {
            boardList.add(c);
        }
        HashMap<Character, Integer> handMap = new HashMap<>();
        for (char c : hand) {
            int temp = handMap.getOrDefault(c, 0);
            handMap.put(c, temp+1);
        }
        return solver(boardList, handMap);
    }

    private static int solver(ArrayList<Character> board, HashMap<Character, Integer> hand) {

        // Clean Board
        removeAdjacent(board);

        // Base Cases
        if (board.isEmpty()) return 0;// emptied the board, go back up the stack
        if (outOfTurns(hand)) return -1;// no more moves can be made, impossible attempt

        return -1;
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
