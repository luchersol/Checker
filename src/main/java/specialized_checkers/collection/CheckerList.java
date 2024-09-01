package specialized_checkers.collection;

import java.util.List;

import util.AbstractChecker;

public class CheckerList<T> extends AbstractChecker<List<T>>{

    public CheckerList(List<T> object, String name) {
        super(object, name);
    }

}
