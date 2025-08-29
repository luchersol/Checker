package specialized_checkers.lambda;

import static util.Message.*;

import java.util.Objects;
import java.util.function.BiFunction;

import util.AbstractChecker;
import util.Cloner;

public class CheckerBiFunction<T, U, R> extends AbstractChecker<BiFunction<T, U, R>, CheckerBiFunction<T, U, R>> {

    private static final String INIT_BI_FUNCTION = "lambda.bifunction";
    private static final String DEFAULT_NAME = "Function";

    private boolean deepClone;

    protected CheckerBiFunction(BiFunction<T, U, R> function, String name) {
        super(function, name);
    }

    /**
     * @param bifunction
     * @param name
     * @return CheckerBiFunction<T, U, R>
     */
    public static <T,U,R> CheckerBiFunction<T, U, R> check(BiFunction<T, U, R> bifunction, String name) {
        return new CheckerBiFunction<>(bifunction, name);
    }

    /**
     * @param bifunction
     * @return CheckerBiFunction<T, U, R>
     */
    public static <T,U,R> CheckerBiFunction<T, U, R> check(BiFunction<T, U, R> bifunction) {
        return check(bifunction, DEFAULT_NAME);
    }

    /**
     * @return CheckerBiFunction<T, U, R>
     */
    @Override
    protected CheckerBiFunction<T, U, R> self() {
        return this;
    }

    /**
     * @return CheckerBiFunction<T, U, R>
     */
    public CheckerBiFunction<T, U, R> activateDeepClone() {
        this.deepClone = true;
        return self();
    }

    /**
     * @return CheckerBiFunction<T, U, R>
     */
    public CheckerBiFunction<T, U, R> deactivateDeepClone() {
        this.deepClone = false;
        return self();
    }

    /**
     * @param input
     * @return T
     */
    private T getInput1(T input) {
        return this.deepClone ? Cloner.deepClone(input) : input;
    }

    /**
     * @param input
     * @return T
     */
    private U getInput2(U input) {
        return this.deepClone ? Cloner.deepClone(input) : input;
    }

    /**
     * @param input
     * @return CheckerBiFunction<T, U, R>
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
     * @param input
     * @param expected
     * @return CheckerBiFunction<T, U, R>
     */
    public CheckerBiFunction<T, U, R> producesExpected(T input1, U input2, R expected) {
        return is(f -> {
            try {
                T processInput1 = getInput1(input1);
                U processInput2 = getInput2(input2);
                R result = f.apply(processInput1, processInput2);
                return Objects.equals(expected, result);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_FUNCTION, "produces_expected", input1, input2, expected));
    }

    /**
     * @param input
     * @return CheckerBiFunction<T, U, R>
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
