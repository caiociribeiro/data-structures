package ed.stack;

public class Stack<E extends Comparable<E>> {

    private Object[] array;
    private int size;

    public Stack(int capacity) {
        this.array = new Object[capacity];
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

        E r = (E) array[size - 1];
        this.size--;

        return r;
    }

    public E peek() {

        if (this.isEmpty()) {
            System.out.println("Pilha vazia! Não é possível espiar.");
            return null;
        }

        return (E) array[size - 1];
    }

    public boolean contem(E elemento) {

        for (int i = 0; i < this.size; i++) {
            if (array[i] == elemento) {
                return true;
            }
        }

        return false;
    }

}