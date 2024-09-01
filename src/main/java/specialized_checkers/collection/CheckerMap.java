package specialized_checkers.collection;

import java.util.Map;

import util.AbstractChecker;

public class CheckerMap<K, V> extends AbstractChecker<Map<K, V>>{

    public CheckerMap(Map<K, V> object, String name) {
        super(object, name);
    }

}
