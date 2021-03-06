import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


// this is our implementation of a rubiks cube. It is your job to use A* or some other search algorithm to write a
// solve() function
public class RubiksCube {

    private BitSet cube;
    private int move;
    private int cost;
    private char prevRot;
    private RubiksCube prevCube;
    private int[][] cubieList = new int[][]{
            {0,16,21}, {1,9,17}, {2,5,8}, {3,4,20},
            {12,19,22}, {13,10,18}, {14,6,11}, {15,7,23}
    };
    private int[][] colors = new int[][]{
            {0,4,5}, {0,2,4}, {0,1,2}, {0,1,5},
            {3,4,5}, {3,2,4}, {3,1,2}, {3,1,5}
    };

    // initialize a solved rubiks cube
    public RubiksCube() {
        // 24 colors to store, each takes 3 bits
        cube = new BitSet(24 * 3);
        for (int side = 0; side < 6; side++) {
            for (int i = 0; i < 4; i++) {
                setColor(side * 4 + i, side);
            }
        }
        move = 0;
        cost = 0;
        prevRot = 'z';
    }

    // initialize a rubiks cube with the input bitset
    private RubiksCube(BitSet s) {
        cube = (BitSet) s.clone();
        move = 0;
        cost = newMan(this) + move;
        prevRot = 'z';
    }

    // creates a copy of the rubiks cube
    public RubiksCube(RubiksCube r) {
        cube = (BitSet) r.cube.clone();
        move = r.move;
        cost = move + newMan(this);
        prevRot = r.prevRot;
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        return other.cube.equals(cube);
    }

    /**
     * return a hashCode for this rubik's cube.
     *
     * Your hashCode must follow this specification:
     *   if A.equals(B), then A.hashCode() == B.hashCode()
     *
     * Note that this does NOT mean:
     *   if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        return cube.hashCode();
    }

    public boolean isSolved() {
        return this.equals(new RubiksCube());
    }


    // takes in 3 bits where bitset.get(0) is the MSB, returns the corresponding int
    private static int bitsetToInt(BitSet s) {
        int i = 0;
        if (s.get(0)) i |= 4;
        if (s.get(1)) i |= 2;
        if (s.get(2)) i |= 1;
        return i;
    }

    // takes in a number 0-5, returns a length-3 bitset, where bitset.get(0) is the MSB
    private static BitSet intToBitset(int i) {
        BitSet s = new BitSet(3);
        if (i % 2 == 1) s.set(2, true);
        i /= 2;
        if (i % 2 == 1) s.set(1, true);
        i /= 2;
        if (i % 2 == 1) s.set(0, true);
        return s;
    }

    // index from 0-23, color from 0-5
    private void setColor(int index, int color) {
        BitSet colorBitset = intToBitset(color);
        for (int i = 0; i < 3; i++)
            cube.set(index * 3 + i, colorBitset.get(i));
    }


    // index from 0-23, returns a number from 0-5
    private int getColor(int index) {
        return bitsetToInt(cube.get(index * 3, (index + 1) * 3));
    }

    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {
        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
        return rub;
    }


    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        int[] faceFrom = null;
        int[] faceTo = null;
        int[] sidesFrom = null;
        int[] sidesTo = null;
        // colors move from the 'from' variable to the 'to' variable
        switch (c) {
            case 'u': // clockwise
            case 'U': // counterclockwise
                faceFrom = new int[]{0, 1, 2, 3};
                faceTo = new int[]{1, 2, 3, 0};
                sidesFrom = new int[]{4, 5, 8, 9, 17, 16, 21, 20};
                sidesTo = new int[]{21, 20, 4, 5, 8, 9, 17, 16};
                break;
            case 'r':
            case 'R':
                faceFrom = new int[]{8, 9, 10, 11};
                faceTo = new int[]{9, 10, 11, 8};
                sidesFrom = new int[]{6, 5, 2, 1, 17, 18, 13, 14};
                sidesTo = new int[]{2, 1, 17, 18, 13, 14, 6, 5};
                break;
            case 'f':
            case 'F':
                faceFrom = new int[]{4, 5, 6, 7};
                faceTo = new int[]{5, 6, 7, 4};
                sidesFrom = new int[]{3, 2, 8, 11, 14, 15, 23, 20};
                sidesTo = new int[]{8, 11, 14, 15, 23, 20, 3, 2};
                break;
            default:
                System.out.println(c);
                assert false;
        }
        // if performing a counter-clockwise rotation, swap from and to
        if (Character.isUpperCase(c)) {
            int[] temp;
            temp = faceFrom;
            faceFrom = faceTo;
            faceTo = temp;
            temp = sidesFrom;
            sidesFrom = sidesTo;
            sidesTo = temp;
        }
        RubiksCube res = new RubiksCube(cube);
        for (int i = 0; i < faceFrom.length; i++) res.setColor(faceTo[i], this.getColor(faceFrom[i]));
        for (int i = 0; i < sidesFrom.length; i++) res.setColor(sidesTo[i], this.getColor(sidesFrom[i]));
        res.prevRot = c;
        return res;
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r= r.rotate(listTurns[i]);
        }
        return r;
    }

    public static char[] getScramble(int size){
        char[] listTurns = new char[size];
        for (int i = 0; i < size; i++) {
            switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0:
                    listTurns[i] = 'u';
                    break;
                case 1:
                    listTurns[i] = 'U';
                    break;
                case 2:
                    listTurns[i] = 'r';
                    break;
                case 3:
                    listTurns[i] = 'R';
                    break;
                case 4:
                    listTurns[i] = 'f';
                    break;
                case 5:
                    listTurns[i] = 'F';
                    break;
            }
        }
        return listTurns;
    }

    /*
     * New heuristic function, essentially Manhattan Distance using cubies
     */

    private int newMan(RubiksCube rc) {
        int man = 0;
        for (int j = 0; j < colors.length; j++) {
            int[] set = cubieList[j];// colors of the cubie currently looking at
            int[] actColors = new int[] {rc.getColor(set[0]), rc.getColor(set[1]), rc.getColor(set[2])};// colors cubie is supposed to be
            for (int i = 0; i < 3; i++) {// check the actual vs the expected colors
                if (actColors[i] - colors[j][i] == 3 || colors[j][i] - actColors[i] == 3) {
                    man+=2;// if on opposite side from where it should be, takes at least 2 moves to fix
                }
                else if (actColors[i] != colors[j][i]) {
                    man++;// if wrong and adjacent to where it should be, takes at least 1 move to fix
                }
            }
        }
        man = man / 4;// moves to fix one cubie actually affect 4 cubies
        return man;
    }

    /*
     * generate each neighbor of the current cube by performing a single rotation of a single type
     */
    private List<RubiksCube> neighbors(RubiksCube rc) {
        List<RubiksCube> neighbors = new LinkedList<>();
        char[] rots = new char[]{'u', 'U', 'r', 'R', 'f', 'F'};
        for (char rot : rots) {
            RubiksCube temp = rc.rotate(rot);
            temp.move = rc.move + 1;
            temp.cost = temp.move + newMan(temp);
            temp.prevCube = rc;
            neighbors.add(temp);
        }
        return neighbors;
    }

    // return the solved cube, for backtracking
    private RubiksCube solver() {
        // SETUP
        RubiksCube state = new RubiksCube(cube);
        PriorityQueue<RubiksCube> open = new PriorityQueue<>((a,b) -> a.cost-b.cost);
        open.add(state);// add initial cube to priority queue
        HashMap<BitSet, Integer> visited = new HashMap<>();// map visited rubik's cubes to the cost when they were seen

        // A* LOOP
        while (!open.isEmpty()) {
            state = open.poll();// remove 'state' that has the least cost

            List<RubiksCube> neighbors = neighbors(state);// generate neighbors
            visited.put(state.cube, state.cost);
            for (int i = 0; i < neighbors.size(); i++) {// look at each neighbor
                RubiksCube nei = neighbors.get(i);
                if (nei.isSolved()) {
                    return nei;
                }
                int prevCost = visited.getOrDefault(nei.cube, -1);// check if bitset of neighbor has been seen before
                if (prevCost == -1 || prevCost > nei.cost) {// if hasn't been seen before or costs less this time...
                    open.add(nei);// add to priority queue
                }
            }
        }

        return new RubiksCube(new BitSet(24*3));
    }

    public List<Character> solve() {
        List<Character> moves = new ArrayList<>();
        RubiksCube solved = solver();
        while(solved.prevRot != 'z') {
            moves.add(solved.prevRot);
            solved = solved.prevCube;
        }
        Collections.reverse(moves);
        return moves;
    }
}
