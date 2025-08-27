package specialized_checkers.lambda;

import static util.Message.*;

import java.util.Objects;
import java.util.concurrent.Callable;

import util.AbstractChecker;

public class CheckerCallable<V> extends AbstractChecker<Callable<V>, CheckerCallable<V>> {

    private static final String INIT_CALLABLE = "lambda.callable";

    public CheckerCallable(Callable<V> callable, String name) {
        super(callable, name);
    }

    @Override
    protected CheckerCallable<V> self() {
        return this;
    }

    /**
     * @return CheckerCallable<V>
     */
    public CheckerCallable<V> callWithoutException() {
        return is(c -> {
            try {
                c.call();
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_CALLABLE, "call_without_exception"));
    }

    /**
     * @param excepted
     * @return CheckerCallable<V>
     */
    public CheckerCallable<V> producesExpected(V expected) {
        return is(c -> {
            try {
                V result = c.call();
                return Objects.equals(expected, result);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_CALLABLE, "produces_expected", expected));
    }

    /**
     * @return CheckerCallable<V>
     */
    public CheckerCallable<V> producesNonNull() {
        return is(c -> {
            try {
                return c.call() != null;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_CALLABLE, "produces_non_null"));
    }
}
