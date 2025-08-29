package specialized_checkers.lambda;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import util.CheckerException;

class CheckerSupplierTest {

    @Test
    void testCheckWithName() {
        Supplier<String> supplier = () -> "hello";
        assertDoesNotThrow(() -> CheckerSupplier.check(supplier, "TestSupplier"));
    }

    @Test
    void testCheckWithoutName() {
        Supplier<Integer> supplier = () -> 42;
        assertDoesNotThrow(() -> CheckerSupplier.check(supplier));
    }

    @Test
    void testApplyWithoutExceptionWithNoException() {
        Supplier<String> supplier = () -> "no exception";
        CheckerSupplier<String> checker = CheckerSupplier.check(supplier);
        assertDoesNotThrow(() -> checker.applyWithoutException());
    }

    @Test
    void testApplyWithoutExceptionWithException() {
        Supplier<String> supplier = () -> { throw new RuntimeException("fail"); };
        CheckerSupplier<String> checker = CheckerSupplier.check(supplier);
        assertThrows(CheckerException.class, () -> checker.applyWithoutException());
    }

    @Test
    void testProducesExpectedWithExpectedValue() {
        Supplier<Integer> supplier = () -> 100;
        CheckerSupplier<Integer> checker = CheckerSupplier.check(supplier);
        assertDoesNotThrow(() -> checker.producesExpected(100));
    }

    @Test
    void testProducesExpectedWithUnexpectedValue() {
        Supplier<Integer> supplier = () -> 200;
        CheckerSupplier<Integer> checker = CheckerSupplier.check(supplier);
        assertThrows(CheckerException.class, () -> checker.producesExpected(100));
    }

    @Test
    void testProducesExpectedWithException() {
        Supplier<Integer> supplier = () -> { throw new RuntimeException(); };
        CheckerSupplier<Integer> checker = CheckerSupplier.check(supplier);
        assertThrows(CheckerException.class, () -> checker.producesExpected(100));
    }

    @Test
    void testProducesNonNullWithNonNull() {
        Supplier<String> supplier = () -> "not null";
        CheckerSupplier<String> checker = CheckerSupplier.check(supplier);
        assertDoesNotThrow(() -> checker.producesNonNull());
    }

    @Test
    void testProducesNonNullWithNull() {
        Supplier<String> supplier = () -> null;
        CheckerSupplier<String> checker = CheckerSupplier.check(supplier);
        assertThrows(CheckerException.class, () -> checker.producesNonNull());
    }

    @Test
    void testProducesNonNullWithException() {
        Supplier<String> supplier = () -> { throw new RuntimeException(); };
        CheckerSupplier<String> checker = CheckerSupplier.check(supplier);
        assertThrows(CheckerException.class, () -> checker.producesNonNull());
    }
}
