import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node before;
    }


    public Deque() {
        size = 0;
    }

    public boolean isEmpty() {
        return (first == null || last == null);
    }

    public int size() {
        // if (isEmpty()) return 0;
        // else if (first.equals(last)) return 1;
        // else {
        //     Node temp = first;
        //     int i = 2;
        //     while (!temp.next.equals(last)) {    //循环造成调用时间增长
        //         temp = temp.next;
        //         i++;
        //     }
        //     return i;
        // }
        return size;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.before = null;
        if (isEmpty()) first = last;
        else {
            oldLast.next = last;
            last.before = oldLast;
        }
        size++;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = null;
        first.before = null;
        if (isEmpty()) last = first;
        else {
            first.next = oldFirst;
            oldFirst.before = first;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Node temp = first;
        first = first.next;
        if (isEmpty()) last = first;
        else first.before = null;
        size--;
        return temp.item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        // Node temp = first;
        // if (first.equals(last)) {
        //         //     last = null;
        //         //     first = null;
        //         //     size--;
        //         //     return temp.item;
        //         // }
        //         // while (!temp.next.equals(last)) {       //循环造成调用时间增长
        //         //     temp = temp.next;
        //         // }
        //         // Node oldLast = last;
        //         // last = temp;
        //         // last.next = null;
        //         // size--;
        //         // return oldLast.item;
        Node temp = last;
        last = last.before;
        if (isEmpty()) first = last;
        else last.next = null;
        size--;
        return temp.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (hasNext()) {
                Item item = current.item;
                current = current.next;
                return item;
            }
            else throw new NoSuchElementException("There is no next item");
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<Integer>();

        deque.addLast(1);
        StdOut.printf(deque.removeFirst() + "\n");
        // deque.addLast(1);
        // StdOut.printf(deque.removeLast() + "\n");
        // deque.addLast(3);
        // deque.addFirst(4);
        // deque.removeLast();
        // deque.addFirst(6);
        // deque.addFirst(7);
        // StdOut.printf(deque.size() + "\n");
        // deque.size();
        // StdOut.printf(deque.isEmpty() + "\n");

        // StdOut.printf("Iterator reads " + deque.iterator().next() + "\n");
        // StdOut.printf("Iterator reads " + deque.iterator().next() + "\n");
        // StdOut.printf("Iterator reads " + deque.iterator().next() + "\n");
        // StdOut.printf("Iterator reads " + deque.iterator().next() + "\n");
        // Iterator<String> i = deque.iterator();
        //
        // StdOut.printf("Iterator reads " + i.next() + "\n");
        // StdOut.printf("Iterator reads " + i.next() + "\n");
        // StdOut.printf("Iterator reads " + i.next() + "\n");

        // while (i.hasNext()) {
        //     StdOut.printf("iterator reads : " + i.next() + "\n");
        // }
        // StdOut.printf("remove first: " + deque.removeFirst() + '\n');
        // StdOut.printf("remove first: " + deque.removeFirst() + '\n');
        // StdOut.printf("remove last: " + deque.removeLast() + '\n');
        // StdOut.printf("remove last: " + deque.removeLast() + '\n');
        // StdOut.printf("remove last: " + deque.removeLast() + '\n');


    }
}
