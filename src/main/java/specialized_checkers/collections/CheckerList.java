package specialized_checkers.collections;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static util.Message.sendMessage;

import util.AbstractChecker;

public class CheckerList<T> extends AbstractChecker<List<T>>{

    private static final String INIT_LIST = "collections.list";

    public CheckerList(List<T> object, String name) {
        super(object, name);
    }

    public CheckerList<T> isEmpty(){
        is(this.object.isEmpty(), sendMessage(INIT_LIST, "is_empty"));
        return this;
    }

    public CheckerList<T> anyMatch(Predicate<T> predicate){
        is(this.object.stream().anyMatch(predicate), sendMessage(INIT_LIST, "any_match"));
        return this;
    }

    public CheckerList<T> allMatch(Predicate<T> predicate){
        is(this.object.stream().allMatch(predicate), sendMessage(INIT_LIST, "all_match"));
        return this;
    }

    public CheckerList<T> allDistinct(){
        is(Set.copyOf(this.object).size() == this.object.size(), sendMessage(INIT_LIST, "all_distinct"));
        return this;
    }

        public CheckerList<T> isSubset(Collection<T> collection){
        is(collection.containsAll(this.object), sendMessage(INIT_LIST, "is_subset"));
        return this;
    }

    public CheckerList<T> isSuperset(Collection<T> collection){
        is(this.object.containsAll(collection), sendMessage(INIT_LIST, "is_superset"));
        return this;
    }

}
