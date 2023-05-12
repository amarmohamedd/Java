import java.util.*;

public class Main {
    static class Node {
        String id;
        List<Node> neighbors = new ArrayList<>();

        Node(String id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        Map<String, Node> graph = new HashMap<>();

        // Connectivity information
        connect(graph, "A", "B");
        connect(graph, "A", "C");
        connect(graph, "A", "D");

        // Build routing tables
        for (Node node : graph.values()) {
            System.out.println("Routing table for " + node.id + ":");
            Map<String, String> routingTable = buildRoutingTable(graph, node);
            for (Map.Entry<String, String> entry : routingTable.entrySet()) {
                System.out.println("(" + entry.getKey() + "," + entry.getValue() + ")");
            }
            System.out.println();
        }
    }

    static void connect(Map<String, Node> graph, String from, String to) {
        Node fromNode = graph.computeIfAbsent(from, Node::new);
        Node toNode = graph.computeIfAbsent(to, Node::new);
        fromNode.neighbors.add(toNode);
        toNode.neighbors.add(fromNode);  // Assuming undirected graph
    }

    static Map<String, String> buildRoutingTable(Map<String, Node> graph, Node startNode) {
        Map<String, String> routingTable = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> prev = new HashMap<>();

        queue.offer(startNode);
        visited.add(startNode.id);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (Node neighbor : node.neighbors) {
                if (!visited.contains(neighbor.id)) {
                    queue.offer(neighbor);
                    visited.add(neighbor.id);
                    prev.put(neighbor.id, node.id);
                }
            }
        }

        for (String nodeId : graph.keySet()) {
            if (!nodeId.equals(startNode.id)) {
                String nextHop = nodeId;
                while (prev.get(nextHop) != null && !prev.get(nextHop).equals(startNode.id)) {
                    nextHop = prev.get(nextHop);
                }
                routingTable.put(nodeId, nextHop);
            }
        }

        return routingTable;
    }
}
