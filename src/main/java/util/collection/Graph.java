package util.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Graph<N,E extends Number> {
    private final Map<N, Set<Edge<N,E>>> adjacencyMap = new HashMap<>();
    private final boolean directed;

    public static class Edge<N,E extends Number> {
        N from;
        N to;
        E weight;
        Map<String, ?> properties;

        public Edge(N from, N to, E weight, Map<String, ?> properties){
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.properties = properties;
        }
        
        public Edge(N from, N to, Map<String, ?> properties){
            this(from, to, null, properties);
        }

        public Edge(N from, N to, E weight){
            this(from, to, weight, new HashMap<>());
        }

        public Edge(N from, N to){
            this(from, to, null, new HashMap<>());
        }

        public N getFrom(){
            return this.from;
        }

        public N getTo(){
            return this.to;
        }

        public double getWeight(){
            return this.weight.doubleValue();
        }

        public <T> T getProperty(String property, Class<T> clazz) {
            return clazz.cast(this.properties.get(property));
        }
    }

    public Graph(Collection<N> nodes, Collection<Edge<N,E>> edges, boolean directed) {
        nodes.forEach(this::addNode);
        this.addEdges(edges);
        this.directed = directed;
    }

    public Graph(Collection<N> nodes, Collection<Edge<N,E>> edges) {
        this(nodes, edges, false);
    }

    public Graph(Collection<Edge<N,E>> edges, boolean directed) {
        this.directed = directed;
        this.addEdges(edges);
    }

    public Graph(Collection<Edge<N,E>> edges) {
        this(edges, false);
    }

    public Graph() {
        this(Collections.emptyList(), false);
    }

    private void addNode(N node) {
        this.adjacencyMap.putIfAbsent(node, new HashSet<>());
    }

    private void addEdge(N from, N to, E weight, Map<String, ?> properties) {
        addNode(from);
        addNode(to);
        this.adjacencyMap.get(from).add(new Graph.Edge<N,E>(from, to, weight, properties));
        if (!directed) {
            this.adjacencyMap.get(to).add(new Graph.Edge<N,E>(from, to, weight, properties));
        }
    }

    private void addEdges(Collection<Edge<N,E>> edges) {
       for (Edge<N,E> edge : edges) {
            this.addEdge(edge.from, edge.to, edge.weight, edge.properties);
       }
    }

    public Set<N> getNeighbors(N node) {
        return this.adjacencyMap.getOrDefault(node, Collections.emptySet())
                                .stream()
                                .map(Graph.Edge::getTo)
                                .collect(Collectors.toSet());
    }

    public Set<N> getNodes() {
        return this.adjacencyMap.keySet();
    }

    public Set<Graph.Edge<N,E>> getEdges() {
        return this.adjacencyMap.values().stream().flatMap(Set::stream).collect(Collectors.toSet());
    }

    // CHECKERS

    public boolean isEmpty() {
        return this.adjacencyMap.isEmpty();
    }

    public boolean isTree() {
        return !isDirected() && isConnected() && !hasCycle();
    }

    public boolean isBinaryTree() {
        if(isTree()) return false;

        N root = null;
        for (N node : adjacencyMap.keySet()) {
            if (getNeighbors(node).size() <= 2) {
                root = node;
                break;
            }
        }
        if (root == null) return false;

        for (N node : adjacencyMap.keySet()) {
            int degree = getNeighbors(node).size();
            if (node.equals(root)) {
                if (degree > 2) return false;
            } else {
                if (degree > 3) return false;
            }
        }
        return true;
    }

    public boolean isDirected() {
        return this.directed;
    }

    public boolean isConnected() {
        if (this.adjacencyMap.isEmpty()) return true;
        Set<N> visited = new HashSet<>();
        N start = this.adjacencyMap.keySet().iterator().next();
        dfsVisit(start, visited);
        return visited.size() == this.adjacencyMap.size();
    }

    public boolean hasCycle() {
        Set<N> visited = new HashSet<>();
        Set<N> recStack = new HashSet<>();

        for (N node : this.adjacencyMap.keySet()) {
            if (dfsCycle(node, visited, recStack)) return true;
        }
        return false;
    }

    public boolean containsNode(N node) {
        return this.adjacencyMap.containsKey(node);
    }

    public boolean containsEdge(N from, N to) {
        return this.adjacencyMap.getOrDefault(from, Collections.emptySet())
                                .stream()
                                .anyMatch(edge -> Objects.equals(edge.getTo(), to));
    }

    public boolean containsEdge(Graph.Edge<N,E> edge) {
        return this.adjacencyMap.containsKey(edge.from) && this.adjacencyMap.get(edge.from).contains(edge.to);
    }

    public boolean anyNodesMatch(Predicate<N> condition) {
        return this.adjacencyMap.keySet().stream().anyMatch(condition);
    }

    public boolean allNodesMatch(Predicate<N> condition) {
        return this.adjacencyMap.keySet().stream().allMatch(condition);
    }

    public boolean anyEdgesMatch(Predicate<Edge<N,E>> condition) {
        return this.adjacencyMap.values().stream().flatMap(set -> set.stream()).anyMatch(condition);
    }

    public boolean allEdgesMatch(Predicate<Edge<N,E>> condition) {
        return this.adjacencyMap.values().stream().flatMap(set -> set.stream()).allMatch(condition);
    }

    public boolean hasPath(N start, N end) {
        Set<N> visited = new HashSet<>();
        return dfsPath(start, end, visited);
    }

    private boolean dfsPath(N current, N target, Set<N> visited) {
        if (current.equals(target)) return true;
        if (!visited.add(current)) return false;

        for (N neighbor : getNeighbors(current)) {
            if (dfsPath(neighbor, target, visited)) return true;
        }
        return false;
    }

    public int countNodes(){
        return this.adjacencyMap.size();
    }

    public int countEdges(){
        int count = adjacencyMap.values().stream().mapToInt(Set::size).sum();
        return directed ? count : count / 2;
    }

    private void dfsVisit(N node, Set<N> visited) {
        if (!visited.add(node)) return;
        for (N neighbor : getNeighbors(node)) {
            dfsVisit(neighbor, visited);
        }
    }

    private boolean dfsCycle(N node, Set<N> visited, Set<N> recStack) {
        if (recStack.contains(node)) return true;
        if (visited.contains(node)) return false;

        visited.add(node);
        recStack.add(node);

        for (N neighbor : getNeighbors(node)) {
            if (dfsCycle(neighbor, visited, recStack)) return true;
        }

        recStack.remove(node);
        return false;
    }

    public int connectedComponents() {
        Set<N> visited = new HashSet<>();
        int count = 0;

        for (N node : this.adjacencyMap.keySet()) {
            if (!visited.contains(node)) {
                dfsVisit(node, visited);
                count++;
            }
        }
        return count;
    }

}
