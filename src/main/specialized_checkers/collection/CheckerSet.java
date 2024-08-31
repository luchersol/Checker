package src.main.specialized_checkers.collection;

import java.util.List;
import java.util.Set;

import src.main.AbstractChecker;

public class CheckerSet<T> extends AbstractChecker<Set<T>>{

    public CheckerSet(Set<T> object, String name) {
        super(object, name);
    }

}
