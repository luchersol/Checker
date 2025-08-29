package specialized_checkers.math.numbers.bigTypes;

import static util.Message.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import specialized_checkers.math.numbers.InterfaceCheckerNumber;
import util.AbstractChecker;

public class CheckerBigInteger extends AbstractChecker<BigInteger, CheckerBigInteger> implements InterfaceCheckerNumber<CheckerBigInteger> {

    private static final String INIT_NUMBERS = "numbers";
    private static final String DEFAULT_NAME = "Big Integer";

    protected CheckerBigInteger(BigInteger object, String name) {
        super(object, name);
    }

    /**
     * @param number
     * @param name
     * @return CheckerBigInteger
     */
    public static CheckerBigInteger check(BigInteger number, String name) {
        return new CheckerBigInteger(number, name);
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    public static CheckerBigInteger check(BigInteger number) {
        return check(number, DEFAULT_NAME);
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
        return is(n_big_integer -> n_big_integer.mod(BigInteger.TWO).equals(BigInteger.ZERO), sendMessage(INIT_NUMBERS, "is_even", DEFAULT_NAME));
    }

    /**
     * @return CheckerBigInteger
     */
    public CheckerBigInteger isOdd(){
        return is(n_big_integer -> !n_big_integer.mod(BigInteger.TWO).equals(BigInteger.ZERO), sendMessage(INIT_NUMBERS, "is_odd", DEFAULT_NAME));
    }

    /**
     * @param divisor
     * @return CheckerBigInteger
     */
    public CheckerBigInteger isDivisibleBy(BigInteger divisor){
        return is(n_big_integer -> n_big_integer.mod(divisor).equals(BigInteger.ZERO), sendMessage(INIT_NUMBERS, "is_odd", DEFAULT_NAME));
    }

    /**
     * @return CheckerBigInteger
     */
    public CheckerBigInteger isPowerOfTwo(){
        return is(n_big_integer -> n_big_integer.signum() > 0 && n_big_integer.bitCount() == 1, sendMessage(INIT_NUMBERS, "is_odd", DEFAULT_NAME));
    }

    /**
     * @param certainty
     * @return CheckerBigInteger
     */
    public CheckerBigInteger isProbablePrime(int certainty) {
        return is(n_big_integer -> n_big_integer.isProbablePrime(certainty), sendMessage(INIT_NUMBERS, "is_probable_prime", DEFAULT_NAME));
    }

    /**
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isPositive() {
        return is(n_big_integer -> n_big_integer.signum() > 0, sendMessage(INIT_NUMBERS, "is_positive", DEFAULT_NAME));
    }

    /**
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isPositiveOrZero() {
        return is(n_big_integer -> n_big_integer.signum() >= 0, sendMessage(INIT_NUMBERS, "is_positive_or_zero", DEFAULT_NAME));
    }

    /**
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isNegative() {
        return is(n_big_integer -> n_big_integer.signum() < 0, sendMessage(INIT_NUMBERS, "is_negative", DEFAULT_NAME));
    }

    /**
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isNegativeOrZero() {
        return is(n_big_integer -> n_big_integer.signum() <= 0, sendMessage(INIT_NUMBERS, "is_negative_or_zero", DEFAULT_NAME));
    }

    /**
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isZero() {
        return is(n_big_integer -> n_big_integer.signum() == 0, sendMessage(INIT_NUMBERS, "is_zero", DEFAULT_NAME));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterThan(Byte number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterThan(Short number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterThan(Integer number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterThan(Long number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterThan(Float number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterThan(Double number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) > 0, sendMessage(INIT_NUMBERS, "is_greather_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Byte number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Short number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Integer number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Long number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Float number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isGreaterOrEqualTo(Double number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) >= 0, sendMessage(INIT_NUMBERS, "is_greather_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessThan(Byte number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessThan(Short number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessThan(Integer number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessThan(Long number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessThan(Float number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessThan(Double number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) < 0, sendMessage(INIT_NUMBERS, "is_less_than", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Byte number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Short number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Integer number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Long number) {
        return is(n_big_integer -> n_big_integer.compareTo(BigInteger.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Float number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

    /**
     * @param number
     * @return CheckerBigInteger
     */
    @Override
    public CheckerBigInteger isLessOrEqualTo(Double number) {
        return is(n_big_integer -> new BigDecimal(n_big_integer).compareTo(BigDecimal.valueOf(number)) <= 0, sendMessage(INIT_NUMBERS, "is_less_or_equal_to", DEFAULT_NAME, number));
    }

}
