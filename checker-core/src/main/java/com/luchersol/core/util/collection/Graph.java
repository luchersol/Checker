package com.luchersol.core.util.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.luchersol.core.util.Utils;


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
     * @return the weight of the edge as a double, or 0.0 if the weight is null
     */
    public double getWeight(){
            return this.weight == null ? 0.0 : this.weight.doubleValue();
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
     * Adds a node to the graph if it does not already exist.
     *
     * @param node the node to add
     */
    private void addNode(N node) {
        this.adjacencyMap.putIfAbsent(node, new HashSet<>());
    }


    /**
     * Adds an edge to the graph between two nodes, with optional weight and properties.
     *
     * @param from       the source node
     * @param to         the destination node
     * @param weight     the weight of the edge (nullable)
     * @param properties additional properties for the edge (nullable)
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
      * Adds a collection of edges to the graph.
      *
      * @param edges the collection of edges to add
      */
     private void addEdges(Collection<Edge<N,E>> edges) {
         for (Edge<N,E> edge : edges) {
                this.addEdge(edge.from, edge.to, edge.weight, edge.properties);
         }
     }


    /**
     * Returns the set of neighbors for a given node.
     *
     * @param node the node whose neighbors are to be returned
     * @return a set of neighboring nodes
     */
    public Set<N> getNeighbors(N node) {
        return this.adjacencyMap.getOrDefault(node, Collections.emptySet())
                                .stream()
                                .map(Graph.Edge::getTo)
                                .collect(Collectors.toSet());
    }


    /**
     * Returns the set of all nodes in the graph.
     *
     * @return a set of all nodes
     */
    public Set<N> getNodes() {
        return this.adjacencyMap.keySet();
    }


    /**
     * Returns the set of all edges in the graph.
     *
     * @return a set of all edges
     */
    public Set<Graph.Edge<N,E>> getEdges() {
        return this.adjacencyMap.values().stream().flatMap(Set::stream).collect(Collectors.toSet());
    }


    /**
     * Checks if the graph is empty (contains no nodes).
     *
     * @return true if the graph is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.adjacencyMap.isEmpty();
    }


    /**
     * Checks if the graph is a tree (undirected, connected, and acyclic).
     *
     * @return true if the graph is a tree, false otherwise
     */
    public boolean isTree() {
        return !isDirected() && isConnected() && !hasCycle();
    }


    /**
     * Checks if the graph is a binary tree (each node has at most two children).
     *
     * @return true if the graph is a binary tree, false otherwise
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
     * Checks if the graph is directed.
     *
     * @return true if the graph is directed, false otherwise
     */
    public boolean isDirected() {
        return this.directed;
    }


    /**
     * Checks if the graph is connected (there is a path between every pair of nodes).
     *
     * @return true if the graph is connected, false otherwise
     */
    public boolean isConnected() {
        if (this.adjacencyMap.isEmpty()) return true;
        Set<N> visited = new HashSet<>();
        N start = this.adjacencyMap.keySet().iterator().next();
        dfsVisit(start, visited);
        return visited.size() == this.adjacencyMap.size();
    }


    /**
     * Checks if the graph contains any cycles.
     *
     * @return true if the graph has a cycle, false otherwise
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
     * Checks if the graph contains the specified node.
     *
     * @param node the node to check
     * @return true if the node exists in the graph, false otherwise
     */
    public boolean containsNode(N node) {
        return this.adjacencyMap.containsKey(node);
    }


    /**
     * Checks if the graph contains an edge from one node to another.
     *
     * @param from the source node
     * @param to   the destination node
     * @return true if the edge exists, false otherwise
     */
    public boolean containsEdge(N from, N to) {
        return this.adjacencyMap.getOrDefault(from, Collections.emptySet())
                                .stream()
                                .anyMatch(edge -> Utils.equalsContent(edge.getTo(), to));
    }


    /**
     * Checks if the graph contains the specified edge.
     *
     * @param edge the edge to check
     * @return true if the edge exists, false otherwise
     */
    public boolean containsEdge(Graph.Edge<N,E> edge) {
        return this.adjacencyMap.containsKey(edge.from) && this.adjacencyMap.get(edge.from).contains(edge.to);
    }


    /**
     * Checks if any node in the graph matches the given condition.
     *
     * @param condition the predicate to test nodes
     * @return true if any node matches, false otherwise
     */
    public boolean anyNodesMatch(Predicate<N> condition) {
        return this.adjacencyMap.keySet().stream().anyMatch(condition);
    }


    /**
     * Checks if all nodes in the graph match the given condition.
     *
     * @param condition the predicate to test nodes
     * @return true if all nodes match, false otherwise
     */
    public boolean allNodesMatch(Predicate<N> condition) {
        return this.adjacencyMap.keySet().stream().allMatch(condition);
    }


    /**
     * Checks if any edge in the graph matches the given condition.
     *
     * @param condition the predicate to test edges
     * @return true if any edge matches, false otherwise
     */
    public boolean anyEdgesMatch(Predicate<Edge<N,E>> condition) {
        return this.adjacencyMap.values().stream().flatMap(set -> set.stream()).anyMatch(condition);
    }


    /**
     * Checks if all edges in the graph match the given condition.
     *
     * @param condition the predicate to test edges
     * @return true if all edges match, false otherwise
     */
    public boolean allEdgesMatch(Predicate<Edge<N,E>> condition) {
        return this.adjacencyMap.values().stream().flatMap(set -> set.stream()).allMatch(condition);
    }


    /**
     * Checks if there is a path between two nodes in the graph.
     *
     * @param start the starting node
     * @param end   the target node
     * @return true if a path exists, false otherwise
     */
    public boolean hasPath(N start, N end) {
        Set<N> visited = new HashSet<>();
        return dfsPath(start, end, visited);
    }


    /**
     * Depth-first search to determine if a path exists between two nodes.
     *
     * @param current the current node
     * @param target  the target node
     * @param visited the set of already visited nodes
     * @return true if a path exists, false otherwise
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
     * Returns the number of nodes in the graph.
     *
     * @return the number of nodes
     */
    public int countNodes(){
        return this.adjacencyMap.size();
    }


    /**
     * Returns the number of edges in the graph.
     *
     * @return the number of edges
     */
    public int countEdges(){
        int count = adjacencyMap.values().stream().mapToInt(Set::size).sum();
        return directed ? count : count / 2;
    }


    /**
     * Depth-first search to visit all nodes reachable from a given node.
     *
     * @param node    the starting node
     * @param visited the set of already visited nodes
     */
    private void dfsVisit(N node, Set<N> visited) {
        if (!visited.add(node)) return;
        for (N neighbor : getNeighbors(node)) {
            dfsVisit(neighbor, visited);
        }
    }


    /**
     * Depth-first search to detect cycles in the graph.
     *
     * @param node     the current node
     * @param visited  the set of already visited nodes
     * @param recStack the recursion stack for cycle detection
     * @return true if a cycle is detected, false otherwise
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
     * Returns the number of connected components in the graph.
     *
     * @return the number of connected components
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
