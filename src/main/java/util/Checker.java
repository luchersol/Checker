package util;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import specialized_checkers.CheckerArray;
import specialized_checkers.CheckerString;
import specialized_checkers.collections.CheckerList;
import specialized_checkers.collections.CheckerMap;
import specialized_checkers.collections.CheckerSet;
import specialized_checkers.numbers.decimalTypes.CheckerDouble;
import specialized_checkers.numbers.decimalTypes.CheckerFloat;
import specialized_checkers.numbers.integerTypes.CheckerInteger;
import specialized_checkers.numbers.integerTypes.CheckerLong;

public class Checker extends AbstractChecker<Object>{

    public Checker(Object object, String name) {
        super(object, name);
    }
    
    public static Checker check(Object object, String name) {
        return new Checker(object, name);
    }

    public static Checker check(Object object) {
        return new Checker(object, "Object");
    }

    public Checker saveErrors(){
        this.saveErrors = true;
        return this;
    }

    public Checker notSaveErrors(){
        this.saveErrors = false;
        return this;
    }

    public <T> void isClass(Class<T> clazz) {
        is(clazz.isInstance(this.object), "message");
    }

    public Checker isNumber() {
        isClass(Number.class);
        return this;
    }

    public Checker isCollection() {
        isClass(Collection.class);
        return this;
    }

    public CheckerString isString() {
        isClass(String.class);
        return new CheckerString((String) this.object, name);
    }

    public CheckerInteger isInteger() {
        isClass(Integer.class);
        return new CheckerInteger((Integer) this.object, name);
    }

    public CheckerLong isLong() {
        isClass(Long.class);
        return new CheckerLong((Long) this.object, name);
    }

    public CheckerFloat isFloat() {
        isClass(Float.class);
        return new CheckerFloat((Float) this.object, name);
    }

    public CheckerDouble isDouble() {
        isClass(Double.class);
        return new CheckerDouble((Double) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerArray<?> isArray() {
        is(this.object.getClass().isArray(), "null");
        return new CheckerArray(((Collection<?>) this.object).toArray(), name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> CheckerArray<T> isArray(Class<T> clazz) {
        is(this.object.getClass().isArray(), "null");
        return new CheckerArray(((Collection<T>) this.object).toArray(), name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerList<?> isList() {
        isClass(List.class);
        return new CheckerList((List<?>) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> CheckerList<T> isList(Class<T> clazz) {
        isClass(List.class);
        return new CheckerList((List<T>) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerSet<?> isSet() {
        isClass(Set.class);
        return new CheckerSet((Set<?>) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> CheckerSet<T> isSet(Class<T> clazz) {
        isClass(Set.class);
        return new CheckerSet((Set<T>) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerMap<?,?> isMap() {
        isClass(Map.class);
        return new CheckerMap((Map<?,?>) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <K,V> CheckerMap<K,V> isMap(Class<K> clazzKey, Class<V> clazzValue) {
        isClass(Map.class);
        return new CheckerMap((Map<K,V>) this.object, name);
    }

}