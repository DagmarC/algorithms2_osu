import java.util.ArrayList;
import java.util.List;

public class Node {
    private City city;
    private Node parent;
    private final ArrayList<Node> children;

    public Node() {
        children = new ArrayList<>();
    }

    public Node(City city) {
        this.city = city;
        this.children = new ArrayList<>();
    }

    public String cityName() {
        return this.city.name();
    }

    public void setParent(Node parent) {
        this.parent = parent; // null if node was a root node
    }

    public Node getParent() {
        return this.parent;
    }

    public void addChild(Node Child) {
        this.children.add(Child);
    }

    public List<Node> getChildren() {
        return this.children;
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {
        return city.name();
    }
}
