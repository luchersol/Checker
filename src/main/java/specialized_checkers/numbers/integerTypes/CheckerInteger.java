package specialized_checkers.numbers.integerTypes;

import static util.Message.sendMessage;

import specialized_checkers.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerInteger extends AbstractChecker<Integer> implements InterfaceCheckerNumber<CheckerInteger> {

    private static final String INTEGER_STRING = "Integer";
    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_INTEGER_TYPES = "numbers.integer_types";

    public CheckerInteger(Integer object, String name) {
        super(object, name);
    }

    public CheckerInteger isPair(){
        is(this.object % 2 == 0, sendMessage(INIT_INTEGER_TYPES, "is_pair", INTEGER_STRING));
        return this;
    }

    public CheckerInteger isEven(){
        is(this.object % 2 != 0, sendMessage(INIT_INTEGER_TYPES, "is_even", INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerInteger isPositive() {
        is(this.object > 0, sendMessage(INIT_NUMBERS, "is_positive", INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerInteger isPositiveOrZero() {
        is(this.object >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerInteger isNegative() {
        is(this.object < 0, sendMessage(INIT_NUMBERS, "is_negative", INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerInteger isNegativeOrZero() {
        is(this.object <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerInteger isZero() {
        is(this.object == 0, sendMessage(INIT_NUMBERS, "is_zero", INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Byte number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Short number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Integer number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Long number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Float number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Double number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Byte number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Short number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Integer number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Long number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Float number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Double number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Byte number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Short number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Integer number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Long number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Float number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Double number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Byte number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Short number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Integer number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Long number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Float number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Double number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", INTEGER_STRING, number));
        return this;
    }
    
}
