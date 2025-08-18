package specialized_checkers.numbers.bigTypes;

import static util.Message.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import specialized_checkers.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerBigInteger extends AbstractChecker<BigInteger, CheckerBigInteger> implements InterfaceCheckerNumber<CheckerBigInteger> {

    private static final String BIG_INTEGER_STRING = "Big Integer";
    private static final String INIT_NUMBERS = "numbers";

    public CheckerBigInteger(BigInteger object, String name) {
        super(object, name);
    }

    @Override
    protected CheckerBigInteger self() {
        return this;
    }

    public CheckerBigInteger isEven(){
        is(n_big_integer -> n_big_integer.mod(BigInteger.TWO).equals(BigInteger.ZERO), sendMessage(INIT_NUMBERS, "is_even", BIG_INTEGER_STRING));
        return this;
    }

    public CheckerBigInteger isOdd(){
        is(n_big_integer -> !n_big_integer.mod(BigInteger.TWO).equals(BigInteger.ZERO), sendMessage(INIT_NUMBERS, "is_odd", BIG_INTEGER_STRING));
        return this;
    }

    public CheckerBigInteger isDivisibleBy(BigInteger divisor){
        is(n_big_integer -> n_big_integer.mod(divisor).equals(BigInteger.ZERO), sendMessage(INIT_NUMBERS, "is_odd", BIG_INTEGER_STRING));
        return this;
    }

    public CheckerBigInteger isPowerOfTwo(){
        is(n_big_integer -> n_big_integer.signum() > 0 && n_big_integer.bitCount() == 1, sendMessage(INIT_NUMBERS, "is_odd", BIG_INTEGER_STRING));
        return this;
    }

    public CheckerBigInteger isProbablePrime(int certainty) {
        is(n_big_integer -> n_big_integer.isProbablePrime(certainty), sendMessage(INIT_NUMBERS, "is_probable_prime", BIG_INTEGER_STRING));
        return this;
    }
    
    @Override
    public CheckerBigInteger isPositive() {
        is(n_big_integer -> n_big_integer.signum() > 0, sendMessage(INIT_NUMBERS, "is_positive", BIG_INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerBigInteger isPositiveOrZero() {
        is(n_big_integer -> n_big_integer.signum() >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", BIG_INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerBigInteger isNegative() {
        is(n_big_integer -> n_big_integer.signum() < 0, sendMessage(INIT_NUMBERS, "is_negative", BIG_INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerBigInteger isNegativeOrZero() {
        is(n_big_integer -> n_big_integer.signum() <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", BIG_INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerBigInteger isZero() {
        is(n_big_integer -> n_big_integer.signum() == 0, sendMessage(INIT_NUMBERS, "is_zero", BIG_INTEGER_STRING));
        return this;
    }

    @Override
    public CheckerBigInteger isGreaterThan(Byte number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isGreaterThan(Short number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isGreaterThan(Integer number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isGreaterThan(Long number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isGreaterThan(Float number) {
        is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isGreaterThan(Double number) {
        is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Byte number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Short number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Integer number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Long number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Float number) {
        is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Double number) {
        is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isLessThan(Byte number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isLessThan(Short number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isLessThan(Integer number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isLessThan(Long number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isLessThan(Float number) {
        is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isLessThan(Double number) {
        is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isLessOrEqualTo(Byte number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isLessOrEqualTo(Short number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isLessOrEqualTo(Integer number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isLessOrEqualTo(Long number) {
        is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isLessOrEqualTo(Float number) {
        is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_INTEGER_STRING, number));
        return this;
    }

    @Override
    public CheckerBigInteger isLessOrEqualTo(Double number) {
        is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_INTEGER_STRING, number));
        return this;
    }
    
}
