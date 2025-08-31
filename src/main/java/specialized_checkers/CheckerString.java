package specialized_checkers;

import static util.Message.*;

import util.AbstractChecker;

/**
 * CheckerString is a specialized checker for validating and performing assertions on String values.
 * <p>
 * It provides a fluent API for common string validations such as checking for emptiness, blankness,
 * length constraints, content, format, and more.
 *
 * @param <T> the type of value to be checked, which is String in this case
 */
public class CheckerString extends AbstractChecker<String, CheckerString> {

    private static final String INIT_STRING = "string";
    private static final String DEFAULT_NAME = "String";

    protected CheckerString(String string, String name) {
        super(string, name);
    }

    /**
     * Creates a new CheckerString instance for the given string and name.
     *
     * @param string the string value to be checked
     * @param name   the name to identify the string in error messages
     * @return a CheckerString instance for further validations
     */
    public static CheckerString check(String string, String name) {
        return new CheckerString(string, name);
    }

    /**
     * Creates a new CheckerString instance for the given string with a default name.
     *
     * @param string the string value to be checked
     * @return a CheckerString instance for further validations
     */
    public static CheckerString check(String string) {
        return check(string, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API usage).
     *
     * @return this CheckerString instance
     */
    @Override
    protected CheckerString self() {
        return this;
    }

    /**
     * Checks if the string is empty.
     *
     * @return this CheckerString instance for chaining
     */
    public CheckerString isEmpty() {
        return is(string -> string.isEmpty(), sendMessage(INIT_STRING, "is_empty"));
    }

    /**
     * Checks if the string is blank (empty or contains only whitespace).
     *
     * @return this CheckerString instance for chaining
     */
    public CheckerString isBlank() {
        return is(string -> string.isBlank(), sendMessage(INIT_STRING, "is_blank"));
    }

    /**
     * Checks if the string length is greater than the specified minimum.
     *
     * @param min the minimum length (exclusive)
     * @return this CheckerString instance for chaining
     */
    public CheckerString min(int min) {
        return is(string -> string.length() > min, sendMessage(INIT_STRING, "min", min));
    }

    /**
     * Checks if the string length is less than the specified maximum.
     *
     * @param max the maximum length (exclusive)
     * @return this CheckerString instance for chaining
     */
    public CheckerString max(int max) {
        return is(string -> string.length() < max, sendMessage(INIT_STRING, "max", max));
    }

    /**
     * Checks if the string length is within the specified range (exclusive).
     *
     * @param min the minimum length (exclusive)
     * @param max the maximum length (exclusive)
     * @return this CheckerString instance for chaining
     */
    public CheckerString inRange(int min, int max) {
        return is(string -> min < string.length() && string.length() < max, sendMessage(INIT_STRING, "in_range", min, max));
    }

    /**
     * Checks if the string equals another string, ignoring case considerations.
     *
     * @param anotherString the string to compare with, ignoring case
     * @return this CheckerString instance for chaining
     */
    public CheckerString isEqualsIgnoreCase(String anotherString) {
        return is(string -> string.equalsIgnoreCase(anotherString), sendMessage(INIT_STRING, "is_equals_ignore_case", anotherString));
    }

    /**
     * Checks if the string contains the specified sequence of char values.
     *
     * @param s the sequence to search for
     * @return this CheckerString instance for chaining
     */
    public CheckerString contains(CharSequence s) {
        return is(string -> string.contains(s), sendMessage(INIT_STRING, "contains", s));
    }

    /**
     * Checks if the string starts with the specified prefix.
     *
     * @param prefix the prefix to look for
     * @return this CheckerString instance for chaining
     */
    public CheckerString startsWith(String prefix) {
        return is(string -> string.startsWith(prefix), sendMessage(prefix, "starts_with", prefix));
    }

    /**
     * Checks if the string ends with the specified suffix.
     *
     * @param suffix the suffix to look for
     * @return this CheckerString instance for chaining
     */
    public CheckerString endsWith(String suffix) {
        return is(string -> string.endsWith(suffix), sendMessage(INIT_STRING, "ends_with", suffix));
    }

    /**
     * Checks if the string matches the given regular expression.
     *
     * @param regex the regular expression to match
     * @return this CheckerString instance for chaining
     */
    public CheckerString matches(String regex) {
        return is(string -> string.matches(regex), sendMessage(INIT_STRING, "matches", regex));
    }

    /**
     * Checks if the string consists only of digits.
     *
     * @return this CheckerString instance for chaining
     */
    public CheckerString isDigit() {
        return is(string -> string.matches("\\d+"), sendMessage(INIT_STRING, "is_digit"));
    }

    /**
     * Checks if the string is a valid Spanish DNI (8 digits followed by an uppercase letter).
     *
     * @return this CheckerString instance for chaining
     */
    public CheckerString isDNI(){
        return is(string -> string.matches("^\\d{8}[A-Z]$"), sendMessage(INIT_STRING, "is_dni"));
    }

    /**
     * Checks if the string is a valid IPv4 address.
     *
     * @return this CheckerString instance for chaining
     */
    public CheckerString isIPv4(){
        return is(string -> string.matches("^(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])(\\.(25[0-5]|2[0-4][0-9]|"+
        "1[0-9]{2}|[1-9]?[0-9])){3}$"),
        sendMessage(INIT_STRING, "is_ipv4"));
    }

    /**
     * Checks if the string is a valid IPv6 address.
     *
     * @return this CheckerString instance for chaining
     */
    public CheckerString isIPv6(){
        return is(string -> string.equals("^(([0-9a-fA-F]{1,4}:){7}([0-9a-fA-F]{1,4})|"+
        "(([0-9a-fA-F]{1,4}:){1,7}|:):(([0-9a-fA-F]{1,4}:){1,6}[0-9a-fA-F]{1,4})?)$"),
        sendMessage(INIT_STRING, "is_ipv6"));
    }

    /**
     * Checks if the string contains any special characters (e.g., !@#$%^&* etc.).
     *
     * @return this CheckerString instance for chaining
     */
    public CheckerString hasSpecialCharacters(){
        return is(string -> string.matches(".*[!@#$%^&*(),.?\":{}|<>].*"), "has_special_characters");
    }

    /**
     * Checks if the string is a palindrome (reads the same forwards and backwards).
     *
     * @return this CheckerString instance for chaining
     */
    public CheckerString isPalindrome() {
        return is(string -> {
            String reversed = new StringBuilder(string).reverse().toString();
            return string.equals(reversed);
        }, sendMessage(INIT_STRING, "is_palindrome"));
    }


}
