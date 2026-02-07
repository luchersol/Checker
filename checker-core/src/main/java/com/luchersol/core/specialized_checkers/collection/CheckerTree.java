package com.luchersol.core.specialized_checkers.collection;

import static com.luchersol.core.util.MessageService.*;

import java.util.List;
import java.util.Map;

import com.luchersol.core.util.AbstractChecker;
import com.luchersol.core.util.collection.Tree;

/**
 * A specialized checker for {@link Tree} data structures, providing a fluent API to assert various properties
 * and invariants about a tree. This class extends {@link AbstractChecker} and allows for custom or default
 * naming of the checker instance.
 * <p>
 * CheckerTree provides static factory methods for convenient construction from a {@code Tree} instance,
 * a root value, or a root value with a map of children. It supports checks for emptiness, binary structure,
 * symmetry, fullness, depth, number of leaves, and diameter, among others.
 * </p>
 *
 * <p>Usage Example:</p>
 * <pre>{@code
 * Tree<Integer> tree = new Tree<>(1);
 * CheckerTree<Integer> checker = CheckerTree.check(tree);
 *      .isBinaryTree()
 *      .minDepth(1);
 * }</pre>
 *
 * @param <T> the type of elements stored in the tree
 * @see Tree
 * @see AbstractChecker
 */
public class CheckerTree<T> extends AbstractChecker<Tree<T>, CheckerTree<T>> {

    private static final String INIT_TREE = "collections.tree";
    private static final String DEFAULT_NAME = "Tree";

    /**
     * Constructs a new {@code CheckerTree} with the specified tree and name.
     *
     * @param tree the underlying tree structure to be wrapped or checked
     * @param name the name associated with this {@code CheckerTree}
     */
    protected CheckerTree(Tree<T> tree, String name) {
        super(tree, name);
    }


    // STATIC CONSTRUCTOR WITH NAME

    /**
     * Creates a CheckerTree for the given tree and assigns a custom name.
     *
     * @param <T> the {@code Tree}'s element type
     * @param tree the tree to check
     * @param name the name to assign to this checker
     * @return a CheckerTree instance for the given tree
     */
    public static <T> CheckerTree<T> check(Tree<T> tree, String name) {
        return new CheckerTree<>(tree, name);
    }

    /**
     * Creates a CheckerTree from a root value and a map of children, and assigns a custom name.
     *
     * @param <T> the {@code Tree}'s element type
     * @param rootValue the value of the root node
     * @param childrenMap a map where each key is a node and the value is a list of its children
     * @param name the name to assign to this checker
     * @return a CheckerTree instance for the constructed tree
     */
    public static <T> CheckerTree<T> check(T rootValue, Map<T, List<T>> childrenMap, String name) {
        Tree<T> tree = new Tree<T>(rootValue, childrenMap);
        return check(tree, name);
    }

    /**
     * Creates a CheckerTree from a root value and assigns a custom name.
     *
     * @param <T> the {@code Tree}'s element type
     * @param rootValue the value of the root node
     * @param name the name to assign to this checker
     * @return a CheckerTree instance for the constructed tree
     */
    public static <T> CheckerTree<T> check(T rootValue, String name) {
        Tree<T> tree = new Tree<T>(rootValue);
        return check(tree, name);
    }

    // STATIC CONSTRUCTOR WITHOUT NAME

    /**
     * Creates a CheckerTree for the given tree with a default name.
     *
     * @param <T>  the {@code Tree}'s element type
     * @param tree the tree to check
     * @return a CheckerTree instance for the given tree
     */
    public static <T> CheckerTree<T> check(Tree<T> tree) {
        return check(tree, DEFAULT_NAME);
    }

    /**
     * Creates a CheckerTree from a root value and a map of children, with a default name.
     *
     * @param <T>  the {@code Tree}'s element type
     * @param rootValue the value of the root node
     * @param childrenMap a map where each key is a node and the value is a list of its children
     * @return a CheckerTree instance for the constructed tree
     */
    public static <T> CheckerTree<T> check(T rootValue, Map<T, List<T>> childrenMap) {
        Tree<T> tree = new Tree<T>(rootValue, childrenMap);
        return check(tree);
    }

    /**
     * Creates a CheckerTree from a root value with a default name.
     *
     * @param <T>  the {@code Tree}'s element type
     * @param rootValue the value of the root node
     * @return a CheckerTree instance for the constructed tree
     */
    public static <T> CheckerTree<T> check(T rootValue) {
        Tree<T> tree = new Tree<T>(rootValue);
        return check(tree);
    }

    /**
     * Returns this instance (for fluent API).
     *
     * @return this CheckerTree instance
     */
    @Override
    protected CheckerTree<T> self() {
        return this;
    }

    /**
     * Checks if the tree is empty.
     *
     * @return this CheckerTree instance
     */
    public CheckerTree<T> isEmpty() {
        return is(tree -> tree.isEmpty(), sendMessage(INIT_TREE, "is_empty"));
    }

    /**
     * Checks if the tree is a binary tree (each node has at most two children).
     *
     * @return this CheckerTree instance
     */
    public CheckerTree<T> isBinaryTree() {
        return is(tree -> tree.isBinaryTree(), sendMessage(INIT_TREE, "is_binary_tree"));
    }

    /**
     * Checks if the tree is symmetric (mirror image around its center).
     *
     * @return this CheckerTree instance
     */
    public CheckerTree<T> isSymmetric() {
        return is(tree -> tree.isSymmetric(), sendMessage(INIT_TREE, "is_symmetric"));
    }

    /**
     * Checks if the tree is full (every node has 0 or 2 children).
     *
     * @return this CheckerTree instance
     */
    public CheckerTree<T> isFull() {
        return is(tree -> tree.isFull(), sendMessage(INIT_TREE, "is_full"));
    }

    /**
     * Checks if the tree's depth is at least the specified minimum.
     *
     * @param min the minimum depth
     * @return this CheckerTree instance
     */
    public CheckerTree<T> minDepth(int min) {
        return is(tree -> min <= tree.getDepth(), sendMessage(INIT_TREE, "min_depth", this.object.getDepth(), min));
    }

    /**
     * Checks if the tree's depth is at most the specified maximum.
     *
     * @param max the maximum depth
     * @return this CheckerTree instance
     */
    public CheckerTree<T> maxDepth(int max) {
        return is(tree -> tree.getDepth() <= max,
            sendMessage(INIT_TREE, "max_depth", this.object.getDepth(), max));
    }

    /**
     * Checks if the tree's depth is within the specified range (inclusive).
     *
     * @param min the minimum depth
     * @param max the maximum depth
     * @return this CheckerTree instance
     */
    public CheckerTree<T> inRangeDepth(int min, int max) {
        return is(tree -> min <= tree.getDepth() && tree.getDepth() <= max,
            sendMessage(INIT_TREE, "in_range_depth", this.object.getDepth(), min, max));
    }

    /**
     * Checks if the number of leaves in the tree is at least the specified minimum.
     *
     * @param min the minimum number of leaves
     * @return this CheckerTree instance
     */
    public CheckerTree<T> minLeaves(int min) {
        return is(tree -> min <= tree.countLeaves(),
            sendMessage(INIT_TREE, "min_leaves", this.object.countLeaves(), min));
    }

    /**
     * Checks if the number of leaves in the tree is at most the specified maximum.
     *
     * @param max the maximum number of leaves
     * @return this CheckerTree instance
     */
    public CheckerTree<T> maxLeaves(int max) {
        return is(tree -> tree.countLeaves() <= max,
            sendMessage(INIT_TREE, "max_leaves", this.object.countLeaves(), max));
    }

    /**
     * Checks if the number of leaves in the tree is within the specified range (inclusive).
     *
     * @param min the minimum number of leaves
     * @param max the maximum number of leaves
     * @return this CheckerTree instance
     */
    public CheckerTree<T> inRangeLeaves(int min, int max) {
        return is(tree -> min <= tree.countLeaves() && tree.countLeaves() <= max,
            sendMessage(INIT_TREE, "in_range_leaves", this.object.countLeaves(), min, max));
    }

    /**
     * Checks if the tree's diameter is at least the specified minimum.
     *
     * @param min the minimum diameter
     * @return this CheckerTree instance
     */
    public CheckerTree<T> minDiamenter(int min) {
        return is(tree -> min <= tree.diameter(),
            sendMessage(INIT_TREE, "min_diamenter", this.object.diameter(), min));
    }

    /**
     * Checks if the tree's diameter is at most the specified maximum.
     *
     * @param max the maximum diameter
     * @return this CheckerTree instance
     */
    public CheckerTree<T> maxDiamenter(int max) {
        return is(tree -> tree.diameter() <= max,
            sendMessage(INIT_TREE, "max_diamenter", this.object.diameter(), max));
    }

    /**
     * Checks if the tree's diameter is within the specified range (inclusive).
     *
     * @param min the minimum diameter
     * @param max the maximum diameter
     * @return this CheckerTree instance
     */
    public CheckerTree<T> inRangeDiamenter(int min, int max) {
        return is(tree -> min <= tree.diameter() && tree.diameter() <= max,
            sendMessage(INIT_TREE, "in_range_diamenter", this.object.diameter(), min, max));
    }

}
