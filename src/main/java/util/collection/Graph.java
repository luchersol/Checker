package util.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import util.Utils;


/**
 * Graph is a generic class representing a graph data structure with nodes and weighted edges.
 * <p>
 * It supports both directed and undirected graphs, and provides methods for common graph operations such as
 * adding nodes and edges, checking connectivity, cycles, paths, and more.
 *
 * @param <N> the type of nodes in the graph
 * @param <E> the type of edge weights, which must extend {@link Number}
 */
public class Graph<N,E extends Number> {
    private final Map<N, Set<Edge<N,E>>> adjacencyMap = new HashMap<>();
    private final boolean directed;

    /**
     * Edge represents a connection between two nodes in the graph, possibly with a weight and additional properties.
     *
     * @param <N> the type of nodes
     * @param <E> the type of edge weight, must extend {@link Number}
     */
    public static class Edge<N,E extends Number> {
        N from;
        N to;
        E weight;
        Map<String, ?> properties;

    /**
     * Constructs an edge with specified nodes, weight, and properties.
     *
     * @param from       the source node
     * @param to         the destination node
     * @param weight     the weight of the edge
     * @param properties additional properties for the edge
     */
    public Edge(N from, N to, E weight, Map<String, ?> properties){
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.properties = properties;
        }

    /**
     * Constructs an edge with specified nodes and properties, no weight.
     *
     * @param from       the source node
     * @param to         the destination node
     * @param properties additional properties for the edge
     */
    public Edge(N from, N to, Map<String, ?> properties){
            this(from, to, null, properties);
        }

    /**
     * Constructs an edge with specified nodes and weight, no properties.
     *
     * @param from   the source node
     * @param to     the destination node
     * @param weight the weight of the edge
     */
    public Edge(N from, N to, E weight){
            this(from, to, weight, new HashMap<>());
        }

    /**
     * Constructs an edge with specified nodes, no weight or properties.
     *
     * @param from the source node
     * @param to   the destination node
     */
    public Edge(N from, N to){
            this(from, to, null, new HashMap<>());
        }

    /**
     * Returns the source node of the edge.
     *
     * @return the source node
     */
    public N getFrom(){
            return this.from;
        }

    /**
     * Returns the destination node of the edge.
     *
     * @return the destination node
     */
    public N getTo(){
            return this.to;
        }

    /**
     * Returns the weight of the edge as a double.
     *
     * @return the weight of the edge
     */
    public double getWeight(){
            return this.weight.doubleValue();
        }

    /**
     * Returns the value of a property for this edge, cast to the specified type.
     *
     * @param property the property name
     * @param clazz    the class of the property value
     * @param <T>      the type of the property value
     * @return the property value cast to the specified type
     */
    public <T> T getProperty(String property, Class<T> clazz) {
            return clazz.cast(this.properties.get(property));
        }
    }

    /**
     * Constructs a graph with the specified nodes, edges, and directionality.
     *
     * @param nodes    the collection of nodes
     * @param edges    the collection of edges
     * @param directed true if the graph is directed, false if undirected
     */
    public Graph(Collection<N> nodes, Collection<Edge<N,E>> edges, boolean directed) {
        nodes.forEach(this::addNode);
        this.addEdges(edges);
        this.directed = directed;
    }

    /**
     * Constructs an undirected graph with the specified nodes and edges.
     *
     * @param nodes the collection of nodes
     * @param edges the collection of edges
     */
    public Graph(Collection<N> nodes, Collection<Edge<N,E>> edges) {
        this(nodes, edges, false);
    }

    /**
     * Constructs a graph with the specified edges and directionality.
     *
     * @param edges    the collection of edges
     * @param directed true if the graph is directed, false if undirected
     */
    public Graph(Collection<Edge<N,E>> edges, boolean directed) {
        this.directed = directed;
        this.addEdges(edges);
    }

    /**
     * Constructs an undirected graph with the specified edges.
     *
     * @param edges the collection of edges
     */
    public Graph(Collection<Edge<N,E>> edges) {
        this(edges, false);
    }

    /**
     * Constructs an empty undirected graph.
     */
    public Graph() {
        this(Collections.emptyList(), false);
    }

    /**
     * @param node
     */
    private void addNode(N node) {
        this.adjacencyMap.putIfAbsent(node, new HashSet<>());
    }

    /**
     * @param from
     * @param to
     * @param weight
     * @param properties
     */
    private void addEdge(N from, N to, E weight, Map<String, ?> properties) {
        addNode(from);
        addNode(to);
        this.adjacencyMap.get(from).add(new Graph.Edge<N,E>(from, to, weight, properties));
        if (!directed) {
            this.adjacencyMap.get(to).add(new Graph.Edge<N,E>(from, to, weight, properties));
        }
    }

    /**
     * @param edges
     */
    private void addEdges(Collection<Edge<N,E>> edges) {
       for (Edge<N,E> edge : edges) {
            this.addEdge(edge.from, edge.to, edge.weight, edge.properties);
       }
    }

    /**
     * @param node
     * @return Set<N>
     */
    public Set<N> getNeighbors(N node) {
        return this.adjacencyMap.getOrDefault(node, Collections.emptySet())
                                .stream()
                                .map(Graph.Edge::getTo)
                                .collect(Collectors.toSet());
    }

    /**
     * @return Set<N>
     */
    public Set<N> getNodes() {
        return this.adjacencyMap.keySet();
    }

    /**
     * @return Set<Edge<N, E>>
     */
    public Set<Graph.Edge<N,E>> getEdges() {
        return this.adjacencyMap.values().stream().flatMap(Set::stream).collect(Collectors.toSet());
    }

    /**
     * @return boolean
     */
    // CHECKERS

    public boolean isEmpty() {
        return this.adjacencyMap.isEmpty();
    }

    /**
     * @return boolean
     */
    public boolean isTree() {
        return !isDirected() && isConnected() && !hasCycle();
    }

    /**
     * @return boolean
     */
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

    /**
     * @return boolean
     */
    public boolean isDirected() {
        return this.directed;
    }

    /**
     * @return boolean
     */
    public boolean isConnected() {
        if (this.adjacencyMap.isEmpty()) return true;
        Set<N> visited = new HashSet<>();
        N start = this.adjacencyMap.keySet().iterator().next();
        dfsVisit(start, visited);
        return visited.size() == this.adjacencyMap.size();
    }

    /**
     * @return boolean
     */
    public boolean hasCycle() {
        Set<N> visited = new HashSet<>();
        Set<N> recStack = new HashSet<>();

        for (N node : this.adjacencyMap.keySet()) {
            if (dfsCycle(node, visited, recStack)) return true;
        }
        return false;
    }

    /**
     * @param node
     * @return boolean
     */
    public boolean containsNode(N node) {
        return this.adjacencyMap.containsKey(node);
    }

    /**
     * @param from
     * @param to
     * @return boolean
     */
    public boolean containsEdge(N from, N to) {
        return this.adjacencyMap.getOrDefault(from, Collections.emptySet())
                                .stream()
                                .anyMatch(edge -> Utils.equalsContent(edge.getTo(), to));
    }

    /**
     * @param edge
     * @return boolean
     */
    public boolean containsEdge(Graph.Edge<N,E> edge) {
        return this.adjacencyMap.containsKey(edge.from) && this.adjacencyMap.get(edge.from).contains(edge.to);
    }

    /**
     * @param condition
     * @return boolean
     */
    public boolean anyNodesMatch(Predicate<N> condition) {
        return this.adjacencyMap.keySet().stream().anyMatch(condition);
    }

    /**
     * @param condition
     * @return boolean
     */
    public boolean allNodesMatch(Predicate<N> condition) {
        return this.adjacencyMap.keySet().stream().allMatch(condition);
    }

    /**
     * @param condition
     * @return boolean
     */
    public boolean anyEdgesMatch(Predicate<Edge<N,E>> condition) {
        return this.adjacencyMap.values().stream().flatMap(set -> set.stream()).anyMatch(condition);
    }

    /**
     * @param condition
     * @return boolean
     */
    public boolean allEdgesMatch(Predicate<Edge<N,E>> condition) {
        return this.adjacencyMap.values().stream().flatMap(set -> set.stream()).allMatch(condition);
    }

    /**
     * @param start
     * @param end
     * @return boolean
     */
    public boolean hasPath(N start, N end) {
        Set<N> visited = new HashSet<>();
        return dfsPath(start, end, visited);
    }

    /**
     * @param current
     * @param target
     * @param visited
     * @return boolean
     */
    private boolean dfsPath(N current, N target, Set<N> visited) {
        if (current.equals(target)) return true;
        if (!visited.add(current)) return false;

        for (N neighbor : getNeighbors(current)) {
            if (dfsPath(neighbor, target, visited)) return true;
        }
        return false;
    }

    /**
     * @return int
     */
    public int countNodes(){
        return this.adjacencyMap.size();
    }

    /**
     * @return int
     */
    public int countEdges(){
        int count = adjacencyMap.values().stream().mapToInt(Set::size).sum();
        return directed ? count : count / 2;
    }

    /**
     * @param node
     * @param visited
     */
    private void dfsVisit(N node, Set<N> visited) {
        if (!visited.add(node)) return;
        for (N neighbor : getNeighbors(node)) {
            dfsVisit(neighbor, visited);
        }
    }

    /**
     * @param node
     * @param visited
     * @param recStack
     * @return boolean
     */
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

    /**
     * @return int
     */
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
