package specialized_checkers.collections;

import static util.Message.*;

import util.AbstractChecker;
import util.Tree;

public class CheckerTree<T> extends AbstractChecker<Tree<T>, CheckerTree<T>> {

    private static final String INIT_TREE = "collections.tree";

    public CheckerTree(Tree<T> object, String name) {
        super(object, name);
    }

    @Override
    protected CheckerTree<T> self() {
        return this;
    }

    public CheckerTree<T> isEmpty() {
        return is(tree -> tree.isEmpty(), sendMessage(INIT_TREE, "is_binary_tree"));
    }

    public CheckerTree<T> isBinaryTree() {
        return is(tree -> tree.isBinaryTree(), sendMessage(INIT_TREE, "is_binary_tree"));
    }

    public CheckerTree<T> isSymmetric() {
        return is(tree -> tree.isSymmetric(), sendMessage(INIT_TREE, "is_symmetric"));
    }

    public CheckerTree<T> isFull() {
        return is(tree -> tree.isFull(), sendMessage(INIT_TREE, "is_symmetric"));
    }

    public CheckerTree<T> minDepth(int min) {
        return is(tree -> min <= tree.getDepth(), sendMessage(INIT_TREE, "min_depth"));
    }

    public CheckerTree<T> maxDepth(int max) {
        return is(tree -> tree.getDepth() <= max, sendMessage(INIT_TREE, "max_depth"));
    }

    public CheckerTree<T> inRangeDepth(int min, int max) {
        return is(tree -> min <= tree.getDepth() && tree.getDepth() <= max, sendMessage(INIT_TREE, "in_range_depth"));
    }

    public CheckerTree<T> minLeaves(int min) {
        return is(tree -> min <= tree.countLeaves(), sendMessage(INIT_TREE, "min_leaves"));
    }

    public CheckerTree<T> maxLeaves(int max) {
        return is(tree -> tree.countLeaves() <= max, sendMessage(INIT_TREE, "max_leaves"));
    }

    public CheckerTree<T> inRangeLeaves(int min, int max) {
        return is(tree -> min <= tree.countLeaves() && tree.countLeaves() <= max, sendMessage(INIT_TREE, "in_range_leaves"));
    }

    public CheckerTree<T> minDiamenter(int min) {
        return is(tree -> min <= tree.diameter(), sendMessage(INIT_TREE, "min_diamenter"));
    }

    public CheckerTree<T> maxDiamenter(int max) {
        return is(tree -> tree.diameter() <= max, sendMessage(INIT_TREE, "max_diamenter"));
    }

    public CheckerTree<T> inRangeDiamenter(int min, int max) {
        return is(tree -> min <= tree.diameter() && tree.diameter() <= max, sendMessage(INIT_TREE, "in_range_diamenter"));
    }

}
