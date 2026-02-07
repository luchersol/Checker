package com.luchersol.core.specialized_checkers;

import static com.luchersol.core.util.MessageService.*;

import java.util.function.Predicate;

import com.luchersol.core.util.AbstractChecker;
import com.luchersol.core.util.Regex;

/**
 * A specialized checker for {@link String} instances, providing a fluent API
 * to assert various string properties such as emptiness, length, content, format and patterns.
 *
 * <p>Typical usage:</p>
 * <pre>{@code
 * CheckerString.check("HelloWorld")
 *     .isBlank()
 *     .min(5)
 *     .max(20)
 *     .contains("Hello")
 *     .startsWith("H")
 *     .endsWith("d")
 *     .matches("[A-Za-z]+");
 * }</pre>
 *
 * <p>This class supports chaining multiple assertions in a fluent style and integrates
 * with {@link com.luchersol.core.util.AbstractChecker} for generalized validation handling.</p>
 *
 * @see com.luchersol.core.util.AbstractChecker
 */
public class CheckerString extends AbstractChecker<String, CheckerString> {

    private static final String INIT_STRING = "string";
    private static final String DEFAULT_NAME = "String";

    /**
     * Constructs a new {@code CheckerString} with the specified string and name.
     *
     * @param string the {@link String} to be associated with this checker
     * @param name the name of the checker
     */
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
        return is(string -> string.matches(Regex.DIGIT), sendMessage(INIT_STRING, "is_digit"));
    }

    /**
     * Checks if the string consists of a hexadecimal sequence.
     *
     * @return this CheckerString instance for chaining
     */
    public CheckerString isHexadecimal() {
        return is(string -> string.matches(Regex.HEXADECIMAL), sendMessage(INIT_STRING, "is_hexadecimal"));
    }

    /**
     * Checks if the string is a valid Spanish DNI (8 digits followed by an uppercase letter).
     *
     * @return this CheckerString instance for chaining
     */
    public CheckerString isDNI(){
        Predicate<String> predicate = dni -> {
            if (dni == null) return false;
            dni = dni.trim().toUpperCase();

            if (!dni.matches(Regex.DNI)) return false;

            String numberStr = dni.substring(0, 8);
            char letter = dni.charAt(8);

            int num = Integer.parseInt(numberStr);

            String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
            char correctLetter = letters.charAt(num % 23);

            return letter == correctLetter;
        };
        return is(predicate, sendMessage(INIT_STRING, "is_dni"));
    }

    /**
     * Checks if the string is a valid IPv4 address.
     *
     * @return this CheckerString instance for chaining
     */
    public CheckerString isIPv4(){
        return is(string -> string.matches(Regex.IPV4),
        sendMessage(INIT_STRING, "is_ipv4"));
    }

    /**
     * Checks if the string is a valid IPv6 address.
     *
     * @return this CheckerString instance for chaining
     */
    public CheckerString isIPv6(){
        return is(string -> string.equals(Regex.IPV6),
        sendMessage(INIT_STRING, "is_ipv6"));
    }

    /**
     * Checks if the string contains any special characters (e.g., !@#$%^&amp;* etc.).
     *
     * @return this CheckerString instance for chaining
     */
    public CheckerString hasSpecialCharacters(){
        return is(string -> string.matches(Regex.SPECIAL_CHARACTERS), "has_special_characters");
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
