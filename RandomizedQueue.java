/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
/*
如何在iterator中实现独立不重复的随机读取
每次使用next() 仅调用一次random
真随机还是伪随机
如何复制整个队列过来
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Node first = null;
    private Node last = null;

    private class Node {
        Item item;
        Node next;
        // Node last;
    }

    public RandomizedQueue() {
        size = 0;
    }

    public boolean isEmpty() {
        return first == null || last == null;
    }

    public int size() {
        // if (first == null) return 0;
        // int size = 1;
        // Node temp = first;
        // while (!temp.next.equals(first)) {  //循环造成调用时间增长
        //     temp = temp.next;
        //     size++;
        // }
        // return size;
        return size;
    }

    // private Node randomNode() {
    //
    // }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        // Node addNode = new Node();
        // addNode.item = item;
        // if (first == null) {
        //     first = addNode;
        //     // first.next = first;
        // }
        // else {
        //     // addNode.next = first.next;
        //     first.next = addNode;
        // }
        Node oldLast = last;
        last = new Node();
        last.item = item;

        if (isEmpty()) first = last;   // enqueue第一个元素
        else oldLast.next = last;
        size++;

        // StdOut.printf(item + " is enque\n");       //    for test
        // StdOut.printf(numNode + " is the number of Item\n");
    }

    public Item dequeue() {
        if (first == null) throw new NoSuchElementException();

        int randomNum = StdRandom.uniform(size);
        Node beforeNode = first;
        Item temp;

        if (first.equals(last)) {                      //仅有一个元素
            temp = first.item;
            first = null;
            last = null;
            size--;
            return temp;
        }

        for (int i = 0; i < randomNum - 1; i++) {
            beforeNode = beforeNode.next;                  // 寻找随机删去元素的前一个元素
        }

        temp = beforeNode.next.item;
        beforeNode.next = beforeNode.next.next;

        return temp;


        // if (first.next.equals(first)) {
        //     Item temp = first.item;
        //     first = null;
        //     // StdOut.printf(temp + " is deque\n");
        //     // StdOut.printf(numNode + " is the number of Item\n");
        //     return temp;
        // }
        //
        // Node beforeNode = first;
        // Node deNode = first;
        // int randomNum = StdRandom.uniform(size());
        // for (int i = 0; i < randomNum; i++) {
        //     deNode = deNode.next;
        // }
        // while (!beforeNode.next.equals(deNode)) {   //寻找需要删去的Node的前一个Node
        //     beforeNode = beforeNode.next;
        // }
        //
        // beforeNode.next = deNode.next;
        // first = beforeNode;


        // StdOut.printf(deNode.item + " is deque\n");
        // StdOut.printf(numNode + " is the number of Item\n");
        // return deNode.item;

    }

    public Item sample() {
        if (first == null) throw new NoSuchElementException();
        Node deNode = first;
        int randomNum = StdRandom.uniform(size());
        // StdOut.printf(randomNum + " is randomNum\n");
        for (int i = 0; i < randomNum; i++) {           //循环造成调用时间增长
            deNode = deNode.next;
        }
        // StdOut.printf(deNode.item + " is a sample\n");
        return deNode.item;
    }

    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        // private Node current = randomCurrent();
        // private final Node startPoint = current;

        // public RandomizedIterator() {
        //     // StdOut.printf(current.item + "is random current\n");
        // }

        // private Node randomCurrent() {
        //     Node findCurrent = first;
        //     int randomNum = StdRandom.uniform(size());
        //     for (int i = 0; i < randomNum; i++) {
        //         findCurrent = findCurrent.next;
        //     }
        //     return findCurrent;
        // }


        private int num;
        private int[] numArray;

        public RandomizedIterator() {
            // StdOut.print(size);
            num = 0;
            numArray = new int[size];
            // for (int i = 0; i < size; i++) {
            //     numArray[i] = 1;
            // StdOut.print(numArray[i]);
        }


        @Override
        public boolean hasNext() {
            // return !current.next.equals(startPoint);
            return num < size;
        }

        public Item next() {
            // if (!hasNext()) throw new NoSuchElementException();
            // Item item = current.item;
            // current = current.next;
            // // StdOut.printf(item + " is current\t");
            // return item;
            if (!hasNext()) throw new NoSuchElementException();
            int randomNum = StdRandom.uniform(size);
            Node temp = first;
            // do {                                       //循环造成调用时间增长
            //     randomNum = StdRandom.uniform(size);
            //     // StdOut.print(randomNum);
            // } while (numArray[randomNum] == 0);

            numArray[randomNum] = 0;
            for (int i = 0; i < randomNum; i++) {       //循环造成调用时间增长
                temp = temp.next;
            }
            num++;
            return temp.item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(4);
        rq.enqueue(15);
        rq.enqueue(37);
        StdOut.printf(rq.size() + "\n");

        Iterator<Integer> i = rq.iterator();
        StdOut.printf(i.next() + "\n");
        StdOut.printf(i.next() + "\n");
        StdOut.printf(i.next() + "\n");
        StdOut.printf(i.next() + "\n");


    }
}
