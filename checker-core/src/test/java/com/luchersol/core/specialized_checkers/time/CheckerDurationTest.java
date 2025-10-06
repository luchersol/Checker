package com.luchersol.core.specialized_checkers.time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import com.luchersol.core.util.CheckerException;


class CheckerDurationTest {

    @Test
    void testIsPositive() {
        new CheckerDuration(Duration.ofSeconds(10), "duration").isPositive();
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(Duration.ofSeconds(-1), "duration").isPositive()
        );
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(Duration.ZERO, "duration").isPositive()
        );
    }

    @Test
    void testIsNegative() {
        new CheckerDuration(Duration.ofSeconds(-5), "duration").isNegative();
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(Duration.ofSeconds(0), "duration").isNegative()
        );
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(Duration.ofSeconds(5), "duration").isNegative()
        );
    }

    @Test
    void testIsZero() {
        new CheckerDuration(Duration.ZERO, "duration").isZero();
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(Duration.ofSeconds(1), "duration").isZero()
        );
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(Duration.ofSeconds(-1), "duration").isZero()
        );
    }

    @Test
    void testIsGreaterThanTemporalUnit() {
        Duration d = Duration.ofSeconds(10);
        new CheckerDuration(d, "duration").isGreaterThan(ChronoUnit.SECONDS, 5);
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(d, "duration").isGreaterThan(ChronoUnit.SECONDS, 10)
        );
    }

    @Test
    void testIsGreaterOrEqualThanTemporalUnit() {
        Duration d = Duration.ofSeconds(10);
        new CheckerDuration(d, "duration").isGreaterOrEqualThan(ChronoUnit.SECONDS, 10);
        new CheckerDuration(d, "duration").isGreaterOrEqualThan(ChronoUnit.SECONDS, 5);
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(d, "duration").isGreaterOrEqualThan(ChronoUnit.SECONDS, 11)
        );
    }

    @Test
    void testIsLessThanTemporalUnit() {
        Duration d = Duration.ofSeconds(10);
        new CheckerDuration(d, "duration").isLessThan(ChronoUnit.SECONDS, 15);
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(d, "duration").isLessThan(ChronoUnit.SECONDS, 10)
        );
    }

    @Test
    void testIsLessOrEqualThanTemporalUnit() {
        Duration d = Duration.ofSeconds(10);
        new CheckerDuration(d, "duration").isLessOrEqualThan(ChronoUnit.SECONDS, 10);
        new CheckerDuration(d, "duration").isLessOrEqualThan(ChronoUnit.SECONDS, 15);
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(d, "duration").isLessOrEqualThan(ChronoUnit.SECONDS, 9)
        );
    }

    @Test
    void testIsEqualTemporalUnit() {
        Duration d = Duration.ofSeconds(10);
        new CheckerDuration(d, "duration").isEqual(ChronoUnit.SECONDS, 10);
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(d, "duration").isEqual(ChronoUnit.SECONDS, 9)
        );
    }

    @Test
    void testIsGreaterThanDuration() {
        Duration d1 = Duration.ofSeconds(10);
        Duration d2 = Duration.ofSeconds(5);
        new CheckerDuration(d1, "duration").isGreaterThan(d2);
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(d2, "duration").isGreaterThan(d1)
        );
    }

    @Test
    void testIsGreaterOrEqualThanDuration() {
        Duration d1 = Duration.ofSeconds(10);
        Duration d2 = Duration.ofSeconds(10);
        Duration d3 = Duration.ofSeconds(5);
        new CheckerDuration(d1, "duration").isGreaterOrEqualThan(d2);
        new CheckerDuration(d1, "duration").isGreaterOrEqualThan(d3);
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(d3, "duration").isGreaterOrEqualThan(d1)
        );
    }

    @Test
    void testIsLessThanDuration() {
        Duration d1 = Duration.ofSeconds(5);
        Duration d2 = Duration.ofSeconds(10);
        new CheckerDuration(d1, "duration").isLessThan(d2);
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(d2, "duration").isLessThan(d1)
        );
    }

    @Test
    void testIsLessOrEqualThanDuration() {
        Duration d1 = Duration.ofSeconds(5);
        Duration d2 = Duration.ofSeconds(10);
        new CheckerDuration(d1, "duration").isLessOrEqualThan(d2);
        new CheckerDuration(d2, "duration").isLessOrEqualThan(d2);
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(d2, "duration").isLessOrEqualThan(d1)
        );
    }

    @Test
    void testIsEqualDuration() {
        Duration d1 = Duration.ofSeconds(10);
        Duration d2 = Duration.ofSeconds(10);
        Duration d3 = Duration.ofSeconds(5);
        new CheckerDuration(d1, "duration").isEqual(d2);
        assertThrows(CheckerException.class, () ->
            new CheckerDuration(d1, "duration").isEqual(d3)
        );
    }
}
