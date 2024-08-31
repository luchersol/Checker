package src.main.specialized_checkers.number;

import java.util.List;

public class CheckerInteger extends CheckerNumber<Integer> {

    public CheckerInteger(Integer object) {
        super(object);
    }

    public CheckerInteger(Integer object, List<Exception> errors) {
        super(object, errors);
    }

    public CheckerInteger(Integer object, List<Exception> errors, boolean saveErrors) {
        super(object, errors, saveErrors);
    }
    
}
