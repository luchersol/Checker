package specialized_checkers;

import static util.Message.sendMessage;

import java.util.function.Predicate;

import util.AbstractChecker;
import util.ExceptionTracker;

public class CheckerString extends AbstractChecker<String> {

    private static final String INIT_STRING = "string";

    public CheckerString(String object, String name, ExceptionTracker exceptionTracker) {
        super(object, name, exceptionTracker);
    }

    @Override
    public CheckerString is(Predicate<String> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerString is(Predicate<String> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerString isNot(Predicate<String> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerString isNot(Predicate<String> condition) {
        super.isNot(condition);
        return this;
    }

    public CheckerString isEmpty() {
        is(string -> string.isEmpty(), sendMessage(INIT_STRING, "is_empty"));
        return this;
    }

    public CheckerString isBlank() {
        is(string -> string.isBlank(), sendMessage(INIT_STRING, "is_blank"));
        return this;
    }

    public CheckerString min(int min) {
        is(string -> string.length() > min, sendMessage(INIT_STRING, "min", min));
        return this;
    }

    public CheckerString max(int max) {
        is(string -> string.length() < max, sendMessage(INIT_STRING, "max", max));
        return this;
    }

    public CheckerString inRange(int min, int max) {
        is(string -> min < string.length() && string.length() < max, sendMessage(INIT_STRING, "in_range", min, max));
        return this;
    }

    public CheckerString isEqualsIgnoreCase(String anotherString) {
        is(string -> string.equalsIgnoreCase(anotherString), sendMessage(INIT_STRING, "is_equals_ignore_case", anotherString));
        return this;
    }

    public CheckerString contains(CharSequence s) {
        is(string -> string.contains(s), sendMessage(INIT_STRING, "contains", s));
        return this;
    }

    public CheckerString startsWith(String prefix) {
        is(string -> string.startsWith(prefix), sendMessage(prefix, "starts_with", prefix));
        return this;
    }

    public CheckerString endsWith(String suffix) {
        is(string -> string.endsWith(suffix), sendMessage(INIT_STRING, "ends_with", suffix));
        return this;
    }

    public CheckerString matches(String regex) {
        is(string -> string.matches(regex), sendMessage(INIT_STRING, "matches", regex));
        return this;
    }

    public CheckerString isDigit() {
        is(string -> string.matches("\\d+"), sendMessage(INIT_STRING, "is_digit"));
        return this;
    }

    public CheckerString isDNI(){
        return is(string -> string.matches("^\\d{8}[A-Z]$"), sendMessage(INIT_STRING, "is_dni"));
    }

    public CheckerString isIPv4(){
        return is(string -> string.matches("^(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])(\\.(25[0-5]|2[0-4][0-9]|"+
        "1[0-9]{2}|[1-9]?[0-9])){3}$"),
        sendMessage(INIT_STRING, "is_ipv4"));
    }

    public CheckerString isIPv6(){
        return is(string -> string.equals("^(([0-9a-fA-F]{1,4}:){7}([0-9a-fA-F]{1,4})|"+
        "(([0-9a-fA-F]{1,4}:){1,7}|:):(([0-9a-fA-F]{1,4}:){1,6}[0-9a-fA-F]{1,4})?)$"), 
        sendMessage(INIT_STRING, "is_ipv6"));
    }

    public CheckerString hasSpecialCharacters(){
        return is(string -> string.matches("[!@#$%^&*(),.?\":{}|<>]"), "has_special_characters");
    }

    public CheckerString isPalindrome() {
        String reversed = new StringBuilder(this.object).reverse().toString();
        is(string -> string.equals(reversed), sendMessage(INIT_STRING, "is_palindrome"));
        return this;
    }
    

}
