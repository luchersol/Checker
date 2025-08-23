package specialized_checkers.time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;


class CheckerPeriodTest {

    @Test
    void testIsPositive() {
        CheckerPeriod checker = new CheckerPeriod(Period.of(1, 2, 3), "positivePeriod");
        assertDoesNotThrow(checker::isPositive);

        CheckerPeriod zeroChecker = new CheckerPeriod(Period.ZERO, "zeroPeriod");
        assertThrows(Exception.class, zeroChecker::isPositive);

        CheckerPeriod negativeChecker = new CheckerPeriod(Period.of(-1, 0, 0), "negativePeriod");
        assertThrows(Exception.class, negativeChecker::isPositive);
    }

    @Test
    void testIsNegative() {
        CheckerPeriod checker = new CheckerPeriod(Period.of(-1, 0, 0), "negativePeriod");
        assertDoesNotThrow(checker::isNegative);

        CheckerPeriod positiveChecker = new CheckerPeriod(Period.of(1, 0, 0), "positivePeriod");
        assertThrows(Exception.class, positiveChecker::isNegative);

        CheckerPeriod zeroChecker = new CheckerPeriod(Period.ZERO, "zeroPeriod");
        assertThrows(Exception.class, zeroChecker::isNegative);
    }

    @Test
    void testIsZero() {
        CheckerPeriod checker = new CheckerPeriod(Period.ZERO, "zeroPeriod");
        assertDoesNotThrow(checker::isZero);

        CheckerPeriod nonZeroChecker = new CheckerPeriod(Period.of(0, 1, 0), "nonZeroPeriod");
        assertThrows(Exception.class, nonZeroChecker::isZero);
    }

    @Test
    void testIsGreaterThanTemporalUnit() {
        CheckerPeriod checker = new CheckerPeriod(Period.of(2, 0, 0), "period");
        assertDoesNotThrow(() -> checker.isGreaterThan(ChronoUnit.YEARS, 1));
        assertThrows(Exception.class, () -> checker.isGreaterThan(ChronoUnit.YEARS, 2));
        assertThrows(Exception.class, () -> checker.isGreaterThan(ChronoUnit.YEARS, 3));
    }

    @Test
    void testIsGreaterOrEqualThanTemporalUnit() {
        CheckerPeriod checker = new CheckerPeriod(Period.of(2, 0, 0), "period");
        assertDoesNotThrow(() -> checker.isGreaterOrEqualThan(ChronoUnit.YEARS, 2));
        assertDoesNotThrow(() -> checker.isGreaterOrEqualThan(ChronoUnit.YEARS, 1));
        assertThrows(Exception.class, () -> checker.isGreaterOrEqualThan(ChronoUnit.YEARS, 3));
    }

    @Test
    void testIsLessThanTemporalUnit() {
        CheckerPeriod checker = new CheckerPeriod(Period.of(1, 0, 0), "period");
        assertDoesNotThrow(() -> checker.isLessThan(ChronoUnit.YEARS, 2));
        assertThrows(Exception.class, () -> checker.isLessThan(ChronoUnit.YEARS, 1));
        assertThrows(Exception.class, () -> checker.isLessThan(ChronoUnit.YEARS, 0));
    }

    @Test
    void testIsLessOrEqualThanTemporalUnit() {
        CheckerPeriod checker = new CheckerPeriod(Period.of(1, 0, 0), "period");
        assertDoesNotThrow(() -> checker.isLessOrEqualThan(ChronoUnit.YEARS, 1));
        assertDoesNotThrow(() -> checker.isLessOrEqualThan(ChronoUnit.YEARS, 2));
        assertThrows(Exception.class, () -> checker.isLessOrEqualThan(ChronoUnit.YEARS, 0));
    }

    @Test
    void testIsEqualTemporalUnit() {
        CheckerPeriod checker = new CheckerPeriod(Period.of(1, 2, 3), "period");
        assertDoesNotThrow(() -> checker.isEqual(ChronoUnit.YEARS, 1));
        assertDoesNotThrow(() -> checker.isEqual(ChronoUnit.MONTHS, 2));
        assertDoesNotThrow(() -> checker.isEqual(ChronoUnit.DAYS, 3));
        assertThrows(Exception.class, () -> checker.isEqual(ChronoUnit.YEARS, 2));
    }

    @Test
    void testIsGreaterThanPeriod() {
        CheckerPeriod checker = new CheckerPeriod(Period.of(2, 0, 0), "period");
        assertDoesNotThrow(() -> checker.isGreaterThan(Period.of(1, 0, 0)));
        assertThrows(Exception.class, () -> checker.isGreaterThan(Period.of(2, 0, 0)));
        assertThrows(Exception.class, () -> checker.isGreaterThan(Period.of(3, 0, 0)));
    }

    @Test
    void testIsGreaterOrEqualThanPeriod() {
        CheckerPeriod checker = new CheckerPeriod(Period.of(2, 0, 0), "period");
        assertDoesNotThrow(() -> checker.isGreaterOrEqualThan(Period.of(1, 0, 0)));
        assertDoesNotThrow(() -> checker.isGreaterOrEqualThan(Period.of(2, 0, 0)));
        assertThrows(Exception.class, () -> checker.isGreaterOrEqualThan(Period.of(3, 0, 0)));
    }

    @Test
    void testIsLessThanPeriod() {
        CheckerPeriod checker = new CheckerPeriod(Period.of(1, 0, 0), "period");
        assertDoesNotThrow(() -> checker.isLessThan(Period.of(2, 0, 0)));
        assertThrows(Exception.class, () -> checker.isLessThan(Period.of(1, 0, 0)));
        assertThrows(Exception.class, () -> checker.isLessThan(Period.of(0, 0, 0)));
    }

    @Test
    void testIsLessOrEqualThanPeriod() {
        CheckerPeriod checker = new CheckerPeriod(Period.of(1, 0, 0), "period");
        assertDoesNotThrow(() -> checker.isLessOrEqualThan(Period.of(1, 0, 0)));
        assertDoesNotThrow(() -> checker.isLessOrEqualThan(Period.of(2, 0, 0)));
        assertThrows(Exception.class, () -> checker.isLessOrEqualThan(Period.of(0, 0, 0)));
    }

    @Test
    void testIsEqualPeriod() {
        CheckerPeriod checker = new CheckerPeriod(Period.of(1, 2, 3), "period");
        assertDoesNotThrow(() -> checker.isEqual(Period.of(1, 2, 3)));
        assertThrows(Exception.class, () -> checker.isEqual(Period.of(1, 2, 4)));
    }
}
