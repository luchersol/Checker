package specialized_checkers.lambda;

import static util.Message.*;

import java.util.Objects;
import java.util.function.BiPredicate;

import util.AbstractChecker;
import util.Cloner;

public class CheckerBiPredicate<T, U> extends AbstractChecker<BiPredicate<T, U>, CheckerBiPredicate<T, U>> {

    private static final String INIT_BI_PREDICATE = "lambda.bipredicate";
    private static final String DEFAULT_NAME = "BiPredicate";

    private boolean deepClone;

    protected CheckerBiPredicate(BiPredicate<T, U> biPredicate, String name) {
        super(biPredicate, name);
    }

    /**
     * @param bipredicate
     * @param name
     * @return CheckerBiPredicate<T, U>
     */
    public static <T,U> CheckerBiPredicate<T, U> check(BiPredicate<T, U> bipredicate, String name) {
        return new CheckerBiPredicate<>(bipredicate, name);
    }

    /**
     * @param bipredicate
     * @return CheckerBiPredicate<T, U>
     */
    public static <T,U> CheckerBiPredicate<T, U> check(BiPredicate<T, U> bipredicate) {
        return check(bipredicate, DEFAULT_NAME);
    }

    /**
     * @return CheckerBiPredicate<T, U>
     */
    @Override
    protected CheckerBiPredicate<T, U> self() {
        return this;
    }

    /**
     * @return CheckerBiPredicate<T, U>
     */
    public CheckerBiPredicate<T, U> activateDeepClone() {
        this.deepClone = true;
        return self();
    }

    /**
     * @return CheckerBiPredicate<T, U>
     */
    public CheckerBiPredicate<T, U> deactivateDeepClone() {
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
     * @return U
     */
    private U getInput2(U input) {
        return this.deepClone ? Cloner.deepClone(input) : input;
    }

    /**
     * @param input1
     * @param input2
     * @return CheckerBiPredicate<T, U>
     */
    public CheckerBiPredicate<T, U> testWithoutException(T input1, U input2) {
        return is(p -> {
            try {
                T first = getInput1(input1);
                U second = getInput2(input2);
                p.test(first, second);
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_PREDICATE, "test_without_exception", input1, input2));
    }

    /**
     * @param input1
     * @param input2
     * @return CheckerBiPredicate<T, U>
     */
    public CheckerBiPredicate<T, U> evaluatesTrue(T input1, U input2) {
        return is(p -> {
            try {
                return p.test(getInput1(input1), getInput2(input2));
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_PREDICATE, "evaluates_true", input1, input2));
    }

    /**
     * @param input1
     * @param input2
     * @return CheckerBiPredicate<T, U>
     */
    public CheckerBiPredicate<T, U> evaluatesFalse(T input1, U input2) {
        return is(p -> {
            try {
                return !p.test(getInput1(input1), getInput2(input2));
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_PREDICATE, "evaluates_false", input1, input2));
    }

    /**
     * @param input1
     * @param input2
     * @param expected
     * @return CheckerBiPredicate<T, U>
     */
    public CheckerBiPredicate<T, U> producesExpected(T input1, U input2, boolean expected) {
        return is(p -> {
            try {
                boolean result = p.test(getInput1(input1), getInput2(input2));
                return Objects.equals(expected, result);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_PREDICATE, "produces_expected", input1, input2, expected));
    }

}
