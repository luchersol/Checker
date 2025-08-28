package specialized_checkers.lambda;

import static util.Message.*;

import java.util.Objects;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.Cloner;

public class CheckerPredicate<T> extends AbstractChecker<Predicate<T>, CheckerPredicate<T>> {

    private static final String INIT_PREDICATE = "lambda.predicate";

    private boolean deepClone;

    public CheckerPredicate(Predicate<T> predicate, String name) {
        super(predicate, name);
    }

    @Override
    protected CheckerPredicate<T> self() {
        return this;
    }

    public CheckerPredicate<T> activateDeepClone() {
        this.deepClone = true;
        return self();
    }

    public CheckerPredicate<T> deactivateDeepClone() {
        this.deepClone = false;
        return self();
    }

    private T getInput(T input) {
        return this.deepClone ? Cloner.deepClone(input) : input;
    }

    /**
     * @param input
     * @return CheckerPredicate<T>
     */
    public CheckerPredicate<T> testWithoutException(T input) {
        return is(p -> {
            try {
                T processInput = getInput(input);
                p.test(processInput);
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_PREDICATE, "test_without_exception", input));
    }

    /**
     * @param input
     * @return CheckerPredicate<T>
     */
    public CheckerPredicate<T> evaluatesTrue(T input) {
        return is(p -> {
            try {
                return p.test(getInput(input));
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_PREDICATE, "evaluates_true", input));
    }

    /**
     * @param input
     * @return CheckerPredicate<T>
     */
    public CheckerPredicate<T> evaluatesFalse(T input) {
        return is(p -> {
            try {
                return !p.test(getInput(input));
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_PREDICATE, "evaluates_false", input));
    }

    /**
     * @param input
     * @param boolean
     * @return CheckerPredicate<T>
     */
    public CheckerPredicate<T> producesExpected(T input, boolean expected) {
        return is(p -> {
            try {
                boolean result = p.test(getInput(input));
                return Objects.equals(expected, result);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_PREDICATE, "produces_expected", input, expected));
    }
}
