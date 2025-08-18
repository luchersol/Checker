package specialized_checkers.time.temporal;

import static util.Message.*;

import java.time.LocalDate;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.ExceptionTracker;

public class CheckerLocalDate extends AbstractChecker<LocalDate, CheckerLocalDate> implements InterfaceCheckerDate<CheckerLocalDate, LocalDate>  {
    
    private static final String LOCAL_DATE_STRING = "LocalDate";
    // private static final String INIT_TIME = "time";
    private static final String INIT_TEMPORAL = "time.temporal";
    // private static final String INIT_LOCAL_DATE = "time.temporal.local_date";

    public CheckerLocalDate(LocalDate object, String name, ExceptionTracker exceptionTracker) {
        super(object, name, exceptionTracker);
    }

    @Override
    protected CheckerLocalDate self() {
        return this;
    }

    private static LocalDate now(){
        return LocalDate.now();
    }

    @Override
    public CheckerLocalDate is(Predicate<LocalDate> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerLocalDate is(Predicate<LocalDate> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerLocalDate isNot(Predicate<LocalDate> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerLocalDate isNot(Predicate<LocalDate> condition) {
        super.isNot(condition);
        return this;
    }

    @Override
    public CheckerLocalDate isBefore(LocalDate date) {
        return is(time -> time.isBefore(date), sendMessage(INIT_TEMPORAL, "is_before", LOCAL_DATE_STRING, date));
    }

    @Override
    public CheckerLocalDate isBeforeOrEqual(LocalDate date) {
        return is(time -> time.isBefore(date) || time.isEqual(date), sendMessage(INIT_TEMPORAL, "is_before_or_equal", LOCAL_DATE_STRING, date));
    }

    @Override
    public CheckerLocalDate isAfter(LocalDate date) {
        return is(time -> time.isAfter(date), sendMessage(INIT_TEMPORAL, "is_after", LOCAL_DATE_STRING, date));
    }

    @Override
    public CheckerLocalDate isAfterOrEqual(LocalDate date) {
        return is(time -> time.isAfter(date) || time.isEqual(date), sendMessage(INIT_TEMPORAL, "is_after_or_equal", LOCAL_DATE_STRING, date));
    }

    @Override
    public CheckerLocalDate inRange(LocalDate date_1, LocalDate date_2) {
        return is(time -> time.isAfter(date_1) && time.isBefore(date_2), sendMessage(INIT_TEMPORAL, "in_range", LOCAL_DATE_STRING, date_1, date_2));
    }

    @Override
    public CheckerLocalDate isPast() {
        return is(time -> time.isBefore(now()), sendMessage(INIT_TEMPORAL, "is_past", LOCAL_DATE_STRING));
    }

    @Override
    public CheckerLocalDate isPastOrPresent() {
        return is(time -> time.isBefore(now()) || time.equals(now()), sendMessage(INIT_TEMPORAL, "is_past_or_present", LOCAL_DATE_STRING));
    }

    @Override
    public CheckerLocalDate isFuture() {
        return is(time -> time.isAfter(now()), sendMessage(INIT_TEMPORAL, "is_future", LOCAL_DATE_STRING));
    }

    @Override
    public CheckerLocalDate isFutureOrPresent() {
        return is(time -> time.isAfter(now()) || time.equals(now()), sendMessage(INIT_TEMPORAL, "is_future_or_present", LOCAL_DATE_STRING));
    }

}
