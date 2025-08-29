package specialized_checkers.math.numbers.integerTypes;

import static util.Message.*;

import java.util.function.Predicate;

import specialized_checkers.math.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerLong extends AbstractChecker<Long, CheckerLong> implements InterfaceCheckerNumber<CheckerLong> {

    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_INTEGER_TYPES = "numbers.integer_types";
    private static final String DEFAULT_NAME = "Long";

    protected CheckerLong(Long object, String name) {
        super(object, name);
    }

    /**
     * @param number
     * @param name
     * @return CheckerLong
     */
    public static CheckerLong check(Long number, String name) {
        return new CheckerLong(number, name);
    }

    /**
     * @param number
     * @param name
     * @return CheckerLong
     */
    public static CheckerLong check(Number number, String name) {
        return check(number.longValue(), name);
    }

    /**
     * @param number
     * @return CheckerLong
     */
    public static CheckerLong check(Long number) {
        return check(number, DEFAULT_NAME);
    }

    /**
     * @param number
     * @return CheckerLong
     */
    public static CheckerLong check(Number number) {
        return check(number.longValue());
    }


    /**
     * @return CheckerLong
     */
    @Override
    protected CheckerLong self() {
        return this;
    }

    /**
     * @return CheckerLong
     */
    public CheckerLong isEven(){
        return is(n_long -> n_long % 2 == 0, sendMessage(INIT_INTEGER_TYPES, "is_pair", DEFAULT_NAME));
    }

    /**
     * @return CheckerLong
     */
    public CheckerLong isOdd(){
        return is(n_long -> n_long % 2 != 0, sendMessage(INIT_INTEGER_TYPES, "is_even", DEFAULT_NAME));
    }

    /**
     * @return CheckerLong
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
     * @return CheckerLong
     */
    @Override
    public CheckerLong isPositive() {
        return is(n_long -> n_long > 0, sendMessage(INIT_NUMBERS, "is_positive", DEFAULT_NAME));
    }

    /**
     * @return CheckerLong
     */
    @Override
    public CheckerLong isPositiveOrZero() {
        return is(n_long -> n_long >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", DEFAULT_NAME));
    }

    /**
     * @return CheckerLong
     */
    @Override
    public CheckerLong isNegative() {
        return is(n_long -> n_long < 0, sendMessage(INIT_NUMBERS, "is_negative", DEFAULT_NAME));
    }

    /**
     * @return CheckerLong
     */
    @Override
    public CheckerLong isNegativeOrZero() {
        return is(n_long -> n_long <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", DEFAULT_NAME));
    }

    /**
     * @return CheckerLong
     */
    @Override
    public CheckerLong isZero() {
        return is(n_long -> n_long == 0, sendMessage(INIT_NUMBERS, "is_zero", DEFAULT_NAME));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isGreaterThan(Byte number) {
        return is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isGreaterThan(Short number) {
        return is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isGreaterThan(Integer number) {
        return is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isGreaterThan(Long number) {
        return is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isGreaterThan(Float number) {
        return is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isGreaterThan(Double number) {
        return is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isGreaterOrEqualTo(Byte number) {
        return is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isGreaterOrEqualTo(Short number) {
        return is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isGreaterOrEqualTo(Integer number) {
        return is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isGreaterOrEqualTo(Long number) {
        return is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isGreaterOrEqualTo(Float number) {
        return is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isGreaterOrEqualTo(Double number) {
        return is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isLessThan(Byte number) {
        return is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isLessThan(Short number) {
        return is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isLessThan(Integer number) {
        return is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isLessThan(Long number) {
        return is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isLessThan(Float number) {
        return is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isLessThan(Double number) {
        return is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isLessOrEqualTo(Byte number) {
        return is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isLessOrEqualTo(Short number) {
        return is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isLessOrEqualTo(Integer number) {
        return is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isLessOrEqualTo(Long number) {
        return is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isLessOrEqualTo(Float number) {
        return is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerLong
     */
    @Override
    public CheckerLong isLessOrEqualTo(Double number) {
        return is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

}
