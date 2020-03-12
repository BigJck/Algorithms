/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {


    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int n = 1;
        if (args.length == 1) n = Integer.parseInt(args[0]);
        StdOut.println(n);
        while (StdIn.hasNextChar()) {
            rq.enqueue(StdIn.readString());
        }

        for (int i = 0; i < n; i++) {
            StdOut.println(rq.sample());
        }

    }
}
