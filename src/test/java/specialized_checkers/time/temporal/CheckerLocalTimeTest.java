package specialized_checkers.time.temporal;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class CheckerLocalTimeTest {

    @Test
    void testIsBefore() {
        LocalTime t1 = LocalTime.of(10, 0);
        LocalTime t2 = LocalTime.of(12, 0);
        CheckerLocalTime checker = new CheckerLocalTime(t1, "testTime");
        assertDoesNotThrow(() -> checker.isBefore(t2));
    }

    @Test
    void testIsBeforeOrEqual_Before() {
        LocalTime t1 = LocalTime.of(8, 0);
        LocalTime t2 = LocalTime.of(9, 0);
        CheckerLocalTime checker = new CheckerLocalTime(t1, "testTime");
        assertDoesNotThrow(() -> checker.isBeforeOrEqual(t2));
    }

    @Test
    void testIsBeforeOrEqual_Equal() {
        LocalTime t1 = LocalTime.of(9, 0);
        CheckerLocalTime checker = new CheckerLocalTime(t1, "testTime");
        assertDoesNotThrow(() -> checker.isBeforeOrEqual(t1));
    }

    @Test
    void testIsAfter() {
        LocalTime t1 = LocalTime.of(15, 0);
        LocalTime t2 = LocalTime.of(13, 0);
        CheckerLocalTime checker = new CheckerLocalTime(t1, "testTime");
        assertDoesNotThrow(() -> checker.isAfter(t2));
    }

    @Test
    void testIsAfterOrEqual_After() {
        LocalTime t1 = LocalTime.of(18, 0);
        LocalTime t2 = LocalTime.of(17, 0);
        CheckerLocalTime checker = new CheckerLocalTime(t1, "testTime");
        assertDoesNotThrow(() -> checker.isAfterOrEqual(t2));
    }

    @Test
    void testIsAfterOrEqual_Equal() {
        LocalTime t1 = LocalTime.of(20, 0);
        CheckerLocalTime checker = new CheckerLocalTime(t1, "testTime");
        assertDoesNotThrow(() -> checker.isAfterOrEqual(t1));
    }

    @Test
    void testInRange_True() {
        LocalTime t1 = LocalTime.of(14, 0);
        LocalTime start = LocalTime.of(13, 0);
        LocalTime end = LocalTime.of(15, 0);
        CheckerLocalTime checker = new CheckerLocalTime(t1, "testTime");
        assertDoesNotThrow(() -> checker.inRange(start, end));
    }

    @Test
    void testInRange_False() {
        LocalTime t1 = LocalTime.of(16, 0);
        LocalTime start = LocalTime.of(13, 0);
        LocalTime end = LocalTime.of(15, 0);
        CheckerLocalTime checker = new CheckerLocalTime(t1, "testTime");
        assertThrows(Exception.class, () -> checker.inRange(start, end));
    }

    @Test
    void testIsPast() {
        LocalTime past = LocalTime.now().minusHours(1);
        CheckerLocalTime checker = new CheckerLocalTime(past, "testTime");
        assertDoesNotThrow(checker::isPast);
    }

    @Test
    void testIsFuture() {
        LocalTime future = LocalTime.now().plusHours(1);
        CheckerLocalTime checker = new CheckerLocalTime(future, "testTime");
        assertDoesNotThrow(checker::isFuture);
    }

}
