package com.luchersol.core.specialized_checkers.time.temporal;

import static com.luchersol.core.util.MessageService.*;

import java.time.LocalDateTime;

import com.luchersol.core.util.AbstractChecker;

/**
 * A specialized checker for {@link java.time.LocalDateTime} instances, providing fluent API methods
 * to assert temporal properties such as being before/after another date-time, inclusivity checks,
 * range validations, and whether a date-time lies in the past or future relative to the system clock.
 *
 * <p>Typical usage:
 * <pre>{@code
 * LocalDateTime now = LocalDateTime.now();
 * LocalDateTime tomorrow = now.plusDays(1);
 *
 * CheckerLocalDateTime checker = CheckerLocalDateTime.check(now)
 *     .isBefore(tomorrow)
 *     .isAfterOrEqual(now)
 *     .isPast();
 * }</pre>
 *
 * <p>This class integrates with {@link InterfaceCheckerDate} for standardized temporal
 * validations and supports chaining multiple assertions in a fluent, expressive style.
 *
 * @see java.time.LocalDateTime
 * @see com.luchersol.core.specialized_checkers.time.temporal.InterfaceCheckerDate
 * @see com.luchersol.core.util.AbstractChecker
 */
public class CheckerLocalDateTime extends AbstractChecker<LocalDateTime, CheckerLocalDateTime> implements InterfaceCheckerDate<CheckerLocalDateTime, LocalDateTime>  {

    private static final String INIT_TEMPORAL = "time.temporal";
    private static final String DEFAULT_NAME = "LocalDateTime";

    /**
     * Constructs a new {@code CheckerLocalDateTime} with the specified localdatetime and name.
     *
     * @param localdatetime the {@link LocalDateTime} to be wrapped and checked
     * @param name the name identifying this checker function
     */
    protected CheckerLocalDateTime(LocalDateTime localdatetime, String name) {
        super(localdatetime, name);
    }

    /**
     * Creates a new CheckerLocalDateTime instance for the given local date-time and name.
     *
     * @param localdatetime the local date-time value to be checked
     * @param name         the name to identify the local date-time in error messages
     * @return a CheckerLocalDateTime instance for further validations
     */
    public static CheckerLocalDateTime check(LocalDateTime localdatetime, String name) {
        return new CheckerLocalDateTime(localdatetime, name);
    }

    /**
     * Creates a new CheckerLocalDateTime instance for the given local date-time with a default name.
     *
     * @param localdatetime the local date-time value to be checked
     * @return a CheckerLocalDateTime instance for further validations
     */
    public static CheckerLocalDateTime check(LocalDateTime localdatetime) {
        return check(localdatetime, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API usage).
     *
     * @return this CheckerLocalDateTime instance
     */
    @Override
    protected CheckerLocalDateTime self() {
        return this;
    }

    /**
     * Returns the current local date-time.
     *
     * @return the current {@link LocalDateTime}
     */
    private static LocalDateTime now(){
        return LocalDateTime.now();
    }

    /**
     * Checks if the local date-time is before the specified date-time.
     *
     * @param date the date-time to compare with
     * @return this CheckerLocalDateTime instance for chaining
     */
    @Override
    public CheckerLocalDateTime isBefore(LocalDateTime date) {
        return is(time -> time.isBefore(date), sendMessage(INIT_TEMPORAL, "is_before", DEFAULT_NAME, date));
    }

    /**
     * Checks if the local date-time is before or equal to the specified date-time.
     *
     * @param date the date-time to compare with
     * @return this CheckerLocalDateTime instance for chaining
     */
    @Override
    public CheckerLocalDateTime isBeforeOrEqual(LocalDateTime date) {
        return is(time -> time.isBefore(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_before_or_equal", DEFAULT_NAME, date));
    }

    /**
     * Checks if the local date-time is after the specified date-time.
     *
     * @param date the date-time to compare with
     * @return this CheckerLocalDateTime instance for chaining
     */
    @Override
    public CheckerLocalDateTime isAfter(LocalDateTime date) {
        return is(time -> time.isAfter(date), sendMessage(INIT_TEMPORAL, "is_after", DEFAULT_NAME, date));
    }

    /**
     * Checks if the local date-time is after or equal to the specified date-time.
     *
     * @param date the date-time to compare with
     * @return this CheckerLocalDateTime instance for chaining
     */
    @Override
    public CheckerLocalDateTime isAfterOrEqual(LocalDateTime date) {
        return is(time -> time.isAfter(date) || time.equals(date), sendMessage(INIT_TEMPORAL, "is_after_or_equal", DEFAULT_NAME, date));
    }

    /**
     * Checks if the local date-time is within the specified range (exclusive).
     *
     * @param date_1 the start date-time (exclusive)
     * @param date_2 the end date-time (exclusive)
     * @return this CheckerLocalDateTime instance for chaining
     */
    @Override
    public CheckerLocalDateTime inRange(LocalDateTime date_1, LocalDateTime date_2) {
        return is(time -> time.isAfter(date_1) && time.isBefore(date_2), sendMessage(INIT_TEMPORAL, "in_range", DEFAULT_NAME, date_1, date_2));
    }

    /**
     * Checks if the local date-time is in the past (before the current date-time).
     *
     * @return this CheckerLocalDateTime instance for chaining
     */
    @Override
    public CheckerLocalDateTime isPast() {
        return is(time -> time.isBefore(now()), sendMessage(INIT_TEMPORAL, "is_past", DEFAULT_NAME));
    }

    /**
     * Checks if the local date-time is in the future (after the current date-time).
     *
     * @return this CheckerLocalDateTime instance for chaining
     */
    @Override
    public CheckerLocalDateTime isFuture() {
        return is(time -> time.isAfter(now()), sendMessage(INIT_TEMPORAL, "is_future", DEFAULT_NAME));
    }

}
