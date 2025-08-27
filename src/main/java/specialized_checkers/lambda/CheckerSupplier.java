package specialized_checkers.lambda;

import static util.Message.*;

import java.util.Objects;
import java.util.function.Supplier;

import util.AbstractChecker;

public class CheckerSupplier<T> extends AbstractChecker<Supplier<T>, CheckerSupplier<T>> {

    private static final String INIT_SUPPLIER = "lambda.supplier";

    public CheckerSupplier(Supplier<T> supplier, String name) {
        super(supplier, name);
    }

    /**
     * @return CheckerSupplier<T>
     */
    @Override
    protected CheckerSupplier<T> self() {
        return this;
    }

    /**
     * @param input
     * @return CheckerSupplier<T>
     */
    public CheckerSupplier<T> applyWithoutException() {
        return is(s -> {
            try {
                s.get();
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_SUPPLIER, "apply_without_exception"));
    }

    /**
     * @param input
     * @param expected
     * @return CheckerSupplier<T>
     */
    public CheckerSupplier<T> producesExpected(T expected) {
        return is(s -> {
            try {
                T result = s.get();
                return Objects.equals(expected, result);
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_SUPPLIER, "produces_expected", expected));
    }

    /**
     * @param input
     * @return CheckerSupplier<T>
     */
    public CheckerSupplier<T> producesNonNull() {
        return is(s -> {
            try {
                return s.get() != null;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_SUPPLIER, "produces_non_null"));
    }
}
