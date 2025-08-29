package specialized_checkers.lambda;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import util.CheckerException;

class CheckerRunnableTest {

    @Test
    void testCheckWithRunnableAndName() {
        Runnable runnable = () -> {};
        assertDoesNotThrow(() -> CheckerRunnable.check(runnable, "MyRunnable"));
    }

    @Test
    void testCheckWithRunnableOnly() {
        Runnable runnable = () -> {};
        assertDoesNotThrow(() -> CheckerRunnable.check(runnable));
    }

    @Test
    void testRunWithoutExceptionWithNoException() {
        Runnable runnable = () -> {};
        CheckerRunnable checker = CheckerRunnable.check(runnable);
        assertDoesNotThrow(() -> checker.runWithoutException());
    }

    @Test
    void testRunWithoutExceptionWithException() {
        Runnable runnable = () -> { throw new RuntimeException("fail"); };
        CheckerRunnable checker = CheckerRunnable.check(runnable);
        assertThrows(CheckerException.class, () -> checker.runWithoutException());
    }

    @Test
    void testSelfReturnsThis() {
        Runnable runnable = () -> {};
        CheckerRunnable checker = CheckerRunnable.check(runnable);
        assertSame(checker, checker.self());
    }
}
