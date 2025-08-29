package specialized_checkers.io;

import static util.Message.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.AbstractChecker;

public class CheckerJson extends AbstractChecker<JsonNode, CheckerJson> {

    private static final String INIT_JSON = "io.json";
    private static final String DEFAULT_NAME = "Json";


    protected CheckerJson(JsonNode json, String name) {
        super(json, name);
    }

    /**
     * @param json
     * @param name
     * @return CheckerJson
     */
    public static CheckerJson check(JsonNode json, String name) {
        return new CheckerJson(json, name);
    }

    /**
     * @param file
     * @param name
     * @return CheckerJson
     * @throws IOException
     */
    public static CheckerJson check(File file, String name) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(file);
        return check(rootNode, name);
    }

    /**
     * @param json
     * @return CheckerJson
     */
    public static CheckerJson check(JsonNode json) {
        return check(json, DEFAULT_NAME);
    }

    /**
     * @param file
     * @return CheckerJson
     * @throws IOException
     */
    public static CheckerJson check(File file) throws IOException {
        return check(file, DEFAULT_NAME);
    }


    /**
     * @return CheckerJson
     */
    @Override
    protected CheckerJson self() {
        return this;
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson hasProperty(String path){
        return is(json -> containsProperty(path), sendMessage(INIT_JSON, "has_property", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isArray(String path) {
        return checkNodeType(path, JsonNode::isArray, sendMessage(INIT_JSON, "is_array", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isBigDecimal(String path) {
        return checkNodeType(path, JsonNode::isBigDecimal, sendMessage(INIT_JSON, "is_big_decimal", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isBigInteger(String path) {
        return checkNodeType(path, JsonNode::isBigInteger, sendMessage(INIT_JSON, "is_big_integer", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isBinary(String path) {
        return checkNodeType(path, JsonNode::isBinary, sendMessage(INIT_JSON, "is_binary", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isBoolean(String path) {
        return checkNodeType(path, JsonNode::isBoolean, sendMessage(INIT_JSON, "is_boolean", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isContainerNode(String path) {
        return checkNodeType(path, JsonNode::isContainerNode, sendMessage(INIT_JSON, "is_container_node", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isDouble(String path) {
        return checkNodeType(path, JsonNode::isDouble, sendMessage(INIT_JSON, "is_node", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isEmpty(String path) {
        return checkNodeType(path, JsonNode::isEmpty, sendMessage(INIT_JSON, "is_empty", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isFloat(String path) {
        return checkNodeType(path, JsonNode::isFloat, sendMessage(INIT_JSON, "is_float", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isFloatingPointNumber(String path) {
        return checkNodeType(path, JsonNode::isFloatingPointNumber, sendMessage(INIT_JSON, "is_floating_point_number", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isInt(String path) {
        return checkNodeType(path, JsonNode::isInt, sendMessage(INIT_JSON, "is_int", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isIntegralNumber(String path) {
        return checkNodeType(path, JsonNode::isIntegralNumber, sendMessage(INIT_JSON, "is_integeral_number", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isLong(String path) {
        return checkNodeType(path, JsonNode::isLong, sendMessage(INIT_JSON, "is_long", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isMissingNode(String path) {
        return checkNodeType(path, JsonNode::isMissingNode, sendMessage(INIT_JSON, "is_missing_node", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isNull(String path) {
        return checkNodeType(path, JsonNode::isNull, sendMessage(INIT_JSON, "is_null", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isNumber(String path) {
        return checkNodeType(path, JsonNode::isNumber, sendMessage(INIT_JSON, "is_number", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isObject(String path) {
        return checkNodeType(path, JsonNode::isObject, sendMessage(INIT_JSON, "is_object", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isPojo(String path) {
        return checkNodeType(path, JsonNode::isPojo, sendMessage(INIT_JSON, "is_pojo", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isShort(String path) {
        return checkNodeType(path, JsonNode::isShort, sendMessage(INIT_JSON, "is_short", path));
    }

    /**
     * @param path
     * @return CheckerJson
     */
    public CheckerJson isTextual(String path) {
        return checkNodeType(path, JsonNode::isTextual, sendMessage(INIT_JSON, "is_textual", path));
    }

    /**
     * @param path
     * @param min
     * @param max
     * @return CheckerJson
     */
    public CheckerJson isInRange(String path, int min, int max) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && node.isInt() && node.asInt() >= min && node.asInt() <= max;
        }, sendMessage(INIT_JSON, "is_in_range", path, min, max));
    }

    /**
     * @param path
     * @param regex
     * @return CheckerJson
     */
    public CheckerJson matchesRegex(String path, String regex) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && node.isTextual() && node.asText().matches(regex);
        }, sendMessage(INIT_JSON, "matches_regex", path, regex));
    }

    /**
     * @param path
     * @param allowedValues
     * @return CheckerJson
     */
    public CheckerJson isInEnum(String path, List<String> allowedValues) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && node.isTextual() && allowedValues.contains(node.asText());
        }, sendMessage(INIT_JSON, "is_in_enum", path, allowedValues));
    }

    /**
     * @param path
     * @param min
     * @param max
     * @return CheckerJson
     */
    public CheckerJson hasLengthBetween(String path, int min, int max) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && node.isTextual() && node.asText().length() >= min && node.asText().length() <= max;
        }, sendMessage(INIT_JSON, "has_length_between", path, min, max));
    }


    /**
     * @param path
     * @param condition
     * @param messageKey
     * @return CheckerJson
     */
    private CheckerJson checkNodeType(String path, Predicate<JsonNode> condition, String messageKey) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && condition.test(node);
        }, sendMessage(INIT_JSON, messageKey, path));
    }

    /**
     * @param path
     * @param clazz
     * @return T
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
     * @param path
     * @return JsonNode
     */
    private JsonNode getProperty(String path) {
        return getProperty(path, JsonNode.class);
    }

    /**
     * @param path
     * @return boolean
     */
    private boolean containsProperty(String path) {
        return getProperty(path) != null;
    }

}
