package specialized_checkers.numbers.decimalTypes;

import static util.Message.*;

import specialized_checkers.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerDouble extends AbstractChecker<Double, CheckerDouble> implements InterfaceCheckerNumber<CheckerDouble> {

    private static final String DOUBLE_STRING = "Double";
    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_DECIMAL_TYPES = "numbers.decimal_types";

    public CheckerDouble(Double object, String name) {
        super(object, name);
    }

    @Override
    protected CheckerDouble self() {
        return this;
    }
    
    public CheckerDouble isNaN(){
        is(n_double -> n_double.isNaN(), sendMessage(INIT_DECIMAL_TYPES, "is_nan", DOUBLE_STRING));
        return this;
    }

    public CheckerDouble isInfinite(){
        is(n_double -> n_double.isInfinite(), sendMessage(INIT_DECIMAL_TYPES, "is_infinite", DOUBLE_STRING));
        return this;
    }

    @Override
    public CheckerDouble isPositive() {
        is(n_double -> n_double > 0, sendMessage(INIT_NUMBERS, "is_positive", DOUBLE_STRING));
        return this;
    }

    @Override
    public CheckerDouble isPositiveOrZero() {
        is(n_double -> n_double >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", DOUBLE_STRING));
        return this;
    }

    @Override
    public CheckerDouble isNegative() {
        is(n_double -> n_double < 0, sendMessage(INIT_NUMBERS, "is_negative", DOUBLE_STRING));
        return this;
    }

    @Override
    public CheckerDouble isNegativeOrZero() {
        is(n_double -> n_double <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", DOUBLE_STRING));
        return this;
    }

    @Override
    public CheckerDouble isZero() {
        is(n_double -> n_double == 0, sendMessage(INIT_NUMBERS, "is_zero", DOUBLE_STRING));
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Byte number) {
        is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Short number) {
        is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Integer number) {
        is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Long number) {
        is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Float number) {
        is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Double number) {
        is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Byte number) {
        is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Short number) {
        is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Integer number) {
        is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Long number) {
        is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Float number) {
        is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Double number) {
        is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Byte number) {
        is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Short number) {
        is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Integer number) {
        is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Long number) {
        is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Float number) {
        is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Double number) {
        is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Byte number) {
        is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Short number) {
        is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Integer number) {
        is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Long number) {
        is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Float number) {
        is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Double number) {
        is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
        return this;
    }

}
