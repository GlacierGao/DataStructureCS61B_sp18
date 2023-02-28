package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;
    private boolean targetFound = false;
    private Queue<Integer> toBeSearchedSequence;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = m.xyTo1D(sourceX, sourceY);
        t = m.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = 0;
        toBeSearchedSequence = new LinkedList<>();
        toBeSearchedSequence.add(s);
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        while (!toBeSearchedSequence.isEmpty()) {
            int searchNode = toBeSearchedSequence.poll();
            marked[searchNode] = true;
            announce();
            if (searchNode == t) {
                targetFound = true;
            }

            if (targetFound) {
                return;
            }

            for (int neighbors : maze.adj(searchNode)) {
                if (!marked[neighbors]) {
                    toBeSearchedSequence.add(neighbors);
                    announce();
                    edgeTo[neighbors] = searchNode;
                    distTo[neighbors] = distTo[searchNode] + 1;
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

