package specialized_checkers.math.numbers.integerTypes;

import static util.Message.*;

import java.util.function.Predicate;

import specialized_checkers.math.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerInteger extends AbstractChecker<Integer, CheckerInteger> implements InterfaceCheckerNumber<CheckerInteger> {

    private static final String INTEGER_STRING = "Integer";
    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_INTEGER_TYPES = "numbers.integer_types";

    public CheckerInteger(Integer object, String name) {
        super(object, name);
    }

    /**
     * @return CheckerInteger
     */
    @Override
    protected CheckerInteger self() {
        return this;
    }

    /**
     * @return CheckerInteger
     */
    public CheckerInteger isEven(){
        return is(n_integer -> (n_integer & 1) == 0, sendMessage(INIT_INTEGER_TYPES, "is_even", INTEGER_STRING));
    }

    /**
     * @return CheckerInteger
     */
    public CheckerInteger isOdd(){
        return is(n_integer -> (n_integer & 1) == 1, sendMessage(INIT_INTEGER_TYPES, "is_odd", INTEGER_STRING));
    }

    /**
     * @return CheckerInteger
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

        return is(isPrime, sendMessage(INIT_INTEGER_TYPES, "is_prime", INTEGER_STRING));
    }

    /**
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isPositive() {
        return is(n_integer -> n_integer > 0, sendMessage(INIT_NUMBERS, "is_positive", INTEGER_STRING));
    }

    /**
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isPositiveOrZero() {
        return is(n_integer -> n_integer >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", INTEGER_STRING));
    }

    /**
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isNegative() {
        return is(n_integer -> n_integer < 0, sendMessage(INIT_NUMBERS, "is_negative", INTEGER_STRING));
    }

    /**
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isNegativeOrZero() {
        return is(n_integer -> n_integer <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", INTEGER_STRING));
    }

    /**
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isZero() {
        return is(n_integer -> n_integer == 0, sendMessage(INIT_NUMBERS, "is_zero", INTEGER_STRING));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterThan(Byte number) {
        return is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterThan(Short number) {
        return is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterThan(Integer number) {
        return is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterThan(Long number) {
        return is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterThan(Float number) {
        return is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterThan(Double number) {
        return is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterOrEqualTo(Byte number) {
        return is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterOrEqualTo(Short number) {
        return is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterOrEqualTo(Integer number) {
        return is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterOrEqualTo(Long number) {
        return is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterOrEqualTo(Float number) {
        return is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isGreaterOrEqualTo(Double number) {
        return is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessThan(Byte number) {
        return is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessThan(Short number) {
        return is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessThan(Integer number) {
        return is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessThan(Long number) {
        return is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessThan(Float number) {
        return is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessThan(Double number) {
        return is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessOrEqualTo(Byte number) {
        return is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessOrEqualTo(Short number) {
        return is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessOrEqualTo(Integer number) {
        return is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessOrEqualTo(Long number) {
        return is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessOrEqualTo(Float number) {
        return is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerInteger
     */
    @Override
    public CheckerInteger isLessOrEqualTo(Double number) {
        return is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
    }

}
