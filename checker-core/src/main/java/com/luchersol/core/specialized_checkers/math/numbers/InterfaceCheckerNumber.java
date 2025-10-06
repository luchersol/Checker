package com.luchersol.core.specialized_checkers.math.numbers;

/**
 * Interface for fluent number checkers, providing common validation methods for numeric types.
 * <p>
 * Implementations of this interface allow for fluent assertions on numeric values, such as positivity, negativity, comparison, and zero checks.
 * </p>
 *
 * @param <T> the type of the implementing checker (enables fluent API chaining)
 */
public interface InterfaceCheckerNumber<T extends InterfaceCheckerNumber<T>> {


    /**
     * Asserts that the checked number is strictly positive.
     *
     * @return this checker instance for fluent chaining
     */
    T isPositive();

    /**
     * Asserts that the checked number is positive or zero.
     *
     * @return this checker instance for fluent chaining
     */
    T isPositiveOrZero();

    /**
     * Asserts that the checked number is strictly negative.
     *
     * @return this checker instance for fluent chaining
     */
    T isNegative();

    /**
     * Asserts that the checked number is negative or zero.
     *
     * @return this checker instance for fluent chaining
     */
    T isNegativeOrZero();

    /**
     * Asserts that the checked number is exactly zero.
     *
     * @return this checker instance for fluent chaining
     */
    T isZero();


    /**
     * Asserts that the checked number is strictly greater than the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isGreaterThan(Byte number);

    /**
     * Asserts that the checked number is strictly greater than the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isGreaterThan(Short number);

    /**
     * Asserts that the checked number is strictly greater than the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isGreaterThan(Integer number);

    /**
     * Asserts that the checked number is strictly greater than the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isGreaterThan(Long number);

    /**
     * Asserts that the checked number is strictly greater than the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isGreaterThan(Float number);

    /**
     * Asserts that the checked number is strictly greater than the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isGreaterThan(Double number);


    /**
     * Asserts that the checked number is greater than or equal to the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isGreaterOrEqualTo(Byte number);

    /**
     * Asserts that the checked number is greater than or equal to the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isGreaterOrEqualTo(Short number);

    /**
     * Asserts that the checked number is greater than or equal to the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isGreaterOrEqualTo(Integer number);

    /**
     * Asserts that the checked number is greater than or equal to the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isGreaterOrEqualTo(Long number);

    /**
     * Asserts that the checked number is greater than or equal to the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isGreaterOrEqualTo(Float number);

    /**
     * Asserts that the checked number is greater than or equal to the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isGreaterOrEqualTo(Double number);


    /**
     * Asserts that the checked number is strictly less than the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isLessThan(Byte number);

    /**
     * Asserts that the checked number is strictly less than the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isLessThan(Short number);

    /**
     * Asserts that the checked number is strictly less than the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isLessThan(Integer number);

    /**
     * Asserts that the checked number is strictly less than the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isLessThan(Long number);

    /**
     * Asserts that the checked number is strictly less than the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isLessThan(Float number);

    /**
     * Asserts that the checked number is strictly less than the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isLessThan(Double number);


    /**
     * Asserts that the checked number is less than or equal to the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isLessOrEqualTo(Byte number);

    /**
     * Asserts that the checked number is less than or equal to the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isLessOrEqualTo(Short number);

    /**
     * Asserts that the checked number is less than or equal to the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isLessOrEqualTo(Integer number);

    /**
     * Asserts that the checked number is less than or equal to the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isLessOrEqualTo(Long number);

    /**
     * Asserts that the checked number is less than or equal to the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isLessOrEqualTo(Float number);

    /**
     * Asserts that the checked number is less than or equal to the given value.
     *
     * @param number the value to compare against
     * @return this checker instance for fluent chaining
     */
    T isLessOrEqualTo(Double number);

}
