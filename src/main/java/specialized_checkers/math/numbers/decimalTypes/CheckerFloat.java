package specialized_checkers.math.numbers.decimalTypes;

import static util.Message.*;

import specialized_checkers.math.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

/**
 * Checker for {@link Float} instances, providing fluent validation methods for single-precision floating-point numbers.
 * <p>
 * This class allows you to validate and assert properties of {@code Float} objects in a fluent and readable way.
 * </p>
 */
public class CheckerFloat extends AbstractChecker<Float, CheckerFloat> implements InterfaceCheckerNumber<CheckerFloat>{

    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_DECIMAL_TYPES = "numbers.decimal_types";
    private static final String DEFAULT_NAME = "Float";

    protected CheckerFloat(Float object, String name) {
        super(object, name);
    }

    /**
     * Creates a new {@code CheckerFloat} for the given {@link Float} instance with a custom name.
     *
     * @param number the {@code Float} instance to be checked
     * @param name   the name to identify this checker instance (useful for error messages)
     * @return a new {@code CheckerFloat} for the provided {@code Float}
     */
    public static CheckerFloat check(Float number, String name) {
        return new CheckerFloat(number, name);
    }

    /**
     * Creates a new {@code CheckerFloat} for the given {@link Number} instance with a custom name.
     *
     * @param number the {@code Number} instance to be checked (converted to float)
     * @param name   the name to identify this checker instance (useful for error messages)
     * @return a new {@code CheckerFloat} for the provided {@code Number}
     */
    public static CheckerFloat check(Number number, String name) {
        return new CheckerFloat(number.floatValue(), name);
    }

    /**
     * Creates a new {@code CheckerFloat} for the given {@link Float} instance with a default name.
     *
     * @param number the {@code Float} instance to be checked
     * @return a new {@code CheckerFloat} for the provided {@code Float}
     */
    public static CheckerFloat check(Float number) {
        return check(number, DEFAULT_NAME);
    }

    /**
     * Creates a new {@code CheckerFloat} for the given {@link Number} instance with a default name.
     *
     * @param number the {@code Number} instance to be checked (converted to float)
     * @return a new {@code CheckerFloat} for the provided {@code Number}
     */
    public static CheckerFloat check(Number number) {
        return check(number.floatValue());
    }

    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerFloat} instance
     */
    @Override
    protected CheckerFloat self() {
        return this;
    }

    /**
     * Asserts that the {@code Float} value is NaN (not a number).
     *
     * @return this {@code CheckerFloat} instance for further validation
     */
    public CheckerFloat isNaN(){
        return is(n_float -> n_float.isNaN(), sendMessage(INIT_DECIMAL_TYPES, "is_nan", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Float} value is infinite.
     *
     * @return this {@code CheckerFloat} instance for further validation
     */
    public CheckerFloat isInfinite(){
        return is(n_float -> n_float.isInfinite(), sendMessage(INIT_DECIMAL_TYPES, "is_infinite", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Float} value is positive (greater than zero).
     *
     * @return this {@code CheckerFloat} instance for further validation
     */
    @Override
    public CheckerFloat isPositive() {
        return is(n_float -> n_float > 0, sendMessage(INIT_NUMBERS, "is_positive", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Float} value is positive or zero (greater than or equal to zero).
     *
     * @return this {@code CheckerFloat} instance for further validation
     */
    @Override
    public CheckerFloat isPositiveOrZero() {
        return is(n_float -> n_float >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Float} value is negative (less than zero).
     *
     * @return this {@code CheckerFloat} instance for further validation
     */
    @Override
    public CheckerFloat isNegative() {
        return is(n_float -> n_float < 0, sendMessage(INIT_NUMBERS, "is_negative", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Float} value is negative or zero (less than or equal to zero).
     *
     * @return this {@code CheckerFloat} instance for further validation
     */
    @Override
    public CheckerFloat isNegativeOrZero() {
        return is(n_float -> n_float <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Float} value is zero.
     *
     * @return this {@code CheckerFloat} instance for further validation
     */
    @Override
    public CheckerFloat isZero() {
        return is(n_float -> n_float == 0, sendMessage(INIT_NUMBERS, "is_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Float} value is greater than the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerFloat} instance for further validation
     */
    @Override
    public CheckerFloat isGreaterThan(Byte number) {
        return is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Float} value is greater than or equal to the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerFloat} instance for further validation
     */
    @Override
    public CheckerFloat isGreaterThan(Short number) {
        return is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Float} value is less than the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerFloat} instance for further validation
     */
    @Override
    public CheckerFloat isGreaterThan(Integer number) {
        return is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Float} value is less than or equal to the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerFloat} instance for further validation
     */
    @Override
    public CheckerFloat isGreaterThan(Long number) {
        return is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isGreaterThan(Float number) {
        return is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isGreaterThan(Double number) {
        return is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isGreaterOrEqualTo(Byte number) {
        return is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isGreaterOrEqualTo(Short number) {
        return is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isGreaterOrEqualTo(Integer number) {
        return is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isGreaterOrEqualTo(Long number) {
        return is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isGreaterOrEqualTo(Float number) {
        return is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isGreaterOrEqualTo(Double number) {
        return is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isLessThan(Byte number) {
        return is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isLessThan(Short number) {
        return is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isLessThan(Integer number) {
        return is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isLessThan(Long number) {
        return is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isLessThan(Float number) {
        return is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isLessThan(Double number) {
        return is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isLessOrEqualTo(Byte number) {
        return is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isLessOrEqualTo(Short number) {
        return is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isLessOrEqualTo(Integer number) {
        return is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isLessOrEqualTo(Long number) {
        return is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isLessOrEqualTo(Float number) {
        return is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerFloat
     */
    @Override
    public CheckerFloat isLessOrEqualTo(Double number) {
        return is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

}
