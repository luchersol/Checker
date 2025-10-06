package com.luchersol.core.specialized_checkers.time.temporal;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.luchersol.core.util.CheckerException;

class CheckerLocalDateTimeTest {

    @Test
    void testIsBefore() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime earlier = now.minusDays(1);
        CheckerLocalDateTime checker = new CheckerLocalDateTime(earlier, "testDate");
        assertDoesNotThrow(() -> checker.isBefore(now));
    }

    @Test
    void testIsBefore_Fails() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plusDays(1);
        CheckerLocalDateTime checker = new CheckerLocalDateTime(later, "testDate");
        assertThrows(CheckerException.class, () -> checker.isBefore(now));
    }

    @Test
    void testIsBeforeOrEqual_Equal() {
        LocalDateTime now = LocalDateTime.now();
        CheckerLocalDateTime checker = new CheckerLocalDateTime(now, "testDate");
        assertDoesNotThrow(() -> checker.isBeforeOrEqual(now));
    }

    @Test
    void testIsAfter() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plusDays(1);
        CheckerLocalDateTime checker = new CheckerLocalDateTime(later, "testDate");
        assertDoesNotThrow(() -> checker.isAfter(now));
    }

    @Test
    void testIsAfter_Fails() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime earlier = now.minusDays(1);
        CheckerLocalDateTime checker = new CheckerLocalDateTime(earlier, "testDate");
        assertThrows(CheckerException.class, () -> checker.isAfter(now));
    }

    @Test
    void testIsAfterOrEqual_Equal() {
        LocalDateTime now = LocalDateTime.now();
        CheckerLocalDateTime checker = new CheckerLocalDateTime(now, "testDate");
        assertDoesNotThrow(() -> checker.isAfterOrEqual(now));
    }

    @Test
    void testInRange() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime middle = start.plusHours(1);
        LocalDateTime end = start.plusHours(2);
        CheckerLocalDateTime checker = new CheckerLocalDateTime(middle, "testDate");
        assertDoesNotThrow(() -> checker.inRange(start, end));
    }

    @Test
    void testInRange_Fails() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(2);
        LocalDateTime outside = start.minusHours(1);
        CheckerLocalDateTime checker = new CheckerLocalDateTime(outside, "testDate");
        assertThrows(CheckerException.class, () -> checker.inRange(start, end));
    }

    @Test
    void testIsPast() {
        LocalDateTime past = LocalDateTime.now().minusDays(1);
        CheckerLocalDateTime checker = new CheckerLocalDateTime(past, "testDate");
        assertDoesNotThrow(checker::isPast);
    }

    @Test
    void testIsFuture() {
        LocalDateTime future = LocalDateTime.now().plusDays(1);
        CheckerLocalDateTime checker = new CheckerLocalDateTime(future, "testDate");
        assertDoesNotThrow(checker::isFuture);
    }

}
