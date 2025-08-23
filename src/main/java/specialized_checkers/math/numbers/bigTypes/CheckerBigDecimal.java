package specialized_checkers.math.numbers.bigTypes;

import static util.Message.*;

import java.math.BigDecimal;

import specialized_checkers.math.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerBigDecimal extends AbstractChecker<BigDecimal, CheckerBigDecimal> implements InterfaceCheckerNumber<CheckerBigDecimal> {

    private static final String BIG_DECIMAL_STRING = "Big Decimal";
    private static final String INIT_NUMBERS = "numbers";

    public CheckerBigDecimal(BigDecimal object, String name) {
        super(object, name);
    }

    @Override
    protected CheckerBigDecimal self() {
        return this;
    }
    
    @Override
    public CheckerBigDecimal isPositive() {
        return is(n_big_decimal -> n_big_decimal.signum() > 0, sendMessage(INIT_NUMBERS, "is_positive", BIG_DECIMAL_STRING));
    }

    @Override
    public CheckerBigDecimal isPositiveOrZero() {
        return is(n_big_decimal -> n_big_decimal.signum() >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", BIG_DECIMAL_STRING));
    }

    @Override
    public CheckerBigDecimal isNegative() {
        return is(n_big_decimal -> n_big_decimal.signum() < 0, sendMessage(INIT_NUMBERS, "is_negative", BIG_DECIMAL_STRING));
    }

    @Override
    public CheckerBigDecimal isNegativeOrZero() {
        return is(n_big_decimal -> n_big_decimal.signum() <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", BIG_DECIMAL_STRING));
    }

    @Override
    public CheckerBigDecimal isZero() {
        return is(n_big_decimal -> n_big_decimal.signum() == 0, sendMessage(INIT_NUMBERS, "is_zero", BIG_DECIMAL_STRING));
    }

    @Override
    public CheckerBigDecimal isGreaterThan(Byte number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isGreaterThan(Short number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isGreaterThan(Integer number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isGreaterThan(Long number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isGreaterThan(Float number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isGreaterThan(Double number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Byte number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Short number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Integer number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Long number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Float number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Double number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isLessThan(Byte number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isLessThan(Short number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isLessThan(Integer number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isLessThan(Long number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isLessThan(Float number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isLessThan(Double number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isLessOrEqualTo(Byte number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isLessOrEqualTo(Short number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isLessOrEqualTo(Integer number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isLessOrEqualTo(Long number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isLessOrEqualTo(Float number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_DECIMAL_STRING, number));
    }

    @Override
    public CheckerBigDecimal isLessOrEqualTo(Double number) {
        return is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_DECIMAL_STRING, number));
    }
    
}
