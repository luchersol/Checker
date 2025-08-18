package util;

import static util.Message.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import specialized_checkers.CheckerArray;
import specialized_checkers.CheckerString;
import specialized_checkers.collections.CheckerList;
import specialized_checkers.collections.CheckerMap;
import specialized_checkers.collections.CheckerSet;
import specialized_checkers.numbers.decimalTypes.CheckerDouble;
import specialized_checkers.numbers.decimalTypes.CheckerFloat;
import specialized_checkers.numbers.integerTypes.CheckerInteger;
import specialized_checkers.numbers.integerTypes.CheckerLong;

public class Checker extends AbstractChecker<Object, Checker> {

    private static final String INIT_CHECKER = "checker";

    public Checker(Object object, String name) {
        super(object, name);
    }

    @Override
    protected Checker self() {
        return this;
    }

    public static Checker check(Object object, String name) {
        return new Checker(object, name);
    }

    public static Checker check(Object object) {
        return new Checker(object, "Object");
    }

    public Checker saveErrors() {
        this.saveErrors = true;
        return this;
    }

    public Checker notSaveErrors() {
        this.saveErrors = false;
        return this;
    }

    public Checker isNull() {
        is(object -> object == null, sendMessage(INIT_CHECKER, "is_null"));
        return this;
    }

    public Checker isNotNull() {
        is(object -> object != null, sendMessage(INIT_CHECKER, "is_not_null"));
        return this;
    }

    public Checker isEqual(Object other){
        is(object -> object.equals(other), sendMessage(INIT_CHECKER, "is_equal"));
        return this;
    }

    public <T> Checker isInstance(Class<T> clazz) {
        is(object -> clazz.isInstance(object), sendMessage(INIT_CHECKER, "is_instance", clazz.getSimpleName()));
        return this;
    }

    @SuppressWarnings({ "unchecked" })
    private static <T> T transformOfNull(Object obj, Class<T> clazz) {
        return clazz.isInstance(obj) ? (T) obj : null;
    }

    public Checker isNumber() {
        isInstance(Number.class);
        return this;
    }

    public Checker isCollection() {
        isInstance(Collection.class);
        return this;
    }

    public CheckerString isString() {
        isInstance(String.class);
        return new CheckerString(transformOfNull(this.object, String.class), this.name, this.exceptionTracker);
    }

    public CheckerInteger isInteger() {
        isInstance(Integer.class);
        return new CheckerInteger(transformOfNull(this.object, Integer.class), name);
    }

    public CheckerLong isLong() {
        isInstance(Long.class);
        return new CheckerLong(transformOfNull(this.object, Long.class), name);
    }

    public CheckerFloat isFloat() {
        isInstance(Float.class);
        return new CheckerFloat(transformOfNull(this.object, Float.class), name);
    }

    public CheckerDouble isDouble() {
        isInstance(Double.class);
        return new CheckerDouble(transformOfNull(this.object, Double.class), name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerArray<?> isArray() {
        is(object -> object.getClass().isArray(), sendMessage(INIT_CHECKER, "is_array"));
        return new CheckerArray(((Collection<?>) this.object).toArray(), this.name, this.exceptionTracker);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> CheckerArray<T> isArray(Class<T> clazz) {
        is(object -> object.getClass().isArray(), sendMessage(INIT_CHECKER, "is_array", clazz.getSimpleName()));
        return new CheckerArray(((Collection<T>) this.object).toArray(), this.name, this.exceptionTracker);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerList<?> isList() {
        isInstance(List.class);
        return new CheckerList((List<?>) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> CheckerList<T> isList(Class<T> clazz) {
        isInstance(List.class);
        Predicate<Object> conditionClazz = object -> ((List<?>) this.object).stream()
                .allMatch(elem -> clazz.isInstance(elem));
        is(conditionClazz, sendMessage(INIT_CHECKER, "is_list.clazz", clazz.getSimpleName()));
        return new CheckerList((List<T>) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerSet<?> isSet() {
        isInstance(Set.class);
        return new CheckerSet((Set<?>) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> CheckerSet<T> isSet(Class<T> clazz) {
        isInstance(Set.class);
        Predicate<Object> conditionClazz = object -> ((Set<?>) object).stream()
                .allMatch(elem -> clazz.isInstance(elem));
        is(conditionClazz, sendMessage(INIT_CHECKER, "is_set.clazz", clazz.getSimpleName()));
        return new CheckerSet((Set<T>) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerMap<?, ?> isMap() {
        isInstance(Map.class);
        return new CheckerMap((Map<?, ?>) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <K, V> CheckerMap<K, V> isMap(Class<K> clazzKey, Class<V> clazzValue) {
        isInstance(Map.class);
        Predicate<Object> conditionClazz = object -> ((Map<?, ?>) object).entrySet().stream()
                .allMatch(entry -> clazzKey.isInstance(entry.getKey()) &&
                        clazzValue.isInstance(entry.getValue()));
        is(conditionClazz,
                sendMessage(INIT_CHECKER, "is_map.clazz", clazzKey.getSimpleName(), clazzValue.getSimpleName()));
        return new CheckerMap((Map<K, V>) this.object, name);
    }

}