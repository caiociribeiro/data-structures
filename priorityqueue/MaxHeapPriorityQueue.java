package ed.priorityqueue;

@SuppressWarnings("unchecked")
public class MaxHeapPriorityQueue<E extends Comparable<E>> {
    private E[] heap;
    private int size;

    public MaxHeapPriorityQueue(int capacity) {
        this.heap = (E[]) new Comparable[capacity + 1];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == heap.length - 1;
    }

    public int size() {
        return size;
    }

    public void insert(E element) {
        if (isFull()) {
            throw new IllegalStateException("Heap is full");
        }

        // heap[size + 1] = element
        // size++;
        heap[++size] = element;

        heapifyUp(size);
    }

    public E extractMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        E r = heap[1];

        heap[1] = heap[size--];
        heap[size + 1] = null;

        heapifyDown(1);

        return r;
    }

    private void heapifyDown(int i) {
        int left = i * 2;
        int right = left + 1;

        if (left <= size) {
            int max = heap[i].compareTo(heap[left]) > 0 ? i : left;

            if (right <= size) {
                max = heap[max].compareTo(heap[right]) > 0 ? max : right;
            }

            if (max != i) {
                E temp = heap[i];
                heap[i] = heap[max];
                heap[max] = temp;

                heapifyDown(max);
            }
        }
    }

    private void heapifyUp(int i) {
        if (i == 1) return;

        if (heap[i].compareTo(heap[i / 2]) > 0) {
            E temp = heap[i];
            heap[i] = heap[i / 2];
            heap[i / 2] = temp;

            heapifyUp(i / 2);
        }
    }

    public boolean contains(E element) {
        for (int i = 1; i <= size; i++) {
            if (element.compareTo(heap[i]) == 0) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 1; i <= size - 1; i++) {
            str.append(heap[i]).append("\n");
        }

        return str.append(heap[size]).toString();
    }
}
