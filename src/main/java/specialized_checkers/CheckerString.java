package specialized_checkers;

import static util.Message.*;

import util.AbstractChecker;

public class CheckerString extends AbstractChecker<String, CheckerString> {

    private static final String INIT_STRING = "string";
    private static final String DEFAULT_NAME = "String";

    protected CheckerString(String string, String name) {
        super(string, name);
    }

    /**
     * @param string
     * @param name
     * @return CheckerString
     */
    public static CheckerString check(String string, String name) {
        return new CheckerString(string, name);
    }

    /**
     * @param string
     * @return CheckerString
     */
    public static CheckerString check(String string) {
        return check(string, DEFAULT_NAME);
    }

    /**
     * @return CheckerString
     */
    @Override
    protected CheckerString self() {
        return this;
    }

    /**
     * @return CheckerString
     */
    public CheckerString isEmpty() {
        return is(string -> string.isEmpty(), sendMessage(INIT_STRING, "is_empty"));
    }

    /**
     * @return CheckerString
     */
    public CheckerString isBlank() {
        return is(string -> string.isBlank(), sendMessage(INIT_STRING, "is_blank"));
    }

    /**
     * @param min
     * @return CheckerString
     */
    public CheckerString min(int min) {
        return is(string -> string.length() > min, sendMessage(INIT_STRING, "min", min));
    }

    /**
     * @param max
     * @return CheckerString
     */
    public CheckerString max(int max) {
        return is(string -> string.length() < max, sendMessage(INIT_STRING, "max", max));
    }

    /**
     * @param min
     * @param max
     * @return CheckerString
     */
    public CheckerString inRange(int min, int max) {
        return is(string -> min < string.length() && string.length() < max, sendMessage(INIT_STRING, "in_range", min, max));
    }

    /**
     * @param anotherString
     * @return CheckerString
     */
    public CheckerString isEqualsIgnoreCase(String anotherString) {
        return is(string -> string.equalsIgnoreCase(anotherString), sendMessage(INIT_STRING, "is_equals_ignore_case", anotherString));
    }

    /**
     * @param s
     * @return CheckerString
     */
    public CheckerString contains(CharSequence s) {
        return is(string -> string.contains(s), sendMessage(INIT_STRING, "contains", s));
    }

    /**
     * @param prefix
     * @return CheckerString
     */
    public CheckerString startsWith(String prefix) {
        return is(string -> string.startsWith(prefix), sendMessage(prefix, "starts_with", prefix));
    }

    /**
     * @param suffix
     * @return CheckerString
     */
    public CheckerString endsWith(String suffix) {
        return is(string -> string.endsWith(suffix), sendMessage(INIT_STRING, "ends_with", suffix));
    }

    /**
     * @param regex
     * @return CheckerString
     */
    public CheckerString matches(String regex) {
        return is(string -> string.matches(regex), sendMessage(INIT_STRING, "matches", regex));
    }

    /**
     * @return CheckerString
     */
    public CheckerString isDigit() {
        return is(string -> string.matches("\\d+"), sendMessage(INIT_STRING, "is_digit"));
    }

    /**
     * @return CheckerString
     */
    public CheckerString isDNI(){
        return is(string -> string.matches("^\\d{8}[A-Z]$"), sendMessage(INIT_STRING, "is_dni"));
    }

    /**
     * @return CheckerString
     */
    public CheckerString isIPv4(){
        return is(string -> string.matches("^(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])(\\.(25[0-5]|2[0-4][0-9]|"+
        "1[0-9]{2}|[1-9]?[0-9])){3}$"),
        sendMessage(INIT_STRING, "is_ipv4"));
    }

    /**
     * @return CheckerString
     */
    public CheckerString isIPv6(){
        return is(string -> string.equals("^(([0-9a-fA-F]{1,4}:){7}([0-9a-fA-F]{1,4})|"+
        "(([0-9a-fA-F]{1,4}:){1,7}|:):(([0-9a-fA-F]{1,4}:){1,6}[0-9a-fA-F]{1,4})?)$"),
        sendMessage(INIT_STRING, "is_ipv6"));
    }

    /**
     * @return CheckerString
     */
    public CheckerString hasSpecialCharacters(){
        return is(string -> string.matches(".*[!@#$%^&*(),.?\":{}|<>].*"), "has_special_characters");
    }

    /**
     * @return CheckerString
     */
    public CheckerString isPalindrome() {
        return is(string -> {
            String reversed = new StringBuilder(string).reverse().toString();
            return string.equals(reversed);
        }, sendMessage(INIT_STRING, "is_palindrome"));
    }


}
