package com.luchersol.core.specialized_checkers.lambda;

import static com.luchersol.core.util.Message.*;

import java.util.concurrent.Callable;

import com.luchersol.core.util.AbstractChecker;
import com.luchersol.core.util.Utils;


/**
 * A specialized checker for {@link Callable} instances, providing fluent assertions
 * for validating the behavior and results of {@code Callable} tasks.
 * <p>
 * This class extends {@link AbstractChecker} to offer convenient methods for
 * asserting that a {@code Callable} does not throw exceptions, produces expected
 * results, or returns non-null values.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * CheckerCallable<Integer> checker = CheckerCallable.check(() -> 42)
 *        .callWithoutException()
 *        .producesExpected(42)
 *        .producesNonNull();
 * }</pre>
 *
 * @param <V> the result type returned by the {@code call()} method of the {@code Callable}
 * @see java.util.concurrent.Callable
 * @see AbstractChecker
 */
public class CheckerCallable<V> extends AbstractChecker<Callable<V>, CheckerCallable<V>> {

    private static final String INIT_CALLABLE = "lambda.callable";
    private static final String DEFAULT_NAME = "Callable";

    /**
     * Constructs a new {@code CheckerCallable} with the specified {@link Callable} task and a name.
     *
     * @param callable the {@code Callable} task to be executed by this checker
     * @param name the name associated with this checker
     */
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
