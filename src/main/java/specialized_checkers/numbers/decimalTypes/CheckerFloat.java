package specialized_checkers.numbers.decimalTypes;

import static util.Message.sendMessage;

import specialized_checkers.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerFloat extends AbstractChecker<Float> implements InterfaceCheckerNumber<CheckerFloat>{

    private static final String FLOAT_STRING = "Float";
    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_DECIMAL_TYPES = "numbers.decimal_types";

    public CheckerFloat(Float object, String name) {
        super(object, name);
    }

    public CheckerFloat isNaN(){
        is(this.object.isNaN(), sendMessage(INIT_DECIMAL_TYPES, "is_nan", FLOAT_STRING));
        return this;
    }

    public CheckerFloat isInfinite(){
        is(this.object.isInfinite(), sendMessage(INIT_DECIMAL_TYPES, "is_infinite", FLOAT_STRING));
        return this;
    }

    @Override
    public CheckerFloat isPositive() {
        is(this.object > 0, sendMessage(INIT_NUMBERS, "is_positive", FLOAT_STRING));
        return this;
    }

    @Override
    public CheckerFloat isPositiveOrZero() {
        is(this.object >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", FLOAT_STRING));
        return this;
    }

    @Override
    public CheckerFloat isNegative() {
        is(this.object < 0, sendMessage(INIT_NUMBERS, "is_negative", FLOAT_STRING));
        return this;
    }

    @Override
    public CheckerFloat isNegativeOrZero() {
        is(this.object <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", FLOAT_STRING));
        return this;
    }

    @Override
    public CheckerFloat isZero() {
        is(this.object == 0, sendMessage(INIT_NUMBERS, "is_zero", FLOAT_STRING));
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Byte number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Short number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Integer number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Long number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Float number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Double number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Byte number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Short number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Integer number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Long number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Float number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Double number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Byte number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Short number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Integer number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Long number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Float number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Double number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Byte number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Short number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Integer number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Long number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Float number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Double number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
        return this;
    }
    
}
