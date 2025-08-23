package specialized_checkers.time.temporal;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


class CheckerLocalDateTest {

    @Test
    void testIsBefore() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate yesterday = today.minusDays(1);
        CheckerLocalDate checker = new CheckerLocalDate(today, "today");
        assertDoesNotThrow(() -> checker.isBefore(tomorrow));
        assertThrows(Exception.class, () -> checker.isBefore(yesterday));
    }

    @Test
    void testIsBeforeOrEqual() {
        LocalDate today = LocalDate.now();
        CheckerLocalDate checker = new CheckerLocalDate(today, "today");
        assertDoesNotThrow(() -> checker.isBeforeOrEqual(today));
        assertDoesNotThrow(() -> checker.isBeforeOrEqual(today.plusDays(1)));
        assertThrows(Exception.class, () -> checker.isBeforeOrEqual(today.minusDays(1)));
    }

    @Test
    void testIsAfter() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        CheckerLocalDate checker = new CheckerLocalDate(today, "today");
        assertDoesNotThrow(() -> checker.isAfter(yesterday));
        assertThrows(Exception.class, () -> checker.isAfter(today.plusDays(1)));
    }

    @Test
    void testIsAfterOrEqual() {
        LocalDate today = LocalDate.now();
        CheckerLocalDate checker = new CheckerLocalDate(today, "today");
        assertDoesNotThrow(() -> checker.isAfterOrEqual(today));
        assertDoesNotThrow(() -> checker.isAfterOrEqual(today.minusDays(1)));
        assertThrows(Exception.class, () -> checker.isAfterOrEqual(today.plusDays(1)));
    }

    @Test
    void testInRange() {
        LocalDate today = LocalDate.now();
        LocalDate start = today.minusDays(1);
        LocalDate end = today.plusDays(1);
        CheckerLocalDate checker = new CheckerLocalDate(today, "today");
        assertDoesNotThrow(() -> checker.inRange(start, end));
        assertThrows(Exception.class, () -> checker.inRange(today, end));
        assertThrows(Exception.class, () -> checker.inRange(start, today));
    }

    @Test
    void testIsPast() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        CheckerLocalDate checker = new CheckerLocalDate(yesterday, "yesterday");
        assertDoesNotThrow(checker::isPast);

        CheckerLocalDate checkerToday = new CheckerLocalDate(LocalDate.now(), "today");
        assertThrows(Exception.class, checkerToday::isPast);
    }

    @Test
    void testIsPastOrPresent() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        CheckerLocalDate checkerToday = new CheckerLocalDate(today, "today");
        CheckerLocalDate checkerYesterday = new CheckerLocalDate(yesterday, "yesterday");
        assertDoesNotThrow(checkerToday::isPastOrPresent);
        assertDoesNotThrow(checkerYesterday::isPastOrPresent);

        CheckerLocalDate checkerTomorrow = new CheckerLocalDate(today.plusDays(1), "tomorrow");
        assertThrows(Exception.class, checkerTomorrow::isPastOrPresent);
    }

    @Test
    void testIsFuture() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        CheckerLocalDate checker = new CheckerLocalDate(tomorrow, "tomorrow");
        assertDoesNotThrow(checker::isFuture);

        CheckerLocalDate checkerToday = new CheckerLocalDate(LocalDate.now(), "today");
        assertThrows(Exception.class, checkerToday::isFuture);
    }

    @Test
    void testIsFutureOrPresent() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        CheckerLocalDate checkerToday = new CheckerLocalDate(today, "today");
        CheckerLocalDate checkerTomorrow = new CheckerLocalDate(tomorrow, "tomorrow");
        assertDoesNotThrow(checkerToday::isFutureOrPresent);
        assertDoesNotThrow(checkerTomorrow::isFutureOrPresent);

        CheckerLocalDate checkerYesterday = new CheckerLocalDate(today.minusDays(1), "yesterday");
        assertThrows(Exception.class, checkerYesterday::isFutureOrPresent);
    }
}
