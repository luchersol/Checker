package specialized_checkers.lambda;

import static util.Message.*;

import java.util.function.BiFunction;

import util.AbstractChecker;
import util.Cloner;
import util.Utils;

/**
 * Checker for BiFunction instances, providing fluent validation methods.
 *
 * @param <T> the type of the first input to the function
 * @param <U> the type of the second input to the function
 * @param <R> the type of the result of the function
 */
public class CheckerBiFunction<T, U, R> extends AbstractChecker<BiFunction<T, U, R>, CheckerBiFunction<T, U, R>> {

    private static final String INIT_BI_FUNCTION = "lambda.bifunction";
    private static final String DEFAULT_NAME = "Function";

    private boolean deepClone;

    protected CheckerBiFunction(BiFunction<T, U, R> function, String name) {
        super(function, name);
    }

    /**
     * Creates a CheckerBiFunction for the given BiFunction and assigns a custom name.
     *
     * @param bifunction the BiFunction to check
     * @param name the name to assign to this checker
     * @return a CheckerBiFunction instance for the given BiFunction
     */
    public static <T,U,R> CheckerBiFunction<T, U, R> check(BiFunction<T, U, R> bifunction, String name) {
        return new CheckerBiFunction<>(bifunction, name);
    }

    /**
     * Creates a CheckerBiFunction for the given BiFunction with a default name.
     *
     * @param bifunction the BiFunction to check
     * @return a CheckerBiFunction instance for the given BiFunction
     */
    public static <T,U,R> CheckerBiFunction<T, U, R> check(BiFunction<T, U, R> bifunction) {
        return check(bifunction, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API).
     *
     * @return this CheckerBiFunction instance
     */
    @Override
    protected CheckerBiFunction<T, U, R> self() {
        return this;
    }

    /**
     * Activates deep cloning of inputs before passing them to the BiFunction.
     *
     * @return this CheckerBiFunction instance
     */
    public CheckerBiFunction<T, U, R> activateDeepClone() {
        this.deepClone = true;
        return self();
    }

    /**
     * Deactivates deep cloning of inputs before passing them to the BiFunction.
     *
     * @return this CheckerBiFunction instance
     */
    public CheckerBiFunction<T, U, R> deactivateDeepClone() {
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
     * Checks that the BiFunction can be applied to the given inputs without throwing an exception.
     *
     * @param input1 the first input value
     * @param input2 the second input value
     * @return this CheckerBiFunction instance
     */
    public CheckerBiFunction<T, U, R> applyWithoutException(T input1, U input2) {
        return is(f -> {
            try {
                T processInput1 = getInput1(input1);
                U processInput2 = getInput2(input2);
                f.apply(processInput1, processInput2);
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_FUNCTION, "apply_without_exception", input1, input2));
    }

    /**
     * Checks that the BiFunction produces the expected result for the given inputs.
     *
     * @param input1 the first input value
     * @param input2 the second input value
     * @param expected the expected result
     * @return this CheckerBiFunction instance
     */
    public CheckerBiFunction<T, U, R> producesExpected(T input1, U input2, R expected) {
        return is(f -> {
            try {
                T processInput1 = getInput1(input1);
                U processInput2 = getInput2(input2);
                R result = f.apply(processInput1, processInput2);
                return Utils.equalsContent(expected, result);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_FUNCTION, "produces_expected", input1, input2, expected));
    }

    /**
     * Checks that the BiFunction produces a non-null result for the given inputs.
     *
     * @param input1 the first input value
     * @param input2 the second input value
     * @return this CheckerBiFunction instance
     */
    public CheckerBiFunction<T, U, R> producesNonNull(T input1, U input2) {
        return is(f -> {
            try {
                T processInput1 = getInput1(input1);
                U processInput2 = getInput2(input2);
                return f.apply(processInput1, processInput2) != null;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_FUNCTION, "produces_non_null", input1, input2));
    }
}
