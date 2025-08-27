package specialized_checkers.collection;

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

    /**
     * @return CheckerList<T>
     */
    @Override
    protected CheckerList<T> self() {
        return this;
    }

    /**
     * @return CheckerList<T>
     */
    public CheckerList<T> isEmpty(){
        return is(list-> list.isEmpty(), sendMessage(INIT_LIST, "is_empty"));
    }

    /**
     * @param predicate
     * @return CheckerList<T>
     */
    public CheckerList<T> anyMatch(Predicate<T> predicate){
        return is(list-> list.stream().anyMatch(predicate), sendMessage(INIT_LIST, "any_match"));
    }

    /**
     * @param predicate
     * @return CheckerList<T>
     */
    public CheckerList<T> allMatch(Predicate<T> predicate){
        return is(list-> list.stream().allMatch(predicate), sendMessage(INIT_LIST, "all_match"));
    }

    /**
     * @return CheckerList<T>
     */
    public CheckerList<T> allDistinct(){
        return is(list -> Set.copyOf(list).size() == list.size(), sendMessage(INIT_LIST, "all_distinct"));
    }

    /**
     * @param collection
     * @return CheckerList<T>
     */
    public CheckerList<T> isSubset(Collection<T> collection){
        return is(list -> collection.containsAll(list), sendMessage(INIT_LIST, "is_subset"));
    }

    /**
     * @param collection
     * @return CheckerList<T>
     */
    public CheckerList<T> isSuperset(Collection<T> collection){
        return is(list-> list.containsAll(collection), sendMessage(INIT_LIST, "is_superset"));
    }

    /**
     * @param matching
     * @param percentage
     * @return CheckerList<T>
     */
    public CheckerList<T> isSufficientPercentage(Predicate<T> matching, double percentage){
        Predicate<List<T>> predicate = list -> {
            double percentageMatching = list.stream().filter(matching).count() * 100. / list.size();
            return percentageMatching >= percentage;
        };
        return is(predicate, sendMessage(INIT_LIST, "is_suffcient_percentage"));
    }

}
