package specialized_checkers.time.temporal;

import static util.Message.sendMessage;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.ExceptionTracker;

public class CheckerLocalDateTime extends AbstractChecker<LocalDateTime> implements InterfaceCheckerDate<CheckerLocalDateTime, LocalDateTime>  {

    private static final String LOCAL_DATE_TIME_STRING = "LocalDateTime";
    private static final String INIT_TIME = "time";
    private static final String INIT_TEMPORAL = "time.temporal";
    private static final String INIT_LOCAL_DATE_TIME = "time.temporal.local_date_time";

    public CheckerLocalDateTime(LocalDateTime object, String name, ExceptionTracker exceptionTracker) {
        super(object, name, exceptionTracker);
    }

    private static LocalDateTime now(){
        return LocalDateTime.now();
    }

    @Override
    public CheckerLocalDateTime is(Predicate<LocalDateTime> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerLocalDateTime is(Predicate<LocalDateTime> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerLocalDateTime isNot(Predicate<LocalDateTime> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerLocalDateTime isNot(Predicate<LocalDateTime> condition) {
        super.isNot(condition);
        return this;
    }

    @Override
    public CheckerLocalDateTime isBefore(LocalDateTime date) {
        return is(time -> time.isBefore(date), sendMessage(INIT_TEMPORAL, "is_before", LOCAL_DATE_TIME_STRING, date));
    }

    @Override
    public CheckerLocalDateTime isBeforeOrEqual(LocalDateTime date) {
        return is(time -> time.isBefore(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_before_or_equal", LOCAL_DATE_TIME_STRING, date));
    }

    @Override
    public CheckerLocalDateTime isAfter(LocalDateTime date) {
        return is(time -> time.isAfter(date), sendMessage(INIT_TEMPORAL, "is_after", LOCAL_DATE_TIME_STRING, date));
    }

    @Override
    public CheckerLocalDateTime isAfterOrEqual(LocalDateTime date) {
        return is(time -> time.isAfter(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_after_or_equal", LOCAL_DATE_TIME_STRING, date));
    }

    @Override
    public CheckerLocalDateTime inRange(LocalDateTime date_1, LocalDateTime date_2) {
        return is(time -> time.isAfter(date_1) && time.isBefore(date_2), sendMessage(INIT_TEMPORAL, "in_range", LOCAL_DATE_TIME_STRING, date_1, date_2));
    }

    @Override
    public CheckerLocalDateTime isPast() {
        return is(time -> time.isBefore(now()), sendMessage(INIT_TEMPORAL, "is_past", LOCAL_DATE_TIME_STRING));
    }

    @Override
    public CheckerLocalDateTime isPastOrPresent() {
        return is(time -> time.isBefore(now()) || time.equals(now()), sendMessage(INIT_TEMPORAL, "is_past_or_present", LOCAL_DATE_TIME_STRING));
    }

    @Override
    public CheckerLocalDateTime isFuture() {
        return is(time -> time.isAfter(now()), sendMessage(INIT_TEMPORAL, "is_future", LOCAL_DATE_TIME_STRING));
    }

    @Override
    public CheckerLocalDateTime isFutureOrPresent() {
        return is(time -> time.isAfter(now()) || time.equals(now()), sendMessage(INIT_TEMPORAL, "is_future_or_present", LOCAL_DATE_TIME_STRING));
    }

}
