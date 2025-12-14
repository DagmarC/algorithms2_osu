import java.util.*;

public class CitiesGraph {
    private final Cities cities;
    private final HashMap<UUID, Node> cityMap;
    private final HashSet<Node> rootNodes;

    public CitiesGraph(Cities cities) {
        this.cities = cities;
        this.cityMap = new HashMap<>();
        this.rootNodes = new HashSet<>(); // In case the map is not the tree but the forest, it has multiple root nodes
        buildGraph();
    }

    private void buildGraph() {
        for(City city : cities.getCities()) {
            // Call the recursion only on the root nodes -> Root node is node, where node.parent is null
            if (city.parentId() == null) {
                buildGraphRec(city.id(), null);
            }
        }
    }

    private void buildGraphRec(UUID uuid, Node parent) {
        // Already visited - to prevent cycle
        if (cityMap.containsKey(uuid)) {
           return;
        }
        // Create node from the current city
        City city = cities.findByUUID(uuid);
        if (city == null) {
            System.err.println("WARNING: Data existence issue. City with ID " + uuid + " is referenced as a child/root but does not exist in the city list.");
            return;
        }
        Node node = new Node(city);

        // Mark it as visited / created
        cityMap.put(uuid, node);

        // Set parent to node
        node.setParent(parent);

        // If parent is null, then we have root node - we need (all) root(s) for tree traversal in methods
        if (parent == null) {
            rootNodes.add(node);
        } else {
            parent.addChild(node);
        }
        // Recursively call method on node`s children, where current node will act as their parent
        for (UUID childUuid : city.childrenIds()) {
            buildGraphRec(childUuid, node);
        }
    }

    public void printGraph() {
        for(Node node : rootNodes) {
            System.out.println("---ROOT NODE---");
            printGraphRec(node);
        }
        System.out.println("---END---");
    }

    private void printGraphRec(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(" " + node + " ");
        for  (Node child : node.getChildren()) {
            printGraphRec(child);
        }
    }

    public int countNodes() {
        return cityMap.size();
    }

    public List<String> shortestPath(UUID start, UUID end) throws IllegalArgumentException {

        if (!cityMap.containsKey(start) || !cityMap.containsKey(end)) {
            throw new IllegalArgumentException("Start or end city UUID is invalid (or both).");
        }

        Node startNode = cityMap.get(start);
        Node endNode = cityMap.get(end);

        if (start.equals(end)) {
            return List.of(startNode.cityName());
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        Map<Node, Node> parentMap = new HashMap<>();

        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            // Check if we are in the end
            if (currentNode.equals(endNode)) {
                return createPath(parentMap, endNode);
            }
            // Add neighbours of the currentNode
            List<Node> neighbours = new ArrayList<>(currentNode.getChildren());
            if (currentNode.getParent() != null) {
                neighbours.add(currentNode.getParent());
            }
            for (Node neighbour : neighbours) {
                // If node was not already visited - explore
                if (!visited.contains(neighbour)) {
                    queue.add(neighbour);
                    visited.add(neighbour);
                    parentMap.put(neighbour, currentNode); // parentMap[node] = parent
                }
            }
        }
        return List.of();  // Path does not exist
    }

    private List<String> createPath(Map<Node, Node> parentMap, Node node) {
        LinkedList<String> path = new LinkedList<>();

        Node currentNode = node;
        while (currentNode != null) {
            path.addFirst(currentNode.cityName());
            currentNode = parentMap.get(currentNode);
        }
        return path;
    }
}


