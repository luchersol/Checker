package specialized_checkers.time;

import static util.Message.sendMessage;

import java.time.Duration;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.ExceptionTracker;

public class CheckerDuration extends AbstractChecker<Duration> {

    private static final String INIT_TIME = "time";
    private static final String INIT_DURATION = "time.duration";

    public CheckerDuration(Duration object, String name, ExceptionTracker exceptionTracker) {
        super(object, name, exceptionTracker);
    }

    @Override
    public CheckerDuration is(Predicate<Duration> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerDuration is(Predicate<Duration> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerDuration isNot(Predicate<Duration> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerDuration isNot(Predicate<Duration> condition) {
        super.isNot(condition);
        return this;
    } 

}
