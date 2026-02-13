package com.luchersol.core.specialized_checkers.time.temporal;

import static com.luchersol.core.util.MessageService.*;

import java.time.LocalTime;

import com.luchersol.core.util.AbstractChecker;

/**
 * A specialized checker for {@link java.time.LocalTime} instances, providing fluent API methods
 * to assert temporal properties such as being before/after another time, inclusivity checks,
 * range validations, and whether a time lies in the past or future relative to the system clock.
 *
 * <p>Typical usage:
 * <pre>{@code
 * LocalTime now = LocalTime.now();
 * LocalTime noon = LocalTime.NOON;
 *
 * CheckerLocalTime checker = CheckerLocalTime.check(now)
 *     .isBefore(noon)
 *     .isAfterOrEqual(now)
 *     .isPast();
 * }</pre>
 *
 * <p>This class integrates with {@link InterfaceCheckerDate} for standardized temporal
 * validations and supports chaining multiple assertions in a fluent, expressive style.
 *
 * @see java.time.LocalTime
 * @see com.luchersol.core.specialized_checkers.time.temporal.InterfaceCheckerDate
 * @see com.luchersol.core.util.AbstractChecker
 */
public class CheckerLocalTime extends AbstractChecker<LocalTime, CheckerLocalTime> implements InterfaceCheckerDate<CheckerLocalTime, LocalTime>  {

    private static final String INIT_TEMPORAL = "time.temporal";
    private static final String DEFAULT_NAME = "LocalTime";

    /**
     * Constructs a new {@code CheckerLocalTime} with the specified localtime and name.
     *
     * @param localtime the {@link LocalTime} to be wrapped and checked
     * @param name the name identifying this checker function
     */
    protected CheckerLocalTime(LocalTime localtime, String name) {
        super(localtime, name);
    }

    /**
     * Creates a new CheckerLocalTime instance for the given local time and name.
     *
     * @param localtime the local time value to be checked
     * @param name      the name to identify the local time in error messages
     * @return a CheckerLocalTime instance for further validations
     */
    public static CheckerLocalTime check(LocalTime localtime, String name) {
        return new CheckerLocalTime(localtime, name);
    }

    /**
     * Creates a new CheckerLocalTime instance for the given local time with a default name.
     *
     * @param localtime the local time value to be checked
     * @return a CheckerLocalTime instance for further validations
     */
    public static CheckerLocalTime check(LocalTime localtime) {
        return check(localtime, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API usage).
     *
     * @return this CheckerLocalTime instance
     */
    @Override
    protected CheckerLocalTime self() {
        return this;
    }

    /**
     * Returns the current local time.
     *
     * @return the current {@link LocalTime}
     */
    private static LocalTime now(){
        return LocalTime.now();
    }

    /**
     * Checks if the local time is before the specified time.
     *
     * @param date the time to compare with
     * @return this CheckerLocalTime instance for chaining
     */
    @Override
    public CheckerLocalTime isBefore(LocalTime date) {
        return is(time -> time.isBefore(date), sendMessage(INIT_TEMPORAL, "is_before", DEFAULT_NAME, date));
    }

    /**
     * Checks if the local time is before or equal to the specified time.
     *
     * @param date the time to compare with
     * @return this CheckerLocalTime instance for chaining
     */
    @Override
    public CheckerLocalTime isBeforeOrEqual(LocalTime date) {
        return is(time -> time.isBefore(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_before_or_equal", DEFAULT_NAME, date));
    }

    /**
     * Checks if the local time is after the specified time.
     *
     * @param date the time to compare with
     * @return this CheckerLocalTime instance for chaining
     */
    @Override
    public CheckerLocalTime isAfter(LocalTime date) {
        return is(time -> time.isAfter(date), sendMessage(INIT_TEMPORAL, "is_after", DEFAULT_NAME, date));
    }

    /**
     * Checks if the local time is after or equal to the specified time.
     *
     * @param date the time to compare with
     * @return this CheckerLocalTime instance for chaining
     */
    @Override
    public CheckerLocalTime isAfterOrEqual(LocalTime date) {
        return is(time -> time.isAfter(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_after_or_equal", DEFAULT_NAME, date));
    }

    /**
     * Checks if the local time is within the specified range (exclusive).
     *
     * @param date_1 the start time (exclusive)
     * @param date_2 the end time (exclusive)
     * @return this CheckerLocalTime instance for chaining
     */
    @Override
    public CheckerLocalTime inRange(LocalTime date_1, LocalTime date_2) {
        return is(time -> time.isAfter(date_1) && time.isBefore(date_2), sendMessage(INIT_TEMPORAL, "in_range", DEFAULT_NAME, date_1, date_2));
    }

    /**
     * Checks if the local time is in the past (before the current time).
     *
     * @return this CheckerLocalTime instance for chaining
     */
    @Override
    public CheckerLocalTime isPast() {
        return is(time -> time.isBefore(now()), sendMessage(INIT_TEMPORAL, "is_past", DEFAULT_NAME));
    }

    /**
     * Checks if the local time is in the future (after the current time).
     *
     * @return this CheckerLocalTime instance for chaining
     */
    @Override
    public CheckerLocalTime isFuture() {
        return is(time -> time.isAfter(now()), sendMessage(INIT_TEMPORAL, "is_future", DEFAULT_NAME));
    }

}
