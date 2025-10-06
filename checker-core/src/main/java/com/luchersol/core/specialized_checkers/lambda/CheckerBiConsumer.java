package com.luchersol.core.specialized_checkers.lambda;

import static com.luchersol.core.util.Message.*;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import com.luchersol.core.util.AbstractChecker;
import com.luchersol.core.util.Cloner;
import com.luchersol.core.util.Utils;

/**
 * A specialized checker for {@link BiConsumer} instances, providing fluent API methods
 * to assert various behaviors and properties of a BiConsumer, such as exception safety,
 * input modification, and no-op behavior. Supports optional deep cloning of inputs
 * before passing them to the BiConsumer under test.
 *
 * <p>Typical usage:</p>
 * <pre>{@code
 * CheckerBiConsumer<MyType, OtherType> checker = CheckerBiConsumer.check(myBiConsumer)
 *     .activateDeepClone()
 *     .applyWithoutException(input1, input2)
 *     .modifiesInput(input1, input2, condition1, condition2);
 * }</pre>
 *
 *
 * @param <T> the type of the first argument to the BiConsumer
 * @param <U> the type of the second argument to the BiConsumer
 *
 * @see java.util.function.BiConsumer
 * @see java.util.function.Predicate
 * @see java.util.function.BiPredicate
 * @see java.util.Map.Entry
 */
public class CheckerBiConsumer<T, U> extends AbstractChecker<BiConsumer<T, U>, CheckerBiConsumer<T, U>> {

    private static final String INIT_BI_CONSUMER = "lambda.biconsumer";
    private static final String DEFAULT_NAME = "BiConsumer";

    private boolean deepClone;

    /**
     * Constructs a new {@code CheckerBiConsumer} with the specified {@link BiConsumer} and name.
     *
     * @param consumer the {@code BiConsumer} to be wrapped and checked
     * @param name the name identifying this checker
     */
    protected CheckerBiConsumer(BiConsumer<T, U> consumer, String name) {
        super(consumer, name);
    }

    /**
     * Creates a CheckerBiConsumer for the given BiConsumer and assigns a custom name.
     *
     * @param <T> the first input type of {@code BiConsumer}
     * @param <U> the second input type of {@code BiConsumer}
     * @param biconsumer the BiConsumer to check
     * @param name the name to assign to this checker
     * @return a CheckerBiConsumer instance for the given BiConsumer
     */
    public static <T,U> CheckerBiConsumer<T, U> check(BiConsumer<T, U> biconsumer, String name) {
        return new CheckerBiConsumer<>(biconsumer, name);
    }

    /**
     * Creates a CheckerBiConsumer for the given BiConsumer with a default name.
     *
     * @param <T> the first input type of {@code BiConsumer}
     * @param <U> the second input type of {@code BiConsumer}
     * @param biconsumer the BiConsumer to check
     * @return a CheckerBiConsumer instance for the given BiConsumer
     */
    public static <T,U> CheckerBiConsumer<T, U> check(BiConsumer<T, U> biconsumer) {
        return check(biconsumer, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API).
     *
     * @return this CheckerBiConsumer instance
     */
    @Override
    protected CheckerBiConsumer<T, U> self() {
        return this;
    }


    /**
     * Activates deep cloning of inputs before passing them to the BiConsumer.
     *
     * @return this CheckerBiConsumer instance
     */
    public CheckerBiConsumer<T, U> activateDeepClone() {
        this.deepClone = true;
        return self();
    }

    /**
     * Deactivates deep cloning of inputs before passing them to the BiConsumer.
     *
     * @return this CheckerBiConsumer instance
     */
    public CheckerBiConsumer<T, U> deactivateDeepClone() {
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
     * Checks that the BiConsumer can be applied to the given inputs without throwing an exception.
     *
     * @param input1 the first input value
     * @param input2 the second input value
     * @return this CheckerBiConsumer instance
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
     * Checks that the BiConsumer can be applied to all entries in the collection without throwing an exception.
     *
     * @param input the collection of key-value entries to test
     * @return this CheckerBiConsumer instance
     */
    public CheckerBiConsumer<T, U> applyWithoutException(Collection<? extends Entry<T, U>> input) {
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
     * Checks that the BiConsumer modifies the inputs as expected, according to the given predicates.
     *
     * @param input1 the first input value
     * @param input2 the second input value
     * @param condition1 the predicate to test the first input after consumption
     * @param condition2 the predicate to test the second input after consumption
     * @return this CheckerBiConsumer instance
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
         * Checks that the BiConsumer modifies the inputs as expected, according to the given bi-predicate.
         *
         * @param input1 the first input value
         * @param input2 the second input value
         * @param condition the bi-predicate to test both inputs after consumption
         * @return this CheckerBiConsumer instance
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
     * Checks that the BiConsumer modifies all entries in the collection as expected, according to the given predicates.
     *
     * @param input the collection of key-value entries to test
     * @param condition1 the predicate to test the first input after consumption
     * @param condition2 the predicate to test the second input after consumption
     * @return this CheckerBiConsumer instance
     */
    public CheckerBiConsumer<T, U> modifiesInput(Collection<? extends Entry<T, U>> input, Predicate<T> condition1, Predicate<U> condition2) {
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
     * Checks that the BiConsumer modifies all entries in the collection as expected, according to the given bi-predicate.
     *
     * @param input the collection of key-value entries to test
     * @param condition the bi-predicate to test both inputs after consumption
     * @return this CheckerBiConsumer instance
     */
    public CheckerBiConsumer<T, U> modifiesInput(Collection<? extends Entry<T, U>> input, BiPredicate<T, U> condition) {
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
     * Checks that the BiConsumer does not modify the given inputs.
     *
     * @param input1 the first input value
     * @param input2 the second input value
     * @return this CheckerBiConsumer instance
     */
    public CheckerBiConsumer<T, U> doesNothing(T input1, U input2) {
        return is(c -> {
            try {
                T before1 = Cloner.deepClone(input1);
                T after1 = getInput1(input1);
                U before2 = Cloner.deepClone(input2);
                U after2 = getInput2(input2);
                c.accept(after1, after2);
                return Utils.equalsContent(after1, before1) && Utils.equalsContent(after2, before2);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_BI_CONSUMER, "does_nothing", input1, input2));
    }

    /**
     * Checks that the BiConsumer does not modify any of the entries in the collection.
     *
     * @param input the collection of key-value entries to test
     * @return this CheckerBiConsumer instance
     */
    public CheckerBiConsumer<T, U> doesNothing(Collection<? extends Entry<T,U>> input) {
        return is(c -> input.stream().allMatch(e -> {
                    try {
                        T before1 = Cloner.deepClone(e.getKey());
                        T after1 = getInput1(e.getKey());
                        U before2 = Cloner.deepClone(e.getValue());
                        U after2 = getInput2(e.getValue());
                        c.accept(after1, after2);
                        return Utils.equalsContent(after1, before1) && Utils.equalsContent(after2, before2);
                    } catch (Exception exc) {
                        return false;
                    }
                }
            )
        , sendMessage(INIT_BI_CONSUMER, "does_nothing.collection"));
    }

}
