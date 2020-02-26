import java.util.Random;


public class PercolationStats {
    private double[] data;
    private Percolation[] test;
    private int T;

    public PercolationStats(int n, int trials){
        data = new double[trials];
        test = new Percolation[trials];
        Random rd = new Random();
        T = trials;

        for(int i=0; i<trials; i++){
            test[i] = new Percolation(n);

            while (!test[i].percolate()){
                int x1 = rd.nextInt(n);
                int x2 = rd.nextInt(n);
                test[i].open(x1,x2);
            }
            data[i] = (double) test[i].numberOfOpenSites() / (n*n);
        }
        System.out.println("mean = " + mean());
        System.out.println("stddev = " + stddev());
        System.out.println("95% confidence interval = [ " + confidenceLo()+ "," + confidenceHi() + "]");

    }

    public double mean(){
        double temp = 0;
        for(int i=0; i<T; i++){
            temp += data[i];
        }
        return temp/T;
    }

    public double stddev(){
        double square = 0;
        double squareAdd = 0;
        double meanTemp = mean();

        for(int i=0; i<T; i++){
            square = (data[i]-meanTemp)*(data[i]-meanTemp);
            squareAdd += square;
        }
        return Math.sqrt(squareAdd / (T-1));
    }

    public double confidenceLo(){
        double sqrt1 = stddev();
        double sqrt2 = Math.sqrt(T);
        return mean() - (1.96 * sqrt1 / sqrt2);
    }

    public double confidenceHi() {
        double sqrt1 = stddev();
        double sqrt2 = Math.sqrt(T);
        return mean() + (1.96 * sqrt1 / sqrt2);
    }

    public static void main(String[] args) {




    }
}

