package specialized_checkers.collections;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.Graph;

class CheckerGraphTest {

    private Graph<String, Integer> emptyGraph;
    private Graph<String, Integer> simpleGraph;
    private CheckerGraph<String, Integer> checkerEmpty;
    private CheckerGraph<String, Integer> checkerSimple;

    @BeforeEach
    void setUp() {
        emptyGraph = new Graph<>();

        simpleGraph = new Graph<>(true, List.of(
            new Graph.Edge<>("A", "B", null, null)
        ));

        checkerEmpty = new CheckerGraph<>(emptyGraph, "emptyGraph");
        checkerSimple = new CheckerGraph<>(simpleGraph, "simpleGraph");
    }

    @Test
    void testConstructor() {
        assertNotNull(checkerEmpty);
        assertNotNull(checkerSimple);
    }

    @Test
    void testIsEmpty() {
        assertDoesNotThrow(() -> checkerEmpty.isEmpty());
        assertThrows(AssertionError.class, () -> checkerSimple.isEmpty());
    }

    @Test
    void testIsTree() {
        assertDoesNotThrow(() -> checkerSimple.isTree());
        assertThrows(AssertionError.class, () -> checkerEmpty.isTree());
    }

    @Test
    void testIsBinaryTree() {
        assertDoesNotThrow(() -> checkerSimple.isBinaryTree());
        assertThrows(AssertionError.class, () -> checkerEmpty.isBinaryTree());
    }

    @Test
    void testIsDirected() {
        assertDoesNotThrow(() -> checkerSimple.isDirected());
        assertThrows(AssertionError.class, () -> checkerEmpty.isDirected());
    }

    @Test
    void testIsConnected() {
        assertDoesNotThrow(() -> checkerSimple.isConnected());
        assertThrows(AssertionError.class, () -> checkerEmpty.isConnected());
    }

    @Test
    void testHasCycle() {
        assertDoesNotThrow(() -> checkerEmpty.hasCycle());
        assertDoesNotThrow(() -> checkerSimple.hasCycle());
    }

    @Test
    void testContainsNode() {
        assertDoesNotThrow(() -> checkerSimple.containsNode("A"));
        assertThrows(AssertionError.class, () -> checkerEmpty.containsNode("A"));
    }

    @Test
    void testContainsEdge() {
        assertDoesNotThrow(() -> checkerSimple.containsEdge("A", "B"));
        assertThrows(AssertionError.class, () -> checkerEmpty.containsEdge("A", "B"));
    }

    @Test
    void testContainsEdgeObject() {
        Graph.Edge<String, Integer> edge = new Graph.Edge<>("A", "B", 1);
        assertDoesNotThrow(() -> checkerSimple.containsEdge(edge));
        assertThrows(AssertionError.class, () -> checkerEmpty.containsEdge(edge));
    }

    @Test
    void testAnyNodesMatch() {
        assertDoesNotThrow(() -> checkerSimple.anyNodesMatch(n -> n.equals("A")));
        assertThrows(AssertionError.class, () -> checkerEmpty.anyNodesMatch(n -> n.equals("A")));
    }

    @Test
    void testAllNodesMatch() {
        assertDoesNotThrow(() -> checkerSimple.allNodesMatch(n -> n.equals("A")));
        assertThrows(AssertionError.class, () -> checkerEmpty.allNodesMatch(n -> n.equals("A")));
    }

    @Test
    void testAnyEdgesMatch() {
        assertDoesNotThrow(() -> checkerSimple.anyEdgesMatch(e -> e.getFrom().equals("A")));
        assertThrows(AssertionError.class, () -> checkerEmpty.anyEdgesMatch(e -> e.getFrom().equals("A")));
    }

    @Test
    void testAllEdgesMatch() {
        assertDoesNotThrow(() -> checkerSimple.allEdgesMatch(e -> e.getFrom().equals("A")));
        assertThrows(AssertionError.class, () -> checkerEmpty.allEdgesMatch(e -> e.getFrom().equals("A")));
    }

    @Test
    void testHasPath() {
        assertDoesNotThrow(() -> checkerSimple.hasPath("A", "B"));
        assertThrows(AssertionError.class, () -> checkerEmpty.hasPath("A", "B"));
    }

    @Test
    void testMinNodes() {
        assertDoesNotThrow(() -> checkerSimple.minNodes(1));
        assertThrows(AssertionError.class, () -> checkerEmpty.minNodes(1));
    }

    @Test
    void testMaxNodes() {
        assertDoesNotThrow(() -> checkerSimple.maxNodes(2));
        assertThrows(AssertionError.class, () -> checkerSimple.maxNodes(1));
    }

    @Test
    void testInRangeNodes() {
        assertDoesNotThrow(() -> checkerSimple.inRangeNodes(1, 2));
        assertThrows(AssertionError.class, () -> checkerSimple.inRangeNodes(3, 4));
    }

    @Test
    void testMinEdges() {
        assertDoesNotThrow(() -> checkerSimple.minEdges(1));
        assertThrows(AssertionError.class, () -> checkerEmpty.minEdges(1));
    }

    @Test
    void testMaxEdges() {
        assertDoesNotThrow(() -> checkerSimple.maxEdges(2));
        assertThrows(AssertionError.class, () -> checkerSimple.maxEdges(0));
    }

    @Test
    void testInRangeEdges() {
        assertDoesNotThrow(() -> checkerSimple.inRangeEdges(1, 2));
        assertThrows(AssertionError.class, () -> checkerSimple.inRangeEdges(3, 4));
    }

    @Test
    void testMinWeight() {
        assertDoesNotThrow(() -> checkerSimple.minWeight(1));
        assertThrows(AssertionError.class, () -> checkerSimple.minWeight(2));
    }

    @Test
    void testMaxWeight() {
        assertDoesNotThrow(() -> checkerSimple.maxWeight(1));
        assertThrows(AssertionError.class, () -> checkerSimple.maxWeight(0));
    }

    @Test
    void testInRangeWeight() {
        assertDoesNotThrow(() -> checkerSimple.inRangeWeight(1, 1));
        assertThrows(AssertionError.class, () -> checkerSimple.inRangeWeight(2, 3));
    }
}