package specialized_checkers.lambda;

import static util.Message.*;

import util.AbstractChecker;

/**
 * A specialized checker for {@link java.lang.Runnable} instances, providing a fluent API for validating
 * function behavior and results. This class allows assertions such as verifying that a function does not throw
 * exceptions, produces expected results, or returns non-null values for given inputs.
 *
 * <p>
 * Example usage:
 * </p>
 * <pre>
 *     CheckerRunnable checker = CheckerRunnable.check(() -> {})
 *            .runWithoutException();
 * </pre>
 *
 *
 * @see java.lang.Runnable
 * @see AbstractChecker
 */
public class CheckerRunnable extends AbstractChecker<Runnable, CheckerRunnable> {

    private static final String INIT_RUNNABLE = "lambda.runnable";
    private static final String DEFAULT_NAME = "Runnable";

    /**
     * Constructs a new {@code CheckerRunnable} with the specified runnable and name.
     *
     * @param runnable the {@link CheckerRunnable} to be used by this checker
     * @param name the name identifying this checker
     */
    protected CheckerRunnable(Runnable runnable, String name) {
        super(runnable, name);
    }

    /**
     * Creates a new {@code CheckerRunnable} for the given {@link Runnable} instance with a custom name.
     *
     * @param runnable the {@code Runnable} instance to be checked
     * @param name     the name to identify this checker instance (useful for error messages)
     * @return a new {@code CheckerRunnable} for the provided {@code Runnable}
     */
    public static CheckerRunnable check(Runnable runnable, String name) {
        return new CheckerRunnable(runnable, DEFAULT_NAME);
    }

    /**
     * Creates a new {@code CheckerRunnable} for the given {@link Runnable} instance with a default name.
     *
     * @param runnable the {@code Runnable} instance to be checked
     * @return a new {@code CheckerRunnable} for the provided {@code Runnable}
     */
    public static CheckerRunnable check(Runnable runnable) {
        return check(runnable, DEFAULT_NAME);
    }

    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerRunnable} instance
     */
    @Override
    protected CheckerRunnable self() {
        return this;
    }

    /**
     * Asserts that running the {@code Runnable} does not throw any exception.
     *
     * @return this {@code CheckerRunnable} instance for further validation
     */
    public CheckerRunnable runWithoutException() {
        return is(r -> {
            try {
                r.run();
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_RUNNABLE, "run_without_exception"));
    }
}
