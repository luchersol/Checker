package specialized_checkers.collections;

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

    @Override
    protected CheckerSet<T> self() {
        return this;
    }

    public CheckerSet<T> isEmpty(){
        return is(set -> set.isEmpty(), sendMessage(INIT_SET, "is_empty"));
    }

    public CheckerSet<T> anyMatch(Predicate<T> predicate){
        return is(set -> set.stream().anyMatch(predicate), sendMessage(INIT_SET, "any_match"));
    }

    public CheckerSet<T> allMatch(Predicate<T> predicate){
        return is(set -> set.stream().allMatch(predicate), sendMessage(INIT_SET, "all_match"));
    }

    public CheckerSet<T> isSubset(Collection<T> collection){
        return is(set -> collection.containsAll(set), sendMessage(INIT_SET, "is_subset"));
    }

    public CheckerSet<T> isSuperset(Collection<T> collection){
        return is(set -> set.containsAll(collection), sendMessage(INIT_SET, "is_superset"));
    }

    public CheckerSet<T> isSufficientPercentage(Predicate<T> matching, double percentage){
        Predicate<Set<T>> predicate = set -> {
            double percentageMatching = set.stream().filter(matching).count() * 100. / set.size();
            return percentageMatching >= percentage;
        };
        return is(predicate, sendMessage(INIT_SET, "is_suffcient_percentage"));
    }

}
