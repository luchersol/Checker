package src.main.specialized_checkers;

import java.io.File;
import java.util.List;

import src.main.AbstractChecker;

public class CheckerFile extends AbstractChecker<File> {

    public CheckerFile(File object) {
        super(object);
    }

    public CheckerFile(File object, List<Exception> errors) {
        super(object, errors);
    }

    public CheckerFile(File object, List<Exception> errors, boolean saveErrors) {
        super(object, errors, saveErrors);
    }
    
    
}
