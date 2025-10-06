package com.luchersol.core.specialized_checkers.lambda;

import static com.luchersol.core.util.Message.*;

import java.util.function.Supplier;

import com.luchersol.core.util.AbstractChecker;
import com.luchersol.core.util.Utils;

/**
 * A specialized checker for {@link java.util.function.Supplier} instances, providing a fluent API for validating
 * function behavior and results. This class allows assertions such as verifying that a function does not throw
 * exceptions, produces expected results, or returns non-null values for given inputs.
 *
 * <p>
 * Example usage:
 * </p>
 * <pre>
 *     CheckerSupplier&lt;Integer&gt; checker = CheckerSupplier.check(() -> 5)
 *            .applyWithoutException()
 *            .producesExpected(5)
 *            .producesNonNull();
 * </pre>
 *
 * @param <T> the type of the result returned by the {@code Supplier}
 *
 * @see java.util.function.Supplier
 * @see AbstractChecker
 */
public class CheckerSupplier<T> extends AbstractChecker<Supplier<T>, CheckerSupplier<T>> {

    private static final String INIT_SUPPLIER = "lambda.supplier";
    private static final String DEFAULT_NAME = "Supplier";

    /**
     * Constructs a new {@code CheckerSupplier} with the specified supplier and name.
     *
     * @param supplier the {@link Supplier} to be used by this checker
     * @param name the name identifying this checker
     */
    protected CheckerSupplier(Supplier<T> supplier, String name) {
        super(supplier, name);
    }

    /**
     * Creates a new {@code CheckerSupplier} for the given {@link Supplier} instance with a custom name.
     *
     * @param supplier the {@code Supplier} instance to be checked
     * @param name     the name to identify this checker instance (useful for error messages)
     * @param <T>      the type of results supplied by the {@code Supplier}
     * @return a new {@code CheckerSupplier} for the provided {@code Supplier}
     */
    public static <T> CheckerSupplier<T> check(Supplier<T> supplier, String name) {
        return new CheckerSupplier<>(supplier, name);
    }

    /**
     * Creates a new {@code CheckerSupplier} for the given {@link Supplier} instance with a default name.
     *
     * @param supplier the {@code Supplier} instance to be checked
     * @param <T>      the type of results supplied by the {@code Supplier}
     * @return a new {@code CheckerSupplier} for the provided {@code Supplier}
     */
    public static <T> CheckerSupplier<T> check(Supplier<T> supplier) {
        return check(supplier, DEFAULT_NAME);
    }

    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerSupplier} instance
     */
    @Override
    protected CheckerSupplier<T> self() {
        return this;
    }

    /**
     * Asserts that calling the {@code get()} method of the {@link Supplier} does not throw any exception.
     *
     * @return this {@code CheckerSupplier} instance for further validation
     */
    public CheckerSupplier<T> applyWithoutException() {
        return is(s -> {
            try {
                s.get();
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_SUPPLIER, "apply_without_exception"));
    }

    /**
     * Asserts that the {@code get()} method of the {@link Supplier} produces the expected result.
     *
     * @param expected the expected result to compare with the actual result of {@code get()}
     * @return this {@code CheckerSupplier} instance for further validation
     */
    public CheckerSupplier<T> producesExpected(T expected) {
        return is(s -> {
            try {
                T result = s.get();
                return Utils.equalsContent(expected, result);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_SUPPLIER, "produces_expected", expected));
    }

    /**
     * Asserts that the {@code get()} method of the {@link Supplier} produces a non-null result.
     *
     * @return this {@code CheckerSupplier} instance for further validation
     */
    public CheckerSupplier<T> producesNonNull() {
        return is(s -> {
            try {
                return s.get() != null;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_SUPPLIER, "produces_non_null"));
    }
}
