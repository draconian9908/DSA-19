import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    public int size;
    public int[][] tiles;

    // A 2D array representing the solved board state
    private int[][] goal = {{1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 0}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        tiles = b;
        size = tiles.length;
    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        return size;
    }


    private int[] find(int[][] checker, int num) {
        int[] found= new int[]{0,0};
        boolean checked = false;
        for (int i = 0; i < checker.length; i++) {
            found[0] = i;
            for (int j = 0; j < checker.length; j++) {
                found[1] = j;
                if (checker[i][j] == num) {
                    checked = true;
                    break;
                }
            }
            if (checked) break;
        }
        return found;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int num = tiles[i][j];
                if (num == 0) continue;
                int[] g = find(goal, num);
                count += Math.abs(i - g[0]);
                count += Math.abs(j - g[1]);
            }
        }
        return count;
    }

    /*
     * Another cost function
     */
    public int placeCost() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int num = tiles[i][j];
                if (num == 0) continue;
                int g = goal[i][j];
                if (num != g) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] != goal[i][j]) return false;
            }
        }
        return true;
    }

    private int countInversions() {
        int[] seen = new int[size*size];
        int addLoc = 0;
        int count = 0;
        for (int[] row:tiles) {
            for (int tile:row) {
                if (tile == 0) {
                    seen[addLoc] = 0;
                    addLoc++;
                    continue;
                }
                for (int el:seen) {
                    if (tile < el || el == 0) count++;
                }
                seen[addLoc] = tile;
                addLoc++;
            }
        }
        return count;
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        int inversions = countInversions();
        if (inversions % 2 == 0) {
            return true;
        }
        else {
            return false;
        }
    }


    private void swap(Board board, int[] a, int[] b) {
        int aNum = board.tiles[a[0]][a[1]];
        int bNum = board.tiles[b[0]][b[1]];
        board.tiles[a[0]][a[1]] = bNum;
        board.tiles[b[0]][b[1]] = aNum;
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new LinkedList<>();
        int[] emptySpace = find(tiles, 0);// indices for empty space
        // generate list of indices to lookup
        int[][] lookup = new int[][]{{emptySpace[0], emptySpace[1]-1},//left
                                    {emptySpace[0], emptySpace[1]+1},//right
                                    {emptySpace[0]-1, emptySpace[1]},//above
                                    {emptySpace[0]+1, emptySpace[1]}};//below
        for (int[] look : lookup) {
            if ((look[0] >= 0 && look[0] < size) && (look[1] >= 0 && look[1] < size)) {
                Board temp = copyOf(this);
                swap(temp, emptySpace, look);
//                System.out.println(this.tiles);
//                printBoard();
                neighbors.add(temp);
            }
        }
        return neighbors;
    }

    private Board copyOf(Board copied) {
        Board newBoard = new Board(new int[size][size]);
        for (int i = 0; i < size; i ++) {
            newBoard.tiles[i] = copied.tiles[i].clone();
        }
        return newBoard;
    }

    public void printBoard() {
        for (int[] row : this.tiles) {
            for (int el : row) {
                System.out.print(el);
            }
            System.out.println();
        }
        System.out.println();
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != size || y.tiles[0].length != size) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};// solvable
//        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};// solvable
//        int[][] initState = {{1, 0, 3}, {2, 4, 5}, {6, 7, 8}};// unsolvable
        Board board = new Board(initState);
        Iterable<Board> it = board.neighbors();

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
//        System.out.println("Neighbors:" + it);
    }
}
