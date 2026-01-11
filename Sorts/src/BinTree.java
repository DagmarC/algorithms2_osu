public class BinTree {

    private static class Node {
        Product product;
        Node left;
        Node right;
        Node parent;

        public Node(Product product) { this.product = product; }
    }

    private Node root;

    public int countNodes() {
        if (root == null)
            return 0;

        return countNodesRec(root);
    }

    private int countNodesRec(Node node) {
        if (node == null) { return 0; }
        return 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }

    public Product findProduct(String name) {
        if (root == null)
            return null;

        return findProductRec(name, root);
    }

    public Product findProductRec(String name, Node node) {
        if (node == null)
            return null;

        int cmp = name.compareTo(node.product.getName());

        if (cmp == 0)
            return node.product;

        if (cmp < 0)
            return findProductRec(name, node.left);

        return findProductRec(name, node.right);
    }

}


