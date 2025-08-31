package specialized_checkers.math.numbers.decimalTypes;

import static util.Message.*;

import specialized_checkers.math.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

/**
 * Checker for {@link Double} instances, providing fluent validation methods for double-precision floating-point numbers.
 * <p>
 * This class allows you to validate and assert properties of {@code Double} objects in a fluent and readable way.
 * </p>
 */
public class CheckerDouble extends AbstractChecker<Double, CheckerDouble> implements InterfaceCheckerNumber<CheckerDouble> {

    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_DECIMAL_TYPES = "numbers.decimal_types";
    private static final String DEFAULT_NAME = "Double";

    protected CheckerDouble(Double object, String name) {
        super(object, name);
    }

    /**
     * Creates a new {@code CheckerDouble} for the given {@link Double} instance with a custom name.
     *
     * @param number the {@code Double} instance to be checked
     * @param name   the name to identify this checker instance (useful for error messages)
     * @return a new {@code CheckerDouble} for the provided {@code Double}
     */
    public static CheckerDouble check(Double number, String name) {
        return new CheckerDouble(number, name);
    }

    /**
     * Creates a new {@code CheckerDouble} for the given {@link Number} instance with a custom name.
     *
     * @param number the {@code Number} instance to be checked (converted to double)
     * @param name   the name to identify this checker instance (useful for error messages)
     * @return a new {@code CheckerDouble} for the provided {@code Number}
     */
    public static CheckerDouble check(Number number, String name) {
        return check(number.doubleValue(), name);
    }


    /**
     * Creates a new {@code CheckerDouble} for the given {@link Double} instance with a default name.
     *
     * @param number the {@code Double} instance to be checked
     * @return a new {@code CheckerDouble} for the provided {@code Double}
     */
    public static CheckerDouble check(Double number) {
        return check(number, DEFAULT_NAME);
    }

    /**
     * Creates a new {@code CheckerDouble} for the given {@link Number} instance with a default name.
     *
     * @param number the {@code Number} instance to be checked (converted to double)
     * @return a new {@code CheckerDouble} for the provided {@code Number}
     */
    public static CheckerDouble check(Number number) {
        return check(number.doubleValue());
    }

    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerDouble} instance
     */
    @Override
    protected CheckerDouble self() {
        return this;
    }

    /**
     * Asserts that the {@code Double} value is NaN (not a number).
     *
     * @return this {@code CheckerDouble} instance for further validation
     */
    public CheckerDouble isNaN(){
        return is(n_double -> n_double.isNaN(), sendMessage(INIT_DECIMAL_TYPES, "is_nan", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Double} value is infinite.
     *
     * @return this {@code CheckerDouble} instance for further validation
     */
    public CheckerDouble isInfinite(){
        return is(n_double -> n_double.isInfinite(), sendMessage(INIT_DECIMAL_TYPES, "is_infinite", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Double} value is positive (greater than zero).
     *
     * @return this {@code CheckerDouble} instance for further validation
     */
    @Override
    public CheckerDouble isPositive() {
        return is(n_double -> n_double > 0, sendMessage(INIT_NUMBERS, "is_positive", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Double} value is positive or zero (greater than or equal to zero).
     *
     * @return this {@code CheckerDouble} instance for further validation
     */
    @Override
    public CheckerDouble isPositiveOrZero() {
        return is(n_double -> n_double >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Double} value is negative (less than zero).
     *
     * @return this {@code CheckerDouble} instance for further validation
     */
    @Override
    public CheckerDouble isNegative() {
        return is(n_double -> n_double < 0, sendMessage(INIT_NUMBERS, "is_negative", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Double} value is negative or zero (less than or equal to zero).
     *
     * @return this {@code CheckerDouble} instance for further validation
     */
    @Override
    public CheckerDouble isNegativeOrZero() {
        return is(n_double -> n_double <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Double} value is zero.
     *
     * @return this {@code CheckerDouble} instance for further validation
     */
    @Override
    public CheckerDouble isZero() {
        return is(n_double -> n_double == 0, sendMessage(INIT_NUMBERS, "is_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Double} value is greater than the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerDouble} instance for further validation
     */
    @Override
    public CheckerDouble isGreaterThan(Byte number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Double} value is greater than or equal to the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerDouble} instance for further validation
     */
    @Override
    public CheckerDouble isGreaterThan(Short number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Double} value is less than the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerDouble} instance for further validation
     */
    @Override
    public CheckerDouble isGreaterThan(Integer number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Double} value is less than or equal to the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerDouble} instance for further validation
     */
    @Override
    public CheckerDouble isGreaterThan(Long number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterThan(Float number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterThan(Double number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterOrEqualTo(Byte number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterOrEqualTo(Short number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterOrEqualTo(Integer number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterOrEqualTo(Long number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterOrEqualTo(Float number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterOrEqualTo(Double number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessThan(Byte number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessThan(Short number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessThan(Integer number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessThan(Long number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessThan(Float number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessThan(Double number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessOrEqualTo(Byte number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessOrEqualTo(Short number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessOrEqualTo(Integer number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessOrEqualTo(Long number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessOrEqualTo(Float number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessOrEqualTo(Double number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

}
