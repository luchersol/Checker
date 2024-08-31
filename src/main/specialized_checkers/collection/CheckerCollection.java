package src.main.specialized_checkers.collection;

import java.util.Collection;
import java.util.List;

import src.main.AbstractChecker;

public class CheckerCollection<T extends Collection<Object>> extends AbstractChecker<T> {

    public CheckerCollection(T object) {
        super(object);
    }

    public CheckerCollection(T object, List<Exception> errors) {
        super(object, errors);
    }

    public CheckerCollection(T object, List<Exception> errors, boolean saveErrors) {
        super(object, errors, saveErrors);
    }

    public CheckerCollection<T> isEmpty(){
        is(this.object.isEmpty(), null);
        return this;
    }

    public CheckerCollection<T> isSorted(){
        is(this.object.isEmpty(), null);
        return this;
    } 

}
