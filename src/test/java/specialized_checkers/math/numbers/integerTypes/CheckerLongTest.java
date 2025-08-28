package specialized_checkers.math.numbers.integerTypes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import util.CheckerException;

class CheckerLongTest {

    @Test
    void testIsEven() {
        CheckerLong checker = new CheckerLong(4, "testEven");
        assertDoesNotThrow(checker::isEven);

        checker = new CheckerLong(5, "testEven");
        assertThrows(CheckerException.class, checker::isEven);
    }

    @Test
    void testIsOdd() {
        CheckerLong checker = new CheckerLong(5, "testOdd");
        assertDoesNotThrow(checker::isOdd);

        checker = new CheckerLong(4, "testOdd");
        assertThrows(CheckerException.class, checker::isOdd);
    }

    @Test
    void testIsPrime() {
        CheckerLong checker = new CheckerLong(7, "testPrime");
        assertDoesNotThrow(checker::isPrime);

        checker = new CheckerLong(8, "testPrime");
        assertThrows(CheckerException.class, checker::isPrime);

        checker = new CheckerLong(1, "testPrime");
        assertThrows(CheckerException.class, checker::isPrime);

        checker = new CheckerLong(2, "testPrime");
        assertDoesNotThrow(checker::isPrime);
    }

    @Test
    void testIsPositive() {
        CheckerLong checker = new CheckerLong(10, "testPositive");
        assertDoesNotThrow(checker::isPositive);

        checker = new CheckerLong(-1, "testPositive");
        assertThrows(CheckerException.class, checker::isPositive);
    }

    @Test
    void testIsPositiveOrZero() {
        CheckerLong checker = new CheckerLong(0, "testPositiveOrZero");
        assertDoesNotThrow(checker::isPositiveOrZero);

        checker = new CheckerLong(-1, "testPositiveOrZero");
        assertThrows(CheckerException.class, checker::isPositiveOrZero);
    }

    @Test
    void testIsNegative() {
        CheckerLong checker = new CheckerLong(-5, "testNegative");
        assertDoesNotThrow(checker::isNegative);

        checker = new CheckerLong(0, "testNegative");
        assertThrows(CheckerException.class, checker::isNegative);
    }

    @Test
    void testIsNegativeOrZero() {
        CheckerLong checker = new CheckerLong(0, "testNegativeOrZero");
        assertDoesNotThrow(checker::isNegativeOrZero);

        checker = new CheckerLong(1, "testNegativeOrZero");
        assertThrows(CheckerException.class, checker::isNegativeOrZero);
    }

    @Test
    void testIsZero() {
        CheckerLong checker = new CheckerLong(0, "testZero");
        assertDoesNotThrow(checker::isZero);

        checker = new CheckerLong(1, "testZero");
        assertThrows(CheckerException.class, checker::isZero);
    }

    @Test
    void testIsGreaterThan() {
        CheckerLong checker = new CheckerLong(10, "testGreaterThan");
        assertDoesNotThrow(() -> checker.isGreaterThan(5));
        assertThrows(CheckerException.class, () -> checker.isGreaterThan(10));
        assertThrows(CheckerException.class, () -> checker.isGreaterThan(15));
    }

    @Test
    void testIsGreaterOrEqualTo() {
        CheckerLong checker = new CheckerLong(10, "testGreaterOrEqualTo");
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(10));
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(5));
        assertThrows(CheckerException.class, () -> checker.isGreaterOrEqualTo(15));
    }

    @Test
    void testIsLessThan() {
        CheckerLong checker = new CheckerLong(5, "testLessThan");
        assertDoesNotThrow(() -> checker.isLessThan(10));
        assertThrows(CheckerException.class, () -> checker.isLessThan(5));
        assertThrows(CheckerException.class, () -> checker.isLessThan(1));
    }

    @Test
    void testIsLessOrEqualTo() {
        CheckerLong checker = new CheckerLong(5, "testLessOrEqualTo");
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(5));
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(10));
        assertThrows(CheckerException.class, () -> checker.isLessOrEqualTo(1));
    }
}
