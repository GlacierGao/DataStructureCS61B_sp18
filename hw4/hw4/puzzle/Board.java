package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private final int[][] b;
    private final int N;
//    private int[][] cowmoo;

    /* Constructs a board from an N-by-N array of tiles where
              tiles[i][j] = tile at row i, column j
     */
    public Board(int[][] tiles) {
        N = tiles[0].length;
        int[][] cowmoo = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                cowmoo[r][c] = tiles[r][c];
            }
        }
        b = cowmoo;
    }

    /* Returns value of tile at row i, column j (or 0 if blank)
     */
    public int tileAt(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        return b[i][j];
    }

    /* Returns the board size N
     */
    public int size() {
        return N;
    }

    /* Returns the neighbors of the current board
    the code was copied by Prof Josh's answer.
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }

    /* Hamming estimate described below
     */
    public int hamming() {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int goalIndex = convertToGoalIndex(i, j);
                int actualIndex = tileAt(i, j);
                if (actualIndex != 0 && actualIndex != goalIndex) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private int convertToGoalIndex(int i, int j) {
        if (i == N - 1 && j == N - 1) {
            return 0;
        }
        return N * (i + 1) + (j + 1);
    }

    private int getCol(int goalIndex) {
        return Math.floorMod(goalIndex - 1, N);
    }

    private int getRow(int goalIndex) {
        return (goalIndex - 1) / N;
    }

    /* Manhattan estimate described below
     */
    public int manhattan() {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int actual = tileAt(i, j);
                if (actual != 0) {
                    int col = getCol(actual);
                    int row = getRow(actual);
                    ans += Math.abs(col - j) + Math.abs(row - i);
                }
            }
        }
        return ans;
    }

    /* Estimated distance to goal. This method should
              simply return the results of manhattan() when submitted to
              Gradescope.
     */
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /* Returns true if this board's tile values are the same
              position as y's
     */
    @Override
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }
        Board other = (Board) y;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (b[i][j] != other.b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // received the hint from SpotBug however I don't know why
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
