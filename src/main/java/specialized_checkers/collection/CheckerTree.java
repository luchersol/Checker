package specialized_checkers.collection;

import static util.Message.*;

import java.util.List;
import java.util.Map;

import util.AbstractChecker;
import util.collection.Tree;

public class CheckerTree<T> extends AbstractChecker<Tree<T>, CheckerTree<T>> {

    private static final String INIT_TREE = "collections.tree";
    private static final String DEFAULT_NAME = "Tree";

    protected CheckerTree(Tree<T> tree, String name) {
        super(tree, name);
    }


    // STATIC CONSTRUCTOR WITH NAME

    /**
     * @param tree
     * @param name
     * @return CheckerTree<T>
     */
    public static <T> CheckerTree<T> check(Tree<T> tree, String name) {
        return new CheckerTree<>(tree, name);
    }

    /**
     * @param childrenMap
     * @param name
     * @return CheckerTree<T>
     */
    public static <T> CheckerTree<T> check(T rootValue, Map<T, List<T>> childrenMap, String name) {
        Tree<T> tree = new Tree<T>(rootValue, childrenMap);
        return check(tree, name);
    }

    /**
     * @param rootValue
     * @param name
     * @return CheckerTree<T>
     */
    public static <T> CheckerTree<T> check(T rootValue, String name) {
        Tree<T> tree = new Tree<T>(rootValue);
        return check(tree, name);
    }

    // STATIC CONSTRUCTOR WITHOUT NAME

    /**
     * @param tree
     * @return CheckerTree<T>
     */
    public static <T> CheckerTree<T> check(Tree<T> tree) {
        return check(tree, DEFAULT_NAME);
    }

    /**
     * @param childrenMap
     * @return CheckerTree<T>
     */
    public static <T> CheckerTree<T> check(T rootValue, Map<T, List<T>> childrenMap) {
        Tree<T> tree = new Tree<T>(rootValue, childrenMap);
        return check(tree);
    }

    /**
     * @param rootValue
     * @return CheckerTree<T>
     */
    public static <T> CheckerTree<T> check(T rootValue) {
        Tree<T> tree = new Tree<T>(rootValue);
        return check(tree);
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
        return is(tree -> tree.isEmpty(), sendMessage(INIT_TREE, "is_empty"));
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
        return is(tree -> tree.isFull(), sendMessage(INIT_TREE, "is_full"));
    }

    /**
     * @param min
     * @return CheckerTree<T>
     */
    public CheckerTree<T> minDepth(int min) {
        return is(tree -> min <= tree.getDepth(), sendMessage(INIT_TREE, "min_depth", this.object.getDepth(), min));
    }

    /**
     * @param max
     * @return CheckerTree<T>
     */
    public CheckerTree<T> maxDepth(int max) {
        return is(tree -> tree.getDepth() <= max,
            sendMessage(INIT_TREE, "max_depth", this.object.getDepth(), max));
    }

    /**
     * @param min
     * @param max
     * @return CheckerTree<T>
     */
    public CheckerTree<T> inRangeDepth(int min, int max) {
        return is(tree -> min <= tree.getDepth() && tree.getDepth() <= max,
            sendMessage(INIT_TREE, "in_range_depth", this.object.getDepth(), min, max));
    }

    /**
     * @param min
     * @return CheckerTree<T>
     */
    public CheckerTree<T> minLeaves(int min) {
        return is(tree -> min <= tree.countLeaves(),
            sendMessage(INIT_TREE, "min_leaves", this.object.countLeaves(), min));
    }

    /**
     * @param max
     * @return CheckerTree<T>
     */
    public CheckerTree<T> maxLeaves(int max) {
        return is(tree -> tree.countLeaves() <= max,
            sendMessage(INIT_TREE, "max_leaves", this.object.countLeaves(), max));
    }

    /**
     * @param min
     * @param max
     * @return CheckerTree<T>
     */
    public CheckerTree<T> inRangeLeaves(int min, int max) {
        return is(tree -> min <= tree.countLeaves() && tree.countLeaves() <= max,
            sendMessage(INIT_TREE, "in_range_leaves", this.object.countLeaves(), min, max));
    }

    /**
     * @param min
     * @return CheckerTree<T>
     */
    public CheckerTree<T> minDiamenter(int min) {
        return is(tree -> min <= tree.diameter(),
            sendMessage(INIT_TREE, "min_diamenter", this.object.diameter(), min));
    }

    /**
     * @param max
     * @return CheckerTree<T>
     */
    public CheckerTree<T> maxDiamenter(int max) {
        return is(tree -> tree.diameter() <= max,
            sendMessage(INIT_TREE, "max_diamenter", this.object.diameter(), max));
    }

    /**
     * @param min
     * @param max
     * @return CheckerTree<T>
     */
    public CheckerTree<T> inRangeDiamenter(int min, int max) {
        return is(tree -> min <= tree.diameter() && tree.diameter() <= max,
            sendMessage(INIT_TREE, "in_range_diamenter", this.object.diameter(), min, max));
    }

}
