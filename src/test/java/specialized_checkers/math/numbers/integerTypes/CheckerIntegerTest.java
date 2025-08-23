package specialized_checkers.math.numbers.integerTypes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CheckerIntegerTest {

    @Test
    void testIsEven() {
        CheckerInteger checker = new CheckerInteger(4, "testEven");
        assertDoesNotThrow(checker::isEven);

        checker = new CheckerInteger(5, "testEven");
        assertThrows(Exception.class, checker::isEven);
    }

    @Test
    void testIsOdd() {
        CheckerInteger checker = new CheckerInteger(5, "testOdd");
        assertDoesNotThrow(checker::isOdd);

        checker = new CheckerInteger(4, "testOdd");
        assertThrows(Exception.class, checker::isOdd);
    }

    @Test
    void testIsPrime() {
        CheckerInteger checker = new CheckerInteger(7, "testPrime");
        assertDoesNotThrow(checker::isPrime);

        checker = new CheckerInteger(8, "testPrime");
        assertThrows(Exception.class, checker::isPrime);

        checker = new CheckerInteger(1, "testPrime");
        assertThrows(Exception.class, checker::isPrime);

        checker = new CheckerInteger(2, "testPrime");
        assertDoesNotThrow(checker::isPrime);
    }

    @Test
    void testIsPositive() {
        CheckerInteger checker = new CheckerInteger(10, "testPositive");
        assertDoesNotThrow(checker::isPositive);

        checker = new CheckerInteger(-1, "testPositive");
        assertThrows(Exception.class, checker::isPositive);
    }

    @Test
    void testIsPositiveOrZero() {
        CheckerInteger checker = new CheckerInteger(0, "testPositiveOrZero");
        assertDoesNotThrow(checker::isPositiveOrZero);

        checker = new CheckerInteger(-1, "testPositiveOrZero");
        assertThrows(Exception.class, checker::isPositiveOrZero);
    }

    @Test
    void testIsNegative() {
        CheckerInteger checker = new CheckerInteger(-5, "testNegative");
        assertDoesNotThrow(checker::isNegative);

        checker = new CheckerInteger(0, "testNegative");
        assertThrows(Exception.class, checker::isNegative);
    }

    @Test
    void testIsNegativeOrZero() {
        CheckerInteger checker = new CheckerInteger(0, "testNegativeOrZero");
        assertDoesNotThrow(checker::isNegativeOrZero);

        checker = new CheckerInteger(1, "testNegativeOrZero");
        assertThrows(Exception.class, checker::isNegativeOrZero);
    }

    @Test
    void testIsZero() {
        CheckerInteger checker = new CheckerInteger(0, "testZero");
        assertDoesNotThrow(checker::isZero);

        checker = new CheckerInteger(1, "testZero");
        assertThrows(Exception.class, checker::isZero);
    }

    @Test
    void testIsGreaterThan() {
        CheckerInteger checker = new CheckerInteger(10, "testGreaterThan");
        assertDoesNotThrow(() -> checker.isGreaterThan(5));
        assertThrows(Exception.class, () -> checker.isGreaterThan(10));
        assertThrows(Exception.class, () -> checker.isGreaterThan(15));
    }

    @Test
    void testIsGreaterOrEqualTo() {
        CheckerInteger checker = new CheckerInteger(10, "testGreaterOrEqualTo");
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(10));
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(5));
        assertThrows(Exception.class, () -> checker.isGreaterOrEqualTo(15));
    }

    @Test
    void testIsLessThan() {
        CheckerInteger checker = new CheckerInteger(5, "testLessThan");
        assertDoesNotThrow(() -> checker.isLessThan(10));
        assertThrows(Exception.class, () -> checker.isLessThan(5));
        assertThrows(Exception.class, () -> checker.isLessThan(1));
    }

    @Test
    void testIsLessOrEqualTo() {
        CheckerInteger checker = new CheckerInteger(5, "testLessOrEqualTo");
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(5));
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(10));
        assertThrows(Exception.class, () -> checker.isLessOrEqualTo(1));
    }
}