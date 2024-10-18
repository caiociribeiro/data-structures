package ed.priorityqueue;

@SuppressWarnings("unchecked")
public class MaxHeapPriorityQueue<E extends Comparable<E>> {
    private Object[] heap;
    private int size;

    public MaxHeapPriorityQueue(int capacity) {
        heap = new Object[capacity + 1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == heap.length + 1;
    }

    private void heapifyUp(int pos) {
        if (pos == 1) return;

        E current = (E) heap[pos];
        E parent = (E) heap[pos / 2];

        if (current.compareTo(parent) > 0) {
            E aux = parent;
            heap[pos / 2] = current;
            heap[pos] = parent;

            heapifyUp(pos / 2);
        }
    }

    private void heapifyDown(int pos) {
        int left = 2 * pos;
        int right = 2 * pos + 1;

        if (left <= size) {
            E current = (E) heap[pos];
            int highest = current.compareTo((E) heap[left]) > 0 ? pos : left;

            if (right <= size) {
                current = (E) heap[highest];
                highest = current.compareTo((E) heap[right]) > 0 ? highest : right;
            }

            if (highest != pos) {
                E aux = (E) heap[highest];
                heap[highest] = heap[pos];
                heap[pos] = aux;

                heapifyDown(highest);
            }
        }

    }

    public void insert(E element) {
        if (isFull()) {
            throw new IllegalStateException("Heap is full");
        }

        heap[++size] = element;

        heapifyUp(size);
    }

    public E extractMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        E r = (E) heap[1];

        heap[1] = heap[size--];

        heapifyDown(1);

        return r;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            str.append(heap[i] + " ");
        }

        return str.toString();
    }
}
