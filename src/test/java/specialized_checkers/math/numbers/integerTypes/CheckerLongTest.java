package specialized_checkers.math.numbers.integerTypes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import util.CheckerException;

class CheckerLongTest {

    @Test
    void testIsEven() {
        CheckerLong checker = CheckerLong.check(4, "testEven");
        assertDoesNotThrow(checker::isEven);

        checker = CheckerLong.check(5, "testEven");
        assertThrows(CheckerException.class, checker::isEven);
    }

    @Test
    void testIsOdd() {
        CheckerLong checker = CheckerLong.check(5, "testOdd");
        assertDoesNotThrow(checker::isOdd);

        checker = CheckerLong.check(4, "testOdd");
        assertThrows(CheckerException.class, checker::isOdd);
    }

    @Test
    void testIsPrime() {
        CheckerLong checker = CheckerLong.check(7, "testPrime");
        assertDoesNotThrow(checker::isPrime);

        checker = CheckerLong.check(8, "testPrime");
        assertThrows(CheckerException.class, checker::isPrime);

        checker = CheckerLong.check(1, "testPrime");
        assertThrows(CheckerException.class, checker::isPrime);

        checker = CheckerLong.check(2, "testPrime");
        assertDoesNotThrow(checker::isPrime);
    }

    @Test
    void testIsPositive() {
        CheckerLong checker = CheckerLong.check(10, "testPositive");
        assertDoesNotThrow(checker::isPositive);

        checker = CheckerLong.check(-1, "testPositive");
        assertThrows(CheckerException.class, checker::isPositive);
    }

    @Test
    void testIsPositiveOrZero() {
        CheckerLong checker = CheckerLong.check(0, "testPositiveOrZero");
        assertDoesNotThrow(checker::isPositiveOrZero);

        checker = CheckerLong.check(-1, "testPositiveOrZero");
        assertThrows(CheckerException.class, checker::isPositiveOrZero);
    }

    @Test
    void testIsNegative() {
        CheckerLong checker = CheckerLong.check(-5, "testNegative");
        assertDoesNotThrow(checker::isNegative);

        checker = CheckerLong.check(0, "testNegative");
        assertThrows(CheckerException.class, checker::isNegative);
    }

    @Test
    void testIsNegativeOrZero() {
        CheckerLong checker = CheckerLong.check(0, "testNegativeOrZero");
        assertDoesNotThrow(checker::isNegativeOrZero);

        checker = CheckerLong.check(1, "testNegativeOrZero");
        assertThrows(CheckerException.class, checker::isNegativeOrZero);
    }

    @Test
    void testIsZero() {
        CheckerLong checker = CheckerLong.check(0, "testZero");
        assertDoesNotThrow(checker::isZero);

        checker = CheckerLong.check(1, "testZero");
        assertThrows(CheckerException.class, checker::isZero);
    }

    @Test
    void testIsGreaterThan() {
        CheckerLong checker = CheckerLong.check(10, "testGreaterThan");
        assertDoesNotThrow(() -> checker.isGreaterThan(5));
        assertThrows(CheckerException.class, () -> checker.isGreaterThan(10));
        assertThrows(CheckerException.class, () -> checker.isGreaterThan(15));
    }

    @Test
    void testIsGreaterOrEqualTo() {
        CheckerLong checker = CheckerLong.check(10, "testGreaterOrEqualTo");
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(10));
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(5));
        assertThrows(CheckerException.class, () -> checker.isGreaterOrEqualTo(15));
    }

    @Test
    void testIsLessThan() {
        CheckerLong checker = CheckerLong.check(5, "testLessThan");
        assertDoesNotThrow(() -> checker.isLessThan(10));
        assertThrows(CheckerException.class, () -> checker.isLessThan(5));
        assertThrows(CheckerException.class, () -> checker.isLessThan(1));
    }

    @Test
    void testIsLessOrEqualTo() {
        CheckerLong checker = CheckerLong.check(5, "testLessOrEqualTo");
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(5));
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(10));
        assertThrows(CheckerException.class, () -> checker.isLessOrEqualTo(1));
    }
}
