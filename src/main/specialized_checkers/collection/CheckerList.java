package src.main.specialized_checkers.collection;

import java.util.List;

import src.main.AbstractChecker;

public class CheckerList<T> extends AbstractChecker<List<T>>{

    public CheckerList(List<T> object, String name) {
        super(object, name);
    }

}
