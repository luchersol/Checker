package util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Utils {

    public static boolean equalsContent(Object a, Object b) {

        if (a == b) return true;
        if (a == null || b == null) return false;

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

        return Objects.equals(a, b);
    }

}
