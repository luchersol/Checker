package specialized_checkers.collection;

import java.util.Set;

import util.AbstractChecker;

public class CheckerSet<T> extends AbstractChecker<Set<T>>{

    public CheckerSet(Set<T> object, String name) {
        super(object, name);
    }

}
