package specialized_checkers.time.temporal;

import static util.Message.*;

import java.time.LocalDate;

import util.AbstractChecker;

/**
 * CheckerLocalDate is a specialized checker for validating and performing assertions on {@link LocalDate} values.
 * <p>
 * It provides a fluent API for common local date validations such as checking if a date is before, after, in range, past, future, or present.
 *
 * @param <T> the type of value to be checked, which is {@link LocalDate} in this case
 */
public class CheckerLocalDate extends AbstractChecker<LocalDate, CheckerLocalDate> implements InterfaceCheckerDate<CheckerLocalDate, LocalDate>  {

    private static final String INIT_TEMPORAL = "time.temporal";
    private static final String INIT_LOCAL_DATE = "time.temporal.local_date";
    private static final String DEFAULT_NAME = "LocalDate";

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
