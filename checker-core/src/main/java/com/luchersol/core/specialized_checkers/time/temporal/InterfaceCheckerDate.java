package com.luchersol.core.specialized_checkers.time.temporal;

/**
 * InterfaceCheckerDate defines a contract for date/time checker classes, providing common validation methods
 * such as comparisons (before, after, in range) and temporal checks (past, future).
 *
 * @param <T> the type of the checker implementing this interface (for fluent API chaining)
 * @param <U> the type of the date/time value being checked
 */
public interface InterfaceCheckerDate<T extends InterfaceCheckerDate<T, U>, U> {

    /**
     * Checks if the value is before the specified date/time.
     *
     * @param date the date/time to compare with
     * @return this checker instance for chaining
     */
    T isBefore(U date);

    /**
     * Checks if the value is before or equal to the specified date/time.
     *
     * @param date the date/time to compare with
     * @return this checker instance for chaining
     */
    T isBeforeOrEqual(U date);

    /**
     * Checks if the value is after the specified date/time.
     *
     * @param date the date/time to compare with
     * @return this checker instance for chaining
     */
    T isAfter(U date);

    /**
     * Checks if the value is after or equal to the specified date/time.
     *
     * @param date the date/time to compare with
     * @return this checker instance for chaining
     */
    T isAfterOrEqual(U date);

    /**
     * Checks if the value is within the specified range (exclusive).
     *
     * @param date_1 the start of the range (exclusive)
     * @param date_2 the end of the range (exclusive)
     * @return this checker instance for chaining
     */
    T inRange(U date_1, U date_2);

    /**
     * Checks if the value is in the past (before now).
     *
     * @return this checker instance for chaining
     */
    T isPast();

    /**
     * Checks if the value is in the future (after now).
     *
     * @return this checker instance for chaining
     */
    T isFuture();
}
