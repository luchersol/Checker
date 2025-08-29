package specialized_checkers.lambda;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import util.CheckerException;


class CheckerBiConsumerTest {

    @Test
    void testApplyWithoutException_singleInput_success() {
        List<String> list = new ArrayList<>();
        BiConsumer<List<String>, String> addConsumer = List::add;
        CheckerBiConsumer<List<String>, String> checker = CheckerBiConsumer.check(addConsumer);

        assertDoesNotThrow(() -> checker.applyWithoutException(list, "hello"));
        assertTrue(list.contains("hello"));
    }

    @Test
    void testApplyWithoutException_singleInput_exception() {
        BiConsumer<Object, Object> throwingConsumer = (a, b) -> { throw new RuntimeException(); };
        CheckerBiConsumer<Object, Object> checker = CheckerBiConsumer.check(throwingConsumer);

        assertThrows(CheckerException.class, () -> checker.applyWithoutException(new Object(), new Object()));
    }

    @Test
    void testApplyWithoutException_collection_success() {
        List<SimpleEntry<List<String>, String>> entries = Arrays.asList(
                new SimpleEntry<>(new ArrayList<>(), "a"),
                new SimpleEntry<>(new ArrayList<>(), "b")
        );
        BiConsumer<List<String>, String> addConsumer = List::add;
        CheckerBiConsumer<List<String>, String> checker = CheckerBiConsumer.check(addConsumer);
        assertDoesNotThrow(() -> checker.applyWithoutException(entries));
    }

    @Test
    void testModifiesInput_predicates_success() {
        List<String> list = new ArrayList<>();
        BiConsumer<List<String>, String> addConsumer = List::add;
        Predicate<List<String>> listNotEmpty = l -> !l.isEmpty();
        Predicate<String> stringIsHello = "hello"::equals;

        CheckerBiConsumer<List<String>, String> checker = CheckerBiConsumer.check(addConsumer);

        assertDoesNotThrow(() -> checker.modifiesInput(list, "hello", listNotEmpty, stringIsHello));
    }

    @Test
    void testModifiesInput_bipredicate_success() {
        List<String> list = new ArrayList<>();
        BiConsumer<List<String>, String> addConsumer = List::add;
        BiPredicate<List<String>, String> condition = (l, s) -> l.contains(s);

        CheckerBiConsumer<List<String>, String> checker = CheckerBiConsumer.check(addConsumer);

        assertDoesNotThrow(() -> checker.modifiesInput(list, "world", condition));
    }

    @Test
    void testModifiesInput_collection_predicates_success() {
        List<SimpleEntry<List<String>, String>> entries = Arrays.asList(
                new SimpleEntry<>(new ArrayList<>(), "a"),
                new SimpleEntry<>(new ArrayList<>(), "b")
        );
        BiConsumer<List<String>, String> addConsumer = List::add;
        Predicate<List<String>> listNotEmpty = l -> !l.isEmpty();
        Predicate<String> stringNotNull = Objects::nonNull;

        CheckerBiConsumer<List<String>, String> checker = CheckerBiConsumer.check(addConsumer);

        assertDoesNotThrow(() -> checker.modifiesInput(entries, listNotEmpty, stringNotNull));
    }

    @Test
    void testModifiesInput_collection_bipredicate_success() {
        List<SimpleEntry<List<String>, String>> entries = Arrays.asList(
                new SimpleEntry<>(new ArrayList<>(), "a"),
                new SimpleEntry<>(new ArrayList<>(), "b")
        );
        BiConsumer<List<String>, String> addConsumer = List::add;
        BiPredicate<List<String>, String> condition = (l, s) -> l.contains(s);

        CheckerBiConsumer<List<String>, String> checker = CheckerBiConsumer.check(addConsumer);

        assertDoesNotThrow(() -> checker.modifiesInput(entries, condition));
    }

    @Test
    void testDoesNothing_singleInput() {
        List<String> list = new ArrayList<>();
        BiConsumer<List<String>, String> noOp = (a, b) -> {};
        CheckerBiConsumer<List<String>, String> checker = CheckerBiConsumer.check(noOp);

        assertDoesNotThrow(() -> checker.doesNothing(list, "test"));
    }

    @Test
    void testDoesNothing_collection() {
        List<SimpleEntry<List<String>, String>> entries = Arrays.asList(
                new SimpleEntry<>(new ArrayList<>(), "a"),
                new SimpleEntry<>(new ArrayList<>(), "b")
        );
        BiConsumer<List<String>, String> noOp = (a, b) -> {};
        CheckerBiConsumer<List<String>, String> checker = CheckerBiConsumer.check(noOp);

        assertDoesNotThrow(() -> checker.doesNothing(entries));
    }

    @Test
    void testActivateAndDeactivateDeepClone() {
        BiConsumer<List<String>, String> addConsumer = List::add;
        CheckerBiConsumer<List<String>, String> checker = CheckerBiConsumer.check(addConsumer);

        checker.activateDeepClone();
        assertSame(checker, checker.activateDeepClone());

        checker.deactivateDeepClone();
        assertSame(checker, checker.deactivateDeepClone());
    }
}
