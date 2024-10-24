package ed.queue;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class Queue<E extends Comparable<E>> {
    private E[] queue;
    private int first;
    private int last;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    public Queue(int capacity) {
        queue = (E[]) new Comparable[capacity];
        first = 0;
        last = 0;
        size = 0;
    }

    public Queue() {
        queue = (E[]) new Comparable[DEFAULT_CAPACITY];
        first = 0;
        last = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void grow(int minCapacity) {
        int newCapacity = queue.length * 2;

        if (minCapacity > newCapacity) {
            newCapacity = minCapacity;
        }

        E[] newArray = (E[]) new Comparable[newCapacity];
        System.arraycopy(queue, 0, newArray, 0, first);

        for (int i = first + newArray.length - queue.length, j = first; i < newArray.length; i++, j++) {
            newArray[i] = queue[j];
        }

        first += (newArray.length - queue.length);
        queue = newArray;
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > queue.length)
            grow(minCapacity);
    }

    public void enqueue(E value) {
        if (size == queue.length) {
            ensureCapacity(queue.length + 1);
        }

        queue[last] = value;

        last = (last + 1) % queue.length;

        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Could not dequeue: List is empty");
        }

        E d = queue[first];

        queue[first] = null;

        first = (first + 1) % queue.length;

        size--;

        return d;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Could not peek: List is empty");
        }

        return queue[first];
    }

    public int indexOf(E value) {
        if (isEmpty()) return -1;

        for (int i = 0, j = size - 1; i <= j; i++, j--) {
            if (queue[i].equals(value)) {
                return i;
            }
            if (queue[j].equals(value)) {
                return j;
            }
        }
        return -1;
    }

    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    public void leave(E element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("Could not leave: No such element");
        }
        for (int i = index; i < size - 1; i++) {
            queue[i] = queue[i + 1];
        }
        size--;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = first, count = 0; count < size - 1; i = (i + 1) % queue.length, count++) {
            sb.append(queue[i]).append(" ");
        }
        return sb.append(queue[size - 1]).toString();
    }
}
