package com.luchersol.core.specialized_checkers.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.luchersol.core.util.CheckerException;

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
        assertDoesNotThrow(() -> CheckerFile.check(tempFile, "tempFile").exists());
        assertThrows(CheckerException.class, () -> CheckerFile.check(new File("nonexistent.txt"), "noFile").exists());
    }

    @Test
    void testIsTypeFile() {
        assertDoesNotThrow(() -> CheckerFile.check(tempFile, "tempFile").isTypeFile());
        assertThrows(CheckerException.class, () -> CheckerFile.check(tempDir, "tempDir").isTypeFile());
    }

    @Test
    void testIsTypeDirectory() {
        assertDoesNotThrow(() -> CheckerFile.check(tempDir, "tempDir").isTypeDirectory());
        assertThrows(CheckerException.class, () -> CheckerFile.check(tempFile, "tempFile").isTypeDirectory());
    }

    @Test
    void testIsTypeHidden() {
        assertDoesNotThrow(() -> CheckerFile.check(hiddenFile, "hiddenFile").isTypeHidden());
        assertThrows(CheckerException.class, () -> CheckerFile.check(tempFile, "tempFile").isTypeHidden());
    }

    @Test
    void testCanReadAndWrite() {
        assertDoesNotThrow(() -> CheckerFile.check(tempFile, "tempFile").canRead());
        assertDoesNotThrow(() -> CheckerFile.check(tempFile, "tempFile").canWrite());
    }

    @Test
    void testMinMaxInRange() {
        long fileSize = tempFile.length();
        assertDoesNotThrow(() -> CheckerFile.check(tempFile, "tempFile").min((int)fileSize));
        assertDoesNotThrow(() -> CheckerFile.check(tempFile, "tempFile").max((int)fileSize));
        assertDoesNotThrow(() -> CheckerFile.check(tempFile, "tempFile").inRange((int)fileSize, (int)fileSize + 10));
        assertThrows(CheckerException.class, () -> CheckerFile.check(tempFile, "tempFile").min((int)fileSize + 100));
        assertThrows(CheckerException.class, () -> CheckerFile.check(tempFile, "tempFile").max((int)fileSize - 1));
    }

    @Test
    void testWithExtension() {
        assertDoesNotThrow(() -> CheckerFile.check(tempFile, "tempFile").withExtension("txt"));
        assertThrows(CheckerException.class, () -> CheckerFile.check(tempFile, "tempFile").withExtension("pdf"));
    }

    @Test
    void testWithAnyExtension() {
        assertDoesNotThrow(() -> CheckerFile.check(tempFile, "tempFile").withAnyExtension("txt", "pdf"));
        assertThrows(CheckerException.class, () -> CheckerFile.check(tempFile, "tempFile").withAnyExtension("doc", "pdf"));
    }

    @Test
    void testIsIdentical() throws IOException {
        File copy = File.createTempFile("copy", ".txt");
        copy.deleteOnExit();
        Files.writeString(copy.toPath(), Files.readString(tempFile.toPath()));

        assertDoesNotThrow(() -> CheckerFile.check(tempFile, "tempFile").isIdentical(copy.getAbsolutePath()));

        File different = File.createTempFile("different", ".txt");
        different.deleteOnExit();
        Files.writeString(different.toPath(), "Different content");

        assertThrows(CheckerException.class, () -> CheckerFile.check(tempFile, "tempFile").isIdentical(different.getAbsolutePath()));
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
        hiddenFile.delete();
        tempDir.delete();
    }
}
