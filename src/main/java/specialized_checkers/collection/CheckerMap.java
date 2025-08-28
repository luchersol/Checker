package specialized_checkers.collection;

import static util.Message.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import util.AbstractChecker;

public class CheckerMap<K, V> extends AbstractChecker<Map<K, V>, CheckerMap<K, V>>{

    private static final String INIT_MAP = "collections.map";

    public CheckerMap(Map<K, V> object, String name) {
        super(object, name);
    }

    /**
     * @return CheckerMap<K, V>
     */
    @Override
    protected CheckerMap<K, V> self() {
        return this;
    }

    /**
     * @return CheckerMap<K, V>
     */
    public CheckerMap<K,V> isEmpty(){
        return is(map -> map.isEmpty(), sendMessage(INIT_MAP, "is_empty"));
    }
    /**
     * @param predicate
     * @return CheckerMap<K, V>
     */
    public CheckerMap<K,V> anyMatch(Predicate<Entry<K,V>> predicate){
        return is(map -> map.entrySet().stream().anyMatch(predicate), sendMessage(INIT_MAP, "any_match"));
    }

    /**
     * @param predicate
     * @return CheckerMap<K, V>
     */
    public CheckerMap<K,V> anyMatch(BiPredicate<K,V> predicate){
        return is(map -> map.entrySet().stream().anyMatch(entry -> predicate.test(entry.getKey(), entry.getValue())), sendMessage(INIT_MAP, "any_match"));
    }

    /**
     * @param predicate
     * @return CheckerMap<K, V>
     */
    public CheckerMap<K,V> allMatch(Predicate<Entry<K,V>> predicate){
        return is(map -> map.entrySet().stream().allMatch(predicate), sendMessage(INIT_MAP, "all_match"));
    }

    /**
     * @param predicate
     * @return CheckerMap<K, V>
     */
    public CheckerMap<K,V> allMatch(BiPredicate<K,V> predicate){
        return is(map -> map.entrySet().stream().allMatch(entry -> predicate.test(entry.getKey(), entry.getValue())), sendMessage(INIT_MAP, "all_match"));
    }

}
