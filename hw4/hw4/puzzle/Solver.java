package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.LinkedList;

public class Solver {

    private MinPQ<SearchNode> toBeMovedSequence;
    private SearchNode finalNode;

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int movesNumFromInit;
        private SearchNode prevSN;
        private int hToGoal;

        public SearchNode(WorldState ws, int moves, SearchNode prev) {
            state = ws;
            movesNumFromInit = moves;
            prevSN = prev;
            hToGoal = state.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(SearchNode sn2) {
            return this.movesNumFromInit - sn2.movesNumFromInit +
                    this.hToGoal - sn2.hToGoal;
        }
    }

    public Solver(WorldState initial) {
        toBeMovedSequence = new MinPQ<>();
        toBeMovedSequence.insert(new SearchNode(initial, 0, null));
        while (true) {
            SearchNode X = toBeMovedSequence.delMin();
            if (X.state.isGoal()) {
                finalNode = X;
                break;
            } else {
                for (WorldState worldState : X.state.neighbors()) {
                    if (!worldState.equals(X.state)) {
                        toBeMovedSequence.insert(new SearchNode(worldState, X.movesNumFromInit + 1, X));
                    }
                }
            }
        }
    }

    public int moves() {
        return finalNode.movesNumFromInit;
    }

    public Iterable<WorldState> solution() {
        SearchNode sn = finalNode;
        LinkedList<WorldState> solutionList = new LinkedList<>();
        while (sn != null) {
            solutionList.addFirst(sn.state);
            sn = sn.prevSN;
        }
        return solutionList;
    }
}
