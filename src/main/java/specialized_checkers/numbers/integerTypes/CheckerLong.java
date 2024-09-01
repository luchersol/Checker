package specialized_checkers.numbers.integerTypes;

import static util.Message.sendMessage;

import specialized_checkers.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerLong extends AbstractChecker<Long> implements InterfaceCheckerNumber<CheckerLong> {

    private static final String LONG_STRING = "Long";
    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_INTEGER_TYPES = "numbers.integer_types";


    public CheckerLong(Long object, String name) {
        super(object, name);
    }

    public CheckerLong isPair(){
        is(this.object % 2 == 0, sendMessage(INIT_INTEGER_TYPES, "is_pair", LONG_STRING));
        return this;
    }

    public CheckerLong isEven(){
        is(this.object % 2 != 0, sendMessage(INIT_INTEGER_TYPES, "is_even", LONG_STRING));
        return this;
    }

    @Override
    public CheckerLong isPositive() {
        is(this.object > 0, sendMessage(INIT_NUMBERS, "is_positive", LONG_STRING));
        return this;
    }

    @Override
    public CheckerLong isPositiveOrZero() {
        is(this.object >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", LONG_STRING));
        return this;
    }

    @Override
    public CheckerLong isNegative() {
        is(this.object < 0, sendMessage(INIT_NUMBERS, "is_negative", LONG_STRING));
        return this;
    }

    @Override
    public CheckerLong isNegativeOrZero() {
        is(this.object <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", LONG_STRING));
        return this;
    }

    @Override
    public CheckerLong isZero() {
        is(this.object == 0, sendMessage(INIT_NUMBERS, "is_zero", LONG_STRING));
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Byte number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Short number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Integer number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Long number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Float number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Double number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Byte number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Short number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Integer number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Long number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Float number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Double number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessThan(Byte number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessThan(Short number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessThan(Integer number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessThan(Long number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessThan(Float number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessThan(Double number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Byte number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Short number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Integer number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Long number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Float number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", LONG_STRING, number));
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Double number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", LONG_STRING, number));
        return this;
    }

}
