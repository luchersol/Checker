package com.luchersol.core.specialized_checkers.lambda;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.luchersol.core.util.CheckerException;


class CheckerFunctionTest {

    @Test
    void testCheckWithName() {
        Function<Integer, Integer> square = x -> x * x;
        assertDoesNotThrow(() -> CheckerFunction.check(square, "SquareFunction"));
    }

    @Test
    void testCheckWithoutName() {
        Function<String, Integer> length = String::length;
        assertDoesNotThrow(() -> CheckerFunction.check(length));
    }

    @Test
    void testActivateAndDeactivateDeepClone() {
        Function<String, String> identity = s -> s;
        CheckerFunction<String, String> checker = CheckerFunction.check(identity);
        assertSame(checker, checker.activateDeepClone());
        assertSame(checker, checker.deactivateDeepClone());
    }

    @Test
    void testGetInputWithoutDeepClone() {
        Function<String, String> identity = s -> s;
        CheckerFunction<String, String> checker = CheckerFunction.check(identity);
        String input = "test";
        assertSame(input, checker.getInput(input));
    }

    @Test
    void testApplyWithoutExceptionSuccess() {
        Function<Integer, Integer> increment = x -> x + 1;
        CheckerFunction<Integer, Integer> checker = CheckerFunction.check(increment);
        assertDoesNotThrow(() -> checker.applyWithoutException(5));
    }

    @Test
    void testApplyWithoutExceptionThrows() {
        Function<Integer, Integer> fail = x -> { throw new RuntimeException(); };
        CheckerFunction<Integer, Integer> checker = CheckerFunction.check(fail);
        assertThrows(CheckerException.class, () -> checker.applyWithoutException(1));
    }

    @Test
    void testProducesExpectedTrue() {
        Function<String, Integer> length = String::length;
        CheckerFunction<String, Integer> checker = CheckerFunction.check(length);
        assertDoesNotThrow(() -> checker.producesExpected("abc", 3));
    }

    @Test
    void testProducesExpectedFalse() {
        Function<String, Integer> length = String::length;
        CheckerFunction<String, Integer> checker = CheckerFunction.check(length);
        assertThrows(CheckerException.class, () -> checker.producesExpected("abc", 5));
    }

    @Test
    void testProducesNonNullTrue() {
        Function<String, Integer> length = String::length;
        CheckerFunction<String, Integer> checker = CheckerFunction.check(length);
        assertDoesNotThrow(() -> checker.producesNonNull("hello"));
    }

    @Test
    void testProducesNonNullFalse() {
        Function<String, Object> alwaysNull = s -> null;
        CheckerFunction<String, Object> checker = CheckerFunction.check(alwaysNull);
        assertThrows(CheckerException.class, () -> checker.producesNonNull("test"));
    }
}
