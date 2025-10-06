package com.luchersol.core.specialized_checkers.math.numbers.decimalTypes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.luchersol.core.util.CheckerException;

class CheckerDoubleTest {

    @Test
    void testIsNaN() {
        CheckerDouble checker = new CheckerDouble(Double.NaN, "testNaN");
        assertDoesNotThrow(() -> checker.isNaN());
    }

    @Test
    void testIsNotNaN() {
        CheckerDouble checker = new CheckerDouble(1.0, "testNotNaN");
        assertThrows(CheckerException.class, checker::isNaN);
    }

    @Test
    void testIsInfinite_PositiveInfinity() {
        CheckerDouble checker = new CheckerDouble(Double.POSITIVE_INFINITY, "testInf");
        assertDoesNotThrow(() -> checker.isInfinite());
    }

    @Test
    void testIsInfinite_NegativeInfinity() {
        CheckerDouble checker = new CheckerDouble(Double.NEGATIVE_INFINITY, "testInf");
        assertDoesNotThrow(() -> checker.isInfinite());
    }

    @Test
    void testIsNotInfinite() {
        CheckerDouble checker = new CheckerDouble(2.0, "testNotInf");
        assertThrows(CheckerException.class, checker::isInfinite);
    }

    @Test
    void testIsPositive() {
        CheckerDouble checker = new CheckerDouble(5.0, "positive");
        assertDoesNotThrow(() -> checker.isPositive());
    }

    @Test
    void testIsPositiveOrZero_Positive() {
        CheckerDouble checker = new CheckerDouble(3.5, "positiveOrZero");
        assertDoesNotThrow(() -> checker.isPositiveOrZero());
    }

    @Test
    void testIsPositiveOrZero_Zero() {
        CheckerDouble checker = new CheckerDouble(0.0, "positiveOrZero");
        assertDoesNotThrow(() -> checker.isPositiveOrZero());
    }

    @Test
    void testIsNegative() {
        CheckerDouble checker = new CheckerDouble(-2.0, "negative");
        assertDoesNotThrow(() -> checker.isNegative());
    }

    @Test
    void testIsNegativeOrZero_Negative() {
        CheckerDouble checker = new CheckerDouble(-1.0, "negativeOrZero");
        assertDoesNotThrow(() -> checker.isNegativeOrZero());
    }

    @Test
    void testIsNegativeOrZero_Zero() {
        CheckerDouble checker = new CheckerDouble(0.0, "negativeOrZero");
        assertDoesNotThrow(() -> checker.isNegativeOrZero());
    }

    @Test
    void testIsZero() {
        CheckerDouble checker = new CheckerDouble(0.0, "zero");
        assertDoesNotThrow(() -> checker.isZero());
    }

    @Test
    void testIsGreaterThan() {
        CheckerDouble checker = new CheckerDouble(10.0, "greaterThan");
        assertDoesNotThrow(() -> checker.isGreaterThan(5));
        assertDoesNotThrow(() -> checker.isGreaterThan(5.0));
        assertThrows(CheckerException.class, () -> checker.isGreaterThan(15));
    }

    @Test
    void testIsGreaterOrEqualTo() {
        CheckerDouble checker = new CheckerDouble(10.0, "greaterOrEqualTo");
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(10));
        assertDoesNotThrow(() -> checker.isGreaterOrEqualTo(5.0));
        assertThrows(CheckerException.class, () -> checker.isGreaterOrEqualTo(15));
    }

    @Test
    void testIsLessThan() {
        CheckerDouble checker = new CheckerDouble(2.0, "lessThan");
        assertDoesNotThrow(() -> checker.isLessThan(5));
        assertDoesNotThrow(() -> checker.isLessThan(5.0));
        assertThrows(CheckerException.class, () -> checker.isLessThan(1));
    }

    @Test
    void testIsLessOrEqualTo() {
        CheckerDouble checker = new CheckerDouble(2.0, "lessOrEqualTo");
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(2));
        assertDoesNotThrow(() -> checker.isLessOrEqualTo(5.0));
        assertThrows(CheckerException.class, () -> checker.isLessOrEqualTo(1));
    }
}
