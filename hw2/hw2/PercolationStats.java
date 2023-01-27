package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int T;
    private double[] xt;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        xt = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation g = pf.make(N);
            int[] toBeOpened = StdRandom.permutation(N * N);
            for (int j = 0; !g.percolates(); j++) {
                int row = toBeOpened[j] / N;
                int col = toBeOpened[j] - row * N;
                g.open(row, col);
            }
            xt[i] = g.numberOfOpenSites() / (N * N * 1.0); // 两int相除默认结果int
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

    /*
    public static void main(String[] args) {
        PercolationFactory pff = new PercolationFactory();
        PercolationStats pss = new PercolationStats(20, 10, pff);
    } */
}
