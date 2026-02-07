package com.luchersol.core.specialized_checkers.time.temporal;

import static com.luchersol.core.util.MessageService.*;

import java.util.Date;

import com.luchersol.core.util.AbstractChecker;

/**
 * A specialized checker for {@link java.util.Date} instances, providing fluent API methods
 * to assert temporal properties such as being before/after another date, inclusivity checks,
 * range validations, and whether a date lies in the past or future.
 *
 * <p>Typical usage:</p>
 * <pre>{@code
 * Date deadline = ...;
 * Date now = new Date();
 *
 * CheckerDate checker = CheckerDate.check(deadline)
 *     .isAfter(now)
 *     .inRange(now, new Date(now.getTime() + 86400000)) // within the next 24 hours
 *     .isFuture();
 * }</pre>
 *
 * <p>This class integrates with {@link InterfaceCheckerDate} for standardized temporal
 * validations and supports chaining multiple assertions in a fluent, expressive style.</p>
 *
 * @see java.util.Date
 * @see com.luchersol.core.specialized_checkers.time.temporal.InterfaceCheckerDate
 * @see com.luchersol.core.util.AbstractChecker
 */
public class CheckerDate extends AbstractChecker<Date, CheckerDate> implements InterfaceCheckerDate<CheckerDate, Date> {

    private static final String INIT_TEMPORAL = "time.temporal";
    private static final String DEFAULT_NAME = "Date";

    /**
     * Constructs a new {@code CheckerDate} with the specified date and name.
     *
     * @param date the {@link Date} to be wrapped and checked
     * @param name the name identifying this checker function
     */
    protected CheckerDate(Date date, String name) {
        super(date, name);
    }

    /**
     * Creates a new CheckerDate instance for the given date and name.
     *
     * @param date the date value to be checked
     * @param name the name to identify the date in error messages
     * @return a CheckerDate instance for further validations
     */
    public static CheckerDate check(Date date, String name) {
        return new CheckerDate(date, name);
    }

    /**
     * Creates a new CheckerDate instance for the given date with a default name.
     *
     * @param date the date value to be checked
     * @return a CheckerDate instance for further validations
     */
    public static CheckerDate check(Date date) {
        return check(date, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API usage).
     *
     * @return this CheckerDate instance
     */
    @Override
    protected CheckerDate self() {
        return this;
    }

    /**
     * Returns the current date and time.
     *
     * @return the current {@link Date}
     */
    private static Date now(){
        return new Date();
    }

    /**
     * Checks if the date is before the specified date.
     *
     * @param date the date to compare with
     * @return this CheckerDate instance for chaining
     */
    @Override
    public CheckerDate isBefore(Date date) {
        return is(time -> time.before(date), sendMessage(INIT_TEMPORAL, "is_before", DEFAULT_NAME, date));
    }

    /**
     * Checks if the date is before or equal to the specified date.
     *
     * @param date the date to compare with
     * @return this CheckerDate instance for chaining
     */
    @Override
    public CheckerDate isBeforeOrEqual(Date date) {
        return is(time -> time.before(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_before_or_equal", DEFAULT_NAME, date));
    }

    /**
     * Checks if the date is after the specified date.
     *
     * @param date the date to compare with
     * @return this CheckerDate instance for chaining
     */
    @Override
    public CheckerDate isAfter(Date date) {
        return is(time -> time.after(date), sendMessage(INIT_TEMPORAL, "is_after", DEFAULT_NAME, date));
    }

    /**
     * Checks if the date is after or equal to the specified date.
     *
     * @param date the date to compare with
     * @return this CheckerDate instance for chaining
     */
    @Override
    public CheckerDate isAfterOrEqual(Date date) {
        return is(time -> time.after(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_after_or_equal", DEFAULT_NAME, date));
    }

    /**
     * Checks if the date is within the specified range (exclusive).
     *
     * @param date_1 the start date (exclusive)
     * @param date_2 the end date (exclusive)
     * @return this CheckerDate instance for chaining
     */
    @Override
    public CheckerDate inRange(Date date_1, Date date_2) {
        return is(time -> time.after(date_1) && time.before(date_2), sendMessage(INIT_TEMPORAL, "in_range", DEFAULT_NAME, date_1, date_2));
    }

    /**
     * Checks if the date is in the past (before the current date and time).
     *
     * @return this CheckerDate instance for chaining
     */
    @Override
    public CheckerDate isPast() {
        return is(time -> time.before(now()), sendMessage(INIT_TEMPORAL, "is_past", DEFAULT_NAME));
    }

    /**
     * Checks if the date is in the future (after the current date and time).
     *
     * @return this CheckerDate instance for chaining
     */
    @Override
    public CheckerDate isFuture() {
        return is(time -> time.after(now()), sendMessage(INIT_TEMPORAL, "is_future", DEFAULT_NAME));
    }

}
