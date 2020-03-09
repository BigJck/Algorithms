/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

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
        return (first == null || last == null)
    }

    public int size() {

    }

    public void addLast(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    public void addFirst(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = null;
        if (isEmpty()) first = last;
        else first.next = oldFirst;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        else first = first.next;
        if (isEmpty()) first = last;

    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Node temp = first;
        if (temp.next == null) first = last = null;
        do {
            temp = temp.next;
        } while (temp.next.equals(last));

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
            }
            else throw new NoSuchElementException("There is no next item");
        }
    }


    public static void main(String[] args) {

    }
}
