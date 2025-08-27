package specialized_checkers.time.temporal;

import static util.Message.*;

import java.time.LocalDate;

import util.AbstractChecker;

public class CheckerLocalDate extends AbstractChecker<LocalDate, CheckerLocalDate> implements InterfaceCheckerDate<CheckerLocalDate, LocalDate>  {

    private static final String LOCAL_DATE_STRING = "LocalDate";
    // private static final String INIT_TIME = "time";
    private static final String INIT_TEMPORAL = "time.temporal";
    // private static final String INIT_LOCAL_DATE = "time.temporal.local_date";

    public CheckerLocalDate(LocalDate object, String name) {
        super(object, name);
    }

    /**
     * @return CheckerLocalDate
     */
    @Override
    protected CheckerLocalDate self() {
        return this;
    }

    /**
     * @return LocalDate
     */
    private static LocalDate now(){
        return LocalDate.now();
    }

    /**
     * @param date
     * @return CheckerLocalDate
     */
    @Override
    public CheckerLocalDate isBefore(LocalDate date) {
        return is(time -> time.isBefore(date), sendMessage(INIT_TEMPORAL, "is_before", LOCAL_DATE_STRING, date));
    }

    /**
     * @param date
     * @return CheckerLocalDate
     */
    @Override
    public CheckerLocalDate isBeforeOrEqual(LocalDate date) {
        return is(time -> time.isBefore(date) || time.isEqual(date), sendMessage(INIT_TEMPORAL, "is_before_or_equal", LOCAL_DATE_STRING, date));
    }

    /**
     * @param date
     * @return CheckerLocalDate
     */
    @Override
    public CheckerLocalDate isAfter(LocalDate date) {
        return is(time -> time.isAfter(date), sendMessage(INIT_TEMPORAL, "is_after", LOCAL_DATE_STRING, date));
    }

    /**
     * @param date
     * @return CheckerLocalDate
     */
    @Override
    public CheckerLocalDate isAfterOrEqual(LocalDate date) {
        return is(time -> time.isAfter(date) || time.isEqual(date), sendMessage(INIT_TEMPORAL, "is_after_or_equal", LOCAL_DATE_STRING, date));
    }

    /**
     * @param date_1
     * @param date_2
     * @return CheckerLocalDate
     */
    @Override
    public CheckerLocalDate inRange(LocalDate date_1, LocalDate date_2) {
        return is(time -> time.isAfter(date_1) && time.isBefore(date_2), sendMessage(INIT_TEMPORAL, "in_range", LOCAL_DATE_STRING, date_1, date_2));
    }

    /**
     * @return CheckerLocalDate
     */
    @Override
    public CheckerLocalDate isPast() {
        return is(time -> time.isBefore(now()), sendMessage(INIT_TEMPORAL, "is_past", LOCAL_DATE_STRING));
    }

    /**
     * @return CheckerLocalDate
     */
    @Override
    public CheckerLocalDate isFuture() {
        return is(time -> time.isAfter(now()), sendMessage(INIT_TEMPORAL, "is_future", LOCAL_DATE_STRING));
    }

    /**
     * @return CheckerLocalDate
     */
    public CheckerLocalDate isPastOrPresent() {
        return is(time -> !time.isAfter(now()), sendMessage(INIT_TEMPORAL, "is_past_or_present", LOCAL_DATE_STRING));
    }

    /**
     * @return CheckerLocalDate
     */
    public CheckerLocalDate isFutureOrPresent() {
        return is(time -> !time.isBefore(now()), sendMessage(INIT_TEMPORAL, "is_future_or_present", LOCAL_DATE_STRING));
    }

}
