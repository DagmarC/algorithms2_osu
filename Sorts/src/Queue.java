import java.util.NoSuchElementException;

// FIFO: synchro, operator rura, kruhovy buffer, system hromadnej obsluhy
public class Queue<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head; // Remove from here (Front)
    private Node<T> tail; // Add to here (Back)
    private int size;

    // --- ENQUEUE: Add to the Back (Tail) ---
    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);

        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;

        // If queue was empty, head is also the new node
        if (head == null) {
            head = tail;
        }
        size++;
    }

    // --- DEQUEUE: Remove from the Front (Head) ---
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        T data = head.data;
        head = head.next;

        // If queue is now empty, tail must also be null
        if (head == null) {
            tail = null;
        }

        size--;
        return data;
    }

    // --- PEEK: Look at the Front ---
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
}