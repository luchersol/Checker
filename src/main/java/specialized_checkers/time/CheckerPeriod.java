package specialized_checkers.time;

import static util.Message.sendMessage;

import java.time.Period;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.ExceptionTracker;

public class CheckerPeriod extends AbstractChecker<Period> {

    private static final String INIT_TIME = "time";
    private static final String INIT_PERIOD = "time.period";

    public CheckerPeriod(Period object, String name, ExceptionTracker exceptionTracker) {
        super(object, name, exceptionTracker);
    }

    @Override
    public CheckerPeriod is(Predicate<Period> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerPeriod is(Predicate<Period> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerPeriod isNot(Predicate<Period> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerPeriod isNot(Predicate<Period> condition) {
        super.isNot(condition);
        return this;
    }

}
