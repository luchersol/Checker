package specialized_checkers.math;

import static util.Message.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import util.AbstractChecker;

public class CheckerArray<T> extends AbstractChecker<T[], CheckerArray<T>>{

    private static final String INIT_ARRAY = "math.array";

    public CheckerArray(T[] object, String name) {
        super(object, name);
    } 

    @Override
    protected CheckerArray<T> self() {
        return this;
    }

    private String getTypeName(){
        return this.object.getClass().getComponentType().getSimpleName();
    }

    public CheckerArray<T> isEmpty(){
        return is(array -> array.length == 0, sendMessage(INIT_ARRAY, "is_empty"));
    }

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

    public CheckerArray<T> isSufficientPercentage(Predicate<T> matching, double percentage){
        Predicate<T[]> predicate = array -> {
            double percentageMatching = Arrays.stream(array).filter(matching).count() * 100. / array.length;
            return percentageMatching >= percentage;
        };
        return is(predicate, sendMessage(INIT_ARRAY, "is_sufficient_percentage"));
    }

    public CheckerArray<T> anyMatch(Predicate<T> predicate){
        return is(array -> Arrays.stream(array).anyMatch(predicate), sendMessage(INIT_ARRAY, "any_match"));
    }

    public CheckerArray<T> allMatch(Predicate<T> predicate){
        return is(array -> Arrays.stream(array).allMatch(predicate), sendMessage(INIT_ARRAY, "all_match"));
    }
    
}
