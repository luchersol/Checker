package util.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tree is a generic class representing a tree data structure with nodes and children.
 * <p>
 * It supports both general and binary trees, and provides methods for common tree operations such as
 * checking symmetry, fullness, depth, leaf count, degree, and diameter.
 *
 * @param <T> the type of value stored in each tree node
 */
public class Tree<T> {

    private final TreeNode<T> root;
    private final boolean isBinaryTree;

    /**
     * TreeNode represents a node in the tree, holding a value and a list of children.
     *
     * @param <T> the type of value stored in the node
     */
    public static class TreeNode<T> {
        public final T value;
        public final List<TreeNode<T>> children = new ArrayList<>();

        /**
         * Constructs a tree node with the specified value.
         *
         * @param value the value to store in the node
         */
        public TreeNode(T value) {
                this.value = value;
        }

        /**
         * Adds a child node to this node.
         *
         * @param child the child node to add
         */
        public void addChild(TreeNode<T> child) {
                this.children.add(child);
            }

        /**
         * Checks if this node is a leaf (has no children).
         *
         * @return true if the node is a leaf, false otherwise
         */
        public boolean isLeaf(){
                return this.children.isEmpty();
        }
    }

    /**
     * Constructs an empty tree (root is null).
     */
    public Tree() {
        this(null);
    }

    /**
     * Constructs a tree with the specified root value.
     *
     * @param rootValue the value for the root node
     */
    public Tree(T rootValue) {
        this.root = new TreeNode<>(rootValue);
        this.isBinaryTree = true;
    }

    /**
     * Constructs a tree with the specified root value and a map of children for each node.
     *
     * @param rootValue   the value for the root node
     * @param childrenMap a map where each key is a node value and the value is a list of its children's values
     */
    public Tree(T rootValue, Map<T, List<T>> childrenMap) {
        this.root = new TreeNode<>(rootValue);
        this.isBinaryTree = childrenMap.values().stream().allMatch(childrens -> childrens.size() <= 2);

        Map<T, TreeNode<T>> nodeMap = new HashMap<>();
        nodeMap.put(rootValue, root);

        for (T key : childrenMap.keySet()) {
            nodeMap.putIfAbsent(key, new TreeNode<>(key));
            for (T childValue : childrenMap.get(key)) {
                nodeMap.putIfAbsent(childValue, new TreeNode<>(childValue));
            }
        }

        for (Map.Entry<T, List<T>> entry : childrenMap.entrySet()) {
            TreeNode<T> parentNode = nodeMap.get(entry.getKey());
            for (T childValue : entry.getValue()) {
                parentNode.addChild(nodeMap.get(childValue));
            }
        }
    }

    /**
     * @return TreeNode<T>
     */
    /**
     * Returns the root node of the tree.
     *
     * @return the root node
     */
    public TreeNode<T> getRoot() {
        return this.root;
    }

    /**
     * @return boolean
     */
    /**
     * Checks if the tree is empty (root value is null).
     *
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.root.value == null;
    }

    /**
     * @return boolean
     */
    /**
     * Checks if the tree is a binary tree (each node has at most two children).
     *
     * @return true if the tree is binary, false otherwise
     */
    public boolean isBinaryTree() {
        return this.isBinaryTree;
    }

    /**
     * @return boolean
     */
    /**
     * Checks if the tree is symmetric (a mirror of itself).
     *
     * @return true if the tree is symmetric, false otherwise
     */
    public boolean isSymmetric() {
        if (root == null) return true;
        return isSymmetric(root, root);
    }

    /**
     * @param t1
     * @param t2
     * @return boolean
     */
    private boolean isSymmetric(TreeNode<T> t1, TreeNode<T> t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (!t1.value.equals(t2.value)) return false;

        int n = t1.children.size();
        if (n != t2.children.size()) return false;

        for (int i = 0; i < n; i++) {
            TreeNode<T> c1 = t1.children.get(i);
            TreeNode<T> c2 = t2.children.get(n - 1 - i);
            if (!isSymmetric(c1, c2)) return false;
        }
        return true;
    }

    /**
     * @return boolean
     */
    /**
     * Checks if the tree is full (every node has 0 or 2 children).
     *
     * @return true if the tree is full, false otherwise
     */
    public boolean isFull() {
        return isFull(root);
    }

    /**
     * @param node
     * @return boolean
     */
    private boolean isFull(TreeNode<T> node) {
        if (node == null) return true;
        if (!node.isLeaf() && node.children.size() != 2) return false;
        return node.children.stream().allMatch(this::isFull);
    }

    /**
     * @return int
     */
    /**
     * Returns the depth (height) of the tree.
     *
     * @return the depth of the tree
     */
    public int getDepth() {
        return getDepth(root);
    }

    /**
     * @param node
     * @return int
     */
    private int getDepth(TreeNode<T> node) {
        if (node == null) return 0;
        if (node.isLeaf()) return 1;
        return 1 + node.children.stream()
                                .mapToInt(this::getDepth)
                                .max()
                                .orElse(0);
    }

    /**
     * @return int
     */
    /**
     * Returns the number of leaf nodes in the tree.
     *
     * @return the number of leaves
     */
    public int countLeaves() {
        return countLeaves(root);
    }

    /**
     * @param node
     * @return int
     */
    private int countLeaves(TreeNode<T> node) {
        if (node == null) return 0;
        if (node.isLeaf()) return 1;
        return node.children.stream().mapToInt(this::countLeaves).sum();
    }

    /**
     * @return int
     */
    /**
     * Returns the maximum degree (number of children) of any node in the tree.
     *
     * @return the maximum degree
     */
    public int maxDegree() {
        return maxDegree(root);
    }

    /**
     * @param node
     * @return int
     */
    private int maxDegree(TreeNode<T> node) {
        if (node == null) return 0;
        int maxChildDegree = node.children.stream()
                                          .mapToInt(this::maxDegree)
                                          .max()
                                          .orElse(0);
        return Math.max(node.children.size(), maxChildDegree);
    }

    /**
     * @return int
     */
    /**
     * Returns the diameter of the tree (the length of the longest path between any two nodes).
     *
     * @return the diameter of the tree
     */
    public int diameter() {
        int[] diameter = new int[1];
        heightForDiameter(root, diameter);
        return diameter[0];
    }

    /**
     * @param node
     * @param diameter
     * @return int
     */
    private int heightForDiameter(TreeNode<T> node, int[] diameter) {
        if (node == null) return 0;
        int max1 = 0, max2 = 0;
        for (TreeNode<T> child : node.children) {
            int h = heightForDiameter(child, diameter);
            if (h > max1) {
                max2 = max1;
                max1 = h;
            } else if (h > max2) {
                max2 = h;
            }
        }
        diameter[0] = Math.max(diameter[0], max1 + max2);
        return max1 + 1;
    }
}
