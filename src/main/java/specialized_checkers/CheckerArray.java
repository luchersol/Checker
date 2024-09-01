package specialized_checkers;

import static util.Message.sendMessage;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import util.AbstractChecker;

public class CheckerArray<T> extends AbstractChecker<T>{

    private static final String INIT_ARRAY = "array";

    protected T[] object;

    public CheckerArray(T[] object, String name) {
        super(name);
        this.object = object;
    }

    private String getTypeName(){
        return this.object.getClass().getComponentType().getSimpleName();
    }

    public CheckerArray<T> isEmpty(){
        is(this.object.length == 0, sendMessage(INIT_ARRAY, "is_empty"));
        return this;
    }

    public CheckerArray<T> isSortedAsc(Comparator<T> comparator){
        if (this.object.length == 0) 
            return this;
        boolean implementComparable = object[0] instanceof Comparable;
        is(implementComparable, sendMessage(INIT_ARRAY, "comparator", getTypeName()));

        if(implementComparable){
            T[] copy = Arrays.copyOf(this.object, this.object.length);
            Arrays.sort(copy, comparator);
            is(Arrays.equals(this.object, copy), sendMessage(INIT_ARRAY, "is_sorted_asc.comparator"));
        }
        
        return this;
    }

    public CheckerArray<T> isSortedAsc(){
        if (this.object.length == 0) 
            return this;
        boolean implementComparable = object[0] instanceof Comparable;
        is(implementComparable, sendMessage(INIT_ARRAY, "comparator", getTypeName()));

        if(implementComparable){
            T[] copy = Arrays.copyOf(this.object, this.object.length);
            Arrays.sort(copy);
            is(Arrays.equals(this.object, copy), sendMessage(INIT_ARRAY, "is_sorted_asc"));
        }
        
        return this;
    }

    public CheckerArray<T> isSortedDesc(Comparator<T> comparator){
        if (this.object.length == 0) 
            return this;
        boolean implementComparable = object[0] instanceof Comparable;
        is(implementComparable, sendMessage(INIT_ARRAY, "comparator", getTypeName()));

        if(implementComparable){
            T[] copy = Arrays.copyOf(this.object, this.object.length);
            Arrays.sort(copy, comparator.reversed());
            is(Arrays.equals(this.object, copy), sendMessage(INIT_ARRAY, "is_sorted_decs.comparator"));
        }
        
        return this;
    }

    public CheckerArray<T> isSortedDesc(){
        if (this.object.length == 0) 
            return this;
        boolean implementComparable = object[0] instanceof Comparable;
        is(implementComparable, sendMessage(INIT_ARRAY, "comparator", getTypeName()));

        if(implementComparable){
            T[] copy = Arrays.copyOf(this.object, this.object.length);
            @SuppressWarnings("unchecked")
            Comparator<T> comparator = (Comparator<T>) Comparator.reverseOrder();
            Arrays.sort(copy, comparator);
            is(Arrays.equals(this.object, copy), sendMessage(INIT_ARRAY, "is_sorted_desc"));
        }
        
        return this;
    }

    public CheckerArray<T> anyMatch(Predicate<T> predicate){
        is(Arrays.stream(this.object).anyMatch(predicate), sendMessage(INIT_ARRAY, "any_match"));
        return this;
    }

    public CheckerArray<T> allMatch(Predicate<T> predicate){
        is(Arrays.stream(this.object).allMatch(predicate), sendMessage(INIT_ARRAY, "all_match"));
        return this;
    }
    
}
