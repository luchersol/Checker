package specialized_checkers.math.numbers.bigTypes;

import static util.Message.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import specialized_checkers.math.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerBigInteger extends AbstractChecker<BigInteger, CheckerBigInteger> implements InterfaceCheckerNumber<CheckerBigInteger> {

    private static final String BIG_INTEGER_STRING = "Big Integer";
    private static final String INIT_NUMBERS = "numbers";

    public CheckerBigInteger(BigInteger object, String name) {
        super(object, name);
    }

    /**
     * @return CheckerBigInteger
     */
    @Override
    protected CheckerBigInteger self() {
        return this;
    }

    /**
     * @return CheckerBigInteger
     */
    public CheckerBigInteger isEven(){
        return is(n_big_integer -> n_big_integer.mod(BigInteger.TWO).equals(BigInteger.ZERO), sendMessage(INIT_NUMBERS, "is_even", BIG_INTEGER_STRING));
    }

    /**
     * @return CheckerBigInteger
     */
    public CheckerBigInteger isOdd(){
        return is(n_big_integer -> !n_big_integer.mod(BigInteger.TWO).equals(BigInteger.ZERO), sendMessage(INIT_NUMBERS, "is_odd", BIG_INTEGER_STRING));
    }

    /**
     * @param divisor
     * @return CheckerBigInteger
     */
    public CheckerBigInteger isDivisibleBy(BigInteger divisor){
        return is(n_big_integer -> n_big_integer.mod(divisor).equals(BigInteger.ZERO), sendMessage(INIT_NUMBERS, "is_odd", BIG_INTEGER_STRING));
    }

    /**
     * @return CheckerBigInteger
     */
    public CheckerBigInteger isPowerOfTwo(){
        return is(n_big_integer -> n_big_integer.signum() > 0 && n_big_integer.bitCount() == 1, sendMessage(INIT_NUMBERS, "is_odd", BIG_INTEGER_STRING));
    }

    /**
     * @param certainty
     * @return CheckerBigInteger
     */
    public CheckerBigInteger isProbablePrime(int certainty) {
        return is(n_big_integer -> n_big_integer.isProbablePrime(certainty), sendMessage(INIT_NUMBERS, "is_probable_prime", BIG_INTEGER_STRING));
    }

    /**
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isPositive() {
        return is(n_big_integer -> n_big_integer.signum() > 0, sendMessage(INIT_NUMBERS, "is_positive", BIG_INTEGER_STRING));
    }

    /**
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isPositiveOrZero() {
        return is(n_big_integer -> n_big_integer.signum() >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", BIG_INTEGER_STRING));
    }

    /**
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isNegative() {
        return is(n_big_integer -> n_big_integer.signum() < 0, sendMessage(INIT_NUMBERS, "is_negative", BIG_INTEGER_STRING));
    }

    /**
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isNegativeOrZero() {
        return is(n_big_integer -> n_big_integer.signum() <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", BIG_INTEGER_STRING));
    }

    /**
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isZero() {
        return is(n_big_integer -> n_big_integer.signum() == 0, sendMessage(INIT_NUMBERS, "is_zero", BIG_INTEGER_STRING));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterThan(Byte number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterThan(Short number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterThan(Integer number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterThan(Long number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterThan(Float number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterThan(Double number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Byte number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Short number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Integer number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Long number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Float number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Double number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessThan(Byte number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessThan(Short number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessThan(Integer number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessThan(Long number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessThan(Float number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessThan(Double number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Byte number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Short number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Integer number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Long number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Float number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_INTEGER_STRING, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Double number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", BIG_INTEGER_STRING, number));
    }

}
