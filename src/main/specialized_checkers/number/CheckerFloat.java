package src.main.specialized_checkers.number;

import java.util.List;

public class CheckerFloat extends CheckerNumber<Float> {

    public CheckerFloat(Float object) {
        super(object);
    }

    public CheckerFloat(Float object, List<Exception> errors) {
        super(object, errors);
    }

    public CheckerFloat(Float object, List<Exception> errors, boolean saveErrors) {
        super(object, errors, saveErrors);
    }

    
}
