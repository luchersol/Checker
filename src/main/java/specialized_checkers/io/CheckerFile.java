package specialized_checkers.io;

import static util.Message.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import util.AbstractChecker;

public class CheckerFile extends AbstractChecker<File, CheckerFile> {

    private static final String INIT_FILE = "io.file";
    private static final String DEFAULT_NAME = "File";


    protected CheckerFile(File file, String name) {
        super(file, name);
    }


    /**
     * @param file
     * @param name
     * @return CheckerFile
     */
    public static CheckerFile check(File file, String name) {
        return new CheckerFile(file, name);
    }

    /**
     * @param pathname
     * @param name
     * @return CheckerFile
     */
    public static CheckerFile check(String pathname, String name) {
        File file = new File(pathname);
        return check(file, name);
    }

    /**
     * @param file
     * @return CheckerFile
     */
    public static CheckerFile check(File file) {
        return check(file, DEFAULT_NAME);
    }

    /**
     * @param pathname
     * @return CheckerFile
     */
    public static CheckerFile check(String pathname) {
        File file = new File(pathname);
        return check(file);
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
        Path file2 = Paths.get(path2);
        try {
            if (file1.length() != Files.size(file2)) return false;

            try (InputStream in1 = Files.newInputStream(file1.toPath());
                InputStream in2 = Files.newInputStream(file2)) {

                byte[] buf1 = new byte[8192];
                byte[] buf2 = new byte[8192];

                int n1, n2;
                while ((n1 = in1.read(buf1)) != -1) {
                    n2 = in2.read(buf2);
                    if (n1 != n2 || !Arrays.equals(buf1, buf2)) {
                        return false;
                    }
                }
                return true;
            }
        } catch (IOException e) {
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
