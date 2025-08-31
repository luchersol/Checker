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
     * Creates a CheckerFile for the given file and assigns a custom name.
     *
     * @param file the file to check
     * @param name the name to assign to this checker
     * @return a CheckerFile instance for the given file
     */
    public static CheckerFile check(File file, String name) {
        return new CheckerFile(file, name);
    }

    /**
     * Creates a CheckerFile from a string pathname and assigns a custom name.
     *
     * @param pathname the path to the file
     * @param name the name to assign to this checker
     * @return a CheckerFile instance for the given path
     */
    public static CheckerFile check(String pathname, String name) {
        File file = new File(pathname);
        return check(file, name);
    }

    /**
     * Creates a CheckerFile for the given file with a default name.
     *
     * @param file the file to check
     * @return a CheckerFile instance for the given file
     */
    public static CheckerFile check(File file) {
        return check(file, DEFAULT_NAME);
    }

    /**
     * Creates a CheckerFile from a string pathname with a default name.
     *
     * @param pathname the path to the file
     * @return a CheckerFile instance for the given path
     */
    public static CheckerFile check(String pathname) {
        File file = new File(pathname);
        return check(file);
    }

    /**
     * Returns this instance (for fluent API).
     *
     * @return this CheckerFile instance
     */
    @Override
    protected CheckerFile self() {
        return this;
    }

    /**
     * Checks if the file exists.
     *
     * @return this CheckerFile instance
     */
    public CheckerFile exists(){
        return is(file -> file.exists(), sendMessage(INIT_FILE, "exists"));
    }

    /**
     * Checks if the file is a regular file (not a directory).
     *
     * @return this CheckerFile instance
     */
    public CheckerFile isTypeFile(){
        return is(file -> file.isFile(), sendMessage(INIT_FILE, "is_type_file"));
    }

    /**
     * Checks if the file is a directory.
     *
     * @return this CheckerFile instance
     */
    public CheckerFile isTypeDirectory(){
        return is(file -> file.isDirectory(), sendMessage(INIT_FILE, "is_type_directory"));
    }

    /**
     * Checks if the file is hidden.
     *
     * @return this CheckerFile instance
     */
    public CheckerFile isTypeHidden(){
        return is(file -> file.isHidden(), sendMessage(INIT_FILE, "is_type_hidden"));
    }

    /**
     * Checks if the file can be read.
     *
     * @return this CheckerFile instance
     */
    public CheckerFile canRead(){
        return is(file -> file.canRead(), sendMessage(INIT_FILE, "can_read"));
    }

    /**
     * Checks if the file can be written to.
     *
     * @return this CheckerFile instance
     */
    public CheckerFile canWrite(){
        return is(file -> file.canWrite(), sendMessage(INIT_FILE, "can_write"));
    }

    /**
     * Checks if the file size is at least the specified minimum number of bytes.
     *
     * @param minBytes the minimum file size in bytes
     * @return this CheckerFile instance
     */
    public CheckerFile min(int minBytes){
        return is(file -> minBytes <= getFileSize(file), sendMessage(INIT_FILE, "min", minBytes));
    }

    /**
     * Checks if the file size is at most the specified maximum number of bytes.
     *
     * @param maxBytes the maximum file size in bytes
     * @return this CheckerFile instance
     */
    public CheckerFile max(int maxBytes){
        isTypeFile();
        return is(file -> getFileSize(file) <= maxBytes, sendMessage(INIT_FILE, "max", maxBytes));
    }

    /**
     * Checks if the file size is within the specified range (inclusive).
     *
     * @param minBytes the minimum file size in bytes
     * @param maxBytes the maximum file size in bytes
     * @return this CheckerFile instance
     */
    public CheckerFile inRange(int minBytes, int maxBytes){
        isTypeFile();
        return is(file -> minBytes <= getFileSize(file) && getFileSize(file) <= maxBytes, sendMessage(INIT_FILE, "in_range", minBytes, maxBytes));
    }

    /**
     * Checks if the file has the specified extension.
     *
     * @param extension the file extension to check for (without dot)
     * @return this CheckerFile instance
     */
    public CheckerFile withExtension(String extension) {
        return is(file -> getFileExtension(file).equals(extension), sendMessage(INIT_FILE, "with_extension"));
    }

    /**
     * Checks if the file has any of the specified extensions.
     *
     * @param extensions the file extensions to check for (without dot)
     * @return this CheckerFile instance
     */
    public CheckerFile withAnyExtension(String... extensions) {
        return is(file -> Stream.of(extensions).anyMatch(extension -> getFileExtension(file).equals(extension)), sendMessage(INIT_FILE, "with_any_extension"));
    }

    /**
     * Checks if the file is identical to another file at the specified path.
     *
     * @param path the path to the file to compare with
     * @return this CheckerFile instance
     */
    public CheckerFile isIdentical(String path) {
        return is(file -> areFilesIdentical(file, path), sendMessage(INIT_FILE, "with_any_extension"));
    }

    /**
     * Compares two files for identical content and size.
     *
     * @param file1 the first file
     * @param path2 the path to the second file
     * @return true if the files are identical, false otherwise
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
     * Gets the extension of the given file.
     *
     * @param file the file whose extension is to be retrieved
     * @return the file extension as a String, or an empty string if none
     */
    private static String getFileExtension(File file){
        String fileName = file.getName();
        int lastIndexOfDot = fileName.lastIndexOf('.');

        if (lastIndexOfDot == -1 || lastIndexOfDot == 0) return "";

        return fileName.substring(lastIndexOfDot + 1);
    }

    /**
     * Gets the size of the given file or directory (recursively).
     *
     * @param file the file or directory
     * @return the size in bytes
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
