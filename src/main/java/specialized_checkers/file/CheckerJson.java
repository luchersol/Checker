package specialized_checkers.file;

import static util.Message.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.AbstractChecker;

public class CheckerJson extends AbstractChecker<JsonNode, CheckerJson> {

    private static final String INIT_JSON = "json";

    public CheckerJson(File object, String name) throws IOException {
        super(name);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(object);
        this.object = rootNode;
    }

    public CheckerJson(JsonNode object, String name) {
        super(object, name);
    }

    @Override
    protected CheckerJson self() {
        return this;
    }

    public CheckerJson hasProperty(String path){
        return is(json -> containsProperty(path), sendMessage(INIT_JSON, "has_property", path));
    }

    public CheckerJson isArray(String path) {
        return checkNodeType(path, JsonNode::isArray, sendMessage(INIT_JSON, "is_array", path));
    }

    public CheckerJson isBigDecimal(String path) {
        return checkNodeType(path, JsonNode::isBigDecimal, sendMessage(INIT_JSON, "is_big_decimal", path));
    }

    public CheckerJson isBigInteger(String path) {
        return checkNodeType(path, JsonNode::isBigInteger, sendMessage(INIT_JSON, "is_big_integer", path));
    }

    public CheckerJson isBinary(String path) {
        return checkNodeType(path, JsonNode::isBinary, sendMessage(INIT_JSON, "is_binary", path));
    }

    public CheckerJson isBoolean(String path) {
        return checkNodeType(path, JsonNode::isBoolean, sendMessage(INIT_JSON, "is_boolean", path));
    }

    public CheckerJson isContainerNode(String path) {
        return checkNodeType(path, JsonNode::isContainerNode, sendMessage(INIT_JSON, "is_container_node", path));
    }

    public CheckerJson isDouble(String path) {
        return checkNodeType(path, JsonNode::isDouble, sendMessage(INIT_JSON, "is_node", path));
    }

    public CheckerJson isEmpty(String path) {
        return checkNodeType(path, JsonNode::isEmpty, sendMessage(INIT_JSON, "is_empty", path));
    }

    public CheckerJson isFloat(String path) {
        return checkNodeType(path, JsonNode::isFloat, sendMessage(INIT_JSON, "is_float", path));
    }

    public CheckerJson isFloatingPointNumber(String path) {
        return checkNodeType(path, JsonNode::isFloatingPointNumber, sendMessage(INIT_JSON, "is_floating_point_number", path));
    }

    public CheckerJson isInt(String path) {
        return checkNodeType(path, JsonNode::isInt, sendMessage(INIT_JSON, "is_int", path));
    }

    public CheckerJson isIntegralNumber(String path) {
        return checkNodeType(path, JsonNode::isIntegralNumber, sendMessage(INIT_JSON, "is_integeral_number", path));
    }

    public CheckerJson isLong(String path) {
        return checkNodeType(path, JsonNode::isLong, sendMessage(INIT_JSON, "is_long", path));
    }

    public CheckerJson isMissingNode(String path) {
        return checkNodeType(path, JsonNode::isMissingNode, sendMessage(INIT_JSON, "is_missing_node", path));
    }

    public CheckerJson isNull(String path) {
        return checkNodeType(path, JsonNode::isNull, sendMessage(INIT_JSON, "is_null", path));
    }

    public CheckerJson isNumber(String path) {
        return checkNodeType(path, JsonNode::isNumber, sendMessage(INIT_JSON, "is_number", path));
    }

    public CheckerJson isObject(String path) {
        return checkNodeType(path, JsonNode::isObject, sendMessage(INIT_JSON, "is_object", path));
    }

    public CheckerJson isPojo(String path) {
        return checkNodeType(path, JsonNode::isPojo, sendMessage(INIT_JSON, "is_pojo", path));
    }

    public CheckerJson isShort(String path) {
        return checkNodeType(path, JsonNode::isShort, sendMessage(INIT_JSON, "is_short", path));
    }

    public CheckerJson isTextual(String path) {
        return checkNodeType(path, JsonNode::isTextual, sendMessage(INIT_JSON, "is_textual", path));
    }

    public CheckerJson isInRange(String path, int min, int max) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && node.isInt() && node.asInt() >= min && node.asInt() <= max;
        }, sendMessage(INIT_JSON, "is_in_range", path));
    }

    public CheckerJson matchesRegex(String path, String regex) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && node.isTextual() && node.asText().matches(regex);
        }, sendMessage(INIT_JSON, "matches_regex", path));
    }

    public CheckerJson isInEnum(String path, List<String> allowedValues) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && node.isTextual() && allowedValues.contains(node.asText());
        }, sendMessage(INIT_JSON, "is_in_enum", path));
    }

    public CheckerJson hasLengthBetween(String path, int min, int max) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && node.isTextual() && node.asText().length() >= min && node.asText().length() <= max;
        }, sendMessage(INIT_JSON, "has_length_between", path));
    }

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

    private JsonNode getProperty(String path) {
        return getProperty(path, JsonNode.class);
    }

    private boolean containsProperty(String path) {
        return getProperty(path) != null;
    }

    private CheckerJson checkNodeType(String path, Predicate<JsonNode> condition, String messageKey) {
        return is(json -> {
            JsonNode node = getProperty(path);
            return node != null && condition.test(node);
        }, sendMessage(INIT_JSON, messageKey, path));
    }

}
