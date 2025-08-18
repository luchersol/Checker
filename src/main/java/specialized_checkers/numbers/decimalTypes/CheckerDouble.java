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
        return is(n_double -> n_double.isNaN(), sendMessage(INIT_DECIMAL_TYPES, "is_nan", DOUBLE_STRING));
    }

    public CheckerDouble isInfinite(){
        return is(n_double -> n_double.isInfinite(), sendMessage(INIT_DECIMAL_TYPES, "is_infinite", DOUBLE_STRING));
    }

    @Override
    public CheckerDouble isPositive() {
        return is(n_double -> n_double > 0, sendMessage(INIT_NUMBERS, "is_positive", DOUBLE_STRING));
    }

    @Override
    public CheckerDouble isPositiveOrZero() {
        return is(n_double -> n_double >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", DOUBLE_STRING));
    }

    @Override
    public CheckerDouble isNegative() {
        return is(n_double -> n_double < 0, sendMessage(INIT_NUMBERS, "is_negative", DOUBLE_STRING));
    }

    @Override
    public CheckerDouble isNegativeOrZero() {
        return is(n_double -> n_double <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", DOUBLE_STRING));
    }

    @Override
    public CheckerDouble isZero() {
        return is(n_double -> n_double == 0, sendMessage(INIT_NUMBERS, "is_zero", DOUBLE_STRING));
    }

    @Override
    public CheckerDouble isGreaterThan(Byte number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isGreaterThan(Short number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isGreaterThan(Integer number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isGreaterThan(Long number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isGreaterThan(Float number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isGreaterThan(Double number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Byte number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Short number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Integer number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Long number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Float number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Double number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isLessThan(Byte number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isLessThan(Short number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isLessThan(Integer number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isLessThan(Long number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isLessThan(Float number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isLessThan(Double number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Byte number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Short number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Integer number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Long number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Float number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Double number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DOUBLE_STRING, number));
    }

}
