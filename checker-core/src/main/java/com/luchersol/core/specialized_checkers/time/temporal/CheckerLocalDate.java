package com.luchersol.core.specialized_checkers.time.temporal;

import static com.luchersol.core.util.MessageService.*;

import java.time.LocalDate;

import com.luchersol.core.util.AbstractChecker;

/**
 * A specialized checker for {@link java.time.LocalDate} instances, providing fluent API methods
 * to assert temporal properties such as being before/after another date, inclusivity checks,
 * range validations, and whether a date lies in the past, future, or present.
 *
 * <p>Typical usage:
 * <pre>{@code
 * LocalDate today = LocalDate.now();
 * LocalDate tomorrow = today.plusDays(1);
 *
 * CheckerLocalDate checker = CheckerLocalDate.check(today)
 *     .isBeforeOrEqual(tomorrow)
 *     .isPastOrPresent()
 *     .isFutureOrPresent();
 * }</pre>
 *
 * <p>This class integrates with {@link InterfaceCheckerDate} for standardized temporal
 * validations and supports chaining multiple assertions in a fluent, expressive style.
 *
 * @see java.time.LocalDate
 * @see com.luchersol.core.specialized_checkers.time.temporal.InterfaceCheckerDate
 * @see com.luchersol.core.util.AbstractChecker
 */
public class CheckerLocalDate extends AbstractChecker<LocalDate, CheckerLocalDate> implements InterfaceCheckerDate<CheckerLocalDate, LocalDate>  {

    private static final String INIT_TEMPORAL = "time.temporal";
    private static final String INIT_LOCAL_DATE = "time.temporal.local_date";
    private static final String DEFAULT_NAME = "LocalDate";

    /**
     * Constructs a new {@code CheckerLocalDate} with the specified localdate and name.
     *
     * @param localdate the {@link LocalDate} to be wrapped and checked
     * @param name the name identifying this checker function
     */
    protected CheckerLocalDate(LocalDate localdate, String name) {
        super(localdate, name);
    }

    /**
     * Creates a new CheckerLocalDate instance for the given local date and name.
     *
     * @param localdate the local date value to be checked
     * @param name      the name to identify the local date in error messages
     * @return a CheckerLocalDate instance for further validations
     */
    public static CheckerLocalDate check(LocalDate localdate, String name) {
        return new CheckerLocalDate(localdate, name);
    }

    /**
     * Creates a new CheckerLocalDate instance for the given local date with a default name.
     *
     * @param localdate the local date value to be checked
     * @return a CheckerLocalDate instance for further validations
     */
    public static CheckerLocalDate check(LocalDate localdate) {
        return check(localdate, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API usage).
     *
     * @return this CheckerLocalDate instance
     */
    @Override
    protected CheckerLocalDate self() {
        return this;
    }

    /**
     * Returns the current local date.
     *
     * @return the current {@link LocalDate}
     */
    private static LocalDate now(){
        return LocalDate.now();
    }

    /**
     * Checks if the local date is before the specified date.
     *
     * @param date the date to compare with
     * @return this CheckerLocalDate instance for chaining
     */
    @Override
    public CheckerLocalDate isBefore(LocalDate date) {
        return is(time -> time.isBefore(date), sendMessage(INIT_TEMPORAL, "is_before", DEFAULT_NAME, date));
    }

    /**
     * Checks if the local date is before or equal to the specified date.
     *
     * @param date the date to compare with
     * @return this CheckerLocalDate instance for chaining
     */
    @Override
    public CheckerLocalDate isBeforeOrEqual(LocalDate date) {
        return is(time -> time.isBefore(date) || time.isEqual(date), sendMessage(INIT_TEMPORAL, "is_before_or_equal", DEFAULT_NAME, date));
    }

    /**
     * Checks if the local date is after the specified date.
     *
     * @param date the date to compare with
     * @return this CheckerLocalDate instance for chaining
     */
    @Override
    public CheckerLocalDate isAfter(LocalDate date) {
        return is(time -> time.isAfter(date), sendMessage(INIT_TEMPORAL, "is_after", DEFAULT_NAME, date));
    }

    /**
     * Checks if the local date is after or equal to the specified date.
     *
     * @param date the date to compare with
     * @return this CheckerLocalDate instance for chaining
     */
    @Override
    public CheckerLocalDate isAfterOrEqual(LocalDate date) {
        return is(time -> time.isAfter(date) || time.isEqual(date), sendMessage(INIT_TEMPORAL, "is_after_or_equal", DEFAULT_NAME, date));
    }

    /**
     * Checks if the local date is within the specified range (exclusive).
     *
     * @param date_1 the start date (exclusive)
     * @param date_2 the end date (exclusive)
     * @return this CheckerLocalDate instance for chaining
     */
    @Override
    public CheckerLocalDate inRange(LocalDate date_1, LocalDate date_2) {
        return is(time -> time.isAfter(date_1) && time.isBefore(date_2), sendMessage(INIT_TEMPORAL, "in_range", DEFAULT_NAME, date_1, date_2));
    }

    /**
     * Checks if the local date is in the past (before the current date).
     *
     * @return this CheckerLocalDate instance for chaining
     */
    @Override
    public CheckerLocalDate isPast() {
        return is(time -> time.isBefore(now()), sendMessage(INIT_TEMPORAL, "is_past", DEFAULT_NAME));
    }

    /**
     * Checks if the local date is in the future (after the current date).
     *
     * @return this CheckerLocalDate instance for chaining
     */
    @Override
    public CheckerLocalDate isFuture() {
        return is(time -> time.isAfter(now()), sendMessage(INIT_TEMPORAL, "is_future", DEFAULT_NAME));
    }

    /**
     * Checks if the local date is in the past or present (not after the current date).
     *
     * @return this CheckerLocalDate instance for chaining
     */
    public CheckerLocalDate isPastOrPresent() {
        return is(time -> !time.isAfter(now()), sendMessage(INIT_LOCAL_DATE, "is_past_or_present", DEFAULT_NAME));
    }

    /**
     * Checks if the local date is in the future or present (not before the current date).
     *
     * @return this CheckerLocalDate instance for chaining
     */
    public CheckerLocalDate isFutureOrPresent() {
        return is(time -> !time.isBefore(now()), sendMessage(INIT_LOCAL_DATE, "is_future_or_present", DEFAULT_NAME));
    }

}
