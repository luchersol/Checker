package specialized_checkers.numbers.decimalTypes;

import static util.Message.sendMessage;

import specialized_checkers.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerDouble extends AbstractChecker<Double> implements InterfaceCheckerNumber<CheckerDouble> {

    private static final String DOUBLE_STRING = "Double";
    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_DECIMAL_TYPES = "numbers.decimal_types";

    public CheckerDouble(Double object, String name) {
        super(object, name);
    }
    
    public CheckerDouble isNaN(){
        is(this.object.isNaN(), sendMessage(INIT_DECIMAL_TYPES, "is_nan", DOUBLE_STRING));
        return this;
    }

    public CheckerDouble isInfinite(){
        is(this.object.isInfinite(), sendMessage(INIT_DECIMAL_TYPES, "is_infinite", DOUBLE_STRING));
        return this;
    }

    @Override
    public CheckerDouble isPositive() {
        is(this.object > 0, sendMessage(INIT_NUMBERS, "is_positive", DOUBLE_STRING));
        return this;
    }

    @Override
    public CheckerDouble isPositiveOrZero() {
        is(this.object >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", DOUBLE_STRING));
        return this;
    }

    @Override
    public CheckerDouble isNegative() {
        is(this.object < 0, sendMessage(INIT_NUMBERS, "is_negative", DOUBLE_STRING));
        return this;
    }

    @Override
    public CheckerDouble isNegativeOrZero() {
        is(this.object <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", DOUBLE_STRING));
        return this;
    }

    @Override
    public CheckerDouble isZero() {
        is(this.object == 0, sendMessage(INIT_NUMBERS, "is_zero", DOUBLE_STRING));
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Byte number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Short number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Integer number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Long number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Float number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Double number) {
        is(this.object > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Byte number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Short number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Integer number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Long number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Float number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Double number) {
        is(this.object >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Byte number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Short number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Integer number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Long number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Float number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Double number) {
        is(this.object < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Byte number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Short number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Integer number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Long number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Float number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Double number) {
        is(this.object <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

}
