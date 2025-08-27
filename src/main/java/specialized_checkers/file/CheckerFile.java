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

    /**
     * @return CheckerFile
     */
    @Override
    protected CheckerFile self() {
        return this;
    }

    /**
     * @return CheckerFile
     */
    public CheckerFile exists(){
        return is(file -> file.exists(), sendMessage(INIT_FILE, "exists"));
    }

    /**
     * @return CheckerFile
     */
    public CheckerFile isTypeFile(){
        return is(file -> file.isFile(), sendMessage(INIT_FILE, "is_type_file"));
    }

    /**
     * @return CheckerFile
     */
    public CheckerFile isTypeDirectory(){
        return is(file -> file.isDirectory(), sendMessage(INIT_FILE, "is_type_directory"));
    }

    /**
     * @return CheckerFile
     */
    public CheckerFile isTypeHidden(){
        return is(file -> file.isHidden(), sendMessage(INIT_FILE, "is_type_hidden"));
    }

    /**
     * @return CheckerFile
     */
    public CheckerFile canRead(){
        return is(file -> file.canRead(), sendMessage(INIT_FILE, "can_read"));
    }

    /**
     * @return CheckerFile
     */
    public CheckerFile canWrite(){
        return is(file -> file.canWrite(), sendMessage(INIT_FILE, "can_write"));
    }

    /**
     * @param minBytes
     * @return CheckerFile
     */
    public CheckerFile min(int minBytes){
        return is(file -> minBytes <= getFileSize(file), sendMessage(INIT_FILE, "min", minBytes));
    }

    /**
     * @param maxBytes
     * @return CheckerFile
     */
    public CheckerFile max(int maxBytes){
        isTypeFile();
        return is(file -> getFileSize(file) <= maxBytes, sendMessage(INIT_FILE, "max", maxBytes));
    }

    /**
     * @param minBytes
     * @param maxBytes
     * @return CheckerFile
     */
    public CheckerFile inRange(int minBytes, int maxBytes){
        isTypeFile();
        return is(file -> minBytes <= getFileSize(file) && getFileSize(file) <= maxBytes, sendMessage(INIT_FILE, "in_range", minBytes, maxBytes));
    }

    /**
     * @param extension
     * @return CheckerFile
     */
    public CheckerFile withExtension(String extension) {
        return is(file -> getFileExtension(file).equals(extension), sendMessage(INIT_FILE, "with_extension"));
    }

    /**
     * @param extensions
     * @return CheckerFile
     */
    public CheckerFile withAnyExtension(String... extensions) {
        return is(file -> Stream.of(extensions).anyMatch(extension -> getFileExtension(file).equals(extension)), sendMessage(INIT_FILE, "with_any_extension"));
    }

    /**
     * @param path
     * @return CheckerFile
     */
    public CheckerFile isIdentical(String path) {
        return is(file -> areFilesIdentical(file, path), sendMessage(INIT_FILE, "with_any_extension"));
    }

    /**
     * @param file1
     * @param path2
     * @return boolean
     */
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

    /**
     * @param file
     * @return String
     */
    private static String getFileExtension(File file){
        String fileName = file.getName();
        int lastIndexOfDot = fileName.lastIndexOf('.');

        if (lastIndexOfDot == -1 || lastIndexOfDot == 0) return "";

        return fileName.substring(lastIndexOfDot + 1);
    }

    /**
     * @param file
     * @return long
     */
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
