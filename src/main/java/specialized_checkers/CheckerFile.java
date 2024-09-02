package specialized_checkers;

import static util.Message.sendMessage;

import java.io.File;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

import netscape.javascript.JSObject;
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
        is(file -> file.exists(), sendMessage(INIT_FILE, "exists"));
        return this;
    }

    public CheckerFile isTypeFile(){
        is(file -> file.isFile(), sendMessage(INIT_FILE, "is_type_file"));
        return this;
    }

    public CheckerFile isTypeDirectory(){
        is(file -> file.isDirectory(), sendMessage(INIT_FILE, "is_type_directory"));
        return this;
    }

    public CheckerFile isTypeHidden(){
        is(file -> file.isHidden(), sendMessage(INIT_FILE, "is_type_hidden"));
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
        isTypeFile();
        is(file -> minBytes < file.length(), sendMessage(INIT_FILE, "min", minBytes));
        return this;
    }

    public CheckerFile max(int maxBytes){
        isTypeFile();
        is(file -> file.length() < maxBytes, sendMessage(INIT_FILE, "max", maxBytes));
        return this;
    }

    public CheckerFile inRange(int minBytes, int maxBytes){
        isTypeFile();
        is(file -> minBytes < file.length() && file.length() < maxBytes, sendMessage(INIT_FILE, "in_range", minBytes, maxBytes));
        return this;
    }

    public CheckerFile withExtension(String extension) {
        is(file -> getFileExtension(file).equals(extension), sendMessage(INIT_FILE, "with_extension"));
        return this;
    }

    public CheckerFile withAnyExtension(String... extensions) {
        is(file -> Stream.of(extensions).anyMatch(extension -> getFileExtension(file).equals(extension)), sendMessage(INIT_FILE, "with_any_extension"));
        return this;
    }

    private static String getFileExtension(File file){
        String fileName = file.getName();
        int lastIndexOfDot = fileName.lastIndexOf('.');

        if (lastIndexOfDot == -1 || lastIndexOfDot == 0) 
            return "";
        
        return fileName.substring(lastIndexOfDot + 1);
    }

}
