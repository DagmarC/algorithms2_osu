// Ukladanie stavov algoritmov, a rekurzivnych
// Synchrinizacia, historia ww stranok, UNDO/REDO

public class Stack {

    private static class Node {
        private int data;
        private Product product;
        private Node next;

        public Node(int data, Product product) {
            this.data = data;
            this.product = product;
        }

        public Node(int data) {
            this.data = data;
            product = new Product();
        }

        public Node(Product product) {
            this.product = product;
        }
    }

    private Node first;
    private int size;

    public void push(int data) {
        Node node = new Node(data);

        node.next = first;
        first = node;
        size++;
    }

    public int popData() {
        if (size == 0) { return -1; }

        int data = first.data;
        first = first.next;

        return data;
    }

    public Product popProduct() {
        if (size == 0) { return null; }

        Product product = first.product;
        first = first.next;

        return product;
    }
}
