package com.luchersol.core.specialized_checkers.math.numbers.bigTypes;

import static com.luchersol.core.util.MessageService.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.luchersol.core.specialized_checkers.math.numbers.InterfaceCheckerNumber;
import com.luchersol.core.util.AbstractChecker;

/**
 * A specialized checker for {@link BigInteger} instances, providing fluent API methods
 * to assert various behaviors and properties of arbitrary-precision integer numbers,
 * such as evenness, oddness, divisibility, primality, positivity, negativity, zero value,
 * and comparisons against other numeric values.
 *
 * <p>Typical usage:</p>
 * <pre>{@code
 * CheckerBigInteger checker = CheckerBigInteger.check(myBigInteger)
 *     .isPositive()
 *     .isEven()
 *     .isGreaterThan(100);
 * }</pre>
 *
 * <p>This class supports chaining multiple assertions in a fluent style and integrates
 * with {@link InterfaceCheckerNumber} for numeric-specific validations.</p>
 *
 * @see java.math.BigInteger
 * @see com.luchersol.core.specialized_checkers.math.numbers.InterfaceCheckerNumber
 */
public class CheckerBigInteger extends AbstractChecker<BigInteger, CheckerBigInteger> implements InterfaceCheckerNumber<CheckerBigInteger> {

    private static final String INIT_NUMBERS = "numbers";
    private static final String DEFAULT_NAME = "Big Integer";


    /**
     * Constructs a new {@code CheckerBigInteger} with the specified bigInteger and name.
     *
     * @param bigInteger the {@link BigInteger} to be wrapped and checked
     * @param name the name identifying this checker function
     */
    protected CheckerBigInteger(BigInteger bigInteger, String name) {
        super(bigInteger, name);
    }

    /**
     * Creates a new {@code CheckerBigInteger} for the given {@link BigInteger} instance with a custom name.
     *
     * @param number the {@code BigInteger} instance to be checked
     * @param name   the name to identify this checker instance (useful for error messages)
     * @return a new {@code CheckerBigInteger} for the provided {@code BigInteger}
     */
    public static CheckerBigInteger check(BigInteger number, String name) {
        return new CheckerBigInteger(number, name);
    }

    /**
     * Creates a new {@code CheckerBigInteger} for the given {@link BigInteger} instance with a default name.
     *
     * @param number the {@code BigInteger} instance to be checked
     * @return a new {@code CheckerBigInteger} for the provided {@code BigInteger}
     */
    public static CheckerBigInteger check(BigInteger number) {
        return check(number, DEFAULT_NAME);
    }

    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerBigInteger} instance
     */
    @Override
    protected CheckerBigInteger self() {
        return this;
    }

    /**
     * Asserts that the {@code BigInteger} is even (divisible by two).
     *
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    public CheckerBigInteger isEven(){
        return is(n_big_integer -> n_big_integer.mod(BigInteger.TWO).equals(BigInteger.ZERO), sendMessage(INIT_NUMBERS, "is_even", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code BigInteger} is odd (not divisible by two).
     *
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    public CheckerBigInteger isOdd(){
        return is(n_big_integer -> !n_big_integer.mod(BigInteger.TWO).equals(BigInteger.ZERO), sendMessage(INIT_NUMBERS, "is_odd", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code BigInteger} is divisible by the specified divisor.
     *
     * @param divisor the value to divide by
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    public CheckerBigInteger isDivisibleBy(BigInteger divisor){
        return is(n_big_integer -> n_big_integer.mod(divisor).equals(BigInteger.ZERO), sendMessage(INIT_NUMBERS, "is_divisible_by", DEFAULT_NAME, divisor));
    }

    /**
     * Asserts that the {@code BigInteger} is a power of two.
     *
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    public CheckerBigInteger isPowerOfTwo(){
        return is(n_big_integer -> n_big_integer.signum() > 0 && n_big_integer.bitCount() == 1, sendMessage(INIT_NUMBERS, "is_power_of_two", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code BigInteger} is probably prime, with the specified certainty.
     *
     * @param certainty a measure of the uncertainty that the caller is willing to tolerate
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    public CheckerBigInteger isProbablePrime(int certainty) {
        return is(n_big_integer -> n_big_integer.isProbablePrime(certainty), sendMessage(INIT_NUMBERS, "is_probable_prime", DEFAULT_NAME, certainty));
    }

    /**
     * Asserts that the {@code BigInteger} is positive (greater than zero).
     *
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isPositive() {
        return is(n_big_integer -> n_big_integer.signum() > 0, sendMessage(INIT_NUMBERS, "is_positive", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code BigInteger} is positive or zero (greater than or equal to zero).
     *
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isPositiveOrZero() {
        return is(n_big_integer -> n_big_integer.signum() >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code BigInteger} is negative (less than zero).
     *
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isNegative() {
        return is(n_big_integer -> n_big_integer.signum() < 0, sendMessage(INIT_NUMBERS, "is_negative", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code BigInteger} is negative or zero (less than or equal to zero).
     *
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isNegativeOrZero() {
        return is(n_big_integer -> n_big_integer.signum() <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code BigInteger} is zero.
     *
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isZero() {
        return is(n_big_integer -> n_big_integer.signum() == 0, sendMessage(INIT_NUMBERS, "is_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code BigInteger} is greater than the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isGreaterThan(Byte number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code BigInteger} is greater than or equal to the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isGreaterThan(Short number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code BigInteger} is less than the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isGreaterThan(Integer number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code BigInteger} is less than or equal to the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isGreaterThan(Long number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is greater than the specified {@code Float} value.
     *
     * @param number the {@code Float} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isGreaterThan(Float number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is greater than the specified {@code Double} value.
     *
     * @param number the {@code Double} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isGreaterThan(Double number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is greater than or equal to the specified {@code Byte} value.
     *
     * @param number the {@code Byte} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Byte number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is greater than or equal to the specified {@code Short} value.
     *
     * @param number the {@code Short} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Short number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is greater than or equal to the specified {@code Integer} value.
     *
     * @param number the {@code Integer} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Integer number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is greater than or equal to the specified {@code Long} value.
     *
     * @param number the {@code Long} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Long number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is greater than or equal to the specified {@code Float} value.
     *
     * @param number the {@code Float} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Float number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is greater than or equal to the specified {@code Double} value.
     *
     * @param number the {@code Double} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Double number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is less than the specified {@code Byte} value.
     *
     * @param number the {@code Byte} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isLessThan(Byte number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is less than the specified {@code Short} value.
     *
     * @param number the {@code Short} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isLessThan(Short number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is less than the specified {@code Integer} value.
     *
     * @param number the {@code Integer} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isLessThan(Integer number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is less than the specified {@code Long} value.
     *
     * @param number the {@code Long} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isLessThan(Long number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is less than the specified {@code Float} value.
     *
     * @param number the {@code Float} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isLessThan(Float number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is less than the specified {@code Double} value.
     *
     * @param number the {@code Double} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isLessThan(Double number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is less than or equal to the specified {@code Byte} value.
     *
     * @param number the {@code Byte} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Byte number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is less than or equal to the specified {@code Short} value.
     *
     * @param number the {@code Short} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Short number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is less than or equal to the specified {@code Integer} value.
     *
     * @param number the {@code Integer} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Integer number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is less than or equal to the specified {@code Long} value.
     *
     * @param number the {@code Long} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Long number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is less than or equal to the specified {@code Float} value.
     *
     * @param number the {@code Float} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Float number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code BigInteger} is less than or equal to the specified {@code Double} value.
     *
     * @param number the {@code Double} value to compare against
     * @return this {@code CheckerBigInteger} instance for further validation
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Double number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

}
