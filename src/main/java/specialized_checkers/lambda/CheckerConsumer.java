package specialized_checkers.lambda;

import static util.Message.*;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.Cloner;

public class CheckerConsumer<T> extends AbstractChecker<Consumer<T>, CheckerConsumer<T>> {

    private static final String INIT_CONSUMER = "lambda.consumer";

    private boolean deepClone;

    public CheckerConsumer(Consumer<T> consumer, String name) {
        super(consumer, name);
    }

    /**
     * @return CheckerConsumer<T>
     */
    @Override
    protected CheckerConsumer<T> self() {
        return this;
    }


    public CheckerConsumer<T> activateDeepClone() {
        this.deepClone = true;
        return self();
    }

    public CheckerConsumer<T> deactivateDeepClone() {
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
     * @return CheckerConsumer<T>
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
     * @param input
     * @return CheckerConsumer<T>
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
     * @param input
     * @param condition
     * @return T
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
     * @param input
     * @param condition
     * @return T
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
     * @param input
     * @return T
     */
    public CheckerConsumer<T> doesNothing(T input) {
        return is(c -> {
            try {
                T before = Cloner.deepClone(input);
                T after = getInput(input);
                c.accept(after);
                return Objects.equals(after, before);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_CONSUMER, "does_nothing", input));
    }

    /**
     * @param input
     * @return T
     */
    public CheckerConsumer<T> doesNothing(Collection<T> input) {
        return is(c -> input.stream().allMatch(e -> {
                    try {
                        T before = Cloner.deepClone(e);
                        T after = getInput(e);
                        c.accept(after);
                        return Objects.equals(after, before);
                    } catch (Exception exc) {
                        return false;
                    }
                }
            )
        , sendMessage(INIT_CONSUMER, "does_nothing", input));
    }

}
