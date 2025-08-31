package specialized_checkers.collection;

import static util.Message.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import util.AbstractChecker;

/**
 * A specialized checker for {@link List} collections, providing fluent API methods
 * to perform various checks and validations on lists.
 * <p>
 * This class extends {@link AbstractChecker} and offers methods to check for emptiness,
 * element matching, distinctness, subset/superset relationships, and percentage-based conditions.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
 * CheckerList<String> checker = CheckerList.check(names);
 * checker.isEmpty().allDistinct().anyMatch(name -> name.startsWith("A"));
 * }</pre>
 *
 * @param <T> the type of elements in the list
 * @see AbstractChecker
 */
public class CheckerList<T> extends AbstractChecker<List<T>, CheckerList<T>>{

    private static final String INIT_LIST = "collections.list";
    private static final String DEFAULT_NAME = "List";

    /**
     * Constructs a new {@code CheckerList} with the specified list and name.
     *
     * @param list the underlying list to be wrapped by this {@code CheckerList}
     * @param name the name associated with this {@code CheckerList}
     */
    protected CheckerList(List<T> list, String name) {
        super(list, name);
    }

    /**
     * Creates a CheckerList for the given list and assigns a custom name.
     *
     * @param <T> the {@code List}'s element type
     * @param list the list to check
     * @param name the name to assign to this checker
     * @return a CheckerList instance for the given list
     */
    public static <T> CheckerList<T> check(List<T> list, String name) {
        return new CheckerList<>(list, name);
    }

    /**
     * Creates a CheckerList for the given list with a default name.
     *
     * @param <T> the {@code List}'s element type
     * @param list the list to check
     * @return a CheckerList instance for the given list
     */
    public static <T> CheckerList<T> check(List<T> list) {
        return new CheckerList<>(list, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API).
     *
     * @return this CheckerList instance
     */
    @Override
    protected CheckerList<T> self() {
        return this;
    }

    /**
     * Checks if the list is empty.
     *
     * @return this CheckerList instance
     */
    public CheckerList<T> isEmpty(){
        return is(list-> list.isEmpty(), sendMessage(INIT_LIST, "is_empty"));
    }

    /**
     * Checks if any element in the list matches the given predicate.
     *
     * @param predicate the condition to test elements
     * @return this CheckerList instance
     */
    public CheckerList<T> anyMatch(Predicate<T> predicate){
        return is(list-> list.stream().anyMatch(predicate), sendMessage(INIT_LIST, "any_match"));
    }

    /**
     * Checks if all elements in the list match the given predicate.
     *
     * @param predicate the condition to test elements
     * @return this CheckerList instance
     */
    public CheckerList<T> allMatch(Predicate<T> predicate){
        return is(list-> list.stream().allMatch(predicate), sendMessage(INIT_LIST, "all_match"));
    }

    /**
     * Checks if all elements in the list are distinct.
     *
     * @return this CheckerList instance
     */
    public CheckerList<T> allDistinct(){
        return is(list -> Set.copyOf(list).size() == list.size(), sendMessage(INIT_LIST, "all_distinct"));
    }

    /**
     * Checks if the list is a subset of the given collection.
     *
     * @param collection the collection to compare with
     * @return this CheckerList instance
     */
    public CheckerList<T> isSubset(Collection<T> collection){
        return is(list -> collection.containsAll(list), sendMessage(INIT_LIST, "is_subset"));
    }

    /**
     * Checks if the list is a superset of the given collection.
     *
     * @param collection the collection to compare with
     * @return this CheckerList instance
     */
    public CheckerList<T> isSuperset(Collection<T> collection){
        return is(list-> list.containsAll(collection), sendMessage(INIT_LIST, "is_superset"));
    }

    /**
     * Checks if at least the specified percentage of elements in the list match the given predicate.
     *
     * @param matching the condition to test elements
     * @param percentage the minimum percentage (0-100) of elements that must match
     * @return this CheckerList instance
     */
    public CheckerList<T> isSufficientPercentage(Predicate<T> matching, double percentage){
        return is(list -> {
            double percentageMatching = list.stream().filter(matching).count() * 100. / list.size();
            return percentageMatching >= percentage;
        }, sendMessage(INIT_LIST, "is_sufficient_percentage", percentage * 100));
    }

}
