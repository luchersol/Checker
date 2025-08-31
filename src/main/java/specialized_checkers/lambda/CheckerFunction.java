package specialized_checkers.lambda;

import static util.Message.*;

import java.util.function.Function;

import util.AbstractChecker;
import util.Cloner;
import util.Utils;

/**
 * Provides fluent validation and assertion methods for {@link Function} instances, enabling expressive checks on lambda expressions or operations that accept an input and produce a result.
 * <p>
 * This checker allows you to validate and assert properties of {@code Function} objects in a fluent and readable way, such as verifying that a function does not throw exceptions, produces expected results, or returns non-null values.
 * </p>
 *
 * <p>
 * Typical usage:
 * <pre>
 *     CheckerFunction&lt;String, Integer&gt;.check(str -&gt; str.length())
 *         .producesExpected("abc", 3)
 *         .producesNonNull("test");
 * </pre>
 * </p>
 *
 * @param <T> the type of the input to the {@code Function} being checked
 *           (the argument type accepted by the function)
 * @param <R> the type of the result returned by the {@code Function}
 *           (the output type produced by the function)
 */
public class CheckerFunction<T, R> extends AbstractChecker<Function<T, R>, CheckerFunction<T, R>> {

    private static final String INIT_FUNCTION = "lambda.function";
    private static final String DEFAULT_NAME = "Function";

    private boolean deepClone;

    protected CheckerFunction(Function<T, R> function, String name) {
        super(function, name);
    }

    /**
     * Creates a new {@code CheckerFunction} for the given {@link Function} instance with a custom name.
     *
     * @param function the {@code Function} instance to be checked
     * @param name     the name to identify this checker instance (useful for error messages)
     * @param <T>      the type of the input to the {@code Function} being checked
     * @param <R>      the type of the result returned by the {@code Function}
     * @return a new {@code CheckerFunction} for the provided {@code Function}
     */
    public static <T,R> CheckerFunction<T, R> check(Function<T, R> function, String name) {
        return new CheckerFunction<>(function, name);
    }

    /**
     * Creates a new {@code CheckerFunction} for the given {@link Function} instance with a default name.
     *
     * @param function the {@code Function} instance to be checked
     * @param <T>      the type of the input to the {@code Function} being checked
     * @param <R>      the type of the result returned by the {@code Function}
     * @return a new {@code CheckerFunction} for the provided {@code Function}
     */
    public static <T,R> CheckerFunction<T, R> check(Function<T, R> function) {
        return check(function, DEFAULT_NAME);
    }

    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerFunction} instance for further validation and chaining
     */
    @Override
    protected CheckerFunction<T, R> self() {
        return this;
    }

    /**
     * Enables deep cloning of input objects before passing them to the {@code Function}.
     * <p>
     * This is useful to ensure that the original input is not modified by the operation, especially when the function may mutate its input.
     * </p>
     *
     * @return this {@code CheckerFunction} instance for further validation and chaining
     */
    public CheckerFunction<T, R> activateDeepClone() {
        this.deepClone = true;
        return self();
    }

    /**
     * Disables deep cloning of input objects before passing them to the {@code Function}.
     *
     * @return this {@code CheckerFunction} instance for further validation and chaining
     */
    public CheckerFunction<T, R> deactivateDeepClone() {
        this.deepClone = false;
        return self();
    }

    /**
     * Returns the input object, deep-cloned if deep cloning is activated, otherwise returns the original input.
     *
     * @param input the input object to process
     * @return the processed input (deep-cloned or original), depending on the current deep clone setting
     */
    public T getInput(T input) {
        return this.deepClone ? Cloner.deepClone(input) : input;
    }

    /**
     * Asserts that applying the {@code Function} to the given input does not throw any exception.
     * <p>
     * This check is useful to ensure that the function is safe to apply to the provided input and does not result in runtime errors.
     * </p>
     *
     * @param input the input object to be processed by the function
     * @return this {@code CheckerFunction} instance for further validation and chaining
     */
    public CheckerFunction<T, R> applyWithoutException(T input) {
        return is(f -> {
            try {
                T processInput = getInput(input);
                f.apply(processInput);
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_FUNCTION, "apply_without_exception", input));
    }

    /**
     * Asserts that applying the {@code Function} to the given input produces the expected result.
     * <p>
     * This check compares the actual result of the function with the expected value using content equality.
     * </p>
     *
     * @param input    the input object to be processed by the function
     * @param expected the expected result to compare with the actual result of the function
     * @return this {@code CheckerFunction} instance for further validation and chaining
     */
    public CheckerFunction<T, R> producesExpected(T input, R expected) {
        return is(f -> {
            try {
                R result = f.apply(input);
                return Utils.equalsContent(expected, result);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_FUNCTION, "produces_expected", input, expected));
    }

    /**
     * Asserts that applying the {@code Function} to the given input produces a non-null result.
     * <p>
     * This check is useful to ensure that the function always returns a valid (non-null) result for the given input.
     * </p>
     *
     * @param input the input object to be processed by the function
     * @return this {@code CheckerFunction} instance for further validation and chaining
     */
    public CheckerFunction<T, R> producesNonNull(T input) {
        return is(f -> {
            try {
                return f.apply(input) != null;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_FUNCTION, "produces_non_null", input));
    }
}
