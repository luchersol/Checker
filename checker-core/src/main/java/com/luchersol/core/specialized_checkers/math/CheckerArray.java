package com.luchersol.core.specialized_checkers.math;

import static com.luchersol.core.util.MessageService.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import com.luchersol.core.util.AbstractChecker;

/**
 * A specialized checker for array instances, providing fluent API methods
 * to assert various properties such as emptiness, sorting order (ascending/descending),
 * element matching conditions, and percentage-based validations.
 *
 * <p>Typical usage:</p>
 * <pre>{@code
 * String[] names = {"Alice", "Bob", "Charlie"};
 * CheckerArray<String> checker = CheckerArray.check(names)
 *     .isSortedAsc()
 *     .anyMatch(name -> name.startsWith("A"))
 *     .isSufficientPercentage(n -> n.length() > 2, 50.0);
 * }</pre>
 *
 * <p>This class supports chaining multiple assertions in a fluent style,
 * making array validations more expressive and readable.</p>
 *
 * @param <T> the type of the array elements
 *
 * @see java.util.Arrays
 * @see java.util.Comparator
 * @see java.util.function.Predicate
 * @see com.luchersol.core.util.AbstractChecker
 */
public class CheckerArray<T> extends AbstractChecker<T[], CheckerArray<T>>{

    private static final String INIT_ARRAY = "math.array";
    private static final String DEFAULT_NAME = "Array";

    /**
     * Constructs a new {@code CheckerArray} with the specified array and name.
     *
     * @param array the array to be used by this checker
     * @param name the name identifying this checker
     */
    protected CheckerArray(T[] array, String name) {
        super(array, name);
    }

    /**
     * Creates a new {@code CheckerArray} for the given array instance with a custom name.
     *
     * @param array the array instance to be checked
     * @param name  the name to identify this checker instance (useful for error messages)
     * @param <T>   the type of the elements in the array
     * @return a new {@code CheckerArray} for the provided array
     */
    public static <T> CheckerArray<T> check(T[] array, String name){
        return new CheckerArray<>(array, name);
    }

    /**
     * Creates a new {@code CheckerArray} for the given array instance with a default name.
     *
     * @param array the array instance to be checked
     * @param <T>   the type of the elements in the array
     * @return a new {@code CheckerArray} for the provided array
     */
    public static <T> CheckerArray<T> check(T[] array){
        return check(array, DEFAULT_NAME);
    }

    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerArray} instance
     */
    @Override
    protected CheckerArray<T> self() {
        return this;
    }

    /**
     * Returns the simple name of the array's component type.
     *
     * @return the simple name of the array's element type
     */
    private String getTypeName(){
        return this.object.getClass().getComponentType().getSimpleName();
    }

    /**
     * Asserts that the array is empty (has zero length).
     *
     * @return this {@code CheckerArray} instance for further validation
     */
    public CheckerArray<T> isEmpty(){
        return is(array -> array.length == 0, sendMessage(INIT_ARRAY, "is_empty"));
    }

    /**
     * Asserts that the array is sorted in ascending order according to the provided comparator.
     *
     * @param comparator the comparator to determine the order of the array
     * @return this {@code CheckerArray} instance for further validation
     */
    public CheckerArray<T> isSortedAsc(Comparator<T> comparator){
        if (this.object.length == 0)
            return this;
        boolean implementComparable = object[0] instanceof Comparable;
        is(array -> array[0] instanceof Comparable, sendMessage(INIT_ARRAY, "comparator", getTypeName()));

        if(implementComparable){
            T[] copy = Arrays.copyOf(this.object, this.object.length);
            Arrays.sort(copy, comparator);
            is(array -> Arrays.equals(array, copy), sendMessage(INIT_ARRAY, "is_sorted_asc.comparator"));
        }

        return this;
    }

    /**
     * Asserts that the array is sorted in ascending order according to the natural ordering of its elements.
     *
     * @return this {@code CheckerArray} instance for further validation
     */
    public CheckerArray<T> isSortedAsc(){
        if (this.object.length == 0)
            return this;
        boolean implementComparable = object[0] instanceof Comparable;
        is(array -> array[0] instanceof Comparable, sendMessage(INIT_ARRAY, "comparator", getTypeName()));

        if(implementComparable){
            T[] copy = Arrays.copyOf(this.object, this.object.length);
            Arrays.sort(copy);
            is(array -> Arrays.equals(array, copy), sendMessage(INIT_ARRAY, "is_sorted_asc"));
        }

        return this;
    }

    /**
     * Asserts that the array is sorted in descending order according to the provided comparator.
     *
     * @param comparator the comparator to determine the order of the array
     * @return this {@code CheckerArray} instance for further validation
     */
    public CheckerArray<T> isSortedDesc(Comparator<T> comparator){
        if (this.object.length == 0)
            return this;
        boolean implementComparable = object[0] instanceof Comparable;
        is(array -> array[0] instanceof Comparable, sendMessage(INIT_ARRAY, "comparator", getTypeName()));

        if(implementComparable){
            T[] copy = Arrays.copyOf(this.object, this.object.length);
            Arrays.sort(copy, comparator.reversed());
            is(array -> Arrays.equals(array, copy), sendMessage(INIT_ARRAY, "is_sorted_decs.comparator"));
        }

        return this;
    }

    /**
     * Asserts that the array is sorted in descending order according to the natural ordering of its elements.
     *
     * @return this {@code CheckerArray} instance for further validation
     */
    public CheckerArray<T> isSortedDesc(){
        if (this.object.length == 0)
            return this;
        boolean implementComparable = object[0] instanceof Comparable;
        is(array -> array[0] instanceof Comparable, sendMessage(INIT_ARRAY, "comparator", getTypeName()));

        if(implementComparable){
            T[] copy = Arrays.copyOf(this.object, this.object.length);
            @SuppressWarnings("unchecked")
            Comparator<T> comparator = (Comparator<T>) Comparator.reverseOrder();
            Arrays.sort(copy, comparator);
            is(array -> Arrays.equals(array, copy), sendMessage(INIT_ARRAY, "is_sorted_desc"));
        }

        return this;
    }

    /**
     * Asserts that at least the given percentage of elements in the array match the provided predicate.
     *
     * @param matching   the predicate to test elements
     * @param percentage the minimum percentage of elements that must match
     * @return this {@code CheckerArray} instance for further validation
     */
    public CheckerArray<T> isSufficientPercentage(Predicate<T> matching, double percentage){
        Predicate<T[]> predicate = array -> {
            double percentageMatching = Arrays.stream(array).filter(matching).count() * 100. / array.length;
            return percentageMatching >= percentage;
        };
        return is(predicate, sendMessage(INIT_ARRAY, "is_sufficient_percentage"));
    }

    /**
     * Asserts that any element in the array matches the provided predicate.
     *
     * @param predicate the predicate to test elements
     * @return this {@code CheckerArray} instance for further validation
     */
    public CheckerArray<T> anyMatch(Predicate<T> predicate){
        return is(array -> Arrays.stream(array).anyMatch(predicate), sendMessage(INIT_ARRAY, "any_match"));
    }

    /**
     * Asserts that all elements in the array match the provided predicate.
     *
     * @param predicate the predicate to test elements
     * @return this {@code CheckerArray} instance for further validation
     */
    public CheckerArray<T> allMatch(Predicate<T> predicate){
        return is(array -> Arrays.stream(array).allMatch(predicate), sendMessage(INIT_ARRAY, "all_match"));
    }

}
