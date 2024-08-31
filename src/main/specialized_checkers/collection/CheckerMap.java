package src.main.specialized_checkers.collection;

import java.util.List;
import java.util.Map;

import src.main.AbstractChecker;

public class CheckerMap<K, V> extends AbstractChecker<Map<K, V>>{

    public CheckerMap(Map<K, V> object, String name) {
        super(object, name);
    }

}
