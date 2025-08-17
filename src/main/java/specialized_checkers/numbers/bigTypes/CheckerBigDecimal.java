package specialized_checkers.numbers.bigTypes;

import static util.Message.*;

import java.math.BigDecimal;
import java.util.function.Predicate;

import specialized_checkers.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerBigDecimal extends AbstractChecker<BigDecimal> implements InterfaceCheckerNumber<CheckerBigDecimal> {

    private static final String BIG_DECIMAL_STRING = "Big Decimal";
    private static final String INIT_NUMBERS = "numbers";

    public CheckerBigDecimal(BigDecimal object, String name) {
        super(object, name);
    }

    @Override
    public CheckerBigDecimal is(Predicate<BigDecimal> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerBigDecimal is(Predicate<BigDecimal> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerBigDecimal isNot(Predicate<BigDecimal> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerBigDecimal isNot(Predicate<BigDecimal> condition) {
        super.isNot(condition);
        return this;
    }

    
    @Override
    public CheckerBigDecimal isPositive() {
        is(n_big_decimal -> n_big_decimal.signum() > 0, sendMessage(INIT_NUMBERS, "is_positive", BIG_DECIMAL_STRING));
        return this;
    }

    @Override
    public CheckerBigDecimal isPositiveOrZero() {
        is(n_big_decimal -> n_big_decimal.signum() >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", BIG_DECIMAL_STRING));
        return this;
    }

    @Override
    public CheckerBigDecimal isNegative() {
        is(n_big_decimal -> n_big_decimal.signum() < 0, sendMessage(INIT_NUMBERS, "is_negative", BIG_DECIMAL_STRING));
        return this;
    }

    @Override
    public CheckerBigDecimal isNegativeOrZero() {
        is(n_big_decimal -> n_big_decimal.signum() <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", BIG_DECIMAL_STRING));
        return this;
    }

    @Override
    public CheckerBigDecimal isZero() {
        is(n_big_decimal -> n_big_decimal.signum() == 0, sendMessage(INIT_NUMBERS, "is_zero", BIG_DECIMAL_STRING));
        return this;
    }

    @Override
    public CheckerBigDecimal isGreaterThan(Byte number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isGreaterThan(Short number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isGreaterThan(Integer number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isGreaterThan(Long number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isGreaterThan(Float number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isGreaterThan(Double number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Byte number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Short number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Integer number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Long number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Float number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isGreaterOrEqualTo(Double number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isLessThan(Byte number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isLessThan(Short number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isLessThan(Integer number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isLessThan(Long number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isLessThan(Float number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isLessThan(Double number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isLessOrEqualTo(Byte number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isLessOrEqualTo(Short number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isLessOrEqualTo(Integer number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isLessOrEqualTo(Long number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isLessOrEqualTo(Float number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_DECIMAL_STRING, number));
        return this;
    }

    @Override
    public CheckerBigDecimal isLessOrEqualTo(Double number) {
        is(n_big_decimal -> n_big_decimal.compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_DECIMAL_STRING, number));
        return this;
    }
    
}
