package com.luchersol.core.specialized_checkers.math.numbers.bigTypes;

import static com.luchersol.core.util.MessageService.*;

import java.math.BigDecimal;

import com.luchersol.core.specialized_checkers.math.numbers.InterfaceCheckerNumber;
import com.luchersol.core.util.AbstractChecker;

/**
 * A specialized checker for {@link BigDecimal} instances, providing fluent API methods
 * to assert various behaviors and properties of arbitrary-precision decimal numbers,
 * such as positivity, negativity, zero value, and comparisons against other numeric values.
 *
 * <p>Typical usage:</p>
 * <pre>{@code
 * CheckerBigDecimal checker = CheckerBigDecimal.check(myBigDecimal)
 *     .isPositive()
 *     .isGreaterThan(10)
 *     .isLessOrEqualTo(100);
 * }</pre>
 *
 * <p>This class supports chaining multiple assertions in a fluent style and integrates
 * with {@link InterfaceCheckerNumber} for numeric-specific validations.</p>
 *
 * @see java.math.BigDecimal
 * @see com.luchersol.core.specialized_checkers.math.numbers.InterfaceCheckerNumber
 */
public class CheckerBigDecimal extends AbstractChecker<BigDecimal, CheckerBigDecimal> implements InterfaceCheckerNumber<CheckerBigDecimal> {

    private static final String INIT_NUMBERS = "numbers";
    private static final String DEFAULT_NAME = "Big Decimal";


    /**
     * Constructs a new {@code CheckerBigDecimal} with the specified bigDecimal and name.
     *
     * @param bigDecimal the {@link BigDecimal} to be wrapped and checked
     * @param name the name identifying this checker function
     */
    protected CheckerBigDecimal(BigDecimal bigDecimal, String name) {
        super(bigDecimal, name);
    }

    /**
     * Creates a new {@code CheckerBigDecimal} for the given {@link BigDecimal} instance with a custom name.
     *
     * @param number the {@code BigDecimal} instance to be checked
     * @param name   the name to identify this checker instance (useful for error messages)
     * @return a new {@code CheckerBigDecimal} for the provided {@code BigDecimal}
     */
    public static CheckerBigDecimal check(BigDecimal number, String name) {
        return new CheckerBigDecimal(number, name);
    }

    /**
     * Creates a new {@code CheckerBigDecimal} for the given {@link BigDecimal} instance with a default name.
     *
     * @param number the {@code BigDecimal} instance to be checked
     * @return a new {@code CheckerBigDecimal} for the provided {@code BigDecimal}
     */
    public static CheckerBigDecimal check(BigDecimal number) {
        return check(number, DEFAULT_NAME);
    }

    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerBigDecimal} instance
     */
    @Override
    protected CheckerBigDecimal self() {
        return this;
    }

    /**
     * Asserts that the {@code BigDecimal} is positive (greater than zero).
     *
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isPositive() {
        return is(n_big_decimal -> n_big_decimal.signum() > 0, sendMessage(INIT_NUMBERS, "is_positive", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code BigDecimal} is positive or zero (greater than or equal to zero).
     *
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isPositiveOrZero() {
        return is(n_big_decimal -> n_big_decimal.signum() >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code BigDecimal} is negative (less than zero).
     *
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isNegative() {
        return is(n_big_decimal -> n_big_decimal.signum() < 0, sendMessage(INIT_NUMBERS, "is_negative", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code BigDecimal} is negative or zero (less than or equal to zero).
     *
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isNegativeOrZero() {
        return is(n_big_decimal -> n_big_decimal.signum() <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code BigDecimal} is zero.
     *
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isZero() {
        return is(n_big_decimal -> n_big_decimal.signum() == 0, sendMessage(INIT_NUMBERS, "is_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code BigDecimal} is greater than the specified {@code Byte} value.
     *
     * @param number the {@code Byte} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isGreaterThan(Byte number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code BigDecimal} is greater than the specified {@code Short} value.
     *
     * @param number the {@code Short} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isGreaterThan(Short number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code BigDecimal} is greater than the specified {@code Integer} value.
     *
     * @param number the {@code Integer} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isGreaterThan(Integer number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is greater than the specified {@code Long} value.
     *
     * @param number the {@code Long} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isGreaterThan(Long number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is greater than the specified {@code Float} value.
     *
     * @param number the {@code Float} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isGreaterThan(Float number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is greater than the specified {@code Double} value.
     *
     * @param number the {@code Double} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isGreaterThan(Double number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is greater than or equal to the specified {@code Byte} value.
     *
     * @param number the {@code Byte} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Byte number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is greater than or equal to the specified {@code Short} value.
     *
     * @param number the {@code Short} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Short number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is greater than or equal to the specified {@code Integer} value.
     *
     * @param number the {@code Integer} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Integer number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is greater than or equal to the specified {@code Long} value.
     *
     * @param number the {@code Long} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Long number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is greater than or equal to the specified {@code Float} value.
     *
     * @param number the {@code Float} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Float number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is greater than or equal to the specified {@code Double} value.
     *
     * @param number the {@code Double} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Double number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is less than the specified {@code Byte} value.
     *
     * @param number the {@code Byte} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isLessThan(Byte number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is less than the specified {@code Short} value.
     *
     * @param number the {@code Short} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isLessThan(Short number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is less than the specified {@code Integer} value.
     *
     * @param number the {@code Integer} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isLessThan(Integer number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is less than the specified {@code Long} value.
     *
     * @param number the {@code Long} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isLessThan(Long number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is less than the specified {@code Float} value.
     *
     * @param number the {@code Float} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isLessThan(Float number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is less than the specified {@code Double} value.
     *
     * @param number the {@code Double} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isLessThan(Double number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is less than or equal to the specified {@code Byte} value.
     *
     * @param number the {@code Byte} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isLessOrEqualTo(Byte number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is less than or equal to the specified {@code Short} value.
     *
     * @param number the {@code Short} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isLessOrEqualTo(Short number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is less than or equal to the specified {@code Integer} value.
     *
     * @param number the {@code Integer} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isLessOrEqualTo(Integer number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is less than or equal to the specified {@code Long} value.
     *
     * @param number the {@code Long} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isLessOrEqualTo(Long number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is less than or equal to the specified {@code Float} value.
     *
     * @param number the {@code Float} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isLessOrEqualTo(Float number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigDecimal} is less than or equal to the specified {@code Double} value.
     *
     * @param number the {@code Double} value to compare against
     * @return this {@code CheckerBigDecimal} instance for further validation
     */
    @Override
    public CheckerBigDecimal isLessOrEqualTo(Double number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

}
