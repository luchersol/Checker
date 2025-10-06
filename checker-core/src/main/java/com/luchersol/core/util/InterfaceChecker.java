package com.luchersol.core.util;

import java.util.function.Predicate;

/**
 * InterfaceChecker defines a contract for checker classes that validate values using predicates.
 * Provides methods for positive and negative assertions with optional custom messages.
 *
 * @param <T> the type of the checker implementing this interface (for fluent API chaining)
 * @param <U> the type of the value being checked
 */
public interface InterfaceChecker<T extends InterfaceChecker<T, U>, U> {


    /**
     * Asserts that the value satisfies the given condition.
     *
     * @param condition the predicate to test the value
     * @return this checker instance for chaining
     */
    T is(Predicate<U> condition);

    /**
     * Asserts that the value satisfies the given condition, with a custom message on failure.
     *
     * @param condition the predicate to test the value
     * @param message   the custom message for assertion failure
     * @return this checker instance for chaining
     */
    T is(Predicate<U> condition, String message);

    /**
     * Asserts that the value does not satisfy the given condition.
     *
     * @param condition the predicate to test the value
     * @return this checker instance for chaining
     */
    T isNot(Predicate<U> condition);

    /**
     * Asserts that the value does not satisfy the given condition, with a custom message on failure.
     *
     * @param condition the predicate to test the value
     * @param message   the custom message for assertion failure
     * @return this checker instance for chaining
     */
    T isNot(Predicate<U> condition, String message);

}
