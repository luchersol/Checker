package com.luchersol.core.specialized_checkers.math.numbers.integerTypes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.luchersol.core.util.CheckerException;

class CheckerIntegerTest {

    @Test
    void testIsEven() {
        CheckerInteger checker = new CheckerInteger(4, "testEven");
        assertDoesNotThrow(checker::isEven);

        checker = new CheckerInteger(5, "testEven");
        assertThrows(CheckerException.class, checker::isEven);
    }

    @Test
    void testIsOdd() {
        CheckerInteger checker = new CheckerInteger(5, "testOdd");
        assertDoesNotThrow(checker::isOdd);

        checker = new CheckerInteger(4, "testOdd");
        assertThrows(CheckerException.class, checker::isOdd);
    }

    @Test
    void testIsPrime() {
        CheckerInteger checker = new CheckerInteger(7, "testPrime");
        assertDoesNotThrow(checker::isPrime);

        checker = new CheckerInteger(8, "testPrime");
        assertThrows(CheckerException.class, checker::isPrime);

        checker = new CheckerInteger(1, "testPrime");
        assertThrows(CheckerException.class, checker::isPrime);

        checker = new CheckerInteger(2, "testPrime");
        assertDoesNotThrow(checker::isPrime);
    }

    @Test
    void testIsPositive() {
        CheckerInteger checker = new CheckerInteger(10, "testPositive");
        assertDoesNotThrow(checker::isPositive);

        checker = new CheckerInteger(-1, "testPositive");
        assertThrows(CheckerException.class, checker::isPositive);
    }

    @Test
    void testIsPositiveOrZero() {
        CheckerInteger checker = new CheckerInteger(0, "testPositiveOrZero");
        assertDoesNotThrow(checker::isPositiveOrZero);

        checker = new CheckerInteger(-1, "testPositiveOrZero");
        assertThrows(CheckerException.class, checker::isPositiveOrZero);
    }

    @Test
    void testIsNegative() {
        CheckerInteger checker = new CheckerInteger(-5, "testNegative");
        assertDoesNotThrow(checker::isNegative);

        checker = new CheckerInteger(0, "testNegative");
        assertThrows(CheckerException.class, checker::isNegative);
    }

    @Test
    void testIsNegativeOrZero() {
        CheckerInteger checker = new CheckerInteger(0, "testNegativeOrZero");
        assertDoesNotThrow(checker::isNegativeOrZero);

        checker = new CheckerInteger(1, "testNegativeOrZero");
        assertThrows(CheckerException.class, checker::isNegativeOrZero);
    }

    @Test
    void testIsZero() {
        CheckerInteger checker = new CheckerInteger(0, "testZero");
        assertDoesNotThrow(checker::isZero);

        checker = new CheckerInteger(1, "testZero");
        assertThrows(CheckerException.class, checker::isZero);
    }

    @Test
    void testIsGreaterThan() {
        CheckerInteger checker = new CheckerInteger(10, "testGreaterThan");
        assertDoesNotThrow(() -> checker.isGreaterThan(5));
        assertThrows(CheckerException.class, () -> checker.isGreaterThan(10));
        assertThrows(CheckerException.class, () -> checker.isGreaterThan(15));
    }

    @Test
    void testIsGreaterOrEqualTo() {
        CheckerInteger checker = new CheckerInteger(10, "testGreaterOrEqualTo");
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(10));
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(5));
        assertThrows(CheckerException.class, () -> checker.isGreaterOrEqualTo(15));
    }

    @Test
    void testIsLessThan() {
        CheckerInteger checker = new CheckerInteger(5, "testLessThan");
        assertDoesNotThrow(() -> checker.isLessThan(10));
        assertThrows(CheckerException.class, () -> checker.isLessThan(5));
        assertThrows(CheckerException.class, () -> checker.isLessThan(1));
    }

    @Test
    void testIsLessOrEqualTo() {
        CheckerInteger checker = new CheckerInteger(5, "testLessOrEqualTo");
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(5));
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(10));
        assertThrows(CheckerException.class, () -> checker.isLessOrEqualTo(1));
    }
}
