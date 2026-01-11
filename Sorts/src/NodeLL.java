public class NodeLL {
    private NodeLL next;
    private NodeLL prev;
    private Product product;

    private int value;

    public NodeLL() {
        this.product = new Product();
    }

    public NodeLL(Product product) {
        this.product = product;
    }

    public NodeLL(NodeLL next, NodeLL prev, Product product) {
        this.next = next;
        this.prev = prev;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public NodeLL getNext() {
        return next;
    }

    public void setNext(NodeLL next) {
        this.next = next;
    }

    public NodeLL getPrev() {
        return prev;
    }

    public void setPrev(NodeLL prev) {
        this.prev = prev;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}


