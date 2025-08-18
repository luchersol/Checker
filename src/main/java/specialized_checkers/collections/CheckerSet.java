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
        is(set -> set.isEmpty(), sendMessage(INIT_SET, "is_empty"));
        return this;
    }

    public CheckerSet<T> anyMatch(Predicate<T> predicate){
        is(set -> set.stream().anyMatch(predicate), sendMessage(INIT_SET, "any_match"));
        return this;
    }

    public CheckerSet<T> allMatch(Predicate<T> predicate){
        is(set -> set.stream().allMatch(predicate), sendMessage(INIT_SET, "all_match"));
        return this;
    }

    public CheckerSet<T> isSubset(Collection<T> collection){
        is(set -> collection.containsAll(set), sendMessage(INIT_SET, "is_subset"));
        return this;
    }

    public CheckerSet<T> isSuperset(Collection<T> collection){
        is(set -> set.containsAll(collection), sendMessage(INIT_SET, "is_superset"));
        return this;
    }

    public CheckerSet<T> isSufficientPercentage(Predicate<T> matching, double percentage){
        Predicate<Set<T>> predicate = set -> {
            double percentageMatching = set.stream().filter(matching).count() * 100. / set.size();
            return percentageMatching >= percentage;
        };
        is(predicate, sendMessage(INIT_SET, "is_suffcient_percentage"));
        return this;
    }

}
