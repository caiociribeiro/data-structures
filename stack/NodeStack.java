package ed.stack;

import java.util.EmptyStackException;

public class NodeStack<T> {
    private Node<T> top;
    private Node<T> bottom;
    private int size;

    public NodeStack() {
        top = null;
        bottom = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(T value) {
        Node<T> node = new Node<>(value);

        if (size == 0) {
            bottom = node;
        }

        node.next = top;
        top = node;
        size++;
    }

    public T pop() {
        if (size == 0) throw new EmptyStackException();

        Node<T> node = top;
        T r = node.data;

        node = null;

        top = top.next;
        size--;

        return r;
    }

    public T peek() {
        return top.data;
    }

    public boolean contains(T value) {
        if (size == 0) return false;

        Node<T> l = top;
        Node<T> r = bottom;

        for (int i = 0, j = size - 1; i < j; i++, j--) {
            if (l.data == value || r.data == value) return true;

            l = l.next;
            r = r.prev;
        }

        return false;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        Node<T> node = top;

        for (int i = 0; i < size; i++) {
            s.append(node.data).append("\n");
            node = node.next;
        }

        return s.toString();
    }

    class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T element) {
            this.data = element;
            this.next = null;
            this.prev = null;
        }
    }
}