package specialized_checkers.lambda;

import static util.Message.*;

import java.util.function.BiPredicate;

import util.AbstractChecker;
import util.Cloner;

/**
 * Checker for BiPredicate instances, providing fluent validation methods.
 *
 * @param <T> the type of the first input to the predicate
 * @param <U> the type of the second input to the predicate
 */
public class CheckerBiPredicate<T, U> extends AbstractChecker<BiPredicate<T, U>, CheckerBiPredicate<T, U>> {

    private static final String INIT_BI_PREDICATE = "lambda.bipredicate";
    private static final String DEFAULT_NAME = "BiPredicate";

    private boolean deepClone;

    protected CheckerBiPredicate(BiPredicate<T, U> biPredicate, String name) {
        super(biPredicate, name);
    }

    /**
     * Creates a CheckerBiPredicate for the given BiPredicate and assigns a custom name.
     *
     * @param bipredicate the BiPredicate to check
     * @param name the name to assign to this checker
     * @return a CheckerBiPredicate instance for the given BiPredicate
     */
    public static <T,U> CheckerBiPredicate<T, U> check(BiPredicate<T, U> bipredicate, String name) {
        return new CheckerBiPredicate<>(bipredicate, name);
    }

    /**
     * Creates a CheckerBiPredicate for the given BiPredicate with a default name.
     *
     * @param bipredicate the BiPredicate to check
     * @return a CheckerBiPredicate instance for the given BiPredicate
     */
    public static <T,U> CheckerBiPredicate<T, U> check(BiPredicate<T, U> bipredicate) {
        return check(bipredicate, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API).
     *
     * @return this CheckerBiPredicate instance
     */
    @Override
    protected CheckerBiPredicate<T, U> self() {
        return this;
    }

    /**
     * Activates deep cloning of inputs before passing them to the BiPredicate.
     *
     * @return this CheckerBiPredicate instance
     */
    public CheckerBiPredicate<T, U> activateDeepClone() {
        this.deepClone = true;
        return self();
    }

    /**
     * Deactivates deep cloning of inputs before passing them to the BiPredicate.
     *
     * @return this CheckerBiPredicate instance
     */
    public CheckerBiPredicate<T, U> deactivateDeepClone() {
        this.deepClone = false;
        return self();
    }

    /**
     * Returns the first input, deep cloned if deepClone is enabled.
     *
     * @param input the first input value
     * @return the (possibly cloned) first input
     */
    private T getInput1(T input) {
        return this.deepClone ? Cloner.deepClone(input) : input;
    }

    /**
     * Returns the second input, deep cloned if deepClone is enabled.
     *
     * @param input the second input value
     * @return the (possibly cloned) second input
     */
    private U getInput2(U input) {
        return this.deepClone ? Cloner.deepClone(input) : input;
    }

    /**
     * Checks that the BiPredicate can be applied to the given inputs without throwing an exception.
     *
     * @param input1 the first input value
     * @param input2 the second input value
     * @return this CheckerBiPredicate instance
     */
    public CheckerBiPredicate<T, U> testWithoutException(T input1, U input2) {
        return is(p -> {
            try {
                T first = getInput1(input1);
                U second = getInput2(input2);
                p.test(first, second);
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_PREDICATE, "test_without_exception", input1, input2));
    }

    /**
     * Checks that the BiPredicate evaluates to true for the given inputs.
     *
     * @param input1 the first input value
     * @param input2 the second input value
     * @return this CheckerBiPredicate instance
     */
    public CheckerBiPredicate<T, U> evaluatesTrue(T input1, U input2) {
        return is(p -> {
            try {
                return p.test(getInput1(input1), getInput2(input2));
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_PREDICATE, "evaluates_true", input1, input2));
    }

    /**
     * Checks that the BiPredicate evaluates to false for the given inputs.
     *
     * @param input1 the first input value
     * @param input2 the second input value
     * @return this CheckerBiPredicate instance
     */
    public CheckerBiPredicate<T, U> evaluatesFalse(T input1, U input2) {
        return is(p -> {
            try {
                return !p.test(getInput1(input1), getInput2(input2));
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_PREDICATE, "evaluates_false", input1, input2));
    }

    /**
     * Checks that the BiPredicate produces the expected boolean result for the given inputs.
     *
     * @param input1 the first input value
     * @param input2 the second input value
     * @param expected the expected boolean result
     * @return this CheckerBiPredicate instance
     */
    public CheckerBiPredicate<T, U> producesExpected(T input1, U input2, boolean expected) {
        return is(p -> {
            try {
                boolean result = p.test(getInput1(input1), getInput2(input2));
                return expected == result;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_PREDICATE, "produces_expected", input1, input2, expected));
    }

}
