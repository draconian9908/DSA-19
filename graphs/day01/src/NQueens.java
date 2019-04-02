import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }

    private static boolean checkAllLeft(char[][] board, int r, int c) {
        // check 135 deg (upper left)
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        // check -135 deg (lower left)
        y = r + 1;
        x = c - 1;
        while (y < board.length && x >= 0) {
            if (board[y][x] == 'Q') return true;
            y++;
            x--;
        }
        // check row to left
        x = c - 1;
        while (x >= 0) {
            if (board[r][x] == 'Q') return true;
            x--;
        }
        return false;
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    public static List<char[][]> nQueensSolutions(int n) {
        List<char[][]> answers = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        halp(0, board, answers, n);
        return answers;
    }

    // work on one column at a time, iterate through rows, going left-to-right so only check against left side
    private static void halp(int col, char[][] board, List<char[][]> answers, int numQ) {
        if (col >= board.length && numQ == 0) {
            answers.add(board);
            return;
        }

        // check each row in the column
        for (int i = 0; i < board.length; i++) {
            if (!checkAllLeft(board,i,col)) {
                char[][] board2 = copyOf(board);
                board2[i][col] = 'Q';
                numQ--;
                halp(col+1, board2, answers, numQ);
                numQ++;
            }
        }
    }

}
