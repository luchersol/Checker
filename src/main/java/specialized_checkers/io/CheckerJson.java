package specialized_checkers.io;

import static util.Message.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.AbstractChecker;

/**
 * CheckerJson is a specialized checker for validating properties and structure of JSON data
 * represented by Jackson's {@link JsonNode}. It provides a fluent API for asserting the presence,
 * type, and value constraints of JSON properties, supporting both simple and complex validation scenarios.
 * <p>
 * This class offers static factory methods to create instances from {@link JsonNode}, {@link File}, or file paths,
 * with optional custom naming. It includes a variety of assertion methods for checking property existence,
 * type (e.g., array, object, number, string), value ranges, regular expression matches, and membership in enumerations.
 * <p>
 * Example usage:
 * <pre>
 *     JsonNode json = ...;
 *     CheckerJson checker = CheckerJson.check(json)
 *         .hasProperty("user.name")
 *         .isTextual("user.name")
 *         .hasLengthBetween("user.name", 3, 20);
 * </pre>
 */
public class CheckerJson extends AbstractChecker<JsonNode, CheckerJson> {

    private static final String INIT_JSON = "io.json";
    private static final String DEFAULT_NAME = "Json";


    /**
     * Constructs a new {@code CheckerJson} instance with the specified JSON node and name.
     *
     * @param json the {@link JsonNode} containing the configuration or data for this checker
     * @param name the name associated with this checker instance
     */
    protected CheckerJson(JsonNode json, String name) {
        super(json, name);
    }

    /**
     * Creates a CheckerJson for the given JsonNode and assigns a custom name.
     *
     * @param json the JsonNode to check
     * @param name the name to assign to this checker
     * @return a CheckerJson instance for the given JsonNode
     */
    public static CheckerJson check(JsonNode json, String name) {
        return new CheckerJson(json, name);
    }

    /**
     * Creates a CheckerJson from a file and assigns a custom name.
     *
     * @param file the file containing JSON data
     * @param name the name to assign to this checker
     * @return a CheckerJson instance for the JSON in the file
     * @throws IOException if the file cannot be read or parsed
     */
    public static CheckerJson check(File file, String name) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(file);
        return check(rootNode, name);
    }

    /**
     * Creates a CheckerJson from a file path and assigns a custom name.
     *
     * @param pathname the path to the file containing JSON data
     * @param name the name to assign to this checker
     * @return a CheckerJson instance for the JSON in the file
     * @throws IOException if the file cannot be read or parsed
     */
    public static CheckerJson check(String pathname, String name) throws IOException {
        File file = new File(pathname);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(file);
        return check(rootNode, name);
    }

    /**
     * Creates a CheckerJson for the given JsonNode with a default name.
     *
     * @param json the JsonNode to check
     * @return a CheckerJson instance for the given JsonNode
     */
    public static CheckerJson check(JsonNode json) {
        return check(json, DEFAULT_NAME);
    }

    /**
     * Creates a CheckerJson from a file with a default name.
     *
     * @param file the file containing JSON data
     * @return a CheckerJson instance for the JSON in the file
     * @throws IOException if the file cannot be read or parsed
     */
    public static CheckerJson check(File file) throws IOException {
        return check(file, DEFAULT_NAME);
    }

    /**
     * Creates a CheckerJson from a file path with a default name.
     *
     * @param pathname the path to the file containing JSON data
     * @return a CheckerJson instance for the JSON in the file
     * @throws IOException if the file cannot be read or parsed
     */
    public static CheckerJson check(String pathname) throws IOException {
        return check(pathname, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API).
     *
     * @return this CheckerJson instance
     */
    @Override
    protected CheckerJson self() {
        return this;
    }

    /**
     * Checks if the JSON contains the specified property path.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson hasProperty(String path){
        return is(json -> containsProperty(path), sendMessage(INIT_JSON, "has_property", path));
    }

    /**
     * Checks if the property at the specified path is an array.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isArray(String path) {
        return checkNodeType(path, JsonNode::isArray, sendMessage(INIT_JSON, "is_array", path));
    }

    /**
     * Checks if the property at the specified path is a BigDecimal.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isBigDecimal(String path) {
        return checkNodeType(path, JsonNode::isBigDecimal, sendMessage(INIT_JSON, "is_big_decimal", path));
    }

    /**
     * Checks if the property at the specified path is a BigInteger.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isBigInteger(String path) {
        return checkNodeType(path, JsonNode::isBigInteger, sendMessage(INIT_JSON, "is_big_integer", path));
    }

    /**
     * Checks if the property at the specified path is binary data.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isBinary(String path) {
        return checkNodeType(path, JsonNode::isBinary, sendMessage(INIT_JSON, "is_binary", path));
    }

    /**
     * Checks if the property at the specified path is a boolean value.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isBoolean(String path) {
        return checkNodeType(path, JsonNode::isBoolean, sendMessage(INIT_JSON, "is_boolean", path));
    }

    /**
     * Checks if the property at the specified path is a container node (object or array).
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isContainerNode(String path) {
        return checkNodeType(path, JsonNode::isContainerNode, sendMessage(INIT_JSON, "is_container_node", path));
    }

    /**
     * Checks if the property at the specified path is a double value.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isDouble(String path) {
        return checkNodeType(path, JsonNode::isDouble, sendMessage(INIT_JSON, "is_node", path));
    }

    /**
     * Checks if the property at the specified path is empty.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isEmpty(String path) {
        return checkNodeType(path, JsonNode::isEmpty, sendMessage(INIT_JSON, "is_empty", path));
    }

    /**
     * Checks if the property at the specified path is a float value.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isFloat(String path) {
        return checkNodeType(path, JsonNode::isFloat, sendMessage(INIT_JSON, "is_float", path));
    }

    /**
     * Checks if the property at the specified path is a floating point number.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isFloatingPointNumber(String path) {
        return checkNodeType(path, JsonNode::isFloatingPointNumber, sendMessage(INIT_JSON, "is_floating_point_number", path));
    }

    /**
     * Checks if the property at the specified path is an integer value.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isInt(String path) {
        return checkNodeType(path, JsonNode::isInt, sendMessage(INIT_JSON, "is_int", path));
    }

    /**
     * Checks if the property at the specified path is an integral number.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isIntegralNumber(String path) {
        return checkNodeType(path, JsonNode::isIntegralNumber, sendMessage(INIT_JSON, "is_integeral_number", path));
    }

    /**
     * Checks if the property at the specified path is a long value.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isLong(String path) {
        return checkNodeType(path, JsonNode::isLong, sendMessage(INIT_JSON, "is_long", path));
    }

    /**
     * Checks if the property at the specified path is a missing node.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isMissingNode(String path) {
        return checkNodeType(path, JsonNode::isMissingNode, sendMessage(INIT_JSON, "is_missing_node", path));
    }

    /**
     * Checks if the property at the specified path is null.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isNull(String path) {
        return checkNodeType(path, JsonNode::isNull, sendMessage(INIT_JSON, "is_null", path));
    }

    /**
     * Checks if the property at the specified path is a number.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isNumber(String path) {
        return checkNodeType(path, JsonNode::isNumber, sendMessage(INIT_JSON, "is_number", path));
    }

    /**
     * Checks if the property at the specified path is a JSON object.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isObject(String path) {
        return checkNodeType(path, JsonNode::isObject, sendMessage(INIT_JSON, "is_object", path));
    }

    /**
     * Checks if the property at the specified path is a POJO (Plain Old Java Object).
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isPojo(String path) {
        return checkNodeType(path, JsonNode::isPojo, sendMessage(INIT_JSON, "is_pojo", path));
    }

    /**
     * Checks if the property at the specified path is a short value.
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isShort(String path) {
        return checkNodeType(path, JsonNode::isShort, sendMessage(INIT_JSON, "is_short", path));
    }

    /**
     * Checks if the property at the specified path is a textual value (string).
     *
     * @param path the dot-separated path to the property
     * @return this CheckerJson instance
     */
    public CheckerJson isTextual(String path) {
        return checkNodeType(path, JsonNode::isTextual, sendMessage(INIT_JSON, "is_textual", path));
    }

    /**
     * Checks if the integer property at the specified path is within the given range (inclusive).
     *
     * @param path the dot-separated path to the property
     * @param min the minimum value
     * @param max the maximum value
     * @return this CheckerJson instance
     */
    public CheckerJson isInRange(String path, int min, int max) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && node.isInt() && node.asInt() >= min && node.asInt() <= max;
        }, sendMessage(INIT_JSON, "is_in_range", path, min, max));
    }

    /**
     * Checks if the textual property at the specified path matches the given regular expression.
     *
     * @param path the dot-separated path to the property
     * @param regex the regular expression to match
     * @return this CheckerJson instance
     */
    public CheckerJson matchesRegex(String path, String regex) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && node.isTextual() && node.asText().matches(regex);
        }, sendMessage(INIT_JSON, "matches_regex", path, regex));
    }

    /**
     * Checks if the textual property at the specified path is one of the allowed values.
     *
     * @param path the dot-separated path to the property
     * @param allowedValues the list of allowed string values
     * @return this CheckerJson instance
     */
    public CheckerJson isInEnum(String path, List<String> allowedValues) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && node.isTextual() && allowedValues.contains(node.asText());
        }, sendMessage(INIT_JSON, "is_in_enum", path, allowedValues));
    }

    /**
     * Checks if the length of the textual property at the specified path is within the given range (inclusive).
     *
     * @param path the dot-separated path to the property
     * @param min the minimum length
     * @param max the maximum length
     * @return this CheckerJson instance
     */
    public CheckerJson hasLengthBetween(String path, int min, int max) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && node.isTextual() && node.asText().length() >= min && node.asText().length() <= max;
        }, sendMessage(INIT_JSON, "has_length_between", path, min, max));
    }


    /**
     * Checks if the property at the specified path satisfies a custom condition.
     *
     * @param path the dot-separated path to the property
     * @param condition the predicate to test the JsonNode
     * @param messageKey the message key for reporting
     * @return this CheckerJson instance
     */
    private CheckerJson checkNodeType(String path, Predicate<JsonNode> condition, String messageKey) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && condition.test(node);
        }, sendMessage(INIT_JSON, messageKey, path));
    }

    /**
     * Gets the property at the specified path and casts it to the given class.
     *
     * @param path the dot-separated path to the property
     * @param clazz the class to cast the property to
     * @return the property cast to the specified class, or null if not found or not castable
     */
    private <T> T getProperty(String path, Class<T> clazz) {
        try {
            String[] keys = path.split("\\.");
            JsonNode current = this.object;

            for (String key : keys) {
                if (current == null) {
                    return null;
                }
                current = current.get(key);
            }

            return clazz.cast(current);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Gets the property at the specified path as a JsonNode.
     *
     * @param path the dot-separated path to the property
     * @return the JsonNode at the specified path, or null if not found
     */
    private JsonNode getProperty(String path) {
        return getProperty(path, JsonNode.class);
    }

    /**
     * Checks if the property at the specified path exists in the JSON.
     *
     * @param path the dot-separated path to the property
     * @return true if the property exists, false otherwise
     */
    private boolean containsProperty(String path) {
        return getProperty(path) != null;
    }

}
