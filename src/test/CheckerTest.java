package src.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import src.main.Checker;

public class CheckerTest {

    private static String STRING_TEST = "string_test";
    private static Integer INTEGER_TEST = 1;
    private static Long LONG_TEST = 1L;
    private static Float FLOAT_TEST = 1F;
    private static Double DOUBLE_TEST = 1D;
    private static int[] ARRAY_TEST = {1};
    private static Collection<Integer> COLLECTION_TEST = new LinkedList<>();
    private static List<Integer> LIST_TEST = new ArrayList<>();
    private static Set<Integer> SET_TEST = new HashSet<>();
    private static Map<Integer, Integer> MAP_TEST = new HashMap<>();
    
    
    public static void main(String[] args) {
        // Checker.check(STRING_TEST).isString();
        // Checker.check(INTEGER_TEST).isInteger().isPositive().hasErrors();
        // Checker.check(LONG_TEST).isLong();
        // Checker.check(FLOAT_TEST).isFloat();
        // Checker.check(DOUBLE_TEST).isDouble();
        // Checker.check(ARRAY_TEST).isArray();
        // Checker.check(COLLECTION_TEST).isCollection();
        // Checker.check(LIST_TEST).isList();
        // Checker.check(SET_TEST).isSet();
        // Checker.check(MAP_TEST).isMap();

    }
}
