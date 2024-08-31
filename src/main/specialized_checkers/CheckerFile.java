package src.main.specialized_checkers;

import java.io.File;
import java.util.List;

import src.main.AbstractChecker;

public class CheckerFile extends AbstractChecker<File> {

    public CheckerFile(File object, String name) {
        super(object, name);
    }
    
}
