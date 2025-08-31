package specialized_checkers.lambda;

import static util.Message.*;

import java.util.function.Predicate;

import util.AbstractChecker;
import util.Cloner;
import util.Utils;

/**
 * Checker for {@link Predicate} instances, providing fluent validation methods for lambda expressions or operations that accept an input and return a boolean result.
 * <p>
 * This class allows you to validate and assert properties of {@code Predicate} objects in a fluent and readable way.
 * </p>
 *
 * @param <T> the type of the input to the {@code Predicate} being checked
 */
public class CheckerPredicate<T> extends AbstractChecker<Predicate<T>, CheckerPredicate<T>> {

    private static final String INIT_PREDICATE = "lambda.predicate";
    private static final String DEFAULT_NAME = "Predicate";

    private boolean deepClone;

    protected CheckerPredicate(Predicate<T> predicate, String name) {
        super(predicate, name);
    }

    /**
     * Creates a new {@code CheckerPredicate} for the given {@link Predicate} instance with a custom name.
     *
     * @param predicate the {@code Predicate} instance to be checked
     * @param name      the name to identify this checker instance (useful for error messages)
     * @param <T>       the type of the input to the {@code Predicate}
     * @return a new {@code CheckerPredicate} for the provided {@code Predicate}
     */
    public static <T> CheckerPredicate<T> check(Predicate<T> predicate, String name) {
        return new CheckerPredicate<>(predicate, name);
    }

    /**
     * Creates a new {@code CheckerPredicate} for the given {@link Predicate} instance with a default name.
     *
     * @param predicate the {@code Predicate} instance to be checked
     * @param <T>       the type of the input to the {@code Predicate}
     * @return a new {@code CheckerPredicate} for the provided {@code Predicate}
     */
    public static <T> CheckerPredicate<T> check(Predicate<T> predicate) {
        return check(predicate, DEFAULT_NAME);
    }

    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerPredicate} instance
     */
    @Override
    protected CheckerPredicate<T> self() {
        return this;
    }

    /**
     * Enables deep cloning of input objects before passing them to the {@code Predicate}.
     * This is useful to ensure that the original input is not modified by the operation.
     *
     * @return this {@code CheckerPredicate} instance for further validation
     */
    public CheckerPredicate<T> activateDeepClone() {
        this.deepClone = true;
        return self();
    }

    /**
     * Disables deep cloning of input objects before passing them to the {@code Predicate}.
     *
     * @return this {@code CheckerPredicate} instance for further validation
     */
    public CheckerPredicate<T> deactivateDeepClone() {
        this.deepClone = false;
        return self();
    }

    /**
     * Returns the input object, deep-cloned if deep cloning is activated, otherwise returns the original input.
     *
     * @param input the input object to process
     * @return the processed input (deep-cloned or original)
     */
    private T getInput(T input) {
        return this.deepClone ? Cloner.deepClone(input) : input;
    }

    /**
     * Asserts that testing the {@code Predicate} with the given input does not throw any exception.
     *
     * @param input the input object to be tested by the predicate
     * @return this {@code CheckerPredicate} instance for further validation
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
     * Asserts that testing the {@code Predicate} with the given input evaluates to {@code true}.
     *
     * @param input the input object to be tested by the predicate
     * @return this {@code CheckerPredicate} instance for further validation
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
     * Asserts that testing the {@code Predicate} with the given input evaluates to {@code false}.
     *
     * @param input the input object to be tested by the predicate
     * @return this {@code CheckerPredicate} instance for further validation
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
     * Asserts that testing the {@code Predicate} with the given input produces the expected boolean result.
     *
     * @param input    the input object to be tested by the predicate
     * @param expected the expected boolean result to compare with the actual result of the predicate
     * @return this {@code CheckerPredicate} instance for further validation
     */
    public CheckerPredicate<T> producesExpected(T input, boolean expected) {
        return is(p -> {
            try {
                boolean result = p.test(getInput(input));
                return Utils.equalsContent(expected, result);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_PREDICATE, "produces_expected", input, expected));
    }
}
