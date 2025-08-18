package specialized_checkers.collections;

import static util.Message.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import util.AbstractChecker;

public class CheckerList<T> extends AbstractChecker<List<T>, CheckerList<T>>{

    private static final String INIT_LIST = "collections.list";

    public CheckerList(List<T> object, String name) {
        super(object, name);
    }

    @Override
    protected CheckerList<T> self() {
        return this;
    }

    public CheckerList<T> isEmpty(){
        is(list-> list.isEmpty(), sendMessage(INIT_LIST, "is_empty"));
        return this;
    }

    public CheckerList<T> anyMatch(Predicate<T> predicate){
        is(list-> list.stream().anyMatch(predicate), sendMessage(INIT_LIST, "any_match"));
        return this;
    }

    public CheckerList<T> allMatch(Predicate<T> predicate){
        is(list-> list.stream().allMatch(predicate), sendMessage(INIT_LIST, "all_match"));
        return this;
    }

    public CheckerList<T> allDistinct(){
        is(list -> Set.copyOf(list).size() == list.size(), sendMessage(INIT_LIST, "all_distinct"));
        return this;
    }

    public CheckerList<T> isSubset(Collection<T> collection){
        is(list -> collection.containsAll(list), sendMessage(INIT_LIST, "is_subset"));
        return this;
    }

    public CheckerList<T> isSuperset(Collection<T> collection){
        is(list-> list.containsAll(collection), sendMessage(INIT_LIST, "is_superset"));
        return this;
    }

    public CheckerList<T> isSufficientPercentage(Predicate<T> matching, double percentage){
        Predicate<List<T>> predicate = list -> {
            double percentageMatching = list.stream().filter(matching).count() * 100. / list.size();
            return percentageMatching >= percentage;
        };
        is(predicate, sendMessage(INIT_LIST, "is_suffcient_percentage"));
        return this;
    }

}
