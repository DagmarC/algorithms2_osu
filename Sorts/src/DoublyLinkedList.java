import java.util.function.Predicate;

public class DoublyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data) { this.data = data; }
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * --- ALGORITHM: LINEAR SEARCH ---
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * * Uses Functional Interface Predicate<T> to decouple 
     * the "traversal logic" from the "matching logic".
     */
    public T search(Predicate<T> criteria) {
        Node<T> current = head;
        while (current != null) {
            // Apply the criteria (lambda) to the data
            if (criteria.test(current.data)) {
                return current.data;
            }
            current = current.next;
        }
        return null; // Not found
    }

    // Helper to print for visualization
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}