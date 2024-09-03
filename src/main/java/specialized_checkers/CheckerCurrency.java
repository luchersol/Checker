package specialized_checkers;

import static util.Message.sendMessage;

import java.util.Currency;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.ExceptionTracker;

public class CheckerCurrency extends AbstractChecker<Currency> {

    private static final String INIT_CURRENCY = "currency";

    public CheckerCurrency(Currency object, String name, ExceptionTracker exceptionTracker) {
        super(object, name, exceptionTracker);
    }

    @Override
    public CheckerCurrency is(Predicate<Currency> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerCurrency is(Predicate<Currency> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerCurrency isNot(Predicate<Currency> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerCurrency isNot(Predicate<Currency> condition) {
        super.isNot(condition);
        return this;
    }

}
