package com.luchersol.core.specialized_checkers.math.numbers.integerTypes;

import static com.luchersol.core.util.MessageService.*;

import java.util.function.Predicate;

import com.luchersol.core.specialized_checkers.math.numbers.InterfaceCheckerNumber;
import com.luchersol.core.util.AbstractChecker;

/**
 * A specialized checker for {@link Long} instances, providing fluent API methods
 * to assert various properties, such as positivity, negativity, zero value, even or odd
 * and numeric comparisons.
 *
 * <p>Typical usage:</p>
 * <pre>{@code
 * CheckerLong checker = CheckerLong.check(myLong)
 *     .isPositive()
 *     .isLessThan(100.0)
 *     .isOdd();
 * }</pre>
 *
 * <p>This class supports chaining multiple assertions in a fluent style and integrates
 * with {@link InterfaceCheckerNumber} for numeric-specific validations.</p>
 *
 * @see java.lang.Long
 * @see com.luchersol.core.specialized_checkers.math.numbers.InterfaceCheckerNumber
 */
public class CheckerLong extends AbstractChecker<Long, CheckerLong> implements InterfaceCheckerNumber<CheckerLong> {

    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_INTEGER_TYPES = "numbers.integer_types";
    private static final String DEFAULT_NAME = "Long";


    /**
     * Constructs a new {@code CheckerLong} with the specified nLong and name.
     *
     * @param nLong the {@link Long} to be wrapped and checked
     * @param name the name identifying this checker function
     */
    protected CheckerLong(Long nLong, String name) {
        super(nLong, name);
    }

    /**
     * Creates a new {@code CheckerLong} for the given {@link Long} instance with a custom name.
     *
     * @param number the {@code Long} instance to be checked
     * @param name   the name to identify this checker instance (useful for error messages)
     * @return a new {@code CheckerLong} for the provided {@code Long}
     */
    public static CheckerLong check(Long number, String name) {
        return new CheckerLong(number, name);
    }

    /**
     * Creates a new {@code CheckerLong} for the given {@link Number} instance with a custom name.
     *
     * @param number the {@code Number} instance to be checked (converted to long)
     * @param name   the name to identify this checker instance (useful for error messages)
     * @return a new {@code CheckerLong} for the provided {@code Number}
     */
    public static CheckerLong check(Number number, String name) {
        return check(number.longValue(), name);
    }

    /**
     * Creates a new {@code CheckerLong} for the given {@link Long} instance with a default name.
     *
     * @param number the {@code Long} instance to be checked
     * @return a new {@code CheckerLong} for the provided {@code Long}
     */
    public static CheckerLong check(Long number) {
        return check(number, DEFAULT_NAME);
    }

    /**
     * Creates a new {@code CheckerLong} for the given {@link Number} instance with a default name.
     *
     * @param number the {@code Number} instance to be checked (converted to long)
     * @return a new {@code CheckerLong} for the provided {@code Number}
     */
    public static CheckerLong check(Number number) {
        return check(number.longValue());
    }

    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerLong} instance
     */
    @Override
    protected CheckerLong self() {
        return this;
    }

    /**
     * Asserts that the {@code Long} value is even (divisible by two).
     *
     * @return this {@code CheckerLong} instance for further validation
     */
    public CheckerLong isEven(){
        return is(n_long -> (n_long & 1) == 0, sendMessage(INIT_INTEGER_TYPES, "is_even", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Long} value is odd (not divisible by two).
     *
     * @return this {@code CheckerLong} instance for further validation
     */
    public CheckerLong isOdd(){
        return is(n_long -> (n_long & 1) == 1, sendMessage(INIT_INTEGER_TYPES, "is_odd", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Long} value is prime.
     *
     * @return this {@code CheckerLong} instance for further validation
     */
    public CheckerLong isPrime() {
        Predicate<Long> isPrime = n_long -> {
            if (n_long <= 1) return false;
            if (n_long <= 3) return true;
            if (n_long % 2 == 0 || n_long % 3 == 0) return false;

            for (int i = 5; i * i <= n_long; i += 6) {
                if (n_long % i == 0 || n_long % (i + 2) == 0) return false;
            }

            return true;
        };

        return is(isPrime, sendMessage(INIT_INTEGER_TYPES, "is_prime", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Long} value is positive (greater than zero).
     *
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isPositive() {
        return is(n_long -> n_long > 0, sendMessage(INIT_NUMBERS, "is_positive", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Long} value is positive or zero (greater than or equal to zero).
     *
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isPositiveOrZero() {
        return is(n_long -> n_long >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Long} value is negative (less than zero).
     *
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isNegative() {
        return is(n_long -> n_long < 0, sendMessage(INIT_NUMBERS, "is_negative", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Long} value is negative or zero (less than or equal to zero).
     *
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isNegativeOrZero() {
        return is(n_long -> n_long <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Long} value is zero.
     *
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isZero() {
        return is(n_long -> n_long == 0, sendMessage(INIT_NUMBERS, "is_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Long} value is greater than the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isGreaterThan(Byte number) {
        return is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is greater than or equal to the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isGreaterThan(Short number) {
        return is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is less than the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isGreaterThan(Integer number) {
        return is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is less than or equal to the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isGreaterThan(Long number) {
        return is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code Long} value is greater than the specified {@code Float} value.
     *
     * @param number the {@code Float} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isGreaterThan(Float number) {
        return is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code Long} value is greater than the specified {@code Double} value.
     *
     * @param number the {@code Double} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isGreaterThan(Double number) {
        return is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is greater than or equal to the specified {@code Byte} value.
     *
     * @param number the {@code Byte} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isGreaterOrEqualTo(Byte number) {
        return is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is greater than or equal to the specified {@code Short} value.
     *
     * @param number the {@code Short} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isGreaterOrEqualTo(Short number) {
        return is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is greater than or equal to the specified {@code Integer} value.
     *
     * @param number the {@code Integer} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isGreaterOrEqualTo(Integer number) {
        return is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is greater than or equal to the specified {@code Long} value.
     *
     * @param number the {@code Long} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isGreaterOrEqualTo(Long number) {
        return is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code Long} value is greater than or equal to the specified {@code Float} value.
     *
     * @param number the {@code Float} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    public CheckerLong isGreaterOrEqualTo(Float number) {
        return is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code Long} value is greater than or equal to the specified {@code Double} value.
     *
     * @param number the {@code Double} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    public CheckerLong isGreaterOrEqualTo(Double number) {
        return is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is less than the specified {@code Byte} value.
     *
     * @param number the {@code Byte} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isLessThan(Byte number) {
        return is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is less than the specified {@code Short} value.
     *
     * @param number the {@code Short} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isLessThan(Short number) {
        return is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is less than the specified {@code Integer} value.
     *
     * @param number the {@code Integer} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isLessThan(Integer number) {
        return is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is less than the specified {@code Long} value.
     *
     * @param number the {@code Long} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isLessThan(Long number) {
        return is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is less than the specified {@code Float} value.
     *
     * @param number the {@code Float} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isLessThan(Float number) {
        return is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is less than the specified {@code Double} value.
     *
     * @param number the {@code Double} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isLessThan(Double number) {
        return is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is less than or equal to the specified {@code Byte} value.
     *
     * @param number the {@code Byte} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isLessOrEqualTo(Byte number) {
        return is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is less than or equal to the specified {@code Short} value.
     *
     * @param number the {@code Short} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isLessOrEqualTo(Short number) {
        return is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is less than or equal to the specified {@code Integer} value.
     *
     * @param number the {@code Integer} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isLessOrEqualTo(Integer number) {
        return is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is less than or equal to the specified {@code Long} value.
     *
     * @param number the {@code Long} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isLessOrEqualTo(Long number) {
        return is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Long} value is less than or equal to the specified {@code Float} value.
     *
     * @param number the {@code Float} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isLessOrEqualTo(Float number) {
        return is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }


    /**
     * Asserts that the {@code Long} value is less than or equal to the specified {@code Double} value.
     *
     * @param number the {@code Double} value to compare against
     * @return this {@code CheckerLong} instance for further validation
     */
    @Override
    public CheckerLong isLessOrEqualTo(Double number) {
        return is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

}
