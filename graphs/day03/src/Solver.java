/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;
import java.util.stream.Collectors;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;
    private State initState;
    private boolean solvable;
    private PriorityQueue<State> states = new PriorityQueue<>((a,b) -> a.cost-b.cost);

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State {
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;
        private boolean solvable;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
//            int h = board.placeCost();
            int h = board.manhattan();
            this.cost = moves + h;
            this.solvable = board.solvable();
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        if (state.prev == null) {
            return state;
        }
        state = root(state.prev);

        return state.prev;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and to identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        this.initState = new State(initial,0,null);
        this.solvable = initState.solvable;
        Iterable<Board> solution = solution();
    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        return solvable;
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        LinkedList<State> solutionTree = new LinkedList<>();
        State state = initState;
        solutionTree.add(state);
        while (!state.board.isGoal()) {
            Iterable<Board> neighbors = state.board.neighbors();
            for (Board n : neighbors) {
                State seen = find(solutionTree, n);
                if (seen == null || seen.cost > (state.cost+1)) {
                    states.add(new State(n, state.moves + 1, state));
                }
            }
            state = states.poll();
            solutionTree.add(state);
        }
        solutionState = state;
        minMoves = state.moves;
        solved = true;
        return solutionTree.stream().map(s -> s.board).collect(Collectors.toList());
    }


    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
//        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
//        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
        int[][] initState = {{8, 6, 7}, {2, 5, 4}, {3, 0, 1}};// insane
        Board initial = new Board(initState);

        Solver solver = new Solver(initial);
        System.out.println("Min Moves: " + solver.minMoves);
    }


}

/*
works...
 */

//    private void solvve(LinkedList<State> tree, State state) {
//        if (state.board.isGoal()) {
//            solutionState = state;
//            minMoves = state.moves;
//            solved = true;
//            return;
//        }
//        Iterable<Board> neighbors = state.board.neighbors();
//        for (Board n : neighbors) {
//            State seen = find(tree, n);
//            if (seen == null || seen.cost > (state.cost+1)) {
//                states.add(new State(n, state.moves + 1, state));
//            }
//        }
//        State progessTo = states.poll();
//        tree.add(progessTo);
//        solvve(tree, progessTo);
//    }

/*
...does not
 */

//    private void solvve(LinkedList<State> tree, State state) {
//        if (state.board.isGoal()) {
//            solutionState = state;
//            minMoves = state.moves;
//            solved = true;
//            return;
//        }
//        Iterable<Board> neighbors = state.board.neighbors();
////        State progessTo = new State(new Board(new int[state.board.size][state.board.size]), state.moves+1, state);
////        int minCost = 10000;
//        for (Board n : neighbors) {
//            State seen = find(tree, n);
//            if (seen == null || seen.cost > (state.cost+1)) {//
////                State stateNew = new State(n, state.moves + 1, state);
//                states.add(new State(n, state.moves + 1, state));
////                if (stateNew.cost <= minCost && stateNew.solvable) {//
////                    minCost = stateNew.cost;
////                    progessTo = stateNew;
////                    tree.add(stateNew);
////                    solvve(tree, stateNew);
////                }
//            }
////            else {
////                State check = new State(new Board(new int[state.board.size][state.board.size]),seen.moves, seen.prev);
////                while (!check.board.equals(seen.board)) {
////                    check = tree.pollLast();
////                }
////                progessTo = seen;
////            }
//        }
//        State progessTo = states.poll();
//        tree.add(progessTo);
//        solvve(tree, progessTo);
//    }