package specialized_checkers.math.numbers.bigTypes;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import util.CheckerException;


class CheckerBigDecimalTest {

    @Test
    void testIsPositive() {
        CheckerBigDecimal checker = CheckerBigDecimal.check(BigDecimal.valueOf(10));
        assertDoesNotThrow(checker::isPositive);

        CheckerBigDecimal zeroChecker = CheckerBigDecimal.check(BigDecimal.ZERO);
        assertThrows(CheckerException.class, zeroChecker::isPositive);

        CheckerBigDecimal negativeChecker = CheckerBigDecimal.check(BigDecimal.valueOf(-5));
        assertThrows(CheckerException.class, negativeChecker::isPositive);
    }

    @Test
    void testIsPositiveOrZero() {
        CheckerBigDecimal checker = CheckerBigDecimal.check(BigDecimal.valueOf(10));
        assertDoesNotThrow(checker::isPositiveOrZero);

        CheckerBigDecimal zeroChecker = CheckerBigDecimal.check(BigDecimal.ZERO);
        assertDoesNotThrow(zeroChecker::isPositiveOrZero);

        CheckerBigDecimal negativeChecker = CheckerBigDecimal.check(BigDecimal.valueOf(-5));
        assertThrows(CheckerException.class, negativeChecker::isPositiveOrZero);
    }

    @Test
    void testIsNegative() {
        CheckerBigDecimal checker = CheckerBigDecimal.check(BigDecimal.valueOf(-10));
        assertDoesNotThrow(checker::isNegative);

        CheckerBigDecimal zeroChecker = CheckerBigDecimal.check(BigDecimal.ZERO);
        assertThrows(CheckerException.class, zeroChecker::isNegative);

        CheckerBigDecimal positiveChecker = CheckerBigDecimal.check(BigDecimal.valueOf(5));
        assertThrows(CheckerException.class, positiveChecker::isNegative);
    }

    @Test
    void testIsNegativeOrZero() {
        CheckerBigDecimal checker = CheckerBigDecimal.check(BigDecimal.valueOf(-10));
        assertDoesNotThrow(checker::isNegativeOrZero);

        CheckerBigDecimal zeroChecker = CheckerBigDecimal.check(BigDecimal.ZERO);
        assertDoesNotThrow(zeroChecker::isNegativeOrZero);

        CheckerBigDecimal positiveChecker = CheckerBigDecimal.check(BigDecimal.valueOf(5));
        assertThrows(CheckerException.class, positiveChecker::isNegativeOrZero);
    }

    @Test
    void testIsZero() {
        CheckerBigDecimal zeroChecker = CheckerBigDecimal.check(BigDecimal.ZERO);
        assertDoesNotThrow(zeroChecker::isZero);

        CheckerBigDecimal positiveChecker = CheckerBigDecimal.check(BigDecimal.valueOf(1));
        assertThrows(CheckerException.class, positiveChecker::isZero);

        CheckerBigDecimal negativeChecker = CheckerBigDecimal.check(BigDecimal.valueOf(-1));
        assertThrows(CheckerException.class, negativeChecker::isZero);
    }

    @Test
    void testIsGreaterThan() {
        CheckerBigDecimal checker = CheckerBigDecimal.check(BigDecimal.valueOf(10));
        assertDoesNotThrow(() -> checker.isGreaterThan(5));
        assertThrows(CheckerException.class, () -> checker.isGreaterThan(10));
        assertThrows(CheckerException.class, () -> checker.isGreaterThan(15));
    }

    @Test
    void testIsGreaterOrEqualTo() {
        CheckerBigDecimal checker = CheckerBigDecimal.check(BigDecimal.valueOf(10));
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(10));
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(5));
        assertThrows(CheckerException.class, () -> checker.isGreaterOrEqualTo(15));
    }

    @Test
    void testIsLessThan() {
        CheckerBigDecimal checker = CheckerBigDecimal.check(BigDecimal.valueOf(10));
        assertDoesNotThrow(() -> checker.isLessThan(15));
        assertThrows(CheckerException.class, () -> checker.isLessThan(10));
        assertThrows(CheckerException.class, () -> checker.isLessThan(5));
    }

    @Test
    void testIsLessOrEqualTo() {
        CheckerBigDecimal checker = CheckerBigDecimal.check(BigDecimal.valueOf(10));
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(10));
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(15));
        assertThrows(CheckerException.class, () -> checker.isLessOrEqualTo(5));
    }

    @Test
    void testWithDifferentNumberTypes() {
        CheckerBigDecimal checker = CheckerBigDecimal.check(BigDecimal.valueOf(10));
        assertDoesNotThrow(() -> checker.isGreaterThan((byte) 5));
        assertDoesNotThrow(() -> checker.isGreaterThan((short) 5));
        assertDoesNotThrow(() -> checker.isGreaterThan(5L));
        assertDoesNotThrow(() -> checker.isGreaterThan(5.0f));
        assertDoesNotThrow(() -> checker.isGreaterThan(5.0d));
        assertThrows(CheckerException.class, () -> checker.isLessThan((byte) 5));
        assertThrows(CheckerException.class, () -> checker.isLessThan((short) 5));
        assertThrows(CheckerException.class, () -> checker.isLessThan(5L));
        assertThrows(CheckerException.class, () -> checker.isLessThan(5.0f));
        assertThrows(CheckerException.class, () -> checker.isLessThan(5.0d));
    }
}
