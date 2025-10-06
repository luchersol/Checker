package com.luchersol.core.specialized_checkers.time;

import static com.luchersol.core.util.Message.*;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

import com.luchersol.core.util.AbstractChecker;

/**
 * A specialized checker for {@link java.time.Duration} instances, providing fluent API methods
 * to assert various temporal properties such as positivity, negativity, zero value,
 * and comparisons with other durations or specific temporal units.
 *
 * <p>Typical usage:</p>
 * <pre>{@code
 * Duration d1 = Duration.ofMinutes(5);
 * Duration d2 = Duration.ofMinutes(10);
 *
 * CheckerDuration checker = CheckerDuration.check(d1)
 *     .isPositive()
 *     .isLessThan(d2)
 *     .isGreaterOrEqualThan(Duration.ofMinutes(3));
 * }</pre>
 *
 * <p>This class supports chaining multiple assertions in a fluent style and integrates
 * with {@link com.luchersol.core.util.AbstractChecker} for generalized validation handling.</p>
 *
 * @see java.time.Duration
 * @see com.luchersol.core.util.AbstractChecker
 */
public class CheckerDuration extends AbstractChecker<Duration, CheckerDuration> {

    private static final String INIT_DURATION = "time.duration";
    private static final String DEFAULT_NAME = "Duration";

    /**
     * Constructs a new {@code CheckerDuration} with the specified duration and name.
     *
     * @param duration the {@link Duration} to be associated with this checker
     * @param name the name of the checker
     */
    protected CheckerDuration(Duration duration, String name) {
        super(duration, name);
    }

    /**
     * Creates a new CheckerDuration instance for the given duration and name.
     *
     * @param duration the duration value to be checked
     * @param name     the name to identify the duration in error messages
     * @return a CheckerDuration instance for further validations
     */
    public static CheckerDuration check(Duration duration, String name) {
        return new CheckerDuration(duration, name);
    }

    /**
     * Creates a new CheckerDuration instance for the given duration with a default name.
     *
     * @param duration the duration value to be checked
     * @return a CheckerDuration instance for further validations
     */
    public static CheckerDuration check(Duration duration) {
        return check(duration, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API usage).
     *
     * @return this CheckerDuration instance
     */
    @Override
    protected CheckerDuration self() {
        return this;
    }

    /**
     * Checks if the duration is positive (greater than zero).
     *
     * @return this CheckerDuration instance for chaining
     */
    public CheckerDuration isPositive(){
        return is(duration -> !duration.isNegative() && !duration.isZero(), sendMessage(INIT_DURATION, "is_positive"));
    }

    /**
     * Checks if the duration is negative (less than zero).
     *
     * @return this CheckerDuration instance for chaining
     */
    public CheckerDuration isNegative(){
        return is(duration -> duration.isNegative(), sendMessage(INIT_DURATION, "is_negative"));
    }

    /**
     * Checks if the duration is zero.
     *
     * @return this CheckerDuration instance for chaining
     */
    public CheckerDuration isZero(){
        return is(duration -> duration.isZero(), sendMessage(INIT_DURATION, "is_zero"));
    }

    /**
     * Checks if the duration is greater than the specified time in the given temporal unit.
     *
     * @param temporalUnit the unit of time to compare (e.g., SECONDS, MINUTES)
     * @param time         the value to compare against
     * @return this CheckerDuration instance for chaining
     */
    public CheckerDuration isGreaterThan(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) > time, sendMessage(INIT_DURATION, "is_greater_than"));
    }

    /**
     * Checks if the duration is greater than or equal to the specified time in the given temporal unit.
     *
     * @param temporalUnit the unit of time to compare (e.g., SECONDS, MINUTES)
     * @param time         the value to compare against
     * @return this CheckerDuration instance for chaining
     */
    public CheckerDuration isGreaterOrEqualThan(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) >= time, sendMessage(INIT_DURATION, "is_greater_or_equal_than"));
    }

    /**
     * Checks if the duration is less than the specified time in the given temporal unit.
     *
     * @param temporalUnit the unit of time to compare (e.g., SECONDS, MINUTES)
     * @param time         the value to compare against
     * @return this CheckerDuration instance for chaining
     */
    public CheckerDuration isLessThan(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) < time, sendMessage(INIT_DURATION, "is_less_than"));
    }

    /**
     * Checks if the duration is less than or equal to the specified time in the given temporal unit.
     *
     * @param temporalUnit the unit of time to compare (e.g., SECONDS, MINUTES)
     * @param time         the value to compare against
     * @return this CheckerDuration instance for chaining
     */
    public CheckerDuration isLessOrEqualThan(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) <= time, sendMessage(INIT_DURATION, "is_less_or_equal_than"));
    }

    /**
     * Checks if the duration is equal to the specified time in the given temporal unit.
     *
     * @param temporalUnit the unit of time to compare (e.g., SECONDS, MINUTES)
     * @param time         the value to compare against
     * @return this CheckerDuration instance for chaining
     */
    public CheckerDuration isEqual(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) == time, sendMessage(INIT_DURATION, "is_equal"));
    }

    /**
     * Checks if the duration is greater than another duration.
     *
     * @param other the other duration to compare with
     * @return this CheckerDuration instance for chaining
     */
    public CheckerDuration isGreaterThan(Duration other){
        return is(duration -> duration.compareTo(other) > 0, sendMessage(INIT_DURATION, "is_greater_than"));
    }

    /**
     * Checks if the duration is greater than or equal to another duration.
     *
     * @param other the other duration to compare with
     * @return this CheckerDuration instance for chaining
     */
    public CheckerDuration isGreaterOrEqualThan(Duration other){
        return is(duration -> duration.compareTo(other) >= 0, sendMessage(INIT_DURATION, "is_greater_or_equal_than"));
    }

    /**
     * Checks if the duration is less than another duration.
     *
     * @param other the other duration to compare with
     * @return this CheckerDuration instance for chaining
     */
    public CheckerDuration isLessThan(Duration other){
        return is(duration -> duration.compareTo(other) < 0, sendMessage(INIT_DURATION, "is_less_than"));
    }

    /**
     * Checks if the duration is less than or equal to another duration.
     *
     * @param other the other duration to compare with
     * @return this CheckerDuration instance for chaining
     */
    public CheckerDuration isLessOrEqualThan(Duration other){
        return is(duration -> duration.compareTo(other) <= 0, sendMessage(INIT_DURATION, "is_less_or_equal_than"));
    }

    /**
     * Checks if the duration is equal to another duration.
     *
     * @param other the other duration to compare with
     * @return this CheckerDuration instance for chaining
     */
    public CheckerDuration isEqual(Duration other){
        return is(duration -> duration.compareTo(other) == 0, sendMessage(INIT_DURATION, "is_equal"));
    }

}
