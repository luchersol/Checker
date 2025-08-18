package specialized_checkers.collections;

import static util.Message.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import util.AbstractChecker;

public class CheckerMap<K, V> extends AbstractChecker<Map<K, V>, CheckerMap<K, V>>{

    private static final String INIT_MAP = "collections.map";

    public CheckerMap(Map<K, V> object, String name) {
        super(object, name);
    }

    @Override
    protected CheckerMap<K, V> self() {
        return this;
    }

    public CheckerMap<K,V> isEmpty(){
        is(map -> map.isEmpty(), sendMessage(INIT_MAP, "is_empty"));
        return this;
    }
    public CheckerMap<K,V> anyMatch(Predicate<Entry<K,V>> predicate){
        is(map -> map.entrySet().stream().anyMatch(predicate), sendMessage(INIT_MAP, "any_match"));
        return this;
    }

    public CheckerMap<K,V> allMatch(Predicate<Entry<K,V>> predicate){
        is(map -> map.entrySet().stream().allMatch(predicate), sendMessage(INIT_MAP, "all_match"));
        return this;
    }

}
