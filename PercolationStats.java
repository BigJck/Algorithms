import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class PercolationStats {
    private double[] data;
    private int trialsNum;
    private double meanTemp;
    private double stddevTemp;

    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException();
        }
        data = new double[trials];
        Percolation[] test = new Percolation[trials];
        trialsNum = trials;

        for (int i = 0; i < trials; i++) {
            test[i] = new Percolation(n);

            while (!test[i].percolates()) {
                int x1 = StdRandom.uniform(n) + 1;
                int x2 = StdRandom.uniform(n) + 1;
                test[i].open(x1, x2);
            }
            data[i] = (double) test[i].numberOfOpenSites() / (n * n);
        }

        double temp = 0;
        for (int i = 0; i < trialsNum; i++) {
            temp += data[i];
        }
        meanTemp = temp / trialsNum;

        double square;
        double squareAdd = 0;
        for (int i = 0; i < trialsNum; i++) {
            square = (data[i] - meanTemp) * (data[i] - meanTemp);
            squareAdd += square;
        }
        stddevTemp = Math.sqrt(squareAdd / (trialsNum - 1));


    }

    public double mean() {
        return meanTemp;
    }

    public double stddev() {
        return stddevTemp;
    }

    public double confidenceLo() {
        double sqrt2 = Math.sqrt(trialsNum);
        return mean() - (1.96 * stddevTemp / sqrt2);
    }

    public double confidenceHi() {
        double sqrt2 = Math.sqrt(trialsNum);
        return mean() + (1.96 * stddevTemp / sqrt2);
    }

    public static void main(String[] args) {
        int iN = Integer.parseInt(args[0]), iT = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(iN, iT);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = " + stats.confidenceLo()
                               + ", " + stats.confidenceHi());
    }
}

