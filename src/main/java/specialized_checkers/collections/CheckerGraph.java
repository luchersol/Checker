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
        is(graph -> graph.isEmpty(), sendMessage(INIT_GRAPH, "is_empty"));
        return this;
    }

    public CheckerGraph<N,E> isTree(){
        is(graph -> graph.isTree(), sendMessage(INIT_GRAPH, "is_tree"));
        return this;
    }

    public CheckerGraph<N,E> isBinaryTree() {
        is(graph -> graph.isBinaryTree(), sendMessage(INIT_GRAPH, "is_binary_tree"));
        return this;
    }
    
    public CheckerGraph<N,E> isDirected() {
        is(graph -> graph.isDirected(), sendMessage(INIT_GRAPH, "is_directed"));
        return this;
    }

    public CheckerGraph<N,E> isConnected() {
        is(graph -> graph.isConnected(), sendMessage(INIT_GRAPH, "is_connected"));
        return this;
    }

    public CheckerGraph<N,E> hasCycle() {
        is(graph -> graph.hasCycle(), sendMessage(INIT_GRAPH, "has_cycle"));
        return this;
    }

    public CheckerGraph<N,E> containsNode(N node) {
        is(graph -> graph.containsNode(node), sendMessage(INIT_GRAPH, "contains_node"));
        return this;
    }

    public CheckerGraph<N,E> containsEdge(N from, N to) {
        is(graph -> graph.containsEdge(from, to), sendMessage(INIT_GRAPH, "contains_edge"));
        return this;
    }

    public CheckerGraph<N,E> containsEdge(Graph.Edge<N, E> edge) {
        is(graph -> graph.containsEdge(edge), sendMessage(INIT_GRAPH, "contains_edge"));
        return this;
    }

    public CheckerGraph<N,E> anyNodesMatch(Predicate<N> condition) {
        is(graph -> graph.anyNodesMatch(condition), sendMessage(INIT_GRAPH, "any_nodes_match"));
        return this;
    }

    public CheckerGraph<N,E> allNodesMatch(Predicate<N> condition) {
        is(graph -> graph.allNodesMatch(condition), sendMessage(INIT_GRAPH, "all_nodes_match"));
        return this;
    }

    public CheckerGraph<N,E> anyEdgesMatch(Predicate<Graph.Edge<N,E>> condition) {
        is(graph -> graph.anyEdgesMatch(condition), sendMessage(INIT_GRAPH, "any_edges_match"));
        return this;
    }

    public CheckerGraph<N,E> allEdgesMatch(Predicate<Graph.Edge<N,E>> condition) {
        is(graph -> graph.allEdgesMatch(condition), sendMessage(INIT_GRAPH, "all_edges_match"));
        return this;
    }

    public CheckerGraph<N,E> hasPath(N start, N end) {
        is(graph -> graph.hasPath(start, end), sendMessage(INIT_GRAPH, "has_path"));
        return this;
    } 

    public CheckerGraph<N,E> minNodes(int min) {
        is(graph -> min <= graph.countNodes(), sendMessage(INIT_GRAPH, "min_nodes"));
        return this;
    }

    public CheckerGraph<N,E> maxNodes(int max) {
        is(graph -> graph.countNodes() <= max, sendMessage(INIT_GRAPH, "max_nodes"));
        return this;
    }

    public CheckerGraph<N,E> inRangeNodes(int min, int max) {
        is(graph -> min <= graph.countNodes() && graph.countNodes() <= max, sendMessage(INIT_GRAPH, "in_range_nodes"));
        return this;
    }

    public CheckerGraph<N,E> minEdges(int min) {
        is(graph -> min <= graph.countNodes(), sendMessage(INIT_GRAPH, "min_edges"));
        return this;
    }

    public CheckerGraph<N,E> maxEdges(int max) {
        is(graph -> graph.countNodes() <= max, sendMessage(INIT_GRAPH, "max_edges"));
        return this;
    }

    public CheckerGraph<N,E> inRangeEdges(int min, int max) {
        is(graph -> min <= graph.countNodes() && graph.countNodes() <= max, sendMessage(INIT_GRAPH, "in_range_edges"));
        return this;
    }

    public CheckerGraph<N,E> minWeight(double min) {
        is(graph -> graph.getEdges().stream().allMatch(edge -> min <= edge.getWeigth()), sendMessage(INIT_GRAPH, "min_edges"));
        return this;
    }

    public CheckerGraph<N,E> maxWeight(double max) {
        is(graph -> graph.getEdges().stream().allMatch(edge -> edge.getWeigth() <= max), sendMessage(INIT_GRAPH, "max_edges"));
        return this;
    }

    public CheckerGraph<N,E> inRangeWeight(double min, double max) {
        is(graph -> graph.getEdges().stream().allMatch(edge -> min <= edge.getWeigth() && edge.getWeigth() <= max), sendMessage(INIT_GRAPH, "in_range_edges"));
        return this;
    }
    
}
