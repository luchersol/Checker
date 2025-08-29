package specialized_checkers.lambda;

import static util.Message.*;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.Cloner;

public class CheckerBiConsumer<T, U> extends AbstractChecker<BiConsumer<T, U>, CheckerBiConsumer<T, U>> {

    private static final String INIT_BI_CONSUMER = "lambda.biconsumer";
    private static final String DEFAULT_NAME = "BiConsumer";

    private boolean deepClone;

    protected CheckerBiConsumer(BiConsumer<T, U> consumer, String name) {
        super(consumer, name);
    }

    /**
     * @param biconsumer
     * @param name
     * @return CheckerBiConsumer<T, U>
     */
    public static <T,U> CheckerBiConsumer<T, U> check(BiConsumer<T, U> biconsumer, String name) {
        return new CheckerBiConsumer<>(biconsumer, name);
    }

    /**
     * @param biconsumer
     * @return CheckerBiConsumer<T, U>
     */
    public static <T,U> CheckerBiConsumer<T, U> check(BiConsumer<T, U> biconsumer) {
        return check(biconsumer, DEFAULT_NAME);
    }

    /**
     * @return CheckerBiConsumer<T, U>
     */
    @Override
    protected CheckerBiConsumer<T, U> self() {
        return this;
    }


    /**
     * @return CheckerBiConsumer<T, U>
     */
    public CheckerBiConsumer<T, U> activateDeepClone() {
        this.deepClone = true;
        return self();
    }

    /**
     * @return CheckerBiConsumer<T, U>
     */
    public CheckerBiConsumer<T, U> deactivateDeepClone() {
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
     * @return CheckerBiConsumer<T, U>
     */
    public CheckerBiConsumer<T, U> applyWithoutException(T input1, U input2) {
        return is(c -> {
            try {
                T processInput1 = getInput1(input1);
                U processInput2 = getInput2(input2);
                c.accept(processInput1, processInput2);
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_CONSUMER, "apply_without_exception", input1, input2));
    }

    /**
     * @param input
     * @return CheckerBiConsumer<T, U>
     */
    public CheckerBiConsumer<T, U> applyWithoutException(Collection<Entry<T, U>> input) {
        return is(c -> input.stream().allMatch(e -> {
                try {
                    T processInput1 = getInput1(e.getKey());
                    U processInput2 = getInput2(e.getValue());
                    c.accept(processInput1, processInput2);
                    return true;
                } catch (Exception exc) {
                    return false;
                }
            }
        ), sendMessage(INIT_BI_CONSUMER, "apply_without_exception.collection"));
    }

    /**
     * @param input1
     * @param input2
     * @param condition1
     * @param condition2
     * @return T
     */
    public CheckerBiConsumer<T, U> modifiesInput(T input1, U input2, Predicate<T> condition1, Predicate<U> condition2) {
        return is(c -> {
            try {
                T processInput1 = getInput1(input1);
                U processInput2 = getInput2(input2);
                c.accept(processInput1, processInput2);
                return condition1.test(processInput1) && condition2.test(processInput2);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_CONSUMER, "modifies_input", input1, input2));
    }

        /**
     * @param input1
     * @param input2
     * @param condition1
     * @param condition2
     * @return T
     */
    public CheckerBiConsumer<T, U> modifiesInput(T input1, U input2, BiPredicate<T, U> condition) {
        return is(c -> {
            try {
                T processInput1 = getInput1(input1);
                U processInput2 = getInput2(input2);
                c.accept(processInput1, processInput2);
                return condition.test(processInput1, processInput2);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_CONSUMER, "modifies_input", input1, input2));
    }

    /**
     * @param input
     * @param condition1
     * @param condition2
     * @return T
     */
    public CheckerBiConsumer<T, U> modifiesInput(Collection<Entry<T, U>> input, Predicate<T> condition1, Predicate<U> condition2) {
        return is(c -> input.stream().allMatch(e -> {
                try {
                    T processInput1 = getInput1(e.getKey());
                    U processInput2 = getInput2(e.getValue());
                    c.accept(processInput1, processInput2);
                    return condition1.test(processInput1) && condition2.test(processInput2);
                } catch (Exception exc) {
                    return false;
                }
            }
        ), sendMessage(INIT_BI_CONSUMER, "modifies_input.collection", input));
    }

    /**
     * @param input
     * @param condition1
     * @param condition2
     * @return T
     */
    public CheckerBiConsumer<T, U> modifiesInput(Collection<Entry<T, U>> input, BiPredicate<T, U> condition) {
        return is(c -> input.stream().allMatch(e -> {
                try {
                    T processInput1 = getInput1(e.getKey());
                    U processInput2 = getInput2(e.getValue());
                    c.accept(processInput1, processInput2);
                    return condition.test(processInput1, processInput2);
                } catch (Exception exc) {
                    return false;
                }
            }
        ), sendMessage(INIT_BI_CONSUMER, "modifies_input.collection", input));
    }

    /**
     * @param input
     * @return T
     */
    public CheckerBiConsumer<T, U> doesNothing(T input1, U input2) {
        return is(c -> {
            try {
                T before1 = Cloner.deepClone(input1);
                T after1 = getInput1(input1);
                U before2 = Cloner.deepClone(input2);
                U after2 = getInput2(input2);
                c.accept(after1, after2);
                return Objects.equals(after1, before1) && Objects.equals(after2, before2);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_CONSUMER, "does_nothing", input1, input2));
    }

    /**
     * @param input
     * @return T
     */
    public CheckerBiConsumer<T, U> doesNothing(Collection<Entry<T,U>> input) {
        return is(c -> input.stream().allMatch(e -> {
                    try {
                        T before1 = Cloner.deepClone(e.getKey());
                        T after1 = getInput1(e.getKey());
                        U before2 = Cloner.deepClone(e.getValue());
                        U after2 = getInput2(e.getValue());
                        c.accept(after1, after2);
                        return Objects.equals(after1, before1) && Objects.equals(after2, before2);
                    } catch (Exception exc) {
                        return false;
                    }
                }
            )
        , sendMessage(INIT_BI_CONSUMER, "does_nothing.collection"));
    }

}
