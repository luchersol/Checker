package specialized_checkers;

import static util.Message.sendMessage;

import util.AbstractChecker;

public class CheckerString extends AbstractChecker<String> {

    private static final String INIT_STRING = "string";

    public CheckerString(String object, String name) {
        super(object, name);
    }

    public CheckerString isEmpty() {
        is(this.object.isEmpty(), sendMessage(INIT_STRING, "is_empty"));
        return this;
    }

    public CheckerString isBlank() {
        is(this.object.isBlank(), sendMessage(INIT_STRING, "is_blank"));
        return this;
    }

    public CheckerString min(int min) {
        is(this.object.length() > min, sendMessage(INIT_STRING, "min", min));
        return this;
    }

    public CheckerString max(int max) {
        is(this.object.length() < max, sendMessage(INIT_STRING, "max", max));
        return this;
    }

    public CheckerString inRange(int min, int max) {
        is(min < this.object.length() && this.object.length() < max, sendMessage(INIT_STRING, "in_range", min, max));
        return this;
    }

    public CheckerString isEqualsIgnoreCase(String anotherString) {
        is(this.object.equalsIgnoreCase(anotherString), sendMessage(INIT_STRING, "is_equals_ignore_case", anotherString));
        return this;
    }

    public CheckerString contains(CharSequence s) {
        is(this.object.contains(s), sendMessage(INIT_STRING, "contains", s));
        return this;
    }

    public CheckerString startsWith(String prefix) {
        is(this.object.startsWith(prefix), sendMessage(prefix, "starts_with", prefix));
        return this;
    }

    public CheckerString endsWith(String suffix) {
        is(this.object.endsWith(suffix), sendMessage(INIT_STRING, "ends_with", suffix));
        return this;
    }

    public CheckerString matches(String regex) {
        is(this.object.matches(regex), sendMessage(INIT_STRING, "matches", regex));
        return this;
    }

    public CheckerString isDigit() {
        is(this.object.matches("\\d+"), sendMessage(INIT_STRING, "is_digit"));
        return this;
    }

    public CheckerString isPalindrome() {
        String reversed = new StringBuilder(this.object).reverse().toString();
        is(this.object.equals(reversed), sendMessage(INIT_STRING, "is_palindrome"));
        return this;
    }
    

}
