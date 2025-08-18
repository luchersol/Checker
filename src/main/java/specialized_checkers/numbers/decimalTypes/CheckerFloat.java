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
        return is(n_float -> n_float.isNaN(), sendMessage(INIT_DECIMAL_TYPES, "is_nan", FLOAT_STRING));
    }

    public CheckerFloat isInfinite(){
        return is(n_float -> n_float.isInfinite(), sendMessage(INIT_DECIMAL_TYPES, "is_infinite", FLOAT_STRING));
    }

    @Override
    public CheckerFloat isPositive() {
        return is(n_float -> n_float > 0, sendMessage(INIT_NUMBERS, "is_positive", FLOAT_STRING));
    }

    @Override
    public CheckerFloat isPositiveOrZero() {
        return is(n_float -> n_float >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", FLOAT_STRING));
    }

    @Override
    public CheckerFloat isNegative() {
        return is(n_float -> n_float < 0, sendMessage(INIT_NUMBERS, "is_negative", FLOAT_STRING));
    }

    @Override
    public CheckerFloat isNegativeOrZero() {
        return is(n_float -> n_float <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", FLOAT_STRING));
    }

    @Override
    public CheckerFloat isZero() {
        return is(n_float -> n_float == 0, sendMessage(INIT_NUMBERS, "is_zero", FLOAT_STRING));
    }

    @Override
    public CheckerFloat isGreaterThan(Byte number) {
        return is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isGreaterThan(Short number) {
        return is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isGreaterThan(Integer number) {
        return is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isGreaterThan(Long number) {
        return is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isGreaterThan(Float number) {
        return is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isGreaterThan(Double number) {
        return is(n_float -> n_float > number, sendMessage(INIT_NUMBERS, "is_greather_than", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Byte number) {
        return is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Short number) {
        return is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Integer number) {
        return is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Long number) {
        return is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Float number) {
        return is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Double number) {
        return is(n_float -> n_float >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isLessThan(Byte number) {
        return is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isLessThan(Short number) {
        return is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isLessThan(Integer number) {
        return is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isLessThan(Long number) {
        return is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isLessThan(Float number) {
        return is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isLessThan(Double number) {
        return is(n_float -> n_float < number, sendMessage(INIT_NUMBERS, "is_less_than", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Byte number) {
        return is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Short number) {
        return is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Integer number) {
        return is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Long number) {
        return is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Float number) {
        return is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Double number) {
        return is(n_float -> n_float <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", FLOAT_STRING, number));
    }
    
}
