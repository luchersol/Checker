package specialized_checkers.numbers.integerTypes;

import static util.Message.*;

import java.util.function.Predicate;

import specialized_checkers.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerLong extends AbstractChecker<Long, CheckerLong> implements InterfaceCheckerNumber<CheckerLong> {

    private static final String LONG_STRING = "Long";
    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_INTEGER_TYPES = "numbers.integer_types";


    public CheckerLong(Long object, String name) {
        super(object, name);
    }

    @Override
    protected CheckerLong self() {
        return this;
    }

    public CheckerLong isEven(){
        is(n_long -> n_long % 2 == 0, sendMessage(INIT_INTEGER_TYPES, "is_pair", LONG_STRING));
        return this;
    }

    public CheckerLong isOdd(){
        is(n_long -> n_long % 2 != 0, sendMessage(INIT_INTEGER_TYPES, "is_even", LONG_STRING));
        return this;
    }

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
        
        is(isPrime, sendMessage(INIT_INTEGER_TYPES, "is_prime", LONG_STRING));
        return this;
    }

    @Override
    public CheckerLong isPositive() {
        is(n_long -> n_long > 0, sendMessage(INIT_NUMBERS, "is_positive", LONG_STRING));
        return this;
    }

    @Override
    public CheckerLong isPositiveOrZero() {
        is(n_long -> n_long >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", LONG_STRING));
        return this;
    }

    @Override
    public CheckerLong isNegative() {
        is(n_long -> n_long < 0, sendMessage(INIT_NUMBERS, "is_negative", LONG_STRING));
        return this;
    }

    @Override
    public CheckerLong isNegativeOrZero() {
        is(n_long -> n_long <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", LONG_STRING));
        return this;
    }

    @Override
    public CheckerLong isZero() {
        is(n_long -> n_long == 0, sendMessage(INIT_NUMBERS, "is_zero", LONG_STRING));
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Byte number) {
        is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Short number) {
        is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Integer number) {
        is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Long number) {
        is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Float number) {
        is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Double number) {
        is(n_long -> n_long > number, sendMessage(INIT_NUMBERS, "is_greather_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Byte number) {
        is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Short number) {
        is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Integer number) {
        is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Long number) {
        is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Float number) {
        is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Double number) {
        is(n_long -> n_long >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessThan(Byte number) {
        is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessThan(Short number) {
        is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessThan(Integer number) {
        is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessThan(Long number) {
        is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessThan(Float number) {
        is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessThan(Double number) {
        is(n_long -> n_long < number, sendMessage(INIT_NUMBERS, "is_less_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Byte number) {
        is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Short number) {
        is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Integer number) {
        is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Long number) {
        is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Float number) {
        is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Double number) {
        is(n_long -> n_long <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", LONG_STRING, number));
        return this;
    }

}
