package src.main.specialized_checkers.collection;

import java.util.List;
import java.util.Set;

public class CheckerSet extends CheckerCollection<Set<Object>>{

    public CheckerSet(Set<Object> object) {
        super(object);
    }

    public CheckerSet(Set<Object> object, List<Exception> errors) {
        super(object, errors);
    }

    public CheckerSet(Set<Object> object, List<Exception> errors, boolean saveErrors) {
        super(object, errors, saveErrors);
    }
    
}
