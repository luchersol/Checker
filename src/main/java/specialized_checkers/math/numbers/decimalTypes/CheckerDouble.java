package specialized_checkers.math.numbers.decimalTypes;

import static util.Message.*;

import specialized_checkers.math.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerDouble extends AbstractChecker<Double, CheckerDouble> implements InterfaceCheckerNumber<CheckerDouble> {

    private static final String INIT_NUMBERS = "numbers";
    private static final String INIT_DECIMAL_TYPES = "numbers.decimal_types";
    private static final String DEFAULT_NAME = "Double";

    protected CheckerDouble(Double object, String name) {
        super(object, name);
    }

    /**
     * @param number
     * @param name
     * @return CheckerDouble
     */
    public static CheckerDouble check(Double number, String name) {
        return new CheckerDouble(number, name);
    }

    /**
     * @param number
     * @param name
     * @return CheckerDouble
     */
    public static CheckerDouble check(Number number, String name) {
        return check(number.doubleValue(), name);
    }


    /**
     * @param number
     * @return CheckerDouble
     */
    public static CheckerDouble check(Double number) {
        return check(number, DEFAULT_NAME);
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    public static CheckerDouble check(Number number) {
        return check(number.doubleValue());
    }

    /**
     * @return CheckerDouble
     */
    @Override
    protected CheckerDouble self() {
        return this;
    }

    /**
     * @return CheckerDouble
     */
    public CheckerDouble isNaN(){
        return is(n_double -> n_double.isNaN(), sendMessage(INIT_DECIMAL_TYPES, "is_nan", DEFAULT_NAME));
    }

    /**
     * @return CheckerDouble
     */
    public CheckerDouble isInfinite(){
        return is(n_double -> n_double.isInfinite(), sendMessage(INIT_DECIMAL_TYPES, "is_infinite", DEFAULT_NAME));
    }

    /**
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isPositive() {
        return is(n_double -> n_double > 0, sendMessage(INIT_NUMBERS, "is_positive", DEFAULT_NAME));
    }

    /**
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isPositiveOrZero() {
        return is(n_double -> n_double >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", DEFAULT_NAME));
    }

    /**
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isNegative() {
        return is(n_double -> n_double < 0, sendMessage(INIT_NUMBERS, "is_negative", DEFAULT_NAME));
    }

    /**
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isNegativeOrZero() {
        return is(n_double -> n_double <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", DEFAULT_NAME));
    }

    /**
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isZero() {
        return is(n_double -> n_double == 0, sendMessage(INIT_NUMBERS, "is_zero", DEFAULT_NAME));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterThan(Byte number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterThan(Short number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterThan(Integer number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterThan(Long number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterThan(Float number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterThan(Double number) {
        return is(n_double -> n_double > number, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterOrEqualTo(Byte number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterOrEqualTo(Short number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterOrEqualTo(Integer number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterOrEqualTo(Long number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterOrEqualTo(Float number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isGreaterOrEqualTo(Double number) {
        return is(n_double -> n_double >= number, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessThan(Byte number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessThan(Short number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessThan(Integer number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessThan(Long number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessThan(Float number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessThan(Double number) {
        return is(n_double -> n_double < number, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessOrEqualTo(Byte number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessOrEqualTo(Short number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessOrEqualTo(Integer number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessOrEqualTo(Long number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessOrEqualTo(Float number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerDouble
     */
    @Override
    public CheckerDouble isLessOrEqualTo(Double number) {
        return is(n_double -> n_double <= number, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

}
