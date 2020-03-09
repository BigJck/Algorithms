import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;

    private class Node {
        Item item;
        Node next;
    }


    public Deque() {

    }

    public boolean isEmpty() {
        return (first == null || last == null);
    }

    public int size() {
        if (isEmpty()) return 0;
        else if (first.equals(last)) return 1;
        else {
            Node temp = first;
            int i = 2;
            while (!temp.next.equals(last)) {
                temp = temp.next;
                i++;
            }
            return i;
        }
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = null;
        if (isEmpty()) last = first;
        else first.next = oldFirst;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Node temp = first;
        first = first.next;
        if (isEmpty()) last = first;
        return temp.item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Node temp = first;
        if (first.equals(last)) {
            last = null;
            first = null;
            return temp.item;
        }
        while (!temp.next.equals(last)) {
            temp = temp.next;
        }
        Node oldLast = last;
        last = temp;
        last.next = null;
        return oldLast.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        public Item next() {
            if (hasNext()) {
                current = current.next;
                return current.item;
            }
            else throw new NoSuchElementException("There is no next item");
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Deque<String> deque = new Deque<String>();
        deque.addFirst(in.readString());
        deque.addFirst(in.readString());
        deque.addLast(in.readString());
        deque.addLast(in.readString());
        deque.addLast(in.readString());

        // StdOut.printf("Iterator reads " + deque.iterator().next() + "\n");
        // StdOut.printf("Iterator reads " + deque.iterator().next() + "\n");
        // StdOut.printf("Iterator reads " + deque.iterator().next() + "\n");
        // StdOut.printf("Iterator reads " + deque.iterator().next() + "\n");
        // StdOut.printf("Iterator reads " + deque.iterator().next() + "\n");
        Iterator<String> i = deque.iterator();

        while (i.hasNext()) {
            StdOut.printf("iterator reads : " + i.next() + "\n");
        }
        StdOut.printf("remove first: " + deque.removeFirst() + '\n');
        StdOut.printf("remove first: " + deque.removeFirst() + '\n');
        StdOut.printf("remove last: " + deque.removeLast() + '\n');
        StdOut.printf("remove last: " + deque.removeLast() + '\n');
        StdOut.printf("remove last: " + deque.removeLast() + '\n');


    }
}
