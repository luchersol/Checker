package specialized_checkers.collection;

import static util.Message.*;

import java.util.Collection;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.collection.Graph;

/**
 * A specialized checker for validating properties and constraints on graph data structures.
 * <p>
 * The {@code CheckerGraph} class provides a fluent API for asserting various conditions
 * on graphs, such as connectivity, acyclicity, node and edge existence, and more.
 * It supports both directed and undirected graphs, as well as weighted edges.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * Graph<String, Integer> graph = ...;
 * CheckerGraph<String, Integer> checker = CheckerGraph.check(graph)
 *     .isConnected()
 *     .minNodes(3)
 *     .hasCycle();
 * }</pre>
 *
 * @param <N> the type of nodes in the graph
 * @param <E> the type of edge weights (must extend {@link Number})
 *
 * @see Graph
 * @see AbstractChecker
 */
public class CheckerGraph<N,E extends Number> extends AbstractChecker<Graph<N,E>, CheckerGraph<N,E>>{

    private static final String INIT_GRAPH = "collections.graph";
    private static final String DEFAULT_NAME = "Graph";

    /**
     * Constructs a new {@code CheckerGraph} with the specified underlying graph and name.
     *
     * @param graph the underlying graph structure to be used by this {@code CheckerGraph}
     * @param name the name assigned to this {@code CheckerGraph}
     */
    protected CheckerGraph(Graph<N,E> graph, String name) {
        super(graph, name);
    }

    /**
     * Creates a CheckerGraph for the given graph and assigns a custom name.
     *
     * @param <N> the type of nodes in the graph
     * @param <E> the type of edge weights (must extend Number)
     * @param graph the graph to check
     * @param name the name to assign to this checker
     * @return a CheckerGraph instance for the given graph
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Graph<N,E> graph, String name) {
        return new CheckerGraph<>(graph, name);
    }

    /**
     * Creates a CheckerGraph from a collection of edges, specifying if the graph is directed, and assigns a name.
     *
     * @param <N> the type of nodes in the graph
     * @param <E> the type of edge weights (must extend Number)
     * @param edges the collection of edges to build the graph
     * @param directed true if the graph is directed, false otherwise
     * @param name the name to assign to this checker
     * @return a CheckerGraph instance for the constructed graph
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<Graph.Edge<N,E>> edges, boolean directed, String name) {
        Graph<N,E> graph = new Graph<N,E>(edges, directed);
        return check(graph, name);
    }

    /**
     * Creates a CheckerGraph from a collection of edges and assigns a name.
     * The graph will be undirected by default.
     *
     * @param <N> the type of nodes in the graph
     * @param <E> the type of edge weights (must extend Number)
     * @param edges the collection of edges to build the graph
     * @param name the name to assign to this checker
     * @return a CheckerGraph instance for the constructed graph
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<Graph.Edge<N,E>> edges, String name) {
        Graph<N,E> graph = new Graph<N,E>(edges);
        return check(graph, name);
    }

    /**
     * Creates a CheckerGraph from a collection of nodes and edges, and assigns a name.
     *
     * @param <N> the type of nodes in the graph
     * @param <E> the type of edge weights (must extend Number)
     * @param nodes the collection of nodes in the graph
     * @param edges the collection of edges in the graph
     * @param name the name to assign to this checker
     * @return a CheckerGraph instance for the constructed graph
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<N> nodes, Collection<Graph.Edge<N,E>> edges, String name) {
        Graph<N,E> graph = new Graph<N,E>(nodes, edges);
        return check(graph, name);
    }

    /**
     * Creates a CheckerGraph from a collection of nodes and edges, specifying if the graph is directed, and assigns a name.
     *
     * @param <N> the type of nodes in the graph
     * @param <E> the type of edge weights (must extend Number)
     * @param nodes the collection of nodes in the graph
     * @param edges the collection of edges in the graph
     * @param directed true if the graph is directed, false otherwise
     * @param name the name to assign to this checker
     * @return a CheckerGraph instance for the constructed graph
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<N> nodes, Collection<Graph.Edge<N,E>> edges, boolean directed, String name) {
        Graph<N,E> graph = new Graph<N,E>(nodes, edges, directed);
        return check(graph, name);
    }

    // STATIC CONSTRUCTOR WITHOUT NAME

    /**
     * Creates a CheckerGraph for the given graph with a default name.
     *
     * @param <N> the type of nodes in the graph
     * @param <E> the type of edge weights (must extend Number)
     * @param graph the graph to check
     * @return a CheckerGraph instance for the given graph
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Graph<N,E> graph) {
        return check(graph, DEFAULT_NAME);
    }

    /**
     * Creates a CheckerGraph from a collection of edges, specifying if the graph is directed, with a default name.
     *
     * @param <N> the type of nodes in the graph
     * @param <E> the type of edge weights (must extend Number)
     * @param edges the collection of edges to build the graph
     * @param directed true if the graph is directed, false otherwise
     * @return a CheckerGraph instance for the constructed graph
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<Graph.Edge<N,E>> edges, boolean directed) {
        Graph<N,E> graph = new Graph<N,E>(edges, directed);
        return check(graph);
    }

    /**
     * Creates a CheckerGraph from a collection of edges with a default name.
     * The graph will be undirected by default.
     *
     * @param <N> the type of nodes in the graph
     * @param <E> the type of edge weights (must extend Number)
     * @param edges the collection of edges to build the graph
     * @return a CheckerGraph instance for the constructed graph
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<Graph.Edge<N,E>> edges) {
        Graph<N,E> graph = new Graph<N,E>(edges);
        return check(graph);
    }

    /**
     * Creates a CheckerGraph from a collection of nodes and edges with a default name.
     *
     * @param <N> the type of nodes in the graph
     * @param <E> the type of edge weights (must extend Number)
     * @param nodes the collection of nodes in the graph
     * @param edges the collection of edges in the graph
     * @return a CheckerGraph instance for the constructed graph
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<N> nodes, Collection<Graph.Edge<N,E>> edges) {
        Graph<N,E> graph = new Graph<N,E>(nodes, edges);
        return check(graph);
    }

    /**
     * Creates a CheckerGraph from a collection of nodes and edges, specifying if the graph is directed, with a default name.
     *
     * @param <N> the type of nodes in the graph
     * @param <E> the type of edge weights (must extend Number)
     * @param nodes the collection of nodes in the graph
     * @param edges the collection of edges in the graph
     * @param directed true if the graph is directed, false otherwise
     * @return a CheckerGraph instance for the constructed graph
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<N> nodes, Collection<Graph.Edge<N,E>> edges, boolean directed) {
        Graph<N,E> graph = new Graph<N,E>(nodes, edges, directed);
        return check(graph);
    }

    /**
     * Returns this instance (for fluent API).
     *
     * @return this CheckerGraph instance
     */
    @Override
    protected CheckerGraph<N,E> self() {
        return this;
    }

    /**
     * Checks if the graph is empty (contains no nodes).
     *
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> isEmpty() {
        return is(graph -> graph.isEmpty(), sendMessage(INIT_GRAPH, "is_empty"));
    }

    /**
     * Checks if the graph is a tree (connected and acyclic).
     *
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> isTree(){
        return is(graph -> graph.isTree(), sendMessage(INIT_GRAPH, "is_tree"));
    }

    /**
     * Checks if the graph is a binary tree (each node has at most two children).
     *
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> isBinaryTree() {
        return is(graph -> graph.isBinaryTree(), sendMessage(INIT_GRAPH, "is_binary_tree"));
    }

    /**
     * Checks if the graph is directed.
     *
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> isDirected() {
        return is(graph -> graph.isDirected(), sendMessage(INIT_GRAPH, "is_directed"));
    }

    /**
     * Checks if the graph is connected (there is a path between every pair of nodes).
     *
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> isConnected() {
        return is(graph -> graph.isConnected(), sendMessage(INIT_GRAPH, "is_connected"));
    }

    /**
     * Checks if the graph contains at least one cycle.
     *
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> hasCycle() {
        return is(graph -> graph.hasCycle(), sendMessage(INIT_GRAPH, "has_cycle"));
    }

    /**
     * Checks if the graph contains the specified node.
     *
     * @param node the node to check for
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> containsNode(N node) {
        return is(graph -> graph.containsNode(node), sendMessage(INIT_GRAPH, "contains_node"));
    }

    /**
     * Checks if the graph contains an edge from one node to another.
     *
     * @param from the source node
     * @param to the destination node
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> containsEdge(N from, N to) {
        return is(graph -> graph.containsEdge(from, to), sendMessage(INIT_GRAPH, "contains_edge"));
    }

    /**
     * Checks if the graph contains the specified edge.
     *
     * @param edge the edge to check for
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> containsEdge(Graph.Edge<N, E> edge) {
        return is(graph -> graph.containsEdge(edge), sendMessage(INIT_GRAPH, "contains_edge"));
    }

    /**
     * Checks if any node in the graph matches the given condition.
     *
     * @param condition the predicate to test nodes
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> anyNodesMatch(Predicate<N> condition) {
        return is(graph -> graph.anyNodesMatch(condition), sendMessage(INIT_GRAPH, "any_nodes_match"));
    }

    /**
     * Checks if all nodes in the graph match the given condition.
     *
     * @param condition the predicate to test nodes
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> allNodesMatch(Predicate<N> condition) {
        return is(graph -> graph.allNodesMatch(condition), sendMessage(INIT_GRAPH, "all_nodes_match"));
    }

    /**
     * Checks if any edge in the graph matches the given condition.
     *
     * @param condition the predicate to test edges
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> anyEdgesMatch(Predicate<Graph.Edge<N,E>> condition) {
        return is(graph -> graph.anyEdgesMatch(condition), sendMessage(INIT_GRAPH, "any_edges_match"));
    }

    /**
     * Checks if all edges in the graph match the given condition.
     *
     * @param condition the predicate to test edges
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> allEdgesMatch(Predicate<Graph.Edge<N,E>> condition) {
        return is(graph -> graph.allEdgesMatch(condition), sendMessage(INIT_GRAPH, "all_edges_match"));
    }

    /**
     * Checks if there is a path between two nodes in the graph.
     *
     * @param start the starting node
     * @param end the ending node
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> hasPath(N start, N end) {
        return is(graph -> graph.hasPath(start, end), sendMessage(INIT_GRAPH, "has_path"));
    }

    /**
     * Checks if the graph has at least the specified minimum number of nodes.
     *
     * @param min the minimum number of nodes
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> minNodes(int min) {
        return is(graph -> min <= graph.countNodes(), sendMessage(INIT_GRAPH, "min_nodes"));
    }

    /**
     * Checks if the graph has at most the specified maximum number of nodes.
     *
     * @param max the maximum number of nodes
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> maxNodes(int max) {
        return is(graph -> graph.countNodes() <= max, sendMessage(INIT_GRAPH, "max_nodes"));
    }

    /**
     * Checks if the number of nodes in the graph is within the specified range (inclusive).
     *
     * @param min the minimum number of nodes
     * @param max the maximum number of nodes
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> inRangeNodes(int min, int max) {
        return is(graph -> min <= graph.countNodes() && graph.countNodes() <= max, sendMessage(INIT_GRAPH, "in_range_nodes"));
    }

    /**
     * Checks if the graph has at least the specified minimum number of edges.
     *
     * @param min the minimum number of edges
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> minEdges(int min) {
        return is(graph -> min <= graph.countEdges(), sendMessage(INIT_GRAPH, "min_edges"));
    }

    /**
     * Checks if the graph has at most the specified maximum number of edges.
     *
     * @param max the maximum number of edges
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> maxEdges(int max) {
        return is(graph -> graph.countEdges() <= max, sendMessage(INIT_GRAPH, "max_edges"));
    }

    /**
     * Checks if the number of edges in the graph is within the specified range (inclusive).
     *
     * @param min the minimum number of edges
     * @param max the maximum number of edges
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> inRangeEdges(int min, int max) {
        return is(graph -> min <= graph.countEdges() && graph.countEdges() <= max, sendMessage(INIT_GRAPH, "in_range_edges"));
    }

    /**
     * Checks if all edge weights in the graph are at least the specified minimum value.
     *
     * @param min the minimum edge weight
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> minWeight(double min) {
        return is(graph -> graph.getEdges().stream().allMatch(edge -> min <= edge.getWeight()), sendMessage(INIT_GRAPH, "min_edges"));
    }

    /**
     * Checks if all edge weights in the graph are at most the specified maximum value.
     *
     * @param max the maximum edge weight
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> maxWeight(double max) {
        return is(graph -> graph.getEdges().stream().allMatch(edge -> edge.getWeight() <= max), sendMessage(INIT_GRAPH, "max_edges"));
    }

    /**
     * Checks if all edge weights in the graph are within the specified range (inclusive).
     *
     * @param min the minimum edge weight
     * @param max the maximum edge weight
     * @return this CheckerGraph instance
     */
    public CheckerGraph<N,E> inRangeWeight(double min, double max) {
        return is(graph -> graph.getEdges().stream().allMatch(edge -> min <= edge.getWeight() && edge.getWeight() <= max), sendMessage(INIT_GRAPH, "in_range_edges"));
    }

}
