package specialized_checkers.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.CheckerException;

public class CheckerJsonTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonNode sampleJson() throws IOException {
        String json = """
            {
                \"name\": \"John\",
                \"age\": 30, \"active\": true,
                \"roles\": [\"admin\", \"user\"],
                \"address\": { \"city\": \"NY\" },
                \"score\": 7
            }
        """;
        return mapper.readTree(json);
    }

    @Test
    public void testCheckWithJsonNodeAndName() throws IOException {
        JsonNode node = sampleJson();
        CheckerJson checker = CheckerJson.check(node, "TestJson");
        assertNotNull(checker);
    }

    @Test
    public void testCheckWithJsonNodeDefaultName() throws IOException {
        JsonNode node = sampleJson();
        CheckerJson checker = CheckerJson.check(node);
        assertNotNull(checker);
    }

    @Test
    public void testHasProperty() throws IOException {
        CheckerJson checker = CheckerJson.check(sampleJson());
        assertDoesNotThrow(() -> checker.hasProperty("name"));
    }

    @Test
    public void testIsArray() throws IOException {
        CheckerJson checker = CheckerJson.check(sampleJson());
        assertDoesNotThrow(() -> checker.isArray("roles"));
    }

    @Test
    public void testIsBoolean() throws IOException {
        CheckerJson checker = CheckerJson.check(sampleJson());
        assertDoesNotThrow(() -> checker.isBoolean("active"));
    }

    @Test
    public void testIsInt() throws IOException {
        CheckerJson checker = CheckerJson.check(sampleJson());
        assertDoesNotThrow(() -> checker.isInt("age"));
    }

    @Test
    public void testIsTextual() throws IOException {
        CheckerJson checker = CheckerJson.check(sampleJson());
        assertDoesNotThrow(() -> checker.isTextual("name"));
    }

    @Test
    public void testIsObject() throws IOException {
        CheckerJson checker = CheckerJson.check(sampleJson());
        assertDoesNotThrow(() -> checker.isObject("address"));
    }

    @Test
    public void testIsInRange() throws IOException {
        CheckerJson checker = CheckerJson.check(sampleJson());
        assertDoesNotThrow(() -> checker.isInRange("score", 5, 10));
    }

    @Test
    public void testMatchesRegex() throws IOException {
        CheckerJson checker = CheckerJson.check(sampleJson());
        assertDoesNotThrow(() -> checker.matchesRegex("name", "J.*"));
    }

    @Test
    public void testIsInEnum() throws IOException {
        CheckerJson checker = CheckerJson.check(sampleJson());
        assertDoesNotThrow(() -> checker.isInEnum("name", Arrays.asList("John", "Jane")));
    }

    @Test
    public void testHasLengthBetween() throws IOException {
        CheckerJson checker = CheckerJson.check(sampleJson());
        assertDoesNotThrow(() -> checker.hasLengthBetween("name", 2, 10));
    }

    @Test
    public void testCheckWithFile() throws IOException {
        File tempFile = File.createTempFile("test", ".json");
        tempFile.deleteOnExit();
        mapper.writeValue(tempFile, sampleJson());
        CheckerJson checker = CheckerJson.check(tempFile);
        assertNotNull(checker);
    }

    @Test
    public void testIsMissingNode() throws IOException {
        CheckerJson checker = CheckerJson.check(sampleJson());
        assertThrows(CheckerException.class, () -> checker.isMissingNode("unknown_node"));
    }

    @Test
    public void testIsNull() throws IOException {
        String json = "{ \"value\": null }";
        JsonNode node = mapper.readTree(json);
        CheckerJson checker = CheckerJson.check(node);
        assertDoesNotThrow(() -> checker.isNull("value"));
    }
}
