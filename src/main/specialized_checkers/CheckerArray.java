package src.main.specialized_checkers;

import java.util.List;

import src.main.AbstractChecker;

public class CheckerArray extends AbstractChecker<Object[]>{

    public CheckerArray(Object[] object) {
        super(object);
    }

    public CheckerArray(Object[] object, List<Exception> errors) {
        super(object, errors);
    }

    public CheckerArray(Object[] object, List<Exception> errors, boolean saveErrors) {
        super(object, errors, saveErrors);
    }
    
}
