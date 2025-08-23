package specialized_checkers.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class CheckerSetTest {

    @Test
    void testIsEmptyTrue() {
        Set<Integer> set = new HashSet<>();
        CheckerSet<Integer> checker = new CheckerSet<>(set, "testSet");
        assertDoesNotThrow(checker::isEmpty);
    }

    @Test
    void testIsEmptyFalse() {
        Set<Integer> set = new HashSet<>(Arrays.asList(1));
        CheckerSet<Integer> checker = new CheckerSet<>(set, "testSet");
        assertThrows(Exception.class, checker::isEmpty);
    }

    @Test
    void testAnyMatchTrue() {
        Set<String> set = new HashSet<>(Arrays.asList("a", "b", "c"));
        CheckerSet<String> checker = new CheckerSet<>(set, "testSet");
        assertDoesNotThrow(() -> checker.anyMatch(s -> s.equals("b")));
    }

    @Test
    void testAnyMatchFalse() {
        Set<String> set = new HashSet<>(Arrays.asList("a", "b", "c"));
        CheckerSet<String> checker = new CheckerSet<>(set, "testSet");
        assertThrows(Exception.class, () -> checker.anyMatch(s -> s.equals("z")));
    }

    @Test
    void testAllMatchTrue() {
        Set<Integer> set = new HashSet<>(Arrays.asList(2, 4, 6));
        CheckerSet<Integer> checker = new CheckerSet<>(set, "testSet");
        assertDoesNotThrow(() -> checker.allMatch(i -> i % 2 == 0));
    }

    @Test
    void testAllMatchFalse() {
        Set<Integer> set = new HashSet<>(Arrays.asList(2, 3, 4));
        CheckerSet<Integer> checker = new CheckerSet<>(set, "testSet");
        assertThrows(Exception.class, () -> checker.allMatch(i -> i % 2 == 0));
    }

    @Test
    void testIsSubsetTrue() {
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2));
        CheckerSet<Integer> checker = new CheckerSet<>(set, "testSet");
        assertDoesNotThrow(() -> checker.isSubset(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testIsSubsetFalse() {
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 4));
        CheckerSet<Integer> checker = new CheckerSet<>(set, "testSet");
        assertThrows(Exception.class, () -> checker.isSubset(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testIsSupersetTrue() {
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
        CheckerSet<Integer> checker = new CheckerSet<>(set, "testSet");
        assertDoesNotThrow(() -> checker.isSuperset(Arrays.asList(1, 2)));
    }

    @Test
    void testIsSupersetFalse() {
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2));
        CheckerSet<Integer> checker = new CheckerSet<>(set, "testSet");
        assertThrows(Exception.class, () -> checker.isSuperset(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testIsSufficientPercentageTrue() {
        Set<Integer> set = new HashSet<>(Arrays.asList(2, 4, 6, 8));
        CheckerSet<Integer> checker = new CheckerSet<>(set, "testSet");
        assertDoesNotThrow(() -> checker.isSufficientPercentage(i -> i % 2 == 0, 75.0));
    }

    @Test
    void testIsSufficientPercentageFalse() {
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        CheckerSet<Integer> checker = new CheckerSet<>(set, "testSet");
        assertThrows(Exception.class, () -> checker.isSufficientPercentage(i -> i % 2 == 0, 75.0));
    }
}

