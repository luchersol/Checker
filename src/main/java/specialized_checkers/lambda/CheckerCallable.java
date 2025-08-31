package specialized_checkers.lambda;

import static util.Message.*;

import java.util.concurrent.Callable;

import util.AbstractChecker;
import util.Utils;

/**
 * Checker for {@link Callable} instances, providing fluent validation methods for lambda expressions or tasks that return a result and may throw an exception.
 * <p>
 * This class allows you to validate and assert properties of {@code Callable} objects in a fluent and readable way.
 * </p>
 *
 * @param <V> the result type returned by the {@code call()} method of the {@link Callable} being checked
 */
public class CheckerCallable<V> extends AbstractChecker<Callable<V>, CheckerCallable<V>> {

    private static final String INIT_CALLABLE = "lambda.callable";
    private static final String DEFAULT_NAME = "Callable";

    protected CheckerCallable(Callable<V> callable, String name) {
        super(callable, name);
    }

    /**
     * Creates a new {@code CheckerCallable} for the given {@link Callable} instance with a custom name.
     *
     * @param callable the {@code Callable} instance to be checked
     * @param name     the name to identify this checker instance (useful for error messages)
     * @param <V>      the result type returned by the {@code call()} method of the {@code Callable}
     * @return a new {@code CheckerCallable} for the provided {@code Callable}
     */
    public static <V> CheckerCallable<V> check(Callable<V> callable, String name) {
        return new CheckerCallable<>(callable, name);
    }

    /**
     * Creates a new {@code CheckerCallable} for the given {@link Callable} instance with a default name.
     *
     * @param callable the {@code Callable} instance to be checked
     * @param <V>      the result type returned by the {@code call()} method of the {@code Callable}
     * @return a new {@code CheckerCallable} for the provided {@code Callable}
     */
    public static <V> CheckerCallable<V> check(Callable<V> callable) {
        return check(callable, DEFAULT_NAME);
    }

    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerCallable} instance
     */
    @Override
    protected CheckerCallable<V> self() {
        return this;
    }

    /**
     * Asserts that calling the {@code call()} method of the {@link Callable} does not throw any exception.
     *
     * @return this {@code CheckerCallable} instance for further validation
     */
    public CheckerCallable<V> callWithoutException() {
        return is(c -> {
            try {
                c.call();
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_CALLABLE, "call_without_exception"));
    }

    /**
     * Asserts that the {@code call()} method of the {@link Callable} produces the expected result.
     *
     * @param expected the expected result to compare with the actual result of {@code call()}
     * @return this {@code CheckerCallable} instance for further validation
     */
    public CheckerCallable<V> producesExpected(V expected) {
        return is(c -> {
            try {
                V result = c.call();
                return Utils.equalsContent(expected, result);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_CALLABLE, "produces_expected", expected));
    }

    /**
     * Asserts that the {@code call()} method of the {@link Callable} produces a non-null result.
     *
     * @return this {@code CheckerCallable} instance for further validation
     */
    public CheckerCallable<V> producesNonNull() {
        return is(c -> {
            try {
                return c.call() != null;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_CALLABLE, "produces_non_null"));
    }
}
