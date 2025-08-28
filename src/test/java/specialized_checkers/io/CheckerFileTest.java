package specialized_checkers.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.CheckerException;

class CheckerFileTest {

    private File tempFile;
    private File tempDir;
    private File hiddenFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
        Files.writeString(tempFile.toPath(), "Hello World");

        tempDir = Files.createTempDirectory("testDir").toFile();
        tempDir.deleteOnExit();

        hiddenFile = new File(tempFile.getParent(), ".hiddenTestFile");
        hiddenFile.createNewFile();
        if (System.getProperty("os.name").startsWith("Windows")) {
            Files.setAttribute(hiddenFile.toPath(), "dos:hidden", true);
        }
        hiddenFile.deleteOnExit();
    }

    @Test
    void testExists() {
        assertDoesNotThrow(() -> new CheckerFile(tempFile, "tempFile").exists());
        assertThrows(CheckerException.class, () -> new CheckerFile(new File("nonexistent.txt"), "noFile").exists());
    }

    @Test
    void testIsTypeFile() {
        assertDoesNotThrow(() -> new CheckerFile(tempFile, "tempFile").isTypeFile());
        assertThrows(CheckerException.class, () -> new CheckerFile(tempDir, "tempDir").isTypeFile());
    }

    @Test
    void testIsTypeDirectory() {
        assertDoesNotThrow(() -> new CheckerFile(tempDir, "tempDir").isTypeDirectory());
        assertThrows(CheckerException.class, () -> new CheckerFile(tempFile, "tempFile").isTypeDirectory());
    }

    @Test
    void testIsTypeHidden() {
        assertDoesNotThrow(() -> new CheckerFile(hiddenFile, "hiddenFile").isTypeHidden());
        assertThrows(CheckerException.class, () -> new CheckerFile(tempFile, "tempFile").isTypeHidden());
    }

    @Test
    void testCanReadAndWrite() {
        assertDoesNotThrow(() -> new CheckerFile(tempFile, "tempFile").canRead());
        assertDoesNotThrow(() -> new CheckerFile(tempFile, "tempFile").canWrite());
    }

    @Test
    void testMinMaxInRange() {
        long fileSize = tempFile.length();
        assertDoesNotThrow(() -> new CheckerFile(tempFile, "tempFile").min((int)fileSize));
        assertDoesNotThrow(() -> new CheckerFile(tempFile, "tempFile").max((int)fileSize));
        assertDoesNotThrow(() -> new CheckerFile(tempFile, "tempFile").inRange((int)fileSize, (int)fileSize + 10));
        assertThrows(CheckerException.class, () -> new CheckerFile(tempFile, "tempFile").min((int)fileSize + 100));
        assertThrows(CheckerException.class, () -> new CheckerFile(tempFile, "tempFile").max((int)fileSize - 1));
    }

    @Test
    void testWithExtension() {
        assertDoesNotThrow(() -> new CheckerFile(tempFile, "tempFile").withExtension("txt"));
        assertThrows(CheckerException.class, () -> new CheckerFile(tempFile, "tempFile").withExtension("pdf"));
    }

    @Test
    void testWithAnyExtension() {
        assertDoesNotThrow(() -> new CheckerFile(tempFile, "tempFile").withAnyExtension("txt", "pdf"));
        assertThrows(CheckerException.class, () -> new CheckerFile(tempFile, "tempFile").withAnyExtension("doc", "pdf"));
    }

    @Test
    void testIsIdentical() throws IOException {
        File copy = File.createTempFile("copy", ".txt");
        copy.deleteOnExit();
        Files.writeString(copy.toPath(), Files.readString(tempFile.toPath()));

        assertDoesNotThrow(() -> new CheckerFile(tempFile, "tempFile").isIdentical(copy.getAbsolutePath()));

        File different = File.createTempFile("different", ".txt");
        different.deleteOnExit();
        Files.writeString(different.toPath(), "Different content");

        assertThrows(CheckerException.class, () -> new CheckerFile(tempFile, "tempFile").isIdentical(different.getAbsolutePath()));
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
        hiddenFile.delete();
        tempDir.delete();
    }
}
