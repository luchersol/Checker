package src.main.specialized_checkers.collection;

import java.util.List;

public class CheckerList extends CheckerCollection<List<Object>>{

    public CheckerList(List<Object> object) {
        super(object);
    }

    public CheckerList(List<Object> object, List<Exception> errors) {
        super(object, errors);
    }

    public CheckerList(List<Object> object, List<Exception> errors, boolean saveErrors) {
        super(object, errors, saveErrors);
    }

}
