
import edu.princeton.cs.algs4.QuickUnionUF;

public class Percolation {
    private int[] open;
    private int count;
    private int num;
    private QuickUnionUF quickU;

    public Percolation(int n) {
        count = n*n;
        num = n;
        quickU = new QuickUnionUF(count);

        open = new int[count];
        for(int i=0; i<count; i++){
            open[i] = 0;
        }

    }

    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)){
            System.out.printf("(%d,%d) is open",row, col);
            open[row*num+col] = 1;
            if (row == 0){
                quickU.union(row*num+col,0);
                if(isOpen(row+1,col)){
                    quickU.union((row+1)*num+col,row*num+col);
                }
             //   return quickU.connected(row*num+col, 0);
            }
            else if (row == num-1){
                quickU.union(row*num+col,count-1);
                if(isOpen(row-1,col)){
                    quickU.union(row*num+col, (row-1)*num+col);
                }
              //  return quickU.connected(row*num+col, 0);
            }
            else {
                if (col != 0) {
                    if (isOpen(row, col - 1)) {
                        quickU.union(row * num + col, row * num + col - 1);
                    }
                }
                if (col != num - 1) {
                    if (isOpen(row, col + 1)) {
                        quickU.union(row * num + col, row * num + col + 1);
                    }
                }
                if (isOpen(row + 1, col)) {
                    quickU.union(row * num + col, (row + 1) * num + col);
                }
                if (isOpen(row - 1, col)) {
                    quickU.union(row * num + col, (row - 1) * num + col);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        int n = row * num +col;
        return (open[n] != 0);
    }

    public boolean isFull(int row, int col){
        validate(row, col);
        if (!isOpen(row, col)){
            return false;
        }
        return quickU.connected(row*num+col, 0);
    }

    public int numberOfOpenSites(){
        int openNum = 0;
        for(int i = 0; i < count; i++){
            if(open[i] != 0){
                openNum++;
            }
        }
        return openNum;
    }

    public boolean percolate(){
        return quickU.connected(0, count-1);
    }

    public void validate(int row, int col){
        if(row < 0 || row > count || col < 0 || col > count){
            throw new IllegalArgumentException("out of range");
        }
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(10,100);

    }
}