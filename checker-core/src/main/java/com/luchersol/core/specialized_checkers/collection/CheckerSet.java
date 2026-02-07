package com.luchersol.core.specialized_checkers.collection;

import static com.luchersol.core.util.MessageService.*;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

import com.luchersol.core.util.AbstractChecker;

/**
 * A specialized checker for {@link Set} collections, providing fluent assertion methods
 * for validating set properties and contents.
 * <p>
 * This class extends {@link AbstractChecker} and offers a variety of checks such as emptiness,
 * subset/superset relationships, and element matching based on predicates.
 * </p>
 *
 * <pre>
 * Example usage:
 *   Set&lt;String&gt; mySet = Set.of("a", "b", "c");
 *   CheckerSet checker = CheckerSet.check(mySet)
 *         .isSuperset(List.of("a"))
 *         .isEmpty();
 * </pre>
 *
 * @param <T> the type of elements in the set
 * @see AbstractChecker
 */
public class CheckerSet<T> extends AbstractChecker<Set<T>, CheckerSet<T>>{

    private static final String INIT_SET = "collections.set";
    private static final String DEFAULT_NAME = "Set";

    /**
     * Constructs a new {@code CheckerSet} with the specified set and name.
     *
     * @param set  the underlying set to be wrapped by this CheckerSet
     * @param name the name associated with this CheckerSet
     */
    protected CheckerSet(Set<T> set, String name) {
        super(set, name);
    }

    /**
     * Creates a CheckerSet for the given set and assigns a custom name.
     *
     * @param <T>  the {@code Set}'s element type
     * @param set the set to check
     * @param name the name to assign to this checker
     * @return a CheckerSet instance for the given set
     */
    public static <T> CheckerSet<T> check(Set<T> set, String name){
        return new CheckerSet<>(set, name);
    }

    /**
     * Creates a CheckerSet for the given set with a default name.
     *
     * @param <T>  the {@code Set}'s element type
     * @param set the set to check
     * @return a CheckerSet instance for the given set
     */
    public static <T> CheckerSet<T> check(Set<T> set){
        return check(set, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API).
     *
     * @return this CheckerSet instance
     */
    @Override
    protected CheckerSet<T> self() {
        return this;
    }

    /**
     * Checks if the set is empty.
     *
     * @return this CheckerSet instance
     */
    public CheckerSet<T> isEmpty(){
        return is(set -> set.isEmpty(), sendMessage(INIT_SET, "is_empty"));
    }

    /**
     * Checks if any element in the set matches the given predicate.
     *
     * @param predicate the condition to test elements
     * @return this CheckerSet instance
     */
    public CheckerSet<T> anyMatch(Predicate<T> predicate){
        return is(set -> set.stream().anyMatch(predicate), sendMessage(INIT_SET, "any_match"));
    }

    /**
     * Checks if all elements in the set match the given predicate.
     *
     * @param predicate the condition to test elements
     * @return this CheckerSet instance
     */
    public CheckerSet<T> allMatch(Predicate<T> predicate){
        return is(set -> set.stream().allMatch(predicate), sendMessage(INIT_SET, "all_match"));
    }

    /**
     * Checks if the set is a subset of the given collection.
     *
     * @param collection the collection to compare with
     * @return this CheckerSet instance
     */
    public CheckerSet<T> isSubset(Collection<T> collection){
        return is(set -> collection.containsAll(set), sendMessage(INIT_SET, "is_subset"));
    }

    /**
     * Checks if the set is a superset of the given collection.
     *
     * @param collection the collection to compare with
     * @return this CheckerSet instance
     */
    public CheckerSet<T> isSuperset(Collection<T> collection){
        return is(set -> set.containsAll(collection), sendMessage(INIT_SET, "is_superset"));
    }

    /**
     * Checks if at least the specified percentage of elements in the set match the given predicate.
     *
     * @param matching the condition to test elements
     * @param percentage the minimum percentage (0-100) of elements that must match
     * @return this CheckerSet instance
     */
    public CheckerSet<T> isSufficientPercentage(Predicate<T> matching, double percentage){
        return is(set -> {
            double percentageMatching = set.stream().filter(matching).count() * 100. / set.size();
            return percentageMatching >= percentage;
        }, sendMessage(INIT_SET, "is_sufficient_percentage", percentage * 100));
    }

}
