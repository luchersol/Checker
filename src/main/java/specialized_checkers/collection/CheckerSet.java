package specialized_checkers.collection;

import static util.Message.*;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

import util.AbstractChecker;

public class CheckerSet<T> extends AbstractChecker<Set<T>, CheckerSet<T>>{

    private static final String INIT_SET = "collections.set";

    public CheckerSet(Set<T> object, String name) {
        super(object, name);
    }

    /**
     * @return CheckerSet<T>
     */
    @Override
    protected CheckerSet<T> self() {
        return this;
    }

    /**
     * @return CheckerSet<T>
     */
    public CheckerSet<T> isEmpty(){
        return is(set -> set.isEmpty(), sendMessage(INIT_SET, "is_empty"));
    }

    /**
     * @param predicate
     * @return CheckerSet<T>
     */
    public CheckerSet<T> anyMatch(Predicate<T> predicate){
        return is(set -> set.stream().anyMatch(predicate), sendMessage(INIT_SET, "any_match"));
    }

    /**
     * @param predicate
     * @return CheckerSet<T>
     */
    public CheckerSet<T> allMatch(Predicate<T> predicate){
        return is(set -> set.stream().allMatch(predicate), sendMessage(INIT_SET, "all_match"));
    }

    /**
     * @param collection
     * @return CheckerSet<T>
     */
    public CheckerSet<T> isSubset(Collection<T> collection){
        return is(set -> collection.containsAll(set), sendMessage(INIT_SET, "is_subset"));
    }

    /**
     * @param collection
     * @return CheckerSet<T>
     */
    public CheckerSet<T> isSuperset(Collection<T> collection){
        return is(set -> set.containsAll(collection), sendMessage(INIT_SET, "is_superset"));
    }

    /**
     * @param matching
     * @param percentage
     * @return CheckerSet<T>
     */
    public CheckerSet<T> isSufficientPercentage(Predicate<T> matching, double percentage){
        Predicate<Set<T>> predicate = set -> {
            double percentageMatching = set.stream().filter(matching).count() * 100. / set.size();
            return percentageMatching >= percentage;
        };
        return is(predicate, sendMessage(INIT_SET, "is_suffcient_percentage"));
    }

}
