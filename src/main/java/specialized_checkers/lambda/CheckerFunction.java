package specialized_checkers.lambda;

import static util.Message.*;

import java.util.Objects;
import java.util.function.Function;

import util.AbstractChecker;
import util.Cloner;

public class CheckerFunction<T, R> extends AbstractChecker<Function<T, R>, CheckerFunction<T, R>> {

    private static final String INIT_FUNCTION = "lambda.function";
    private static final String DEFAULT_NAME = "Function";

    private boolean deepClone;

    protected CheckerFunction(Function<T, R> function, String name) {
        super(function, name);
    }

    /**
     * @param function
     * @param name
     * @return CheckerFunction<T, R>
     */
    public static <T,R> CheckerFunction<T, R> check(Function<T, R> function, String name) {
        return new CheckerFunction<>(function, name);
    }

    /**
     * @param function
     * @return CheckerFunction<T, R>
     */
    public static <T,R> CheckerFunction<T, R> check(Function<T, R> function) {
        return check(function, DEFAULT_NAME);
    }

    /**
     * @return CheckerFunction<T, R>
     */
    @Override
    protected CheckerFunction<T, R> self() {
        return this;
    }

    /**
     * @return CheckerFunction<T, R>
     */
    public CheckerFunction<T, R> activateDeepClone() {
        this.deepClone = true;
        return self();
    }

    /**
     * @return CheckerFunction<T, R>
     */
    public CheckerFunction<T, R> deactivateDeepClone() {
        this.deepClone = false;
        return self();
    }

    /**
     * @param input
     * @return T
     */
    public T getInput(T input) {
        return this.deepClone ? Cloner.deepClone(input) : input;
    }

    /**
     * @param input
     * @return CheckerFunction<T, R>
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
     * @param input
     * @param expected
     * @return CheckerFunction<T, R>
     */
    public CheckerFunction<T, R> producesExpected(T input, R expected) {
        return is(f -> {
            try {
                R result = f.apply(input);
                return Objects.equals(expected, result);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_FUNCTION, "produces_expected", input, expected));
    }

    /**
     * @param input
     * @return CheckerFunction<T, R>
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
