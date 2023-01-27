package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private static final boolean BLOCK = false;
    private static final boolean OPEN = true;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufWithoutBottom;
    private int N;
    private final int TOP;
    private final int BOTTOM;
    private int openedN;

    // create N-by-N grid, with all sites initially blocked
    // the complexity equals THETA(N^2 + 2*N) = THETA(N^2), which meets the requirement.
    public Percolation(int N) {
        this.N = N;
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufWithoutBottom = new WeightedQuickUnionUF(N * N + 1);
        grid = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                grid[r][c] = BLOCK;
            }
        }
        TOP = N * N;
        BOTTOM = N * N + 1;
        for (int r = 0; r < N; r++) {
//            uf.union(TOP, xyTo1D(0, r)); // 涉及渗漏问题，如果这里就连上了，说明直接就漏了？可视化那里有问题
            uf.union(BOTTOM, xyTo1D(N - 1, r));
//            ufWithoutBottom.union(TOP, xyTo1D(0, r));
        }
        openedN = 0;
    }

    // a helper method converting the row number and col number to the number of
    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    // a helper method throwing the IndexOutOfBoundsException.
    private void outOfBoundCheck(int row, int col) {
        if (row < 0 || col < 0 || row > N - 1 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        outOfBoundCheck(row, col);
        grid[row][col] = OPEN;
        if (!isOpen(row, col)) {
            openedN++;
        }
        if (row == 0) {
            ufWithoutBottom.union(TOP, xyTo1D(row, col));
            uf.union(TOP, xyTo1D(row, col));
        }
        if (col + 1 <= N - 1 && grid[row][col + 1]) {
            uf.union(xyTo1D(row, col + 1), xyTo1D(row, col));
            ufWithoutBottom.union(xyTo1D(row, col + 1), xyTo1D(row, col));
        }
        if (row + 1 <= N - 1 && grid[row + 1][col]) {
            uf.union(xyTo1D(row + 1, col), xyTo1D(row, col));
            ufWithoutBottom.union(xyTo1D(row + 1, col), xyTo1D(row, col));
        }
        if (col - 1 >= 0 && grid[row][col - 1]) {
            uf.union(xyTo1D(row, col - 1), xyTo1D(row, col));
            ufWithoutBottom.union(xyTo1D(row, col - 1), xyTo1D(row, col));
        }
        if (row - 1 >= 0 && grid[row - 1][col]) {
            uf.union(xyTo1D(row - 1, col), xyTo1D(row, col));
            ufWithoutBottom.union(xyTo1D(row - 1, col), xyTo1D(row, col));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        outOfBoundCheck(row, col);
        return grid[row][col] == OPEN;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        outOfBoundCheck(row, col);
        return ufWithoutBottom.connected(xyTo1D(row, col), TOP);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openedN;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(TOP, BOTTOM);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation g = new Percolation(3);
        boolean t = g.isFull(0, 0); // false
        int tN = g.numberOfOpenSites(); // 0

    }
}
