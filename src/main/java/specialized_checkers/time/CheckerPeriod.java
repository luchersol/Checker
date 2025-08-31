package specialized_checkers.time;

import static util.Message.*;

import java.time.Period;
import java.time.temporal.TemporalUnit;

import util.AbstractChecker;

/**
 * CheckerPeriod is a specialized checker for validating and performing assertions on {@link Period} values.
 * <p>
 * It provides a fluent API for common period validations such as checking positivity, negativity, zero, and comparisons with other periods or temporal units.
 */
public class CheckerPeriod extends AbstractChecker<Period, CheckerPeriod> {

    private static final String INIT_PERIOD = "time.period";
    private static final String DEFAULT_NAME = "Period";

    /**
     * Constructs a new {@code CheckerPeriod} with the specified period and name.
     *
     * @param period the {@link Period} to be associated with this checker
     * @param name the name of the checker
     */
    protected CheckerPeriod(Period period, String name) {
        super(period, name);
    }

    /**
     * Creates a new CheckerPeriod instance for the given period and name.
     *
     * @param period the period value to be checked
     * @param name   the name to identify the period in error messages
     * @return a CheckerPeriod instance for further validations
     */
    public static CheckerPeriod check(Period period, String name) {
        return new CheckerPeriod(period, name);
    }

    /**
     * Creates a new CheckerPeriod instance for the given period with a default name.
     *
     * @param period the period value to be checked
     * @return a CheckerPeriod instance for further validations
     */
    public static CheckerPeriod check(Period period) {
        return check(period, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API usage).
     *
     * @return this CheckerPeriod instance
     */
    @Override
    protected CheckerPeriod self() {
        return this;
    }

    /**
     * Checks if the period is positive (greater than zero).
     *
     * @return this CheckerPeriod instance for chaining
     */
    public CheckerPeriod isPositive(){
        return is(period -> !period.isNegative() && !period.isZero(), sendMessage(INIT_PERIOD, "is_positive"));
    }

    /**
     * Checks if the period is negative (less than zero).
     *
     * @return this CheckerPeriod instance for chaining
     */
    public CheckerPeriod isNegative(){
        return is(period -> period.isNegative(), sendMessage(INIT_PERIOD, "is_negative"));
    }

    /**
     * Checks if the period is zero.
     *
     * @return this CheckerPeriod instance for chaining
     */
    public CheckerPeriod isZero(){
        return is(period -> period.isZero(), sendMessage(INIT_PERIOD, "is_zero"));
    }

    /**
     * Checks if the period is greater than the specified time in the given temporal unit.
     *
     * @param temporalUnit the unit of time to compare (e.g., YEARS, MONTHS, DAYS)
     * @param time         the value to compare against
     * @return this CheckerPeriod instance for chaining
     */
    public CheckerPeriod isGreaterThan(TemporalUnit temporalUnit, double time){
        return is(period -> period.get(temporalUnit) > time, sendMessage(INIT_PERIOD, "is_greater_than"));
    }

    /**
     * Checks if the period is greater than or equal to the specified time in the given temporal unit.
     *
     * @param temporalUnit the unit of time to compare (e.g., YEARS, MONTHS, DAYS)
     * @param time         the value to compare against
     * @return this CheckerPeriod instance for chaining
     */
    public CheckerPeriod isGreaterOrEqualThan(TemporalUnit temporalUnit, double time){
        return is(period -> period.get(temporalUnit) >= time, sendMessage(INIT_PERIOD, "is_greater_or_equal_than"));
    }

    /**
     * Checks if the period is less than the specified time in the given temporal unit.
     *
     * @param temporalUnit the unit of time to compare (e.g., YEARS, MONTHS, DAYS)
     * @param time         the value to compare against
     * @return this CheckerPeriod instance for chaining
     */
    public CheckerPeriod isLessThan(TemporalUnit temporalUnit, double time){
        return is(period -> period.get(temporalUnit) < time, sendMessage(INIT_PERIOD, "is_less_than"));
    }

    /**
     * Checks if the period is less than or equal to the specified time in the given temporal unit.
     *
     * @param temporalUnit the unit of time to compare (e.g., YEARS, MONTHS, DAYS)
     * @param time         the value to compare against
     * @return this CheckerPeriod instance for chaining
     */
    public CheckerPeriod isLessOrEqualThan(TemporalUnit temporalUnit, double time){
        return is(period -> period.get(temporalUnit) <= time, sendMessage(INIT_PERIOD, "is_less_or_equal_than"));
    }

    /**
     * Checks if the period is equal to the specified time in the given temporal unit.
     *
     * @param temporalUnit the unit of time to compare (e.g., YEARS, MONTHS, DAYS)
     * @param time         the value to compare against
     * @return this CheckerPeriod instance for chaining
     */
    public CheckerPeriod isEqual(TemporalUnit temporalUnit, double time){
        return is(period -> period.get(temporalUnit) == time, sendMessage(INIT_PERIOD, "is_equal"));
    }

    /**
     * Compares two periods for ordering. Returns 0 if equal, 1 if self is greater, -1 otherwise.
     *
     * @param self  the first period to compare
     * @param other the second period to compare
     * @return 0 if equal, 1 if self is greater, -1 otherwise
     */
    private static int comparateTo(Period self, Period other){
        if(self.equals(other))
            return 0;
        if(self.getYears() > other.getYears())
            return 1;
        else if(self.getMonths() > other.getMonths())
            return 1;
        else if(self.getDays() > other.getDays())
            return 1;
        else
            return -1;
    }

    /**
     * Checks if the period is greater than another period.
     *
     * @param other the other period to compare with
     * @return this CheckerPeriod instance for chaining
     */
    public CheckerPeriod isGreaterThan(Period other){
        return is(period -> comparateTo(period, other) > 0, sendMessage(INIT_PERIOD, "is_greater_than"));
    }

    /**
     * Checks if the period is greater than or equal to another period.
     *
     * @param other the other period to compare with
     * @return this CheckerPeriod instance for chaining
     */
    public CheckerPeriod isGreaterOrEqualThan(Period other){
        return is(period -> comparateTo(period, other) >= 0, sendMessage(INIT_PERIOD, "is_greater_or_equal_than"));
    }

    /**
     * Checks if the period is less than another period.
     *
     * @param other the other period to compare with
     * @return this CheckerPeriod instance for chaining
     */
    public CheckerPeriod isLessThan(Period other){
        return is(period -> comparateTo(period, other) < 0, sendMessage(INIT_PERIOD, "is_less_than"));
    }

    /**
     * Checks if the period is less than or equal to another period.
     *
     * @param other the other period to compare with
     * @return this CheckerPeriod instance for chaining
     */
    public CheckerPeriod isLessOrEqualThan(Period other){
        return is(period -> comparateTo(period, other) <= 0, sendMessage(INIT_PERIOD, "is_less_or_equal_than"));
    }

    /**
     * Checks if the period is equal to another period.
     *
     * @param other the other period to compare with
     * @return this CheckerPeriod instance for chaining
     */
    public CheckerPeriod isEqual(Period other){
        return is(period -> comparateTo(period, other) == 0, sendMessage(INIT_PERIOD, "is_equal"));
    }

}
