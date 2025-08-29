package specialized_checkers.collection;

import static util.Message.*;

import java.util.Collection;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.collection.Graph;

public class CheckerGraph<N,E extends Number> extends AbstractChecker<Graph<N,E>, CheckerGraph<N,E>>{

    private static final String INIT_GRAPH = "collections.graph";
    private static final String DEFAULT_NAME = "Graph";

    protected CheckerGraph(Graph<N,E> graph, String name) {
        super(graph, name);
    }

    // STATIC CONSTRUCTOR WITH NAME

    /**
     * @param graph
     * @param name
     * @return CheckerGraph<N, E>
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Graph<N,E> graph, String name) {
        return new CheckerGraph<>(graph, name);
    }

    /**
     * @param edges
     * @param directed
     * @param name
     * @return CheckerGraph<N, E>
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<Graph.Edge<N,E>> edges, boolean directed, String name) {
        Graph<N,E> graph = new Graph<N,E>(edges, directed);
        return check(graph, name);
    }

    /**
     * @param edges
     * @param name
     * @return CheckerGraph<N, E>
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<Graph.Edge<N,E>> edges, String name) {
        Graph<N,E> graph = new Graph<N,E>(edges);
        return check(graph, name);
    }

    /**
     * @param edges
     * @param name
     * @return CheckerGraph<N, E>
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<N> nodes, Collection<Graph.Edge<N,E>> edges, String name) {
        Graph<N,E> graph = new Graph<N,E>(nodes, edges);
        return check(graph, name);
    }

    /**
     * @param edges
     * @param directed
     * @param name
     * @return CheckerGraph<N, E>
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<N> nodes, Collection<Graph.Edge<N,E>> edges, boolean directed, String name) {
        Graph<N,E> graph = new Graph<N,E>(nodes, edges, directed);
        return check(graph, name);
    }

    // STATIC CONSTRUCTOR WITHOUT NAME

    /**
     * @param graph
     * @return CheckerGraph<N, E>
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Graph<N,E> graph) {
        return check(graph, DEFAULT_NAME);
    }

    /**
     * @param edges
     * @param directed
     * @return CheckerGraph<N, E>
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<Graph.Edge<N,E>> edges, boolean directed) {
        Graph<N,E> graph = new Graph<N,E>(edges, directed);
        return check(graph);
    }

    /**
     * @param edges
     * @return CheckerGraph<N, E>
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<Graph.Edge<N,E>> edges) {
        Graph<N,E> graph = new Graph<N,E>(edges);
        return check(graph);
    }

    /**
     * @param edges
     * @return CheckerGraph<N, E>
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<N> nodes, Collection<Graph.Edge<N,E>> edges) {
        Graph<N,E> graph = new Graph<N,E>(nodes, edges);
        return check(graph);
    }

    /**
     * @param edges
     * @param directed
     * @return CheckerGraph<N, E>
     */
    public static <N,E extends Number> CheckerGraph<N,E> check(Collection<N> nodes, Collection<Graph.Edge<N,E>> edges, boolean directed) {
        Graph<N,E> graph = new Graph<N,E>(nodes, edges, directed);
        return check(graph);
    }

    /**
     * @return CheckerGraph<N, E>
     */
    @Override
    protected CheckerGraph<N,E> self() {
        return this;
    }

    /**
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> isEmpty() {
        return is(graph -> graph.isEmpty(), sendMessage(INIT_GRAPH, "is_empty"));
    }

    /**
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> isTree(){
        return is(graph -> graph.isTree(), sendMessage(INIT_GRAPH, "is_tree"));
    }

    /**
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> isBinaryTree() {
        return is(graph -> graph.isBinaryTree(), sendMessage(INIT_GRAPH, "is_binary_tree"));
    }

    /**
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> isDirected() {
        return is(graph -> graph.isDirected(), sendMessage(INIT_GRAPH, "is_directed"));
    }

    /**
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> isConnected() {
        return is(graph -> graph.isConnected(), sendMessage(INIT_GRAPH, "is_connected"));
    }

    /**
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> hasCycle() {
        return is(graph -> graph.hasCycle(), sendMessage(INIT_GRAPH, "has_cycle"));
    }

    /**
     * @param node
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> containsNode(N node) {
        return is(graph -> graph.containsNode(node), sendMessage(INIT_GRAPH, "contains_node"));
    }

    /**
     * @param from
     * @param to
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> containsEdge(N from, N to) {
        return is(graph -> graph.containsEdge(from, to), sendMessage(INIT_GRAPH, "contains_edge"));
    }

    /**
     * @param edge
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> containsEdge(Graph.Edge<N, E> edge) {
        return is(graph -> graph.containsEdge(edge), sendMessage(INIT_GRAPH, "contains_edge"));
    }

    /**
     * @param condition
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> anyNodesMatch(Predicate<N> condition) {
        return is(graph -> graph.anyNodesMatch(condition), sendMessage(INIT_GRAPH, "any_nodes_match"));
    }

    /**
     * @param condition
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> allNodesMatch(Predicate<N> condition) {
        return is(graph -> graph.allNodesMatch(condition), sendMessage(INIT_GRAPH, "all_nodes_match"));
    }

    /**
     * @param condition
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> anyEdgesMatch(Predicate<Graph.Edge<N,E>> condition) {
        return is(graph -> graph.anyEdgesMatch(condition), sendMessage(INIT_GRAPH, "any_edges_match"));
    }

    /**
     * @param condition
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> allEdgesMatch(Predicate<Graph.Edge<N,E>> condition) {
        return is(graph -> graph.allEdgesMatch(condition), sendMessage(INIT_GRAPH, "all_edges_match"));
    }

    /**
     * @param start
     * @param end
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> hasPath(N start, N end) {
        return is(graph -> graph.hasPath(start, end), sendMessage(INIT_GRAPH, "has_path"));
    }

    /**
     * @param min
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> minNodes(int min) {
        return is(graph -> min <= graph.countNodes(), sendMessage(INIT_GRAPH, "min_nodes"));
    }

    /**
     * @param max
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> maxNodes(int max) {
        return is(graph -> graph.countNodes() <= max, sendMessage(INIT_GRAPH, "max_nodes"));
    }

    /**
     * @param min
     * @param max
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> inRangeNodes(int min, int max) {
        return is(graph -> min <= graph.countNodes() && graph.countNodes() <= max, sendMessage(INIT_GRAPH, "in_range_nodes"));
    }

    /**
     * @param min
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> minEdges(int min) {
        return is(graph -> min <= graph.countEdges(), sendMessage(INIT_GRAPH, "min_edges"));
    }

    /**
     * @param max
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> maxEdges(int max) {
        return is(graph -> graph.countEdges() <= max, sendMessage(INIT_GRAPH, "max_edges"));
    }

    /**
     * @param min
     * @param max
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> inRangeEdges(int min, int max) {
        return is(graph -> min <= graph.countEdges() && graph.countEdges() <= max, sendMessage(INIT_GRAPH, "in_range_edges"));
    }

    /**
     * @param min
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> minWeight(double min) {
        return is(graph -> graph.getEdges().stream().allMatch(edge -> min <= edge.getWeight()), sendMessage(INIT_GRAPH, "min_edges"));
    }

    /**
     * @param max
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> maxWeight(double max) {
        return is(graph -> graph.getEdges().stream().allMatch(edge -> edge.getWeight() <= max), sendMessage(INIT_GRAPH, "max_edges"));
    }

    /**
     * @param min
     * @param max
     * @return CheckerGraph<N, E>
     */
    public CheckerGraph<N,E> inRangeWeight(double min, double max) {
        return is(graph -> graph.getEdges().stream().allMatch(edge -> min <= edge.getWeight() && edge.getWeight() <= max), sendMessage(INIT_GRAPH, "in_range_edges"));
    }

}
