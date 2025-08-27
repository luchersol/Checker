package util;

import java.util.function.Predicate;

public interface InterfaceChecker<T extends InterfaceChecker<T, U>, U> {

    T is(Predicate<U> condition);
    T is(Predicate<U> condition, String message);

    T isNot(Predicate<U> condition);
    T isNot(Predicate<U> condition, String message);

}
