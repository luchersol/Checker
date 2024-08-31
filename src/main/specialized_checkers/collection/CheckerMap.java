package src.main.specialized_checkers.collection;

import java.util.List;
import java.util.Map;

import src.main.AbstractChecker;

public class CheckerMap extends AbstractChecker<Map<Object, Object>>{

    public CheckerMap(Map<Object, Object> object) {
        super(object);
    }

    public CheckerMap(Map<Object, Object> object, List<Exception> errors) {
        super(object, errors);
    }

    public CheckerMap(Map<Object, Object> object, List<Exception> errors, boolean saveErrors) {
        super(object, errors, saveErrors);
    }

}
