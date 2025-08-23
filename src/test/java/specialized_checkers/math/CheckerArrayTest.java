package specialized_checkers.math;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

class CheckerArrayTest {

    @Test
    void testIsEmptyWithEmptyArray() {
        Integer[] arr = {};
        CheckerArray<Integer> checker = new CheckerArray<>(arr, "testArr");
        assertDoesNotThrow(checker::isEmpty);
    }

    @Test
    void testIsEmptyWithNonEmptyArray() {
        Integer[] arr = {1, 2};
        CheckerArray<Integer> checker = new CheckerArray<>(arr, "testArr");
        assertThrows(Exception.class, checker::isEmpty);
    }

    @Test
    void testIsSortedAscWithSortedArray() {
        Integer[] arr = {1, 2, 3};
        CheckerArray<Integer> checker = new CheckerArray<>(arr, "testArr");
        assertDoesNotThrow(() -> checker.isSortedAsc());
    }

    @Test
    void testIsSortedAscWithUnsortedArray() {
        Integer[] arr = {3, 2, 1};
        CheckerArray<Integer> checker = new CheckerArray<>(arr, "testArr");
        assertThrows(Exception.class, checker::isSortedAsc);
    }

    @Test
    void testIsSortedAscWithComparator() {
        String[] arr = {"a", "b", "c"};
        CheckerArray<String> checker = new CheckerArray<>(arr, "testArr");
        assertDoesNotThrow(() -> checker.isSortedAsc(Comparator.naturalOrder()));
    }

    @Test
    void testIsSortedDescWithSortedArray() {
        Integer[] arr = {3, 2, 1};
        CheckerArray<Integer> checker = new CheckerArray<>(arr, "testArr");
        assertDoesNotThrow(() -> checker.isSortedDesc());
    }

    @Test
    void testIsSortedDescWithUnsortedArray() {
        Integer[] arr = {1, 2, 3};
        CheckerArray<Integer> checker = new CheckerArray<>(arr, "testArr");
        assertThrows(Exception.class, checker::isSortedDesc);
    }

    @Test
    void testIsSortedDescWithComparator() {
        String[] arr = {"c", "b", "a"};
        CheckerArray<String> checker = new CheckerArray<>(arr, "testArr");
        assertDoesNotThrow(() -> checker.isSortedDesc(Comparator.naturalOrder()));
    }

    @Test
    void testIsSufficientPercentageTrue() {
        Integer[] arr = {1, 2, 3, 4};
        CheckerArray<Integer> checker = new CheckerArray<>(arr, "testArr");
        Predicate<Integer> even = x -> x % 2 == 0;
        assertDoesNotThrow(() -> checker.isSufficientPercentage(even, 50.0));
    }

    @Test
    void testIsSufficientPercentageFalse() {
        Integer[] arr = {1, 3, 5, 7};
        CheckerArray<Integer> checker = new CheckerArray<>(arr, "testArr");
        Predicate<Integer> even = x -> x % 2 == 0;
        assertThrows(Exception.class, () -> checker.isSufficientPercentage(even, 25.0));
    }

    @Test
    void testAnyMatchTrue() {
        String[] arr = {"foo", "bar"};
        CheckerArray<String> checker = new CheckerArray<>(arr, "testArr");
        assertDoesNotThrow(() -> checker.anyMatch(s -> s.equals("foo")));
    }

    @Test
    void testAnyMatchFalse() {
        String[] arr = {"foo", "bar"};
        CheckerArray<String> checker = new CheckerArray<>(arr, "testArr");
        assertThrows(Exception.class, () -> checker.anyMatch(s -> s.equals("baz")));
    }

    @Test
    void testAllMatchTrue() {
        Integer[] arr = {2, 4, 6};
        CheckerArray<Integer> checker = new CheckerArray<>(arr, "testArr");
        assertDoesNotThrow(() -> checker.allMatch(x -> x % 2 == 0));
    }

    @Test
    void testAllMatchFalse() {
        Integer[] arr = {2, 3, 4};
        CheckerArray<Integer> checker = new CheckerArray<>(arr, "testArr");
        assertThrows(Exception.class, () -> checker.allMatch(x -> x % 2 == 0));
    }

    @Test
    void testIsSortedAscWithEmptyArray() {
        Integer[] arr = {};
        CheckerArray<Integer> checker = new CheckerArray<>(arr, "testArr");
        assertDoesNotThrow(() -> checker.isSortedAsc());
    }

    @Test
    void testIsSortedDescWithEmptyArray() {
        Integer[] arr = {};
        CheckerArray<Integer> checker = new CheckerArray<>(arr, "testArr");
        assertDoesNotThrow(() -> checker.isSortedDesc());
    }
}