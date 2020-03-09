import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int[] open;
    private int count;
    private int num;
    private WeightedQuickUnionUF quickU;

    public Percolation(int n) {
        if (n < 1)
            throw new IllegalArgumentException();
        count = n * n;
        num = n;
        quickU = new WeightedQuickUnionUF(count);

        open = new int[count];
        for (int i = 0; i < count; i++) {
            open[i] = 0;
        }

    }

    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            open[(row - 1) * num + (col - 1)] = 1;
            if (num == 1) {
                open[(row - 1) * num + (col - 1)] = 2;
                quickU.union(0, 0);
            }
            else if (row == 1) {
                quickU.union((row - 1) * num + (col - 1), 0);
                if (isOpen(row + 1, col)) {
                    quickU.union(row * num + (col - 1), (row - 1) * num + (col - 1));
                    open[row * num + (col - 1)] = 2;
                }
                open[(row - 1) * num + (col - 1)] = 2;
            }
            else if (row == num) {
                quickU.union((row - 1) * num + (col - 1), count - 1);
                if (isOpen(row - 1, col)) {
                    quickU.union((row - 1) * num + (col - 1), (row - 2) * num + (col - 1));
                }
            }
            else {
                if (col != 1) {
                    if (isOpen(row, col - 1)) {
                        quickU.union((row - 1) * num + (col - 1), (row - 1) * num + (col - 2));
                    }
                }
                if (col != num) {
                    if (isOpen(row, col + 1)) {
                        quickU.union((row - 1) * num + (col - 1), (row - 1) * num + col);
                    }
                }
                if (isOpen(row + 1, col)) {
                    quickU.union((row - 1) * num + (col - 1), row * num + (col - 1));
                }
                if (isOpen(row - 1, col)) {
                    quickU.union((row - 1) * num + (col - 1), (row - 2) * num + (col - 1));
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        int n = (row - 1) * num + (col - 1);
        return (open[n] != 0);
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        if (open[(row - 1) * num + (col - 1)] == 0) {
            return false;
        }

        if (open[(row - 1) * num + (col - 1)] == 1) {

        }
        return quickU.connected((row - 1) * num + (col - 1), 0);
    }

    public int numberOfOpenSites() {
        int openNum = 0;
        for (int i = 0; i < count; i++) {
            if (open[i] != 0) {
                openNum++;
            }
        }
        return openNum;
    }

    public boolean percolates() {
        return quickU.connected(0, count - 1);
    }

    private void validate(int row, int col) {
        if (row < 1 || row > num || col < 1 || col > num) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        Percolation perc = new Percolation(N);
        boolean percolates = false;
        int count = 0;
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            if (!perc.isOpen(i, j)) {
                ++count;
            }
            perc.open(i, j);
            percolates = perc.percolates();
            if (percolates) break;
        }
        StdOut.println(count + " open sites");
        if (percolates)
            StdOut.println("percolates");
        else StdOut.println("does not percolate");
    }
}
