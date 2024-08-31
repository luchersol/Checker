package src.main.specialized_checkers.number;

import java.util.List;

public class CheckerLong extends CheckerNumber<Long> {

    public CheckerLong(Long object) {
        super(object);
    }
    
    public CheckerLong(Long object, List<Exception> errors) {
        super(object, errors);
    }

    public CheckerLong(Long object, List<Exception> errors, boolean saveErrors) {
        super(object, errors, saveErrors);
    }
}
