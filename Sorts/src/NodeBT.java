public class NodeBT {
    private NodeBT left;
    private NodeBT right;
    private NodeBT parent;

    private Product product;
    int value;


    public NodeBT(NodeBT left, NodeBT right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
        this.product = new Product();
    }

    public NodeBT(NodeBT left, NodeBT right, NodeBT parent, Product product, int value) {
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.product = product;
        this.value = value;
    }

    public NodeBT(NodeBT left, NodeBT right, NodeBT parent, int value) {
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.value = value;
    }

    public NodeBT() {
        this.product = new Product();
    }

    public NodeBT(int value) {
        this.value = value;
        this.product = new Product();
    }

    public NodeBT getLeft() {
        return left;
    }

    public void setLeft(NodeBT left) {
        this.left = left;
    }

    public NodeBT getRight() {
        return right;
    }

    public void setRight(NodeBT right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
