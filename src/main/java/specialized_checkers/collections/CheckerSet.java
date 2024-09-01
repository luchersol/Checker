package specialized_checkers.collections;

import static util.Message.sendMessage;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

import util.AbstractChecker;

public class CheckerSet<T> extends AbstractChecker<Set<T>>{

    private static final String INIT_SET = "collections.set";

    public CheckerSet(Set<T> object, String name) {
        super(object, name);
    }

    public CheckerSet<T> isEmpty(){
        is(this.object.isEmpty(), sendMessage(INIT_SET, "is_empty"));
        return this;
    }

    public CheckerSet<T> anyMatch(Predicate<T> predicate){
        is(this.object.stream().anyMatch(predicate), sendMessage(INIT_SET, "any_match"));
        return this;
    }

    public CheckerSet<T> allMatch(Predicate<T> predicate){
        is(this.object.stream().allMatch(predicate), sendMessage(INIT_SET, "all_match"));
        return this;
    }

    public CheckerSet<T> isSubset(Collection<T> collection){
        is(collection.containsAll(this.object), sendMessage(INIT_SET, "is_subset"));
        return this;
    }

    public CheckerSet<T> isSuperset(Collection<T> collection){
        is(this.object.containsAll(collection), sendMessage(INIT_SET, "is_superset"));
        return this;
    }

}
