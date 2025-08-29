package specialized_checkers.math.numbers.decimalTypes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import util.CheckerException;

class CheckerFloatTest {

    @Test
    void testIsNaN() {
        CheckerFloat checker = CheckerFloat.check(Float.NaN, "testNaN");
        assertDoesNotThrow(() -> checker.isNaN());
    }

    @Test
    void testIsNotNaN() {
        CheckerFloat checker = CheckerFloat.check(1.0, "testNotNaN");
        assertThrows(CheckerException.class, checker::isNaN);
    }

    @Test
    void testIsInfinite_PositiveInfinity() {
        CheckerFloat checker = CheckerFloat.check(Float.POSITIVE_INFINITY, "testInf");
        assertDoesNotThrow(() -> checker.isInfinite());
    }

    @Test
    void testIsInfinite_NegativeInfinity() {
        CheckerFloat checker = CheckerFloat.check(Float.NEGATIVE_INFINITY, "testInf");
        assertDoesNotThrow(() -> checker.isInfinite());
    }

    @Test
    void testIsNotInfinite() {
        CheckerFloat checker = CheckerFloat.check(2.0, "testNotInf");
        assertThrows(CheckerException.class, checker::isInfinite);
    }

    @Test
    void testIsPositive() {
        CheckerFloat checker = CheckerFloat.check(5.0, "positive");
        assertDoesNotThrow(() -> checker.isPositive());
    }

    @Test
    void testIsPositiveOrZero_Positive() {
        CheckerFloat checker = CheckerFloat.check(3.5, "positiveOrZero");
        assertDoesNotThrow(() -> checker.isPositiveOrZero());
    }

    @Test
    void testIsPositiveOrZero_Zero() {
        CheckerFloat checker = CheckerFloat.check(0.0, "positiveOrZero");
        assertDoesNotThrow(() -> checker.isPositiveOrZero());
    }

    @Test
    void testIsNegative() {
        CheckerFloat checker = CheckerFloat.check(-2.0, "negative");
        assertDoesNotThrow(() -> checker.isNegative());
    }

    @Test
    void testIsNegativeOrZero_Negative() {
        CheckerFloat checker = CheckerFloat.check(-1.0, "negativeOrZero");
        assertDoesNotThrow(() -> checker.isNegativeOrZero());
    }

    @Test
    void testIsNegativeOrZero_Zero() {
        CheckerFloat checker = CheckerFloat.check(0.0, "negativeOrZero");
        assertDoesNotThrow(() -> checker.isNegativeOrZero());
    }

    @Test
    void testIsZero() {
        CheckerFloat checker = CheckerFloat.check(0.0, "zero");
        assertDoesNotThrow(() -> checker.isZero());
    }

    @Test
    void testIsGreaterThan() {
        CheckerFloat checker = CheckerFloat.check(10.0, "greaterThan");
        assertDoesNotThrow(() -> checker.isGreaterThan(5));
        assertDoesNotThrow(() -> checker.isGreaterThan(5.0));
        assertThrows(CheckerException.class, () -> checker.isGreaterThan(15));
    }

    @Test
    void testIsGreaterOrEqualTo() {
        CheckerFloat checker = CheckerFloat.check(10.0, "greaterOrEqualTo");
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(10));
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(5.0));
        assertThrows(CheckerException.class, () -> checker.isGreaterOrEqualTo(15));
    }

    @Test
    void testIsLessThan() {
        CheckerFloat checker = CheckerFloat.check(2.0, "lessThan");
        assertDoesNotThrow(() -> checker.isLessThan(5));
        assertDoesNotThrow(() -> checker.isLessThan(5.0));
        assertThrows(CheckerException.class, () -> checker.isLessThan(1));
    }

    @Test
    void testIsLessOrEqualTo() {
        CheckerFloat checker = CheckerFloat.check(2.0, "lessOrEqualTo");
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(2));
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(5.0));
        assertThrows(CheckerException.class, () -> checker.isLessOrEqualTo(1));
    }
}
