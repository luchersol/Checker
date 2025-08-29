package specialized_checkers.time.temporal;

import static util.Message.*;

import java.util.Date;

import util.AbstractChecker;

public class CheckerDate extends AbstractChecker<Date, CheckerDate> implements InterfaceCheckerDate<CheckerDate, Date> {

    private static final String INIT_TEMPORAL = "time.temporal";
    private static final String DEFAULT_NAME = "Date";

    protected CheckerDate(Date date, String name) {
        super(date, name);
    }

    /**
     * @param date
     * @param name
     * @return CheckerDate
     */
    public static CheckerDate check(Date date, String name) {
        return new CheckerDate(date, name);
    }

    /**
     * @param date
     * @return CheckerDate
     */
    public static CheckerDate check(Date date) {
        return check(date, DEFAULT_NAME);
    }

    /**
     * @return CheckerDate
     */
    @Override
    protected CheckerDate self() {
        return this;
    }

    /**
     * @return Date
     */
    private static Date now(){
        return new Date();
    }

    /**
     * @param date
     * @return CheckerDate
     */
    @Override
    public CheckerDate isBefore(Date date) {
        return is(time -> time.before(date), sendMessage(INIT_TEMPORAL, "is_before", DEFAULT_NAME, date));
    }

    /**
     * @param date
     * @return CheckerDate
     */
    @Override
    public CheckerDate isBeforeOrEqual(Date date) {
        return is(time -> time.before(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_before_or_equal", DEFAULT_NAME, date));
    }

    /**
     * @param date
     * @return CheckerDate
     */
    @Override
    public CheckerDate isAfter(Date date) {
        return is(time -> time.after(date), sendMessage(INIT_TEMPORAL, "is_after", DEFAULT_NAME, date));
    }

    /**
     * @param date
     * @return CheckerDate
     */
    @Override
    public CheckerDate isAfterOrEqual(Date date) {
        return is(time -> time.after(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_after_or_equal", DEFAULT_NAME, date));
    }

    /**
     * @param date_1
     * @param date_2
     * @return CheckerDate
     */
    @Override
    public CheckerDate inRange(Date date_1, Date date_2) {
        return is(time -> time.after(date_1) && time.before(date_2), sendMessage(INIT_TEMPORAL, "in_range", DEFAULT_NAME, date_1, date_2));
    }

    /**
     * @return CheckerDate
     */
    @Override
    public CheckerDate isPast() {
        return is(time -> time.before(now()), sendMessage(INIT_TEMPORAL, "is_past", DEFAULT_NAME));
    }

    /**
     * @return CheckerDate
     */
    @Override
    public CheckerDate isFuture() {
        return is(time -> time.after(now()), sendMessage(INIT_TEMPORAL, "is_future", DEFAULT_NAME));
    }

}
