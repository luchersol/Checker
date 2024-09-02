package specialized_checkers;

import static util.Message.sendMessage;

import java.io.File;
import java.util.function.Predicate;

import util.AbstractChecker;

public class CheckerFile extends AbstractChecker<File> {

    private static final String INIT_FILE = "file";

    public CheckerFile(File object, String name) {
        super(object, name);
    }

    @Override
    public CheckerFile is(Predicate<File> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerFile is(Predicate<File> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerFile isNot(Predicate<File> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerFile isNot(Predicate<File> condition) {
        super.isNot(condition);
        return this;
    }

    public CheckerFile exists(){
        return this;
    }

    public CheckerFile isFile(){
        is(file -> file.isFile(), sendMessage(INIT_FILE, "is_file"));
        return this;
    }

    public CheckerFile isDirectory(){
        is(file -> file.isDirectory(), sendMessage(INIT_FILE, "is_directory"));
        return this;
    }

    public CheckerFile isHidden(){
        is(file -> file.isHidden(), sendMessage(INIT_FILE, "is_hidden"));
        return this;
    }

    public CheckerFile canRead(){
        is(file -> file.canRead(), sendMessage(INIT_FILE, "can_read"));
        return this;
    }

    public CheckerFile canWrite(){
        is(file -> file.canWrite(), sendMessage(INIT_FILE, "can_write"));
        return this;
    }

    public CheckerFile min(int minBytes){
        isFile();
        is(file -> minBytes < file.length(), sendMessage(INIT_FILE, "min"));
        return this;
    }

    public CheckerFile max(int maxBytes){
        isFile();
        is(file -> file.length() < maxBytes, sendMessage(INIT_FILE, "max"));
        return this;
    }

    public CheckerFile inRange(int minBytes, int maxBytes){
        isFile();
        is(file -> minBytes < file.length() && file.length() < maxBytes, sendMessage(INIT_FILE, "in_range"));
        return this;
    }

}
