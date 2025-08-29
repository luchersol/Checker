package specialized_checkers.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import util.CheckerException;

class CheckerMapTest {

        @Test
        void testIsEmptyOnEmptyMap() {
            Map<String, Integer> map = new HashMap<>();
            CheckerMap<String, Integer> checker = CheckerMap.check(map, "testMap");
            assertDoesNotThrow(checker::isEmpty);
        }

        @Test
        void testIsEmptyOnNonEmptyMap() {
            Map<String, Integer> map = new HashMap<>();
            map.put("a", 1);
            CheckerMap<String, Integer> checker = CheckerMap.check(map, "testMap");
            assertThrows(CheckerException.class, checker::isEmpty);
        }

        @Test
        void testAnyMatchTrue() {
            Map<String, Integer> map = new HashMap<>();
            map.put("a", 1);
            map.put("b", 2);
            CheckerMap<String, Integer> checker = CheckerMap.check(map, "testMap");
            assertDoesNotThrow(() -> checker.anyMatch(e -> e.getKey().equals("a")));
        }

        @Test
        void testAnyMatchFalse() {
            Map<String, Integer> map = new HashMap<>();
            map.put("a", 1);
            map.put("b", 2);
            CheckerMap<String, Integer> checker = CheckerMap.check(map, "testMap");
            assertThrows(CheckerException.class, () -> checker.anyMatch(e -> e.getKey().equals("c")));
        }

        @Test
        void testAllMatchTrue() {
            Map<String, Integer> map = new HashMap<>();
            map.put("a", 1);
            map.put("b", 1);
            CheckerMap<String, Integer> checker = CheckerMap.check(map, "testMap");
            assertDoesNotThrow(() -> checker.allMatch(e -> e.getValue() == 1));
        }

        @Test
        void testAllMatchFalse() {
            Map<String, Integer> map = new HashMap<>();
            map.put("a", 1);
            map.put("b", 2);
            CheckerMap<String, Integer> checker = CheckerMap.check(map, "testMap");
            assertThrows(CheckerException.class, () -> checker.allMatch(e -> e.getValue() == 1));
        }
}
