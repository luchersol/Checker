package specialized_checkers.math.numbers.integerTypes;

import static util.Message.*;

import java.util.function.Predicate;

import specialized_checkers.math.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

/**
 * Checker for {@link Integer} instances, providing fluent validation methods for integer numbers.
 * <p>
 * This class allows you to validate and assert properties of {@code Integer} objects in a fluent and readable way.
 * </p>
 */
public class CheckerInteger extends AbstractChecker<Integer, CheckerInteger> implements InterfaceCheckerNumber<CheckerInteger> {

    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_INTEGER_TYPES = "numbers.integer_types";
    private static final String DEFAULT_NAME = "Integer";

    protected CheckerInteger(Integer object, String name) {
        super(object, name);
    }

    /**
     * Creates a new {@code CheckerInteger} for the given {@link Integer} instance with a custom name.
     *
     * @param number the {@code Integer} instance to be checked
     * @param name   the name to identify this checker instance (useful for error messages)
     * @return a new {@code CheckerInteger} for the provided {@code Integer}
     */
    public static CheckerInteger check(Integer number, String name) {
        return new CheckerInteger(number, name);
    }

    /**
     * Creates a new {@code CheckerInteger} for the given {@link Number} instance with a custom name.
     *
     * @param number the {@code Number} instance to be checked (converted to int)
     * @param name   the name to identify this checker instance (useful for error messages)
     * @return a new {@code CheckerInteger} for the provided {@code Number}
     */
    public static CheckerInteger check(Number number, String name) {
        return check(number.intValue(), name);
    }

    /**
     * Creates a new {@code CheckerInteger} for the given {@link Integer} instance with a default name.
     *
     * @param number the {@code Integer} instance to be checked
     * @return a new {@code CheckerInteger} for the provided {@code Integer}
     */
    public static CheckerInteger check(Integer number) {
        return check(number, DEFAULT_NAME);
    }

    /**
     * Creates a new {@code CheckerInteger} for the given {@link Number} instance with a default name.
     *
     * @param number the {@code Number} instance to be checked (converted to int)
     * @return a new {@code CheckerInteger} for the provided {@code Number}
     */
    public static CheckerInteger check(Number number) {
        return check(number.intValue());
    }

    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerInteger} instance
     */
    @Override
    protected CheckerInteger self() {
        return this;
    }

    /**
     * Asserts that the {@code Integer} value is even (divisible by two).
     *
     * @return this {@code CheckerInteger} instance for further validation
     */
    public CheckerInteger isEven(){
        return is(n_integer -> (n_integer & 1) == 0, sendMessage(INIT_INTEGER_TYPES, "is_even", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Integer} value is odd (not divisible by two).
     *
     * @return this {@code CheckerInteger} instance for further validation
     */
    public CheckerInteger isOdd(){
        return is(n_integer -> (n_integer & 1) == 1, sendMessage(INIT_INTEGER_TYPES, "is_odd", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Integer} value is prime.
     *
     * @return this {@code CheckerInteger} instance for further validation
     */
    public CheckerInteger isPrime() {
        Predicate<Integer> isPrime = n_integer -> {
            if (n_integer <= 1) return false;
            if (n_integer <= 3) return true;
            if (n_integer % 2 == 0 || n_integer % 3 == 0) return false;

            for (int i = 5; i * i <= n_integer; i += 6) {
                if (n_integer % i == 0 || n_integer % (i + 2) == 0) return false;
            }

            return true;
        };

        return is(isPrime, sendMessage(INIT_INTEGER_TYPES, "is_prime", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Integer} value is positive (greater than zero).
     *
     * @return this {@code CheckerInteger} instance for further validation
     */
    @Override
    public CheckerInteger isPositive() {
        return is(n_integer -> n_integer > 0, sendMessage(INIT_NUMBERS, "is_positive", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Integer} value is positive or zero (greater than or equal to zero).
     *
     * @return this {@code CheckerInteger} instance for further validation
     */
    @Override
    public CheckerInteger isPositiveOrZero() {
        return is(n_integer -> n_integer >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Integer} value is negative (less than zero).
     *
     * @return this {@code CheckerInteger} instance for further validation
     */
    @Override
    public CheckerInteger isNegative() {
        return is(n_integer -> n_integer < 0, sendMessage(INIT_NUMBERS, "is_negative", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Integer} value is negative or zero (less than or equal to zero).
     *
     * @return this {@code CheckerInteger} instance for further validation
     */
    @Override
    public CheckerInteger isNegativeOrZero() {
        return is(n_integer -> n_integer <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Integer} value is zero.
     *
     * @return this {@code CheckerInteger} instance for further validation
     */
    @Override
    public CheckerInteger isZero() {
        return is(n_integer -> n_integer == 0, sendMessage(INIT_NUMBERS, "is_zero", DEFAULT_NAME));
    }

    /**
     * Asserts that the {@code Integer} value is greater than the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerInteger} instance for further validation
     */
    @Override
    public CheckerInteger isGreaterThan(Byte number) {
        return is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Integer} value is greater than or equal to the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerInteger} instance for further validation
     */
    @Override
    public CheckerInteger isGreaterThan(Short number) {
        return is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Integer} value is less than the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerInteger} instance for further validation
     */
    @Override
    public CheckerInteger isGreaterThan(Integer number) {
        return is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * Asserts that the {@code Integer} value is less than or equal to the specified value.
     *
     * @param number the value to compare against
     * @return this {@code CheckerInteger} instance for further validation
     */
    @Override
    public CheckerInteger isGreaterThan(Long number) {
        return is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterThan(Float number) {
        return is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterThan(Double number) {
        return is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterOrEqualTo(Byte number) {
        return is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterOrEqualTo(Short number) {
        return is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterOrEqualTo(Integer number) {
        return is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterOrEqualTo(Long number) {
        return is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterOrEqualTo(Float number) {
        return is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterOrEqualTo(Double number) {
        return is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessThan(Byte number) {
        return is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessThan(Short number) {
        return is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessThan(Integer number) {
        return is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessThan(Long number) {
        return is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessThan(Float number) {
        return is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessThan(Double number) {
        return is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessOrEqualTo(Byte number) {
        return is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessOrEqualTo(Short number) {
        return is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessOrEqualTo(Integer number) {
        return is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessOrEqualTo(Long number) {
        return is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessOrEqualTo(Float number) {
        return is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessOrEqualTo(Double number) {
        return is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

}
