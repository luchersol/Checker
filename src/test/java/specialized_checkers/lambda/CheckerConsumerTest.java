package specialized_checkers.lambda;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import util.CheckerException;


class CheckerConsumerTest {

    @Test
    void testApplyWithoutException_singleInput_noException() {
        List<String> list = new ArrayList<>();
        Consumer<String> consumer = list::add;
        CheckerConsumer<String> checker = CheckerConsumer.check(consumer);

        assertDoesNotThrow(() -> checker.applyWithoutException("test"));
    }

    @Test
    void testApplyWithoutException_singleInput_withException() {
        Consumer<String> consumer = s -> { throw new RuntimeException(); };
        CheckerConsumer<String> checker = CheckerConsumer.check(consumer);

        assertThrows(CheckerException.class, () -> checker.applyWithoutException("fail"));
    }

    @Test
    void testApplyWithoutException_collection_noException() {
        List<String> list = new ArrayList<>();
        Consumer<String> consumer = list::add;
        CheckerConsumer<String> checker = CheckerConsumer.check(consumer);

        List<String> input = Arrays.asList("a", "b", "c");
        assertDoesNotThrow(() -> checker.applyWithoutException(input));
    }

    @Test
    void testApplyWithoutException_collection_withException() {
        Consumer<String> consumer = s -> { if ("b".equals(s)) throw new RuntimeException(); };
        CheckerConsumer<String> checker = CheckerConsumer.check(consumer);

        List<String> input = Arrays.asList("a", "b", "c");
        assertThrows(CheckerException.class, () -> checker.applyWithoutException(input));
    }

    @Test
    void testModifiesInput_singleInput_conditionTrue() {
        Consumer<StringBuilder> consumer = sb -> sb.append("x");
        Predicate<StringBuilder> condition = sb -> sb.toString().endsWith("x");
        CheckerConsumer<StringBuilder> checker = CheckerConsumer.check(consumer);

        StringBuilder sb = new StringBuilder("a");
        assertDoesNotThrow(() -> checker.modifiesInput(sb, condition));
    }

    @Test
    void testModifiesInput_singleInput_conditionFalse() {
        Consumer<StringBuilder> consumer = sb -> {};
        Predicate<StringBuilder> condition = sb -> sb.toString().endsWith("x");
        CheckerConsumer<StringBuilder> checker = CheckerConsumer.check(consumer);

        StringBuilder sb = new StringBuilder("a");
        assertThrows(CheckerException.class, () -> checker.modifiesInput(sb, condition));
    }

    @Test
    void testModifiesInput_collection_conditionTrue() {
        Consumer<StringBuilder> consumer = sb -> sb.append("x");
        Predicate<StringBuilder> condition = sb -> sb.toString().endsWith("x");
        CheckerConsumer<StringBuilder> checker = CheckerConsumer.check(consumer);

        List<StringBuilder> input = Arrays.asList(new StringBuilder("a"), new StringBuilder("b"));
        assertDoesNotThrow(() -> checker.modifiesInput(input, condition));
    }

    @Test
    void testModifiesInput_collection_conditionFalse() {
        Consumer<StringBuilder> consumer = sb -> {};
        Predicate<StringBuilder> condition = sb -> sb.toString().endsWith("x");
        CheckerConsumer<StringBuilder> checker = CheckerConsumer.check(consumer);

        List<StringBuilder> input = Arrays.asList(new StringBuilder("a"), new StringBuilder("b"));
        assertThrows(CheckerException.class, () -> checker.modifiesInput(input, condition));
    }

    @Test
    void testDoesNothing_singleInput() {
        Consumer<int[]> consumer = arr -> {};
        CheckerConsumer<int[]> checker = CheckerConsumer.check(consumer);

        int[] arr = { 1 };
        assertDoesNotThrow(() -> checker.doesNothing(arr));
    }

    @Test
    void testDoesNothing_singleInput_modified() {
        Consumer<int[]> consumer = arr -> arr[0]++;
        CheckerConsumer<int[]> checker = CheckerConsumer.check(consumer);

        int[] arr = { 1 };
        assertThrows(CheckerException.class, () -> checker.doesNothing(arr));
    }

    @Test
    void testDoesNothing_collection() {
        Consumer<int[]> consumer = arr -> {};
        CheckerConsumer<int[]> checker = CheckerConsumer.check(consumer);

        List<int[]> input = Arrays.asList(new int[] {1}, new int[] {2});
        assertDoesNotThrow(() -> checker.doesNothing(input));
    }

    @Test
    void testDoesNothing_collection_modified() {
        Consumer<int[]> consumer = arr -> arr[0]++;
        CheckerConsumer<int[]> checker = CheckerConsumer.check(consumer);

        List<int[]> input = Arrays.asList(new int[] {1}, new int[] {2});
        assertThrows(CheckerException.class, () -> checker.doesNothing(input));
    }

    @Test
    void testActivateAndDeactivateDeepClone() {
        Consumer<StringBuilder> consumer = sb -> sb.append("x");
        CheckerConsumer<StringBuilder> checker = CheckerConsumer.check(consumer);

        checker.activateDeepClone();
        assertDoesNotThrow(() -> checker.applyWithoutException(new StringBuilder("a")));

        checker.deactivateDeepClone();
        assertDoesNotThrow(() -> checker.applyWithoutException(new StringBuilder("b")));
    }
}
