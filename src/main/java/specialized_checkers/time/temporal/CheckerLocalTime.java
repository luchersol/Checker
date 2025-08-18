package specialized_checkers.time.temporal;

import static util.Message.*;

import java.time.LocalTime;

import util.AbstractChecker;
import util.ExceptionTracker;

public class CheckerLocalTime extends AbstractChecker<LocalTime, CheckerLocalTime> implements InterfaceCheckerDate<CheckerLocalTime, LocalTime>  {

    private static final String LOCAL_TIME_STRING = "LocalTime";
    // private static final String INIT_TIME = "time";
    private static final String INIT_TEMPORAL = "time.temporal";
    // private static final String INIT_LOCAL_DATE = "time.temporal.local_time";

    public CheckerLocalTime(LocalTime object, String name, ExceptionTracker exceptionTracker) {
        super(object, name, exceptionTracker);
    }

    @Override
    protected CheckerLocalTime self() {
        return this;
    }

    private static LocalTime now(){
        return LocalTime.now();
    }

    @Override
    public CheckerLocalTime isBefore(LocalTime date) {
        return is(time -> time.isBefore(date), sendMessage(INIT_TEMPORAL, "is_before", LOCAL_TIME_STRING, date));
    }

    @Override
    public CheckerLocalTime isBeforeOrEqual(LocalTime date) {
        return is(time -> time.isBefore(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_before_or_equal", LOCAL_TIME_STRING, date));
    }

    @Override
    public CheckerLocalTime isAfter(LocalTime date) {
        return is(time -> time.isAfter(date), sendMessage(INIT_TEMPORAL, "is_after", LOCAL_TIME_STRING, date));
    }

    @Override
    public CheckerLocalTime isAfterOrEqual(LocalTime date) {
        return is(time -> time.isAfter(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_after_or_equal", LOCAL_TIME_STRING, date));
    }

    @Override
    public CheckerLocalTime inRange(LocalTime date_1, LocalTime date_2) {
        return is(time -> time.isAfter(date_1) && time.isBefore(date_2), sendMessage(INIT_TEMPORAL, "in_range", LOCAL_TIME_STRING, date_1, date_2));
    }

    @Override
    public CheckerLocalTime isPast() {
        return is(time -> time.isBefore(now()), sendMessage(INIT_TEMPORAL, "is_past", LOCAL_TIME_STRING));
    }

    @Override
    public CheckerLocalTime isPastOrPresent() {
        return is(time -> time.isBefore(now()) || time.equals(now()), sendMessage(INIT_TEMPORAL, "is_past_or_present", LOCAL_TIME_STRING));
    }

    @Override
    public CheckerLocalTime isFuture() {
        return is(time -> time.isAfter(now()), sendMessage(INIT_TEMPORAL, "is_future", LOCAL_TIME_STRING));
    }

    @Override
    public CheckerLocalTime isFutureOrPresent() {
        return is(time -> time.isAfter(now()) || time.equals(now()), sendMessage(INIT_TEMPORAL, "is_future_or_present", LOCAL_TIME_STRING));
    }

}
