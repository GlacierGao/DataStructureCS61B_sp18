package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int N; // not necessary
    private int T;
    private int[] xt;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        this.N = N;
        xt = new int[T];
        for (int i = 0; i < T; i++) {
            Percolation g = pf.make(N);
            int[] toBeOpened = StdRandom.permutation(N * N);
            for (int j = 0; !g.percolates(); j++) {
                int row = toBeOpened[j] / N;
                int col = toBeOpened[j] - row;
                g.open(row, col);
            }
            xt[i] = g.numberOfOpenSites();
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double sum = 0.0;
        for (int i = 0; i < T; i++) {
            sum += xt[i];
        }
        return sum / T;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(xt);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }
}
