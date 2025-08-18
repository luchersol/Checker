package specialized_checkers.collections;

import static util.Message.*;

import java.util.function.Predicate;

import util.AbstractChecker;
import util.Graph;

public class CheckerGraph<N,E extends Number> extends AbstractChecker<Graph<N,E>, CheckerGraph<N,E>>{

    private static final String INIT_GRAPH = "collections.graph";

    public CheckerGraph(Graph<N,E> object, String name) {
        super(object, name);
    }

    @Override
    protected CheckerGraph<N,E> self() {
        return this;
    }

    public CheckerGraph<N,E> isEmpty() {
        return is(graph -> graph.isEmpty(), sendMessage(INIT_GRAPH, "is_empty"));
    }

    public CheckerGraph<N,E> isTree(){
        return is(graph -> graph.isTree(), sendMessage(INIT_GRAPH, "is_tree"));
    }

    public CheckerGraph<N,E> isBinaryTree() {
        return is(graph -> graph.isBinaryTree(), sendMessage(INIT_GRAPH, "is_binary_tree"));
    }
    
    public CheckerGraph<N,E> isDirected() {
        return is(graph -> graph.isDirected(), sendMessage(INIT_GRAPH, "is_directed"));
    }

    public CheckerGraph<N,E> isConnected() {
        return is(graph -> graph.isConnected(), sendMessage(INIT_GRAPH, "is_connected"));
    }

    public CheckerGraph<N,E> hasCycle() {
        return is(graph -> graph.hasCycle(), sendMessage(INIT_GRAPH, "has_cycle"));
    }

    public CheckerGraph<N,E> containsNode(N node) {
        return is(graph -> graph.containsNode(node), sendMessage(INIT_GRAPH, "contains_node"));
    }

    public CheckerGraph<N,E> containsEdge(N from, N to) {
        return is(graph -> graph.containsEdge(from, to), sendMessage(INIT_GRAPH, "contains_edge"));
    }

    public CheckerGraph<N,E> containsEdge(Graph.Edge<N, E> edge) {
        return is(graph -> graph.containsEdge(edge), sendMessage(INIT_GRAPH, "contains_edge"));
    }

    public CheckerGraph<N,E> anyNodesMatch(Predicate<N> condition) {
        return is(graph -> graph.anyNodesMatch(condition), sendMessage(INIT_GRAPH, "any_nodes_match"));
    }

    public CheckerGraph<N,E> allNodesMatch(Predicate<N> condition) {
        return is(graph -> graph.allNodesMatch(condition), sendMessage(INIT_GRAPH, "all_nodes_match"));
    }

    public CheckerGraph<N,E> anyEdgesMatch(Predicate<Graph.Edge<N,E>> condition) {
        return is(graph -> graph.anyEdgesMatch(condition), sendMessage(INIT_GRAPH, "any_edges_match"));
    }

    public CheckerGraph<N,E> allEdgesMatch(Predicate<Graph.Edge<N,E>> condition) {
        return is(graph -> graph.allEdgesMatch(condition), sendMessage(INIT_GRAPH, "all_edges_match"));
    }

    public CheckerGraph<N,E> hasPath(N start, N end) {
        return is(graph -> graph.hasPath(start, end), sendMessage(INIT_GRAPH, "has_path"));
    } 

    public CheckerGraph<N,E> minNodes(int min) {
        return is(graph -> min <= graph.countNodes(), sendMessage(INIT_GRAPH, "min_nodes"));
    }

    public CheckerGraph<N,E> maxNodes(int max) {
        return is(graph -> graph.countNodes() <= max, sendMessage(INIT_GRAPH, "max_nodes"));
    }

    public CheckerGraph<N,E> inRangeNodes(int min, int max) {
        return is(graph -> min <= graph.countNodes() && graph.countNodes() <= max, sendMessage(INIT_GRAPH, "in_range_nodes"));
    }

    public CheckerGraph<N,E> minEdges(int min) {
        return is(graph -> min <= graph.countNodes(), sendMessage(INIT_GRAPH, "min_edges"));
    }

    public CheckerGraph<N,E> maxEdges(int max) {
        return is(graph -> graph.countNodes() <= max, sendMessage(INIT_GRAPH, "max_edges"));
    }

    public CheckerGraph<N,E> inRangeEdges(int min, int max) {
        return is(graph -> min <= graph.countNodes() && graph.countNodes() <= max, sendMessage(INIT_GRAPH, "in_range_edges"));
    }

    public CheckerGraph<N,E> minWeight(double min) {
        return is(graph -> graph.getEdges().stream().allMatch(edge -> min <= edge.getWeigth()), sendMessage(INIT_GRAPH, "min_edges"));
    }

    public CheckerGraph<N,E> maxWeight(double max) {
        return is(graph -> graph.getEdges().stream().allMatch(edge -> edge.getWeigth() <= max), sendMessage(INIT_GRAPH, "max_edges"));
    }

    public CheckerGraph<N,E> inRangeWeight(double min, double max) {
        return is(graph -> graph.getEdges().stream().allMatch(edge -> min <= edge.getWeigth() && edge.getWeigth() <= max), sendMessage(INIT_GRAPH, "in_range_edges"));
    }
    
}
