package specialized_checkers;

import static util.Message.sendMessage;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Currency;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.ExceptionTracker;

public class CheckerArray<T> extends AbstractChecker<T[]>{

    private static final String INIT_ARRAY = "array";

    public CheckerArray(T[] object, String name, ExceptionTracker exceptionTracker) {
        super(object, name, exceptionTracker);
    } 

    @Override
    public CheckerArray<T> is(Predicate<T[]> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerArray<T> is(Predicate<T[]> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerArray<T> isNot(Predicate<T[]> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerArray<T> isNot(Predicate<T[]> condition) {
        super.isNot(condition);
        return this;
    }

    private String getTypeName(){
        return this.object.getClass().getComponentType().getSimpleName();
    }

    public CheckerArray<T> isEmpty(){
        is(array -> array.length == 0, sendMessage(INIT_ARRAY, "is_empty"));
        return this;
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
        is(predicate, sendMessage(INIT_ARRAY, "is_suffcient_percentage"));
        return this;
    }

    public CheckerArray<T> anyMatch(Predicate<T> predicate){
        is(array -> Arrays.stream(array).anyMatch(predicate), sendMessage(INIT_ARRAY, "any_match"));
        return this;
    }

    public CheckerArray<T> allMatch(Predicate<T> predicate){
        is(array -> Arrays.stream(array).allMatch(predicate), sendMessage(INIT_ARRAY, "all_match"));
        return this;
    }
    
}
