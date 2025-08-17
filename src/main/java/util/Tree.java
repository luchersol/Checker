package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree<T> {
    
    private final TreeNode<T> root;
    private final boolean isBinaryTree;

    public static class TreeNode<T> {
        public final T value;
        public final List<TreeNode<T>> children = new ArrayList<>();

        public TreeNode(T value) {
            this.value = value;
        }

        public void addChild(TreeNode<T> child) {
            this.children.add(child);
        }

        public boolean isLeaf(){
            return this.children.isEmpty();
        }
    }

    public Tree() {
        this.root = new TreeNode<>(null);
        this.isBinaryTree = true;
    }

    public Tree(T rootValue) {
        this.root = new TreeNode<>(rootValue);
        this.isBinaryTree = true;
    }

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

    public TreeNode<T> getRoot() {
        return this.root;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean isBinaryTree() {
        return this.isBinaryTree;
    }

    public boolean isSymmetric() {
        if (root == null) return true;
        return isSymmetric(root, root);
    }

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

    public boolean isFull() {
        return isFull(root);
    }

    private boolean isFull(TreeNode<T> node) {
        if (node == null) return true;
        if (!node.isLeaf() && node.children.size() != 2) return false;
        return node.children.stream().allMatch(this::isFull);
    }

    public int getDepth() {
        return getDepth(root);
    }

    private int getDepth(TreeNode<T> node) {
        if (node == null) return 0;
        if (node.isLeaf()) return 1;
        return 1 + node.children.stream()
                                .mapToInt(this::getDepth)
                                .max()
                                .orElse(0);
    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(TreeNode<T> node) {
        if (node == null) return 0;
        if (node.isLeaf()) return 1;
        return node.children.stream().mapToInt(this::countLeaves).sum();
    }

    public int maxDegree() {
        return maxDegree(root);
    }

    private int maxDegree(TreeNode<T> node) {
        if (node == null) return 0;
        int maxChildDegree = node.children.stream()
                                          .mapToInt(this::maxDegree)
                                          .max()
                                          .orElse(0);
        return Math.max(node.children.size(), maxChildDegree);
    }

    public int diameter() {
        int[] diameter = new int[1];
        heightForDiameter(root, diameter);
        return diameter[0];
    }

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
