package specialized_checkers.collection;

import static util.Message.*;

import java.util.List;
import java.util.Map;

import util.AbstractChecker;
import util.collection.Tree;

public class CheckerTree<T> extends AbstractChecker<Tree<T>, CheckerTree<T>> {

    private static final String INIT_TREE = "collections.tree";

    public CheckerTree(Tree<T> object, String name) {
        super(object, name);
    }

    public CheckerTree(T rootValue, Map<T, List<T>> childrenMap, String name) {
        super(name);
        this.object = new Tree<T>(rootValue, childrenMap);
    }

    public CheckerTree(T rootValue, String name) {
        super(name);
        this.object = new Tree<T>(rootValue);
    }

    /**
     * @return CheckerTree<T>
     */
    @Override
    protected CheckerTree<T> self() {
        return this;
    }

    /**
     * @return CheckerTree<T>
     */
    public CheckerTree<T> isEmpty() {
        return is(tree -> tree.isEmpty(), sendMessage(INIT_TREE, "is_binary_tree"));
    }

    /**
     * @return CheckerTree<T>
     */
    public CheckerTree<T> isBinaryTree() {
        return is(tree -> tree.isBinaryTree(), sendMessage(INIT_TREE, "is_binary_tree"));
    }

    /**
     * @return CheckerTree<T>
     */
    public CheckerTree<T> isSymmetric() {
        return is(tree -> tree.isSymmetric(), sendMessage(INIT_TREE, "is_symmetric"));
    }

    /**
     * @return CheckerTree<T>
     */
    public CheckerTree<T> isFull() {
        return is(tree -> tree.isFull(), sendMessage(INIT_TREE, "is_symmetric"));
    }

    /**
     * @param min
     * @return CheckerTree<T>
     */
    public CheckerTree<T> minDepth(int min) {
        return is(tree -> min <= tree.getDepth(), sendMessage(INIT_TREE, "min_depth"));
    }

    /**
     * @param max
     * @return CheckerTree<T>
     */
    public CheckerTree<T> maxDepth(int max) {
        return is(tree -> tree.getDepth() <= max, sendMessage(INIT_TREE, "max_depth"));
    }

    /**
     * @param min
     * @param max
     * @return CheckerTree<T>
     */
    public CheckerTree<T> inRangeDepth(int min, int max) {
        return is(tree -> min <= tree.getDepth() && tree.getDepth() <= max, sendMessage(INIT_TREE, "in_range_depth"));
    }

    /**
     * @param min
     * @return CheckerTree<T>
     */
    public CheckerTree<T> minLeaves(int min) {
        return is(tree -> min <= tree.countLeaves(), sendMessage(INIT_TREE, "min_leaves"));
    }

    /**
     * @param max
     * @return CheckerTree<T>
     */
    public CheckerTree<T> maxLeaves(int max) {
        return is(tree -> tree.countLeaves() <= max, sendMessage(INIT_TREE, "max_leaves"));
    }

    /**
     * @param min
     * @param max
     * @return CheckerTree<T>
     */
    public CheckerTree<T> inRangeLeaves(int min, int max) {
        return is(tree -> min <= tree.countLeaves() && tree.countLeaves() <= max, sendMessage(INIT_TREE, "in_range_leaves"));
    }

    /**
     * @param min
     * @return CheckerTree<T>
     */
    public CheckerTree<T> minDiamenter(int min) {
        return is(tree -> min <= tree.diameter(), sendMessage(INIT_TREE, "min_diamenter"));
    }

    /**
     * @param max
     * @return CheckerTree<T>
     */
    public CheckerTree<T> maxDiamenter(int max) {
        return is(tree -> tree.diameter() <= max, sendMessage(INIT_TREE, "max_diamenter"));
    }

    /**
     * @param min
     * @param max
     * @return CheckerTree<T>
     */
    public CheckerTree<T> inRangeDiamenter(int min, int max) {
        return is(tree -> min <= tree.diameter() && tree.diameter() <= max, sendMessage(INIT_TREE, "in_range_diamenter"));
    }

}
