package specialized_checkers.collections;

import static util.Message.*;

import java.util.function.Predicate;

import util.AbstractChecker;
import util.Tree;

public class CheckerTree<T> extends AbstractChecker<Tree<T>> {

    private static final String INIT_TREE = "collections.tree";

    public CheckerTree(Tree<T> object, String name) {
        super(object, name);
    }
    
    @Override
    public CheckerTree<T> is(Predicate<Tree<T>> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerTree<T> is(Predicate<Tree<T>> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerTree<T> isNot(Predicate<Tree<T>>  condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerTree<T> isNot(Predicate<Tree<T>>  condition) {
        super.isNot(condition);
        return this;
    }

    public CheckerTree<T> isEmpty() {
        is(tree -> tree.isEmpty(), sendMessage(INIT_TREE, "is_binary_tree"));
        return this;
    }

    public CheckerTree<T> isBinaryTree() {
        is(tree -> tree.isBinaryTree(), sendMessage(INIT_TREE, "is_binary_tree"));
        return this;
    }

    public CheckerTree<T> isSymmetric() {
        is(tree -> tree.isSymmetric(), sendMessage(INIT_TREE, "is_symmetric"));
        return this;
    }

    public CheckerTree<T> isFull() {
        is(tree -> tree.isFull(), sendMessage(INIT_TREE, "is_symmetric"));
        return this;
    }

    public CheckerTree<T> minDepth(int min) {
        is(tree -> min <= tree.getDepth(), sendMessage(INIT_TREE, "min_depth"));
        return this;
    }

    public CheckerTree<T> maxDepth(int max) {
        is(tree -> tree.getDepth() <= max, sendMessage(INIT_TREE, "max_depth"));
        return this;
    }

    public CheckerTree<T> inRangeDepth(int min, int max) {
        is(tree -> min <= tree.getDepth() && tree.getDepth() <= max, sendMessage(INIT_TREE, "in_range_depth"));
        return this;
    }

    public CheckerTree<T> minLeaves(int min) {
        is(tree -> min <= tree.countLeaves(), sendMessage(INIT_TREE, "min_leaves"));
        return this;
    }

    public CheckerTree<T> maxLeaves(int max) {
        is(tree -> tree.countLeaves() <= max, sendMessage(INIT_TREE, "max_leaves"));
        return this;
    }

    public CheckerTree<T> inRangeLeaves(int min, int max) {
        is(tree -> min <= tree.countLeaves() && tree.countLeaves() <= max, sendMessage(INIT_TREE, "in_range_leaves"));
        return this;
    }

    public CheckerTree<T> minDiamenter(int min) {
        is(tree -> min <= tree.diameter(), sendMessage(INIT_TREE, "min_diamenter"));
        return this;
    }

    public CheckerTree<T> maxDiamenter(int max) {
        is(tree -> tree.diameter() <= max, sendMessage(INIT_TREE, "max_diamenter"));
        return this;
    }

    public CheckerTree<T> inRangeDiamenter(int min, int max) {
        is(tree -> min <= tree.diameter() && tree.diameter() <= max, sendMessage(INIT_TREE, "in_range_diamenter"));
        return this;
    }

}
