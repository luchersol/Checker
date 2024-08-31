package src.main.specialized_checkers;

import java.util.List;

import src.main.AbstractChecker;

public class CheckerString extends AbstractChecker<String> {

    public CheckerString(String object) {
        super(object);
    }

    public CheckerString(String object, List<Exception> errors) {
        super(object, errors);
    }

    public CheckerString(String object, List<Exception> errors, boolean saveErrors) {
        super(object, errors, saveErrors);
    }

    public CheckerString isDigit() {
        is(this.object.matches("\\d+"), "");
        return this;
    }
    

}
