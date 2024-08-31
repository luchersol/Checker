package src.main.specialized_checkers;

import src.main.AbstractChecker;

public class CheckerArray<T> extends AbstractChecker<T[]>{

    public CheckerArray(T[] object, String name) {
        super(object, name);
    }
    
}
