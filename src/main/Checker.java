package src.main;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import src.main.specialized_checkers.CheckerArray;
import src.main.specialized_checkers.CheckerString;
import src.main.specialized_checkers.collection.CheckerCollection;
import src.main.specialized_checkers.collection.CheckerList;
import src.main.specialized_checkers.collection.CheckerMap;
import src.main.specialized_checkers.collection.CheckerSet;
import src.main.specialized_checkers.number.CheckerDouble;
import src.main.specialized_checkers.number.CheckerFloat;
import src.main.specialized_checkers.number.CheckerInteger;
import src.main.specialized_checkers.number.CheckerLong;
import src.main.specialized_checkers.number.CheckerNumber;

public class Checker extends AbstractChecker<Object>{

    public Checker(Object object) {
        super(object);
    }

    public Checker(Object object, List<Exception> errors) {
        super(object, errors);
    }

    public Checker(Object object, List<Exception> errors, boolean saveErrors) {
        super(object, errors, saveErrors);
    }
    
    public static Checker check(Object object) {
        return new Checker(object);
    }

    public Checker notSaveErrors(){
        this.saveErrors = false;
        return this;
    }

    public <T> void isClass(Class<T> clazz) {
        is(clazz.isInstance(this.object), "message");
    }

    public CheckerString isString() {
        isClass(String.class);
        return new CheckerString((String) this.object);
    }

    public CheckerInteger isInteger() {
        isClass(Integer.class);
        return new CheckerInteger((Integer) this.object);
    }

    public CheckerLong isLong() {
        isClass(Long.class);
        return new CheckerLong((Long) this.object);
    }

    public CheckerFloat isFloat() {
        isClass(Float.class);
        return new CheckerFloat((Float) this.object);
    }

    public CheckerDouble isDouble() {
        isClass(Double.class);
        return new CheckerDouble((Double) this.object);
    }

    public CheckerNumber isNumber() {
        isClass(Number.class);
        return new CheckerNumber((Number) this.object);
    }

    public CheckerArray isArray() {
        is(this.object.getClass().isArray(), "null");
        return new CheckerArray(((Collection<?>) this.object).toArray());
    }

    public CheckerCollection isCollection() {
        isClass(Collection.class);
        return new CheckerCollection((Collection<?>) this.object);
    }

    public CheckerList isList() {
        isClass(List.class);
        return new CheckerList((List) this.object);
    }

    public CheckerSet isSet() {
        isClass(Set.class);
        return new CheckerSet((Set) this.object);
    }

    public CheckerMap isMap() {
        isClass(Map.class);
        return new CheckerMap((Map) this.object);
    }
}