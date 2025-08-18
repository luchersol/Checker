package specialized_checkers.file;

import static util.Message.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

import util.AbstractChecker;

public class CheckerFile extends AbstractChecker<File, CheckerFile> {

    private static final String INIT_FILE = "file";

    public CheckerFile(File object, String name) {
        super(object, name);
    }

    @Override
    protected CheckerFile self() {
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
        is(file -> minBytes <= getFileSize(file), sendMessage(INIT_FILE, "min", minBytes));
        return this;
    }

    public CheckerFile max(int maxBytes){
        isTypeFile();
        is(file -> getFileSize(file) <= maxBytes, sendMessage(INIT_FILE, "max", maxBytes));
        return this;
    }

    public CheckerFile inRange(int minBytes, int maxBytes){
        isTypeFile();

        is(file -> minBytes <= getFileSize(file) && getFileSize(file) <= maxBytes, sendMessage(INIT_FILE, "in_range", minBytes, maxBytes));
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

    public CheckerFile isIdentical(String path) {
        is(file -> areFilesIdentical(file, path), sendMessage(INIT_FILE, "with_any_extension"));
        return this;
    }

    private static boolean areFilesIdentical(File file1, String path2) {
        try {
            Path file2 = Paths.get(path2);
            
            if (file1.length() != Files.size(file2)) return false;

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash1 = md.digest(Files.readAllBytes(file1.toPath()));
            byte[] hash2 = md.digest(Files.readAllBytes(file2));

            return MessageDigest.isEqual(hash1, hash2);
        } catch (IOException | NoSuchAlgorithmException e) {
            return false;
        }
    }

    private static String getFileExtension(File file){
        String fileName = file.getName();
        int lastIndexOfDot = fileName.lastIndexOf('.');

        if (lastIndexOfDot == -1 || lastIndexOfDot == 0) return "";
        
        return fileName.substring(lastIndexOfDot + 1);
    }

    private static long getFileSize(File file) {
        if (file == null || !file.exists()) return 0;

        if (file.isFile()) return file.length();

        long size = 0;
        File[] files = file.listFiles();

        if (files != null) {
            for (File f : files) {
                size += getFileSize(f);
            }
        }

        return size;
    }

}
