package com.luchersol.core.specialized_checkers.time.temporal;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.luchersol.core.util.CheckerException;


public class CheckerDateTest {

    private static Date now() {
        return new Date();
    }

    private static Date plusMillis(Date date, long millis) {
        return new Date(date.getTime() + millis);
    }

    private static Date minusMillis(Date date, long millis) {
        return new Date(date.getTime() - millis);
    }

    @Test
    public void testIsBefore() {
        Date d1 = now();
        Date d2 = plusMillis(d1, 1000);
        CheckerDate checker = new CheckerDate(d1, "date1");
        assertDoesNotThrow(() -> checker.isBefore(d2));
    }

    @Test
    public void testIsBeforeFails() {
        Date d1 = now();
        Date d2 = minusMillis(d1, 1000);
        CheckerDate checker = new CheckerDate(d1, "date1");
        assertThrows(CheckerException.class, () -> checker.isBefore(d2));
    }

    @Test
    public void testIsBeforeOrEqual() {
        Date d1 = now();
        CheckerDate checker = new CheckerDate(d1, "date1");
        assertDoesNotThrow(() -> checker.isBeforeOrEqual(d1));
    }

    @Test
    public void testIsAfter() {
        Date d1 = now();
        Date d2 = minusMillis(d1, 1000);
        CheckerDate checker = new CheckerDate(d1, "date1");
        assertDoesNotThrow(() -> checker.isAfter(d2));
    }

    @Test
    public void testIsAfterFails() {
        Date d1 = now();
        Date d2 = plusMillis(d1, 1000);
        CheckerDate checker = new CheckerDate(d1, "date1");
        assertThrows(CheckerException.class, () -> checker.isAfter(d2));
    }

    @Test
    public void testIsAfterOrEqual() {
        Date d1 = now();
        CheckerDate checker = new CheckerDate(d1, "date1");
        assertDoesNotThrow(() -> checker.isAfterOrEqual(d1));
    }

    @Test
    public void testInRange() {
        Date d1 = now();
        Date d2 = plusMillis(d1, 1000);
        Date d3 = plusMillis(d1, 2000);
        CheckerDate checker = new CheckerDate(d2, "date2");
        assertDoesNotThrow(() -> checker.inRange(d1, d3));
    }

    @Test
    public void testInRangeFails() {
        Date d1 = now();
        Date d2 = plusMillis(d1, 1000);
        Date d3 = plusMillis(d1, 2000);
        CheckerDate checker = new CheckerDate(d1, "date1");
        assertThrows(CheckerException.class, () -> checker.inRange(d2, d3));
    }

    @Test
    public void testIsPast() throws InterruptedException {
        Date d1 = now();
        TimeUnit.MILLISECONDS.sleep(10);
        CheckerDate checker = new CheckerDate(d1, "date1");
        assertDoesNotThrow(() -> checker.isPast());
    }

    @Test
    public void testIsFuture() {
        Date d1 = plusMillis(now(), 1000);
        CheckerDate checker = new CheckerDate(d1, "date1");
        assertDoesNotThrow(() -> checker.isFuture());
    }

}
