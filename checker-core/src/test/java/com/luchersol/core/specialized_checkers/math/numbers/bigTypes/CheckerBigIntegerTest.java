package com.luchersol.core.specialized_checkers.math.numbers.bigTypes;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import com.luchersol.core.util.CheckerException;


class CheckerBigIntegerTest {

    @Test
    void testIsEven() {
        CheckerBigInteger checker = CheckerBigInteger.check(BigInteger.valueOf(4));
        assertDoesNotThrow(checker::isEven);

        checker = CheckerBigInteger.check(BigInteger.valueOf(5));
        assertThrows(CheckerException.class, checker::isEven);
    }

    @Test
    void testIsOdd() {
        CheckerBigInteger checker = CheckerBigInteger.check(BigInteger.valueOf(5));
        assertDoesNotThrow(checker::isOdd);

        checker = CheckerBigInteger.check(BigInteger.valueOf(4));
        assertThrows(CheckerException.class, checker::isOdd);
    }

    @Test
    void testIsDivisibleBy() {
        CheckerBigInteger checker = CheckerBigInteger.check(BigInteger.valueOf(10));
        assertDoesNotThrow(() -> checker.isDivisibleBy(BigInteger.valueOf(5)));
        assertThrows(CheckerException.class, () -> checker.isDivisibleBy(BigInteger.valueOf(3)));
    }

    @Test
    void testIsPowerOfTwo() {
        CheckerBigInteger checker = CheckerBigInteger.check(BigInteger.valueOf(8));
        assertDoesNotThrow(checker::isPowerOfTwo);

        checker = CheckerBigInteger.check(BigInteger.valueOf(10));
        assertThrows(CheckerException.class, checker::isPowerOfTwo);
    }

    @Test
    void testIsProbablePrime() {
        CheckerBigInteger checkerIsPrime = CheckerBigInteger.check(BigInteger.valueOf(13));
        assertDoesNotThrow(() -> checkerIsPrime.isProbablePrime(10));

        CheckerBigInteger checkerIsNotPrime = CheckerBigInteger.check(BigInteger.valueOf(15));
        assertThrows(CheckerException.class, () -> checkerIsNotPrime.isProbablePrime(10));
    }

    @Test
    void testIsPositive() {
        CheckerBigInteger checker = CheckerBigInteger.check(BigInteger.valueOf(1));
        assertDoesNotThrow(checker::isPositive);

        checker = CheckerBigInteger.check(BigInteger.valueOf(-1));
        assertThrows(CheckerException.class, checker::isPositive);
    }

    @Test
    void testIsPositiveOrZero() {
        CheckerBigInteger checker = CheckerBigInteger.check(BigInteger.ZERO);
        assertDoesNotThrow(checker::isPositiveOrZero);

        checker = CheckerBigInteger.check(BigInteger.valueOf(-1));
        assertThrows(CheckerException.class, checker::isPositiveOrZero);
    }

    @Test
    void testIsNegative() {
        CheckerBigInteger checker = CheckerBigInteger.check(BigInteger.valueOf(-1));
        assertDoesNotThrow(checker::isNegative);

        checker = CheckerBigInteger.check(BigInteger.valueOf(1));
        assertThrows(CheckerException.class, checker::isNegative);
    }

    @Test
    void testIsNegativeOrZero() {
        CheckerBigInteger checker = CheckerBigInteger.check(BigInteger.ZERO);
        assertDoesNotThrow(checker::isNegativeOrZero);

        checker = CheckerBigInteger.check(BigInteger.valueOf(1));
        assertThrows(CheckerException.class, checker::isNegativeOrZero);
    }

    @Test
    void testIsZero() {
        CheckerBigInteger checker = CheckerBigInteger.check(BigInteger.ZERO);
        assertDoesNotThrow(checker::isZero);

        checker = CheckerBigInteger.check(BigInteger.valueOf(1));
        assertThrows(CheckerException.class, checker::isZero);
    }

    @Test
    void testIsGreaterThan() {
        CheckerBigInteger checker = CheckerBigInteger.check(BigInteger.valueOf(10));
        assertDoesNotThrow(() -> checker.isGreaterThan(5));
        assertThrows(CheckerException.class, () -> checker.isGreaterThan(10));
    }

    @Test
    void testIsGreaterOrEqualTo() {
        CheckerBigInteger checker = CheckerBigInteger.check(BigInteger.valueOf(10));
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(10));
        assertThrows(CheckerException.class, () -> checker.isGreaterOrEqualTo(11));
    }

    @Test
    void testIsLessThan() {
        CheckerBigInteger checker = CheckerBigInteger.check(BigInteger.valueOf(5));
        assertDoesNotThrow(() -> checker.isLessThan(10));
        assertThrows(CheckerException.class, () -> checker.isLessThan(5));
    }

    @Test
    void testIsLessOrEqualTo() {
        CheckerBigInteger checker = CheckerBigInteger.check(BigInteger.valueOf(5));
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(5));
        assertThrows(CheckerException.class, () -> checker.isLessOrEqualTo(4));
    }
}
