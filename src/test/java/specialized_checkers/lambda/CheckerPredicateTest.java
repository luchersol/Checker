package specialized_checkers.lambda;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import util.CheckerException;


class CheckerPredicateTest {

    @Test
    void testCheckWithName() {
        Predicate<Integer> pred = x -> x > 0;
        assertDoesNotThrow(() -> CheckerPredicate.check(pred, "PositiveCheck"));
    }

    @Test
    void testCheckWithoutName() {
        Predicate<String> pred = s -> s != null && !s.isEmpty();
        assertDoesNotThrow(() -> CheckerPredicate.check(pred));
    }

    @Test
    void testActivateAndDeactivateDeepClone() {
        Predicate<Integer> pred = x -> x > 0;
        CheckerPredicate<Integer> checker = CheckerPredicate.check(pred);
        assertSame(checker, checker.activateDeepClone());
        assertSame(checker, checker.deactivateDeepClone());
    }

    @Test
    void testTestWithoutExceptionTrue() {
        Predicate<Integer> pred = x -> x > 0;
        CheckerPredicate<Integer> checker = CheckerPredicate.check(pred);
        assertDoesNotThrow(() -> checker.testWithoutException(5));
    }

    @Test
    void testTestWithoutExceptionFalse() {
        Predicate<Integer> pred = x -> { throw new RuntimeException(); };
        CheckerPredicate<Integer> checker = CheckerPredicate.check(pred);
        assertThrows(CheckerException.class, () -> checker.testWithoutException(5));
    }

    @Test
    void testEvaluatesTrue() {
        Predicate<String> pred = s -> s.equals("hello");
        CheckerPredicate<String> checker = CheckerPredicate.check(pred);
        assertDoesNotThrow(() -> checker.evaluatesTrue("hello"));
    }

    @Test
    void testEvaluatesFalse() {
        Predicate<String> pred = s -> s.equals("hello");
        CheckerPredicate<String> checker = CheckerPredicate.check(pred);
        assertDoesNotThrow(() -> checker.evaluatesFalse("world"));
    }

    @Test
    void testProducesExpectedTrue() {
        Predicate<Integer> pred = x -> x % 2 == 0;
        CheckerPredicate<Integer> checker = CheckerPredicate.check(pred);
        assertDoesNotThrow(() -> checker.producesExpected(4, true));
    }

    @Test
    void testProducesExpectedFalse() {
        Predicate<Integer> pred = x -> x % 2 == 0;
        CheckerPredicate<Integer> checker = CheckerPredicate.check(pred);
        assertDoesNotThrow(() -> checker.producesExpected(5, false));
    }
}
