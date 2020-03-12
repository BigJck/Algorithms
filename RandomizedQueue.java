/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static int numNode = 0;

    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    public RandomizedQueue() {

    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return numNode;
    }

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        Node addNode = new Node();
        addNode.item = item;
        if (isEmpty()) {
            first = addNode;
            first.next = first;
        }
        else {
            addNode.next = first.next;
            first.next = addNode;
        }
        numNode++;
        // StdOut.printf(item + " is enque\n");
        // StdOut.printf(numNode + " is the number of Item\n");
    }

    public Item dequeue() {
        if (numNode == 0) throw new NoSuchElementException();
        if (numNode == 1) {
            Item temp = first.item;
            first = null;
            numNode--;
            // StdOut.printf(temp + " is deque\n");
            // StdOut.printf(numNode + " is the number of Item\n");
            return temp;
        }

        Node lastNode = first;
        Node deNode = first;
        int randomNum = StdRandom.uniform(numNode);
        for (int i = 0; i < randomNum; i++) {
            deNode = deNode.next;
        }
        while (!lastNode.next.equals(deNode)) {
            lastNode = lastNode.next;
        }
        if (randomNum == 0) {
            first = first.next;
        }
        lastNode.next = deNode.next;
        numNode--;
        // StdOut.printf(deNode.item + " is deque\n");
        // StdOut.printf(numNode + " is the number of Item\n");
        return deNode.item;

    }

    public Item sample() {
        if (numNode == 0) throw new NoSuchElementException();
        Node deNode = first;
        int randomNum = StdRandom.uniform(numNode);
        // StdOut.printf(randomNum + " is randomNum\n");
        for (int i = 0; i < randomNum; i++) {
            deNode = deNode.next;
        }
        // StdOut.printf(deNode.item + " is a sample\n");
        return deNode.item;
    }

    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    public class RandomizedIterator implements Iterator<Item> {
        private Node current = randomCurrent();
        private Node startPoint = current;

        // public RandomizedIterator() {
        //     // StdOut.printf(current.item + "is random current\n");
        // }

        private Node randomCurrent() {
            Node findCurrent = first;
            int randomNum = StdRandom.uniform(numNode);
            for (int i = 0; i < randomNum; i++) {
                findCurrent = findCurrent.next;
            }
            return findCurrent;
        }

        @Override
        public boolean hasNext() {
            return !current.next.equals(startPoint);
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            // StdOut.printf(item + " is current\t");
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue(in.readString());
        rq.enqueue(in.readString());
        rq.enqueue(in.readString());
        rq.enqueue(in.readString());
        rq.enqueue(in.readString());

        rq.sample();
        rq.sample();
        rq.sample();
        rq.sample();
        rq.sample();
        rq.sample();

        // rq.dequeue();
        // rq.dequeue();
        // rq.dequeue();
        // rq.dequeue();
        // rq.dequeue();
        // rq.dequeue();


        Iterator<String> a = rq.iterator();
        Iterator<String> b = rq.iterator();
        Iterator<String> c = rq.iterator();
        Iterator<String> d = rq.iterator();
        while (a.hasNext()) {
            a.next();
        }
        StdOut.printf("\n");
        while (b.hasNext()) {
            b.next();
        }
        StdOut.printf("\n");
        while (c.hasNext()) {
            c.next();
        }
        StdOut.printf("\n");
        while (d.hasNext()) {
            d.next();
        }
        StdOut.printf("\n");

    }
}
