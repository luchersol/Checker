package src.main.specialized_checkers;

import java.util.List;

import src.main.AbstractChecker;

public class CheckerString extends AbstractChecker<String> {

    public CheckerString(String object, String name) {
        super(object, name);
    }

    public CheckerString isEmpty() {
        is(this.object.isEmpty(), "");
        return this;
    }

    public CheckerString isBlank() {
        is(this.object.isBlank(), "");
        return this;
    }

    public CheckerString equalsIgnoreCase(String anotherString) {
        is(this.object.equalsIgnoreCase(anotherString), "");
        return this;
    }

    public CheckerString contains(CharSequence s) {
        is(this.object.contains(s), "");
        return this;
    }

    public CheckerString startWith(String prefix) {
        is(this.object.startsWith(prefix), "");
        return this;
    }

    public CheckerString endsWith(String suffix) {
        is(this.object.endsWith(suffix), "");
        return this;
    }

    public CheckerString matches(String regex) {
        is(this.object.matches(regex), "");
        return this;
    }

    public CheckerString isDigit() {
        is(this.object.matches("\\d+"), "");
        return this;
    }

    public CheckerString isPalindrome() {
        String reversed = new StringBuilder(this.object).reverse().toString();
        is(this.object.equals(reversed), "object");
        return this;
    }
    

}
