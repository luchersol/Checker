package specialized_checkers.time.temporal;

import static util.Message.*;

import java.time.LocalTime;

import util.AbstractChecker;

public class CheckerLocalTime extends AbstractChecker<LocalTime, CheckerLocalTime> implements InterfaceCheckerDate<CheckerLocalTime, LocalTime>  {

    private static final String INIT_TEMPORAL = "time.temporal";
    private static final String DEFAULT_NAME = "LocalTime";

    protected CheckerLocalTime(LocalTime localtime, String name) {
        super(localtime, name);
    }

    /**
     * @param localtime
     * @param name
     * @return CheckerLocalTime
     */
    public static CheckerLocalTime check(LocalTime localtime, String name) {
        return new CheckerLocalTime(localtime, name);
    }

    /**
     * @param localtime
     * @return CheckerLocalTime
     */
    public static CheckerLocalTime check(LocalTime localtime) {
        return check(localtime, DEFAULT_NAME);
    }

    /**
     * @return CheckerLocalTime
     */
    @Override
    protected CheckerLocalTime self() {
        return this;
    }

    /**
     * @return LocalTime
     */
    private static LocalTime now(){
        return LocalTime.now();
    }

    /**
     * @param date
     * @return CheckerLocalTime
     */
    @Override
    public CheckerLocalTime isBefore(LocalTime date) {
        return is(time -> time.isBefore(date), sendMessage(INIT_TEMPORAL, "is_before", DEFAULT_NAME, date));
    }

    /**
     * @param date
     * @return CheckerLocalTime
     */
    @Override
    public CheckerLocalTime isBeforeOrEqual(LocalTime date) {
        return is(time -> time.isBefore(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_before_or_equal", DEFAULT_NAME, date));
    }

    /**
     * @param date
     * @return CheckerLocalTime
     */
    @Override
    public CheckerLocalTime isAfter(LocalTime date) {
        return is(time -> time.isAfter(date), sendMessage(INIT_TEMPORAL, "is_after", DEFAULT_NAME, date));
    }

    /**
     * @param date
     * @return CheckerLocalTime
     */
    @Override
    public CheckerLocalTime isAfterOrEqual(LocalTime date) {
        return is(time -> time.isAfter(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_after_or_equal", DEFAULT_NAME, date));
    }

    /**
     * @param date_1
     * @param date_2
     * @return CheckerLocalTime
     */
    @Override
    public CheckerLocalTime inRange(LocalTime date_1, LocalTime date_2) {
        return is(time -> time.isAfter(date_1) && time.isBefore(date_2), sendMessage(INIT_TEMPORAL, "in_range", DEFAULT_NAME, date_1, date_2));
    }

    /**
     * @return CheckerLocalTime
     */
    @Override
    public CheckerLocalTime isPast() {
        return is(time -> time.isBefore(now()), sendMessage(INIT_TEMPORAL, "is_past", DEFAULT_NAME));
    }

    /**
     * @return CheckerLocalTime
     */
    @Override
    public CheckerLocalTime isFuture() {
        return is(time -> time.isAfter(now()), sendMessage(INIT_TEMPORAL, "is_future", DEFAULT_NAME));
    }

}
