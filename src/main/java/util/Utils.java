package util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Utils provides utility methods for deep equality checks and other helper functions.
 */
public class Utils {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Utils(){
    }


    /**
     * Checks deep equality between two objects, supporting arrays, collections, maps, atomic types, and CharSequences.
     * <p>
     * - For arrays, performs a deep equality check.
     * - For collections, checks size and recursively compares elements.
     * - For maps, checks size and recursively compares values for each key.
     * - For CharSequences, compares content.
     * - For AtomicInteger/AtomicLong, compares values.
     * - Falls back to {@link Objects#equals(Object, Object)} for other types.
     *
     * @param a the first object to compare
     * @param b the second object to compare
     * @return {@code true} if the objects are deeply equal, {@code false} otherwise
     */
    public static boolean equalsContent(Object a, Object b) {
        if (Objects.equals(a, b)) return true;

        // CharSequence (StringBuilder, StringBuffer, String)
        if (a instanceof CharSequence && b instanceof CharSequence) {
            return a.toString().contentEquals((CharSequence) b);
        }

        // Arrays (profundos)
        if (a.getClass().isArray() && b.getClass().isArray()) {
            return Arrays.deepEquals(new Object[]{a}, new Object[]{b});
        }

        // Colecciones
        if (a instanceof Collection<?> ca && b instanceof Collection<?> cb) {
            if (ca.size() != cb.size()) return false;
            Iterator<?> itA = ca.iterator();
            Iterator<?> itB = cb.iterator();
            while (itA.hasNext()) {
                if (!equalsContent(itA.next(), itB.next())) return false;
            }
            return true;
        }

        // Maps
        if (a instanceof Map<?,?> ma && b instanceof Map<?,?> mb) {
            if (ma.size() != mb.size()) return false;
            for (var e : ma.entrySet()) {
                if (!equalsContent(e.getValue(), mb.get(e.getKey()))) return false;
            }
            return true;
        }

        // AtomicInteger / AtomicLong
        if (a instanceof AtomicInteger ai && b instanceof AtomicInteger bi) {
            return ai.get() == bi.get();
        }
        if (a instanceof AtomicLong al && b instanceof AtomicLong bl) {
            return al.get() == bl.get();
        }

        return false;
    }

}
