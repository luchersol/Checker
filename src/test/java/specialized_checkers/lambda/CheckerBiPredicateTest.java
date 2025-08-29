package specialized_checkers.lambda;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BiPredicate;

import org.junit.jupiter.api.Test;

import util.CheckerException;

class CheckerBiPredicateTest {

    @Test
    void testEvaluatesTrueReturnsSelfAndTrue() {
        BiPredicate<Integer, Integer> greater = (a, b) -> a > b;
        CheckerBiPredicate<Integer, Integer> checker = CheckerBiPredicate.check(greater);
        assertDoesNotThrow(() -> checker.evaluatesTrue(5, 3));
    }

    @Test
    void testEvaluatesTrueReturnsFalseWhenPredicateIsFalse() {
        BiPredicate<Integer, Integer> greater = (a, b) -> a > b;
        CheckerBiPredicate<Integer, Integer> checker = CheckerBiPredicate.check(greater);
        assertThrows(CheckerException.class, () -> checker.evaluatesTrue(2, 3));
    }

    @Test
    void testEvaluatesFalseReturnsSelfAndTrue() {
        BiPredicate<String, String> equals = String::equals;
        CheckerBiPredicate<String, String> checker = CheckerBiPredicate.check(equals);
        assertDoesNotThrow(() -> checker.evaluatesFalse("foo", "bar"));
    }

    @Test
    void testEvaluatesFalseReturnsFalseWhenPredicateIsTrue() {
        BiPredicate<String, String> equals = String::equals;
        CheckerBiPredicate<String, String> checker = CheckerBiPredicate.check(equals);
        assertThrows(CheckerException.class, () -> checker.evaluatesFalse("foo", "foo"));
    }

    @Test
    void testProducesExpectedTrue() {
        BiPredicate<Integer, Integer> evenSum = (a, b) -> (a + b) % 2 == 0;
        CheckerBiPredicate<Integer, Integer> checker = CheckerBiPredicate.check(evenSum);
        assertDoesNotThrow(() -> checker.producesExpected(2, 2, true));
    }

    @Test
    void testProducesExpectedFalse() {
        BiPredicate<Integer, Integer> evenSum = (a, b) -> (a + b) % 2 == 0;
        CheckerBiPredicate<Integer, Integer> checker = CheckerBiPredicate.check(evenSum);
        assertDoesNotThrow(() -> checker.producesExpected(2, 3, false));
    }

    @Test
    void testTestWithoutExceptionReturnsSelf() {
        BiPredicate<Object, Object> nonNull = (a, b) -> a != null && b != null;
        CheckerBiPredicate<Object, Object> checker = CheckerBiPredicate.check(nonNull);
        assertDoesNotThrow(() -> checker.testWithoutException("a", "b"));
    }

    @Test
    void testTestWithoutExceptionHandlesException() {
        BiPredicate<Object, Object> throwsException = (a, b) -> { throw new RuntimeException(); };
        CheckerBiPredicate<Object, Object> checker = CheckerBiPredicate.check(throwsException);
        assertThrows(CheckerException.class, () -> checker.testWithoutException("a", "b"));
    }

    @Test
    void testActivateAndDeactivateDeepClone() {
        BiPredicate<StringBuilder, StringBuilder> sameLength = (a, b) -> a.length() == b.length();
        CheckerBiPredicate<StringBuilder, StringBuilder> checker = CheckerBiPredicate.check(sameLength);
        assertSame(checker, checker.activateDeepClone());
        assertSame(checker, checker.deactivateDeepClone());
    }

    @Test
    void testCheckWithName() {
        BiPredicate<Integer, Integer> alwaysTrue = (a, b) -> true;
        CheckerBiPredicate<Integer, Integer> checker = CheckerBiPredicate.check(alwaysTrue, "customName");
        assertNotNull(checker);
    }

    @Test
    void testCheckWithoutName() {
        BiPredicate<Integer, Integer> alwaysTrue = (a, b) -> true;
        CheckerBiPredicate<Integer, Integer> checker = CheckerBiPredicate.check(alwaysTrue);
        assertNotNull(checker);
    }
}
