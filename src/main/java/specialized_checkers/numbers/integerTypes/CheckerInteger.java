package specialized_checkers.numbers.integerTypes;

import static util.Message.sendMessage;

import java.util.function.Predicate;

import specialized_checkers.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerInteger extends AbstractChecker<Integer> implements InterfaceCheckerNumber<CheckerInteger> {

    private static final String INTEGER_STRING = "Integer";
    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_INTEGER_TYPES = "numbers.integer_types";

    public CheckerInteger(Integer object, String name) {
        super(object, name);
    }

    @Override
    public CheckerInteger is(Predicate<Integer> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerInteger is(Predicate<Integer> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerInteger isNot(Predicate<Integer> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerInteger isNot(Predicate<Integer> condition) {
        super.isNot(condition);
        return this;
    }

    public CheckerInteger isEven(){
        is(n_integer -> (n_integer & 1) == 0, sendMessage(INIT_INTEGER_TYPES, "is_pair", INTEGER_STRING));
        return this;
    }

    public CheckerInteger isOdd(){
        is(n_integer -> (n_integer & 1) == 1, sendMessage(INIT_INTEGER_TYPES, "is_even", INTEGER_STRING));
        return this;
    }

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
        
        is(isPrime, sendMessage(INIT_INTEGER_TYPES, "is_prime", INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerInteger isPositive() {
        is(n_integer -> n_integer > 0, sendMessage(INIT_NUMBERS, "is_positive", INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerInteger isPositiveOrZero() {
        is(n_integer -> n_integer >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerInteger isNegative() {
        is(n_integer -> n_integer < 0, sendMessage(INIT_NUMBERS, "is_negative", INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerInteger isNegativeOrZero() {
        is(n_integer -> n_integer <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerInteger isZero() {
        is(n_integer -> n_integer == 0, sendMessage(INIT_NUMBERS, "is_zero", INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Byte number) {
        is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Short number) {
        is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Integer number) {
        is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Long number) {
        is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Float number) {
        is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Double number) {
        is(n_integer -> n_integer > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Byte number) {
        is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Short number) {
        is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Integer number) {
        is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Long number) {
        is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Float number) {
        is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Double number) {
        is(n_integer -> n_integer >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Byte number) {
        is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Short number) {
        is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Integer number) {
        is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Long number) {
        is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Float number) {
        is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Double number) {
        is(n_integer -> n_integer < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Byte number) {
        is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Short number) {
        is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Integer number) {
        is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Long number) {
        is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Float number) {
        is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Double number) {
        is(n_integer -> n_integer <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
        return this;
    }
    
}
