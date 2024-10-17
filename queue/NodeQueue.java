package ed.queue;

import java.util.NoSuchElementException;

public class NodeQueue<E extends Comparable<E>> {
    Node<E> first;
    Node<E> last;
    int size;

    public NodeQueue() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(E e) {
        Node<E> newNode = new Node<>(e);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }

        newNode.prev = last;
        last.next = newNode;
        last = newNode;

        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Could not dequeue: List is Empty");
        }

        Node<E> removed = first;
        E e = removed.element;

        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = removed.next;
            first.prev = null;
            removed.next = null;
        }

        removed = null;

        size--;
        return e;

    }

    public E leave(E e) {
        if (isEmpty()) {
            throw new NoSuchElementException("Could not leave: List is Empty");
        }

        // left &right pointers
        Node<E> l = first;
        Node<E> r = last;

        Node<E> node = null;

        for (int i = 0, j = size - 1; i <= j; i++, j--) {
            if (l.element.equals(e)) {
                node = l;
                break;
            }
            if (r.element.equals(e)) {
                node = r;
                break;
            }

            l = l.next;
            r = r.prev;
        }

        if (node == null) {
            throw new NoSuchElementException();
        }

        if (node == first) {
            return dequeue();
        }
        else if (node == last) {
            last = last.prev;
            last.next = null;
            node.prev = null;
        }
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }

        E removed = node.element;
        node = null;

        size--;

        return removed;

    }

    class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(E element) {
            this.element = element;
            next = null;
            prev = null;
        }
    }

    public String toString() {
        if (this.first == null) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        Node<E> node = this.first;
        for (int i = 0; i < this.size - 1; i++) {
            s.append(String.format("%s ", node.element.toString()));
            node = node.next;
        }
        s.append(node.element.toString());
        return s.toString();
    }
}
