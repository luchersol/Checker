package com.luchersol.core.specialized_checkers.lambda;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import com.luchersol.core.util.CheckerException;

class CheckerBiFunctionTest {

    @Test
    void testProducesExpectedWithCorrectResult() {
        BiFunction<Integer, Integer, Integer> sum = Integer::sum;
        CheckerBiFunction<Integer, Integer, Integer> checker = CheckerBiFunction.check(sum, "Sum");
        assertDoesNotThrow(() -> checker.producesExpected(2, 3, 5));
    }

    @Test
    void testProducesExpectedWithIncorrectResult() {
        BiFunction<Integer, Integer, Integer> sum = Integer::sum;
        CheckerBiFunction<Integer, Integer, Integer> checker = CheckerBiFunction.check(sum);
        assertThrows(CheckerException.class, () -> checker.producesExpected(2, 3, 6));
    }

    @Test
    void testApplyWithoutExceptionWithNoException() {
        BiFunction<String, String, String> concat = String::concat;
        CheckerBiFunction<String, String, String> checker = CheckerBiFunction.check(concat);
        assertDoesNotThrow(() -> checker.applyWithoutException("Hello", "World"));
    }

    @Test
    void testApplyWithoutExceptionWithException() {
        BiFunction<String, String, String> throwsException = (a, b) -> { throw new RuntimeException(); };
        CheckerBiFunction<String, String, String> checker = CheckerBiFunction.check(throwsException);
        assertThrows(CheckerException.class, () -> checker.applyWithoutException("a", "b"));
    }

    @Test
    void testProducesNonNullWithNonNullResult() {
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        CheckerBiFunction<Integer, Integer, Integer> checker = CheckerBiFunction.check(multiply);
        assertDoesNotThrow(() -> checker.producesNonNull(2, 4));
    }

    @Test
    void testProducesNonNullWithNullResult() {
        BiFunction<Integer, Integer, Integer> returnsNull = (a, b) -> null;
        CheckerBiFunction<Integer, Integer, Integer> checker = CheckerBiFunction.check(returnsNull);
        assertThrows(CheckerException.class, () -> checker.producesNonNull(1, 2));
    }

    @Test
    void testActivateAndDeactivateDeepClone() {
        BiFunction<StringBuilder, StringBuilder, String> concat = (a, b) -> a.append(b).toString();
        CheckerBiFunction<StringBuilder, StringBuilder, String> checker = CheckerBiFunction.check(concat);

        assertSame(checker, checker.activateDeepClone());
        assertSame(checker, checker.deactivateDeepClone());
    }
}
