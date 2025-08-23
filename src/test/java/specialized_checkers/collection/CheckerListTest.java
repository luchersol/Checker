package specialized_checkers.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckerListTest {

        private List<Integer> list;
        private CheckerList<Integer> checker;

        @BeforeEach
        void setUp() {
            list = Arrays.asList(1, 2, 3, 4, 5);
            checker = new CheckerList<>(list, "testList");
        }

        @Test
        void isEmpty_shouldPassForEmptyList() {
            CheckerList<?> emptyChecker = new CheckerList<>(Collections.emptyList(), "emptyList");
            assertDoesNotThrow(emptyChecker::isEmpty);
        }

        @Test
        void isEmpty_shouldFailForNonEmptyList() {
            assertThrows(Exception.class, () -> checker.isEmpty());
        }

        @Test
        void anyMatch_shouldPassIfAnyElementMatches() {
            assertDoesNotThrow(() -> checker.anyMatch(i -> i == 3));
        }

        @Test
        void anyMatch_shouldFailIfNoElementMatches() {
            assertThrows(Exception.class, () -> checker.anyMatch(i -> i == 10));
        }

        @Test
        void allMatch_shouldPassIfAllMatch() {
            CheckerList<Integer> evenChecker = new CheckerList<>(Arrays.asList(2, 4, 6), "evenList");
            assertDoesNotThrow(() -> evenChecker.allMatch(i -> i % 2 == 0));
        }

        @Test
        void allMatch_shouldFailIfNotAllMatch() {
            assertThrows(Exception.class, () -> checker.allMatch(i -> i < 5));
        }

        @Test
        void allDistinct_shouldPassForDistinctList() {
            assertDoesNotThrow(() -> checker.allDistinct());
        }

        @Test
        void allDistinct_shouldFailForNonDistinctList() {
            CheckerList<Integer> dupChecker = new CheckerList<>(Arrays.asList(1, 2, 2, 3), "dupList");
            assertThrows(Exception.class, dupChecker::allDistinct);
        }

        @Test
        void isSubset_shouldPassIfSubset() {
            List<Integer> superset = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
            assertDoesNotThrow(() -> checker.isSubset(superset));
        }

        @Test
        void isSubset_shouldFailIfNotSubset() {
            List<Integer> notSuperset = Arrays.asList(1, 2, 3);
            assertThrows(Exception.class, () -> checker.isSubset(notSuperset));
        }

        @Test
        void isSuperset_shouldPassIfSuperset() {
            List<Integer> subset = Arrays.asList(1, 2, 3);
            assertDoesNotThrow(() -> checker.isSuperset(subset));
        }

        @Test
        void isSuperset_shouldFailIfNotSuperset() {
            List<Integer> notSubset = Arrays.asList(10, 11);
            assertThrows(Exception.class, () -> checker.isSuperset(notSubset));
        }

        @Test
        void isSufficientPercentage_shouldPassIfEnoughMatch() {
            assertDoesNotThrow(() -> checker.isSufficientPercentage(i -> i < 4, 60.0));
        }

        @Test
        void isSufficientPercentage_shouldFailIfNotEnoughMatch() {
            assertThrows(Exception.class, () -> checker.isSufficientPercentage(i -> i < 3, 60.0));
        }
    }
