package specialized_checkers.time.temporal;

import static util.Message.*;

import java.util.Date;

import util.AbstractChecker;

public class CheckerDate extends AbstractChecker<Date, CheckerDate> implements InterfaceCheckerDate<CheckerDate, Date> {

    private static final String DATE_STRING = "Date";
    // private static final String INIT_TIME = "time";
    private static final String INIT_TEMPORAL = "time.temporal";
    // private static final String INIT_DATE = "time.temporal.date";

    public CheckerDate(Date object, String name) {
        super(object, name);
    }

    @Override
    protected CheckerDate self() {
        return this;
    }

    private static Date now(){
        return new Date();
    }

    @Override
    public CheckerDate isBefore(Date date) {
        return is(time -> time.before(date), sendMessage(INIT_TEMPORAL, "is_before", DATE_STRING, date));
    }

    @Override
    public CheckerDate isBeforeOrEqual(Date date) {
        return is(time -> time.before(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_before_or_equal", DATE_STRING, date));
    }

    @Override
    public CheckerDate isAfter(Date date) {
        return is(time -> time.after(date), sendMessage(INIT_TEMPORAL, "is_after", DATE_STRING, date));
    }

    @Override
    public CheckerDate isAfterOrEqual(Date date) {
        return is(time -> time.after(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_after_or_equal", DATE_STRING, date));
    }

    @Override
    public CheckerDate inRange(Date date_1, Date date_2) {
        return is(time -> time.after(date_1) && time.before(date_2), sendMessage(INIT_TEMPORAL, "in_range", DATE_STRING, date_1, date_2));
    }

    @Override
    public CheckerDate isPast() {
        return is(time -> time.before(now()), sendMessage(INIT_TEMPORAL, "is_past", DATE_STRING));
    }

    @Override
    public CheckerDate isFuture() {
        return is(time -> time.after(now()), sendMessage(INIT_TEMPORAL, "is_future", DATE_STRING));
    }

}
