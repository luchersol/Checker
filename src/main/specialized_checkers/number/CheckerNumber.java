package src.main.specialized_checkers.number;

import java.util.List;

import src.main.AbstractChecker;

public class CheckerNumber<T extends Number> extends AbstractChecker<T> {

    public CheckerNumber(T object) {
        super(object);
    }
    
    public CheckerNumber(T object, List<Exception> errors) {
        super(object, errors);
    }

    public CheckerNumber(T object, List<Exception> errors, boolean saveErrors) {
        super(object, errors, saveErrors);
    }
}
