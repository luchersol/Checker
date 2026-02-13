package com.luchersol.core.specialized_checkers.collection;

import static com.luchersol.core.util.MessageService.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import com.luchersol.core.util.AbstractChecker;

/**
 * A specialized checker for {@link Map} instances, providing fluent assertion methods
 * for validating map properties and contents.
 * <p>
 * This class extends {@code AbstractChecker} and allows for expressive, chainable
 * checks on {@code Map} objects, such as verifying emptiness, matching entries
 * against predicates, and more.
 *
 *
 * <p>Example usage:
 * <pre>{@code
 * Map<String, Integer> ages = Map.of("Alice", 19, "Bob", 40);
 * CheckerMap<String> checker = CheckerMap.check(ages)
 *      .isEmpty()
 *      .anyMatch((name, age) -> name.startsWith("A") && age >= 18);
 * }</pre>
 *
 * @param <K> the type of keys maintained by the map
 * @param <V> the type of mapped values
 *
 * @see java.util.Map
 * @see java.util.function.Predicate
 * @see java.util.function.BiPredicate
 */
public class CheckerMap<K, V> extends AbstractChecker<Map<K, V>, CheckerMap<K, V>>{

    private static final String INIT_MAP = "collections.map";
    private static final String DEFAULT_NAME = "Map";

    /**
     * Constructs a new {@code CheckerMap} with the specified backing map and name.
     *
     * @param map  the backing map to be used by this {@code CheckerMap}
     * @param name the name associated with this {@code CheckerMap}
     */
    protected CheckerMap(Map<K, V> map, String name) {
        super(map, name);
    }

    /**
     * Creates a CheckerMap for the given map and assigns a custom name.
     *
     * @param <K> the {@code Map}'s key type
     * @param <V> the {@code Map}'s value type
     * @param map the map to check
     * @param name the name to assign to this checker
     * @return a CheckerMap instance for the given map
     */
    public static <K, V> CheckerMap<K, V> check(Map<K, V> map, String name) {
        return new CheckerMap<>(map, name);
    }

    /**
     * Creates a CheckerMap for the given map with a default name.
     *
     * @param <K> the {@code Map}'s key type
     * @param <V> the {@code Map}'s value type
     * @param map the map to check
     * @return a CheckerMap instance for the given map
     */
    public static <K, V> CheckerMap<K, V> check(Map<K, V> map) {
        return check(map, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API).
     *
     * @return this CheckerMap instance
     */
    @Override
    protected CheckerMap<K, V> self() {
        return this;
    }

    /**
     * Checks if the map is empty.
     *
     * @return this CheckerMap instance
     */
    public CheckerMap<K,V> isEmpty(){
        return is(map -> map.isEmpty(), sendMessage(INIT_MAP, "is_empty"));
    }
    /**
     * Checks if any entry in the map matches the given predicate.
     *
     * @param predicate the condition to test map entries
     * @return this CheckerMap instance
     */
    public CheckerMap<K,V> anyMatch(Predicate<Entry<K,V>> predicate){
        return is(map -> map.entrySet().stream().anyMatch(predicate), sendMessage(INIT_MAP, "any_match"));
    }

    /**
     * Checks if any entry in the map matches the given bi-predicate.
     *
     * @param predicate the condition to test key-value pairs
     * @return this CheckerMap instance
     */
    public CheckerMap<K,V> anyMatch(BiPredicate<K,V> predicate){
        return is(map -> map.entrySet().stream().anyMatch(entry -> predicate.test(entry.getKey(), entry.getValue())), sendMessage(INIT_MAP, "any_match"));
    }

    /**
     * Checks if all entries in the map match the given predicate.
     *
     * @param predicate the condition to test map entries
     * @return this CheckerMap instance
     */
    public CheckerMap<K,V> allMatch(Predicate<Entry<K,V>> predicate){
        return is(map -> map.entrySet().stream().allMatch(predicate), sendMessage(INIT_MAP, "all_match"));
    }

    /**
     * Checks if all entries in the map match the given bi-predicate.
     *
     * @param predicate the condition to test key-value pairs
     * @return this CheckerMap instance
     */
    public CheckerMap<K,V> allMatch(BiPredicate<K,V> predicate){
        return is(map -> map.entrySet().stream().allMatch(entry -> predicate.test(entry.getKey(), entry.getValue())), sendMessage(INIT_MAP, "all_match"));
    }

}
