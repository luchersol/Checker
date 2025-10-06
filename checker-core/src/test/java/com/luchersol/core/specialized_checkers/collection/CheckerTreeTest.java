package com.luchersol.core.specialized_checkers.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.luchersol.core.util.CheckerException;
import com.luchersol.core.util.collection.Tree;


class CheckerTreeTest {

    @Test
    void testCheckWithTreeAndName() {
        Tree<Integer> tree = new Tree<>(1);
        CheckerTree<Integer> checker = CheckerTree.check(tree, "MyTree");
        assertNotNull(checker);
    }

    @Test
    void testCheckWithRootValueAndChildrenMapAndName() {
        Map<Integer, List<Integer>> children = new HashMap<>();
        children.put(1, Arrays.asList(2, 3));
        CheckerTree<Integer> checker = CheckerTree.check(1, children, "TreeWithChildren");
        assertNotNull(checker);
    }

    @Test
    void testCheckWithRootValueAndName() {
        CheckerTree<String> checker = CheckerTree.check("root", "NamedTree");
        assertNotNull(checker);
    }

    @Test
    void testCheckWithTree() {
        Tree<String> tree = new Tree<>("root");
        CheckerTree<String> checker = CheckerTree.check(tree);
        assertNotNull(checker);
    }

    @Test
    void testCheckWithRootValueAndChildrenMap() {
        Map<String, List<String>> children = new HashMap<>();
        children.put("root", Arrays.asList("left", "right"));
        CheckerTree<String> checker = CheckerTree.check("root", children);
        assertNotNull(checker);
    }

    @Test
    void testCheckWithRootValue() {
        CheckerTree<Integer> checker = CheckerTree.check(42);
        assertNotNull(checker);
    }

    @Test
    void testIsEmptyOnEmptyTree() {
        Tree<Integer> tree = new Tree<>(null);
        CheckerTree<Integer> checker = CheckerTree.check(tree);
        assertDoesNotThrow(() -> checker.isEmpty());
    }

    @Test
    void testIsEmptyOnNonEmptyTree() {
        Tree<Integer> tree = new Tree<>(1);
        CheckerTree<Integer> checker = CheckerTree.check(tree);
        assertThrows(CheckerException.class, () -> checker.isEmpty());
    }

    @Test
    void testIsBinaryTree() {
        Map<Integer, List<Integer>> children = new HashMap<>();
        children.put(1, Arrays.asList(2, 3));
        Tree<Integer> tree = new Tree<>(1, children);
        CheckerTree<Integer> checker = CheckerTree.check(tree);
        assertDoesNotThrow(() -> checker.isBinaryTree());
    }

    @Test
    void testIsSymmetric() {
        Map<Integer, List<Integer>> children = new HashMap<>();
        children.put(1, Arrays.asList(2, 2));
        Tree<Integer> tree = new Tree<>(1, children);
        CheckerTree<Integer> checker = CheckerTree.check(tree);
        assertDoesNotThrow(() -> checker.isSymmetric());
    }

    @Test
    void testIsFull() {
        Map<Integer, List<Integer>> children = new HashMap<>();
        children.put(1, Arrays.asList(2, 3));
        children.put(2, Collections.emptyList());
        children.put(3, Collections.emptyList());
        Tree<Integer> tree = new Tree<>(1, children);
        CheckerTree<Integer> checker = CheckerTree.check(tree);
        assertDoesNotThrow(() -> checker.isFull());
    }

    @Test
    void testMinDepth() {
        Tree<Integer> tree = new Tree<>(1);
        CheckerTree<Integer> checker = CheckerTree.check(tree);
        assertDoesNotThrow(() -> checker.minDepth(0));
    }

    @Test
    void testMaxDepth() {
        Tree<Integer> tree = new Tree<>(1);
        CheckerTree<Integer> checker = CheckerTree.check(tree);
        assertDoesNotThrow(() -> checker.maxDepth(1));
    }

    @Test
    void testInRangeDepth() {
        Tree<Integer> tree = new Tree<>(1);
        CheckerTree<Integer> checker = CheckerTree.check(tree);
        assertDoesNotThrow(() -> checker.inRangeDepth(0, 2));
    }

    @Test
    void testMinLeaves() {
        Tree<Integer> tree = new Tree<>(1);
        CheckerTree<Integer> checker = CheckerTree.check(tree);
        assertDoesNotThrow(() -> checker.minLeaves(1));
    }

    @Test
    void testMaxLeaves() {
        Tree<Integer> tree = new Tree<>(1);
        CheckerTree<Integer> checker = CheckerTree.check(tree);
        assertDoesNotThrow(() -> checker.maxLeaves(1));
    }

    @Test
    void testInRangeLeaves() {
        Tree<Integer> tree = new Tree<>(1);
        CheckerTree<Integer> checker = CheckerTree.check(tree);
        assertDoesNotThrow(() -> checker.inRangeLeaves(1, 2));
    }

    @Test
    void testMinDiamenter() {
        Tree<Integer> tree = new Tree<>(1);
        CheckerTree<Integer> checker = CheckerTree.check(tree);
        assertDoesNotThrow(() -> checker.minDiamenter(0));
    }

    @Test
    void testMaxDiamenter() {
        Tree<Integer> tree = new Tree<>(1);
        CheckerTree<Integer> checker = CheckerTree.check(tree);
        assertDoesNotThrow(() -> checker.maxDiamenter(1));
    }

    @Test
    void testInRangeDiamenter() {
        Tree<Integer> tree = new Tree<>(1);
        CheckerTree<Integer> checker = CheckerTree.check(tree);
        assertDoesNotThrow(() -> checker.inRangeDiamenter(0, 2));
    }
}
