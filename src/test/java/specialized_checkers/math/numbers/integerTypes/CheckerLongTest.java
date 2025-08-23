package specialized_checkers.math.numbers.integerTypes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CheckerLongTest {

    @Test
    void testIsEven() {
        CheckerLong checker = new CheckerLong(4, "testEven");
        assertDoesNotThrow(checker::isEven);

        checker = new CheckerLong(5, "testEven");
        assertThrows(Exception.class, checker::isEven);
    }

    @Test
    void testIsOdd() {
        CheckerLong checker = new CheckerLong(5, "testOdd");
        assertDoesNotThrow(checker::isOdd);

        checker = new CheckerLong(4, "testOdd");
        assertThrows(Exception.class, checker::isOdd);
    }

    @Test
    void testIsPrime() {
        CheckerLong checker = new CheckerLong(7, "testPrime");
        assertDoesNotThrow(checker::isPrime);

        checker = new CheckerLong(8, "testPrime");
        assertThrows(Exception.class, checker::isPrime);

        checker = new CheckerLong(1, "testPrime");
        assertThrows(Exception.class, checker::isPrime);

        checker = new CheckerLong(2, "testPrime");
        assertDoesNotThrow(checker::isPrime);
    }

    @Test
    void testIsPositive() {
        CheckerLong checker = new CheckerLong(10, "testPositive");
        assertDoesNotThrow(checker::isPositive);

        checker = new CheckerLong(-1, "testPositive");
        assertThrows(Exception.class, checker::isPositive);
    }

    @Test
    void testIsPositiveOrZero() {
        CheckerLong checker = new CheckerLong(0, "testPositiveOrZero");
        assertDoesNotThrow(checker::isPositiveOrZero);

        checker = new CheckerLong(-1, "testPositiveOrZero");
        assertThrows(Exception.class, checker::isPositiveOrZero);
    }

    @Test
    void testIsNegative() {
        CheckerLong checker = new CheckerLong(-5, "testNegative");
        assertDoesNotThrow(checker::isNegative);

        checker = new CheckerLong(0, "testNegative");
        assertThrows(Exception.class, checker::isNegative);
    }

    @Test
    void testIsNegativeOrZero() {
        CheckerLong checker = new CheckerLong(0, "testNegativeOrZero");
        assertDoesNotThrow(checker::isNegativeOrZero);

        checker = new CheckerLong(1, "testNegativeOrZero");
        assertThrows(Exception.class, checker::isNegativeOrZero);
    }

    @Test
    void testIsZero() {
        CheckerLong checker = new CheckerLong(0, "testZero");
        assertDoesNotThrow(checker::isZero);

        checker = new CheckerLong(1, "testZero");
        assertThrows(Exception.class, checker::isZero);
    }

    @Test
    void testIsGreaterThan() {
        CheckerLong checker = new CheckerLong(10, "testGreaterThan");
        assertDoesNotThrow(() -> checker.isGreaterThan(5));
        assertThrows(Exception.class, () -> checker.isGreaterThan(10));
        assertThrows(Exception.class, () -> checker.isGreaterThan(15));
    }

    @Test
    void testIsGreaterOrEqualTo() {
        CheckerLong checker = new CheckerLong(10, "testGreaterOrEqualTo");
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(10));
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(5));
        assertThrows(Exception.class, () -> checker.isGreaterOrEqualTo(15));
    }

    @Test
    void testIsLessThan() {
        CheckerLong checker = new CheckerLong(5, "testLessThan");
        assertDoesNotThrow(() -> checker.isLessThan(10));
        assertThrows(Exception.class, () -> checker.isLessThan(5));
        assertThrows(Exception.class, () -> checker.isLessThan(1));
    }

    @Test
    void testIsLessOrEqualTo() {
        CheckerLong checker = new CheckerLong(5, "testLessOrEqualTo");
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(5));
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(10));
        assertThrows(Exception.class, () -> checker.isLessOrEqualTo(1));
    }
}