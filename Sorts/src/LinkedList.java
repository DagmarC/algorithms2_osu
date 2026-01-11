import java.util.function.Predicate;

public class LinkedList {

    // Inner class is now specific to Product
    private static class Node {
        Product product;
        Node next;
        Node prev;

        public Node(Product product) {
            this.product = product;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    private void setFirstNode(Node newNode) {
        head = tail = newNode;
        size++;
    }

    public int size() {
        return size;
    }

    public boolean clear() {
        head = tail = null;
        size = 0;
        return true;
    }

    // Add to the beginning
    public void addFirst(Product product) {
        Node newNode = new Node(product);

        if (isEmpty()) {
            setFirstNode(newNode);
            return;
        }

        newNode.next = head;
        head.prev= newNode;
        head = newNode;

        size++;
    }

    public void addLast(Product product) {
        Node newNode = new Node(product);

        if (isEmpty()) {
            setFirstNode(newNode);
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;

        size++;
    }

    // Insert at specific index
    public void insert(int index, Product p) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        // Add new head
        if (index == 0) {
            addFirst(p);
            return;
        }

        // Add new tail
        if (index == size) {
            addLast(p);
            return;
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        Node newNode = new Node(p);
        Node prev = current.prev;

        prev.next = newNode;
        newNode.prev = prev;

        newNode.next = current;
        current.prev = newNode;

        size++;
    }

    public double findMin() {
        double minPrice = Integer.MAX_VALUE;

        if (isEmpty()) {
            System.out.println("List is empty");
            return minPrice;
        }

        Node current = head;
        while (current != null) {
            if (current.product.getPrice() < minPrice) {
                minPrice = current.product.getPrice();
            }
            current = current.next;
        }
        return minPrice;
    }

    /**
     * Enhanced Search: Strategy Pattern
     * Allows finding a product by ANY criteria (ID, Name, Price).
     */
    public Product search(Predicate<Product> criteria) {
        Node current = head;
        while (current != null) {
            // Check if current product satisfies the criteria
            if (criteria.test(current.product)) {
                return current.product;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Standard Search: Find by Exact Product (Relies on equals/ID)
     */
    public boolean contains(Product productToCheck) {
        return search(p -> p.equals(productToCheck)) != null;
    }


    // Prerequisite: First match node-product will be removed
    public boolean remove(Product p) {
        if (isEmpty()) {
            System.out.println("Product can't be removed. List is empty.");
            return false;
        }

        Node current = head;
        while (current != null) {

            if (current.product.equals(p)) {
                removeNode(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        // Remove head element (not tail)
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null; // remove pointer
        }

        if (next == null) {
            tail = prev; // remove tail
        } else {
            next.prev = prev;
            node.next = null;
        }
        node.product = null; // For garbage Collector
        size--;
    }

    public void printList() {
        Node current = head;
        System.out.println("--- List Contents ---");
        while (current != null) {
            System.out.println(current.product);
            current = current.next;
        }
    }
}
