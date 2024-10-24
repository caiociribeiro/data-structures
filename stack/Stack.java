package ed.stack;

@SuppressWarnings("unchecked")
public class Stack<E extends Comparable<E>> {

    private E[] array;
    private int size;

    public Stack(int capacity) {
        this.array = (E[]) new Comparable[capacity];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public int size() {
        return size;
    }

    public void push(E element) {

        if (this.isFull()) {
            System.out.println("Pilha Cheia. Não é possível empilhar. STACK OVERFLOW");
            return;
        }

        array[size] = element;
        size++;
    }

    public E pop() {
        if (this.isEmpty()) {
            System.out.println("Pilha vazia! Não é possível desempilhar.");
            return null;
        }

        E r = array[size - 1];
        this.size--;

        return r;
    }

    public E peek() {

        if (this.isEmpty()) {
            System.out.println("Pilha vazia! Não é possível espiar.");
            return null;
        }

        return array[size - 1];
    }

    public boolean contains(E element) {

        for (int i = 0; i < this.size; i++) {
            if (array[i] == element) {
                return true;
            }
        }

        return false;
    }

}