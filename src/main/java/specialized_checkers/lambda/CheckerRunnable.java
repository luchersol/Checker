package specialized_checkers.lambda;

import static util.Message.*;

import util.AbstractChecker;

/**
 * Checker for {@link Runnable} instances, providing fluent validation methods for lambda expressions or operations that take no arguments and return no result.
 * <p>
 * This class allows you to validate and assert properties of {@code Runnable} objects in a fluent and readable way.
 * </p>
 */
public class CheckerRunnable extends AbstractChecker<Runnable, CheckerRunnable> {

    private static final String INIT_RUNNABLE = "lambda.runnable";
    private static final String DEFAULT_NAME = "Runnable";

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
