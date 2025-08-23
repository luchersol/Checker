package specialized_checkers.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.collection.Graph;

class CheckerGraphTest {
    private Graph<String, Integer> graph;
    private CheckerGraph<String, Integer> checker;

    @BeforeEach
    void setUp() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<Graph.Edge<String, Integer>> edges = Arrays.asList(
            new Graph.Edge<String, Integer>("A", "B", 1),
            new Graph.Edge<String, Integer>("B", "C", 2)
        );
        graph = new Graph<>(nodes, edges);
        checker = new CheckerGraph<>(graph, "testGraph");
    }

    @Test
    void testIsEmpty() {
        Graph<String, Integer> emptyGraph = new Graph<>();
        CheckerGraph<String, Integer> emptyChecker = new CheckerGraph<>(emptyGraph, "empty");
        assertDoesNotThrow(emptyChecker::isEmpty);
    }

    @Test
    void testContainsNode() {
        assertDoesNotThrow(() -> checker.containsNode("A"));
        assertThrows(Exception.class, () -> checker.containsNode("Z"));
    }

    @Test
    void testContainsEdge() {
        assertDoesNotThrow(() -> checker.containsEdge("A", "B"));
        assertThrows(Exception.class, () -> checker.containsEdge("A", "C"));
    }

    @Test
    void testAnyNodesMatch() {
        assertDoesNotThrow(() -> checker.anyNodesMatch(n -> n.equals("B")));
        assertThrows(Exception.class, () -> checker.anyNodesMatch(n -> n.equals("Z")));
    }

    @Test
    void testAllNodesMatch() {
        assertThrows(Exception.class, () -> checker.allNodesMatch(n -> n.equals("A")));
        assertDoesNotThrow(() -> checker.allNodesMatch(n -> n.length() == 1));
    }

    @Test
    void testAnyEdgesMatch() {
        assertDoesNotThrow(() -> checker.anyEdgesMatch(e -> e.getWeight() == 1));
        assertThrows(Exception.class, () -> checker.anyEdgesMatch(e -> e.getWeight() == 99));
    }

    @Test
    void testAllEdgesMatch() {
        assertDoesNotThrow(() -> checker.allEdgesMatch(e -> e.getWeight() > 0));
        assertThrows(Exception.class, () -> checker.allEdgesMatch(e -> e.getWeight() == 1));
    }

    @Test
    void testMinNodes() {
        assertDoesNotThrow(() -> checker.minNodes(2));
        assertThrows(Exception.class, () -> checker.minNodes(5));
    }

    @Test
    void testMaxNodes() {
        assertDoesNotThrow(() -> checker.maxNodes(4));
        assertThrows(Exception.class, () -> checker.maxNodes(2));
    }

    @Test
    void testInRangeNodes() {
        assertDoesNotThrow(() -> checker.inRangeNodes(2, 4));
        assertThrows(Exception.class, () -> checker.inRangeNodes(5, 6));
    }

    @Test
    void testMinEdges() {
        assertDoesNotThrow(() -> checker.minEdges(2));
        assertThrows(Exception.class, () -> checker.minEdges(5));
    }

    @Test
    void testMaxEdges() {
        assertDoesNotThrow(() -> checker.maxEdges(3));
        assertThrows(Exception.class, () -> checker.maxEdges(1));
    }

    @Test
    void testInRangeEdges() {
        assertDoesNotThrow(() -> checker.inRangeEdges(2, 3));
        assertThrows(Exception.class, () -> checker.inRangeEdges(4, 5));
    }

    @Test
    void testMinWeight() {
        assertDoesNotThrow(() -> checker.minWeight(1));
        assertThrows(Exception.class, () -> checker.minWeight(3));
    }

    @Test
    void testMaxWeight() {
        assertDoesNotThrow(() -> checker.maxWeight(2));
        assertThrows(Exception.class, () -> checker.maxWeight(1));
    }

    @Test
    void testInRangeWeight() {
        assertDoesNotThrow(() -> checker.inRangeWeight(1, 2));
        assertThrows(Exception.class, () -> checker.inRangeWeight(3, 4));
    }

    @Test
    void testHasPath() {
        assertDoesNotThrow(() -> checker.hasPath("A", "C"));
        assertThrows(Exception.class, () -> checker.hasPath("A", "D"));
    }

}