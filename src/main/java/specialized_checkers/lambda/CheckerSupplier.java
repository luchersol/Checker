package specialized_checkers.lambda;

import static util.Message.*;

import java.util.function.Supplier;

import util.AbstractChecker;
import util.Utils;

public class CheckerSupplier<T> extends AbstractChecker<Supplier<T>, CheckerSupplier<T>> {

    private static final String INIT_SUPPLIER = "lambda.supplier";
    private static final String DEFAULT_NAME = "Supplier";

    protected CheckerSupplier(Supplier<T> supplier, String name) {
        super(supplier, name);
    }

    /**
     * @param supplier
     * @param name
     * @return CheckerSupplier<T>
     */
    public static <T> CheckerSupplier<T> check(Supplier<T> supplier, String name) {
        return new CheckerSupplier<>(supplier, name);
    }

    /**
     * @param supplier
     * @return CheckerSupplier<T>
     */
    public static <T> CheckerSupplier<T> check(Supplier<T> supplier) {
        return check(supplier, DEFAULT_NAME);
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
                return Utils.equalsContent(expected, result);
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
