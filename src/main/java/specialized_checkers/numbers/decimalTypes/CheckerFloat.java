package specialized_checkers.numbers.decimalTypes;

import static util.Message.*;

import specialized_checkers.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerFloat extends AbstractChecker<Float, CheckerFloat> implements InterfaceCheckerNumber<CheckerFloat>{

    private static final String FLOAT_STRING = "Float";
    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_DECIMAL_TYPES = "numbers.decimal_types";

    public CheckerFloat(Float object, String name) {
        super(object, name);
    }

    @Override
    protected CheckerFloat self() {
        return this;
    }

    public CheckerFloat isNaN(){
        is(n_float -> n_float.isNaN(), sendMessage(INIT_DECIMAL_TYPES, "is_nan", FLOAT_STRING));
        return this;
    }

    public CheckerFloat isInfinite(){
        is(n_float -> n_float.isInfinite(), sendMessage(INIT_DECIMAL_TYPES, "is_infinite", FLOAT_STRING));
        return this;
    }

    @Override
    public CheckerFloat isPositive() {
        is(n_float -> n_float > 0, sendMessage(INIT_NUMBERS, "is_positive", FLOAT_STRING));
        return this;
    }

    @Override
    public CheckerFloat isPositiveOrZero() {
        is(n_float -> n_float >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", FLOAT_STRING));
        return this;
    }

    @Override
    public CheckerFloat isNegative() {
        is(n_float -> n_float < 0, sendMessage(INIT_NUMBERS, "is_negative", FLOAT_STRING));
        return this;
    }

    @Override
    public CheckerFloat isNegativeOrZero() {
        is(n_float -> n_float <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", FLOAT_STRING));
        return this;
    }

    @Override
    public CheckerFloat isZero() {
        is(n_float -> n_float == 0, sendMessage(INIT_NUMBERS, "is_zero", FLOAT_STRING));
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Byte number) {
        is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Short number) {
        is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Integer number) {
        is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Long number) {
        is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Float number) {
        is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Double number) {
        is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Byte number) {
        is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Short number) {
        is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Integer number) {
        is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Long number) {
        is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Float number) {
        is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Double number) {
        is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Byte number) {
        is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Short number) {
        is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Integer number) {
        is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Long number) {
        is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Float number) {
        is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Double number) {
        is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Byte number) {
        is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Short number) {
        is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Integer number) {
        is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Long number) {
        is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Float number) {
        is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Double number) {
        is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
        return this;
    }
    
}
