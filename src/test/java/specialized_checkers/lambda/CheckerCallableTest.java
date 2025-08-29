package specialized_checkers.lambda;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.Callable;

import org.junit.jupiter.api.Test;

import util.CheckerException;

class CheckerCallableTest {

    @Test
    void testCheckWithName() throws Exception {
        Callable<String> callable = () -> "hello";
        CheckerCallable<String> checker = CheckerCallable.check(callable, "TestCallable");
        assertNotNull(checker);
    }

    @Test
    void testCheckWithoutName() throws Exception {
        Callable<Integer> callable = () -> 42;
        CheckerCallable<Integer> checker = CheckerCallable.check(callable);
        assertNotNull(checker);
    }

    @Test
    void testCallWithoutExceptionSuccess() {
        Callable<String> callable = () -> "no exception";
        CheckerCallable<String> checker = CheckerCallable.check(callable);
        assertDoesNotThrow(() -> checker.callWithoutException());
    }

    @Test
    void testCallWithoutExceptionFailure() {
        Callable<String> callable = () -> { throw new RuntimeException("fail"); };
        CheckerCallable<String> checker = CheckerCallable.check(callable);
        assertThrows(CheckerException.class, () -> checker.callWithoutException());
    }

    @Test
    void testProducesExpectedSuccess() {
        Callable<Integer> callable = () -> 100;
        CheckerCallable<Integer> checker = CheckerCallable.check(callable);
        assertDoesNotThrow(() -> checker.producesExpected(100));
    }

    @Test
    void testProducesExpectedFailure() {
        Callable<Integer> callable = () -> 200;
        CheckerCallable<Integer> checker = CheckerCallable.check(callable);
        assertThrows(CheckerException.class, () -> checker.producesExpected(100));
    }

    @Test
    void testProducesNonNullSuccess() {
        Callable<String> callable = () -> "not null";
        CheckerCallable<String> checker = CheckerCallable.check(callable);
        assertDoesNotThrow(() -> checker.producesNonNull());
    }

    @Test
    void testProducesNonNullFailure() {
        Callable<String> callable = () -> null;
        CheckerCallable<String> checker = CheckerCallable.check(callable);
        assertThrows(CheckerException.class, () -> checker.producesNonNull());
    }

    @Test
    void testProducesExpectedWithException() {
        Callable<String> callable = () -> { throw new Exception("error"); };
        CheckerCallable<String> checker = CheckerCallable.check(callable);
        assertThrows(CheckerException.class, () -> checker.producesExpected("anything"));
    }
}
