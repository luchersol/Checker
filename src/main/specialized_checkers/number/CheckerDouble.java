package src.main.specialized_checkers.number;

import java.util.List;

public class CheckerDouble extends CheckerNumber<Double> {

    public CheckerDouble(Double object) {
        super(object);
    }

    public CheckerDouble(Double object, List<Exception> errors) {
        super(object, errors);
    }

    public CheckerDouble(Double object, List<Exception> errors, boolean saveErrors) {
        super(object, errors, saveErrors);
    }
    
    public CheckerDouble isNaN(){
        is(this.object.isNaN(), null);
        return this;
    }

    public CheckerDouble isInfinite(){
        is(this.object.isInfinite(), null);
        return this;
    }
}
