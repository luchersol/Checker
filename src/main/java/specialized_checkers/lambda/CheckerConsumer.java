package specialized_checkers.lambda;

import static util.Message.*;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.Cloner;
import util.Utils;

/**
 * Checker for {@link Consumer} instances, providing fluent validation methods for lambda expressions or operations that accept a single input argument and return no result.
 * <p>
 * This class allows you to validate and assert properties of {@code Consumer} objects in a fluent and readable way.
 * </p>
 *
 * @param <T> the type of the input to the {@code Consumer} operation being checked
 */
public class CheckerConsumer<T> extends AbstractChecker<Consumer<T>, CheckerConsumer<T>> {

    private static final String INIT_CONSUMER = "lambda.consumer";
    private static final String DEFAULT_NAME = "Consumer";

    private boolean deepClone;

    protected CheckerConsumer(Consumer<T> consumer, String name) {
        super(consumer, name);
    }

    /**
     * Creates a new {@code CheckerConsumer} for the given {@link Consumer} instance with a custom name.
     *
     * @param Consumer the {@code Consumer} instance to be checked
     * @param name     the name to identify this checker instance (useful for error messages)
     * @param <T>      the type of the input to the {@code Consumer}
     * @return a new {@code CheckerConsumer} for the provided {@code Consumer}
     */
    public static <T> CheckerConsumer<T> check(Consumer<T> Consumer, String name) {
        return new CheckerConsumer<>(Consumer, name);
    }

    /**
     * Creates a new {@code CheckerConsumer} for the given {@link Consumer} instance with a default name.
     *
     * @param Consumer the {@code Consumer} instance to be checked
     * @param <T>      the type of the input to the {@code Consumer}
     * @return a new {@code CheckerConsumer} for the provided {@code Consumer}
     */
    public static <T> CheckerConsumer<T> check(Consumer<T> Consumer) {
        return check(Consumer, DEFAULT_NAME);
    }

    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerConsumer} instance
     */
    @Override
    protected CheckerConsumer<T> self() {
        return this;
    }


    /**
     * Enables deep cloning of input objects before passing them to the {@code Consumer}.
     * This is useful to ensure that the original input is not modified by the operation.
     *
     * @return this {@code CheckerConsumer} instance for further validation
     */
    public CheckerConsumer<T> activateDeepClone() {
        this.deepClone = true;
        return self();
    }

    /**
     * Disables deep cloning of input objects before passing them to the {@code Consumer}.
     *
     * @return this {@code CheckerConsumer} instance for further validation
     */
    public CheckerConsumer<T> deactivateDeepClone() {
        this.deepClone = false;
        return self();
    }

    /**
     * Returns the input object, deep-cloned if deep cloning is activated, otherwise returns the original input.
     *
     * @param input the input object to process
     * @return the processed input (deep-cloned or original)
     */
    public T getInput(T input) {
        return this.deepClone ? Cloner.deepClone(input) : input;
    }


    /**
     * Asserts that applying the {@code Consumer} to the given input does not throw any exception.
     *
     * @param input the input object to be consumed
     * @return this {@code CheckerConsumer} instance for further validation
     */
    public CheckerConsumer<T> applyWithoutException(T input) {
        return is(c -> {
            try {
                T processInput = getInput(input);
                c.accept(processInput);
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_CONSUMER, "apply_without_exception"));
    }

    /**
     * Asserts that applying the {@code Consumer} to all elements in the given collection does not throw any exception.
     *
     * @param input the collection of input objects to be consumed
     * @return this {@code CheckerConsumer} instance for further validation
     */
    public CheckerConsumer<T> applyWithoutException(Collection<T> input) {
        return is(c -> input.stream().allMatch(e -> {
                    try {
                        T processInput = getInput(e);
                        c.accept(processInput);
                        return true;
                    } catch (Exception exc) {
                        return false;
                    }
                }
            )
        , sendMessage(INIT_CONSUMER, "apply_without_exception"));
    }

    /**
     * Asserts that applying the {@code Consumer} to the given input modifies the input as specified by the provided condition.
     *
     * @param input     the input object to be consumed
     * @param condition a predicate to test the modified input after consumption
     * @return this {@code CheckerConsumer} instance for further validation
     */
    public CheckerConsumer<T> modifiesInput(T input, Predicate<T> condition) {
        return is(c -> {
            try {
                T processInput = getInput(input);
                c.accept(processInput);
                return condition.test(processInput);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_CONSUMER, "modifies_input", input));
    }

    /**
     * Asserts that applying the {@code Consumer} to all elements in the given collection modifies each input as specified by the provided condition.
     *
     * @param input     the collection of input objects to be consumed
     * @param condition a predicate to test each modified input after consumption
     * @return this {@code CheckerConsumer} instance for further validation
     */
    public CheckerConsumer<T> modifiesInput(Collection<T> input, Predicate<T> condition) {
        return is(c -> input.stream().allMatch(e -> {
                    try {
                        T processInput = getInput(e);
                        c.accept(processInput);
                        return condition.test(processInput);
                    } catch (Exception exc) {
                        return false;
                    }
                }
            )
        , sendMessage(INIT_CONSUMER, "modifies_input", input));
    }

    /**
     * Asserts that applying the {@code Consumer} to the given input does not modify the input (input remains unchanged).
     *
     * @param input the input object to be consumed
     * @return this {@code CheckerConsumer} instance for further validation
     */
    public CheckerConsumer<T> doesNothing(T input) {
        return is(c -> {
            try {
                T before = Cloner.deepClone(input);
                T after = getInput(input);
                c.accept(after);
                return Utils.equalsContent(after, before);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_CONSUMER, "does_nothing", input));
    }

    /**
     * Asserts that applying the {@code Consumer} to all elements in the given collection does not modify any input (all inputs remain unchanged).
     *
     * @param input the collection of input objects to be consumed
     * @return this {@code CheckerConsumer} instance for further validation
     */
    public CheckerConsumer<T> doesNothing(Collection<T> input) {
        return is(c -> input.stream().allMatch(e -> {
                    try {
                        T before = Cloner.deepClone(e);
                        T after = getInput(e);
                        c.accept(after);
                        return Utils.equalsContent(after, before);
                    } catch (Exception exc) {
                        return false;
                    }
                }
            )
        , sendMessage(INIT_CONSUMER, "does_nothing", input));
    }

}
