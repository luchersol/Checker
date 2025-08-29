package specialized_checkers.math;

import static util.Message.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import util.AbstractChecker;

public class CheckerArray<T> extends AbstractChecker<T[], CheckerArray<T>>{

    private static final String INIT_ARRAY = "math.array";
    private static final String DEFAULT_NAME = "Array";

    protected CheckerArray(T[] array, String name) {
        super(array, name);
    }

    /**
     * @param array
     * @param name
     * @return CheckerArray<T>
     */
    public static <T> CheckerArray<T> check(T[] array, String name){
        return new CheckerArray<>(array, name);
    }

    /**
     * @param array
     * @return CheckerArray<T>
     */
    public static <T> CheckerArray<T> check(T[] array){
        return check(array, DEFAULT_NAME);
    }

    /**
     * @return CheckerArray<T>
     */
    @Override
    protected CheckerArray<T> self() {
        return this;
    }

    /**
     * @return String
     */
    private String getTypeName(){
        return this.object.getClass().getComponentType().getSimpleName();
    }

    /**
     * @return CheckerArray<T>
     */
    public CheckerArray<T> isEmpty(){
        return is(array -> array.length == 0, sendMessage(INIT_ARRAY, "is_empty"));
    }

    /**
     * @param comparator
     * @return CheckerArray<T>
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
     * @return CheckerArray<T>
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
     * @param comparator
     * @return CheckerArray<T>
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
     * @return CheckerArray<T>
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
     * @param matching
     * @param percentage
     * @return CheckerArray<T>
     */
    public CheckerArray<T> isSufficientPercentage(Predicate<T> matching, double percentage){
        Predicate<T[]> predicate = array -> {
            double percentageMatching = Arrays.stream(array).filter(matching).count() * 100. / array.length;
            return percentageMatching >= percentage;
        };
        return is(predicate, sendMessage(INIT_ARRAY, "is_sufficient_percentage"));
    }

    /**
     * @param predicate
     * @return CheckerArray<T>
     */
    public CheckerArray<T> anyMatch(Predicate<T> predicate){
        return is(array -> Arrays.stream(array).anyMatch(predicate), sendMessage(INIT_ARRAY, "any_match"));
    }

    /**
     * @param predicate
     * @return CheckerArray<T>
     */
    public CheckerArray<T> allMatch(Predicate<T> predicate){
        return is(array -> Arrays.stream(array).allMatch(predicate), sendMessage(INIT_ARRAY, "all_match"));
    }

}
