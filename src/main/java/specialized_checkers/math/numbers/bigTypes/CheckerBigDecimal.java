package specialized_checkers.math.numbers.bigTypes;

import static util.Message.*;

import java.math.BigDecimal;

import specialized_checkers.math.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerBigDecimal extends AbstractChecker<BigDecimal, CheckerBigDecimal> implements InterfaceCheckerNumber<CheckerBigDecimal> {

    private static final String INIT_NUMBERS = "numbers";
    private static final String DEFAULT_NAME = "Big Decimal";

    protected CheckerBigDecimal(BigDecimal bigDecimal, String name) {
        super(bigDecimal, name);
    }

    /**
     * @param number
     * @param name
     * @return CheckerBigDecimal
     */
    public static CheckerBigDecimal check(BigDecimal number, String name) {
        return new CheckerBigDecimal(number, name);
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    public static CheckerBigDecimal check(BigDecimal number) {
        return check(number, DEFAULT_NAME);
    }

    /**
     * @return CheckerBigDecimal
     */
    @Override
    protected CheckerBigDecimal self() {
        return this;
    }

    /**
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isPositive() {
        return is(n_big_decimal -> n_big_decimal.signum() > 0, sendMessage(INIT_NUMBERS, "is_positive", DEFAULT_NAME));
    }

    /**
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isPositiveOrZero() {
        return is(n_big_decimal -> n_big_decimal.signum() >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", DEFAULT_NAME));
    }

    /**
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isNegative() {
        return is(n_big_decimal -> n_big_decimal.signum() < 0, sendMessage(INIT_NUMBERS, "is_negative", DEFAULT_NAME));
    }

    /**
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isNegativeOrZero() {
        return is(n_big_decimal -> n_big_decimal.signum() <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", DEFAULT_NAME));
    }

    /**
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isZero() {
        return is(n_big_decimal -> n_big_decimal.signum() == 0, sendMessage(INIT_NUMBERS, "is_zero", DEFAULT_NAME));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isGreaterThan(Byte number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isGreaterThan(Short number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isGreaterThan(Integer number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isGreaterThan(Long number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isGreaterThan(Float number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isGreaterThan(Double number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Byte number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Short number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Integer number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Long number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Float number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Double number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isLessThan(Byte number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isLessThan(Short number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isLessThan(Integer number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isLessThan(Long number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isLessThan(Float number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isLessThan(Double number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isLessOrEqualTo(Byte number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isLessOrEqualTo(Short number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isLessOrEqualTo(Integer number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isLessOrEqualTo(Long number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isLessOrEqualTo(Float number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigDecimal
     */
    @Override
    public CheckerBigDecimal isLessOrEqualTo(Double number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

}
