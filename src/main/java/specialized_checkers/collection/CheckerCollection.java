package specialized_checkers.collection;

import java.util.Collection;

import util.AbstractChecker;

public class CheckerCollection<T> extends AbstractChecker<Collection<T>> {

    public CheckerCollection(Collection<T> object, String name) {
        super(object, name);
    }

    public CheckerCollection<T> isEmpty() {

        is(this.object.isEmpty(), null);
        return this;
    }

    public CheckerCollection<T> isSorted() {
        is(this.object.isEmpty(), null);
        return this;
    }

}
