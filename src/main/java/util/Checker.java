package util;

import static util.Message.*;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Set;

import specialized_checkers.CheckerColor;
import specialized_checkers.CheckerCurrency;
import specialized_checkers.CheckerString;
import specialized_checkers.collection.CheckerGraph;
import specialized_checkers.collection.CheckerList;
import specialized_checkers.collection.CheckerMap;
import specialized_checkers.collection.CheckerSet;
import specialized_checkers.collection.CheckerTree;
import specialized_checkers.io.CheckerFile;
import specialized_checkers.io.CheckerJson;
import specialized_checkers.io.CheckerURI;
import specialized_checkers.math.CheckerArray;
import specialized_checkers.math.CheckerMatrix;
import specialized_checkers.math.numbers.bigTypes.CheckerBigDecimal;
import specialized_checkers.math.numbers.bigTypes.CheckerBigInteger;
import specialized_checkers.math.numbers.decimalTypes.CheckerDouble;
import specialized_checkers.math.numbers.decimalTypes.CheckerFloat;
import specialized_checkers.math.numbers.integerTypes.CheckerInteger;
import specialized_checkers.math.numbers.integerTypes.CheckerLong;
import util.collection.Graph;


/**
 * Main entry point for object validation and type checking.
 * <p>
 * The Checker class provides a fluent API for validating and inspecting objects of various types.
 * It supports type checks, collection checks, file and URI checks, number and matrix checks, and more.
 * Specialized checkers are returned for specific types, enabling further type-specific validation.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>
 * Checker = Checker.check(myList)
 *     .isList(String.class)
 *     .isNotEmpty();
 * </pre>
 *
 * Checker is the main class for validating and inspecting objects of any type.
 * It extends AbstractChecker and provides methods to check for type, structure, and content,
 * returning specialized checkers for further validation when appropriate.
 */
public class Checker extends AbstractChecker<Object, Checker> {


    /**
     * Message key for this checker type.
     */
    private static final String INIT_CHECKER = "checker";

    /**
     * Default name for checked objects when none is provided.
     */
    private static final String DEFAULT_NAME = "Object";


    /**
     * Constructs a Checker for the given object and name.
     *
     * @param object the object to check
     * @param name   the name or label for the object (used in error messages)
     */
    protected Checker(Object object, String name) {
        super(object, name);
    }


    /**
     * Returns this Checker instance (for fluent API).
     *
     * @return this Checker
     */
    @Override
    protected Checker self() {
        return this;
    }


    /**
     * Creates a Checker for the given object and name.
     *
     * @param object the object to check
     * @param name   the name or label for the object
     * @return a new Checker instance
     */
    public static Checker check(Object object, String name) {
        return new Checker(object, name);
    }


    /**
     * Creates a Checker for the given object with a default name.
     *
     * @param object the object to check
     * @return a new Checker instance
     */
    public static Checker check(Object object) {
        return new Checker(object, DEFAULT_NAME);
    }


    /**
     * Checks if the object is an instance of the given class.
     *
     * @param clazz the class to check against
     * @param <T>   the type of the class
     * @return this Checker
     */
    public <T> Checker isInstance(Class<T> clazz) {
        return is(object -> clazz.isInstance(object), sendMessage(INIT_CHECKER, "is_instance", clazz.getSimpleName()));
    }


    /**
     * Casts the object to the given class if possible, or returns null if not compatible.
     *
     * @param obj   the object to cast
     * @param clazz the target class
     * @param <T>   the type of the class
     * @return the casted object or null
     */
    @SuppressWarnings("unchecked")
    private static <T> T transformOfNull(Object obj, Class<T> clazz) {
        return clazz.isInstance(obj) ? (T) obj : null;
    }


    /**
     * Checks if the object is a Collection.
     *
     * @return this Checker
     */
    public Checker isCollection() {
        isInstance(Collection.class);
        return this;
    }


    /**
     * Creates a CheckerGraph for the given edges and directionality.
     *
     * @param edges    the collection of edges
     * @param directed true if the graph is directed
     * @param <N>      node type
     * @param <E>      edge weight type (extends Number)
     * @return a CheckerGraph instance
     */
    public <N,E extends Number> CheckerGraph<N,E> isGraph(Collection<Graph.Edge<N,E>> edges, boolean directed) {
        return CheckerGraph.check(edges, directed, name);
    }


    /**
     * Creates a CheckerGraph for the given edges (undirected by default).
     *
     * @param edges the collection of edges
     * @param <N>   node type
     * @param <E>   edge weight type (extends Number)
     * @return a CheckerGraph instance
     */
    public <N,E extends Number> CheckerGraph<N,E> isGraph(Collection<Graph.Edge<N,E>> edges) {
        return CheckerGraph.check(edges, name);
    }


    /**
     * Creates a CheckerGraph for the given nodes and edges (undirected by default).
     *
     * @param nodes the collection of nodes
     * @param edges the collection of edges
     * @param <N>   node type
     * @param <E>   edge weight type (extends Number)
     * @return a CheckerGraph instance
     */
    public <N,E extends Number> CheckerGraph<N,E> isGraph(Collection<N> nodes, Collection<Graph.Edge<N,E>> edges) {
        return CheckerGraph.check(nodes, edges, name);
    }


    /**
     * Creates a CheckerGraph for the given nodes, edges, and directionality.
     *
     * @param nodes    the collection of nodes
     * @param edges    the collection of edges
     * @param directed true if the graph is directed
     * @param <N>      node type
     * @param <E>      edge weight type (extends Number)
     * @return a CheckerGraph instance
     */
    public <N,E extends Number> CheckerGraph<N,E> isGraph(Collection<N> nodes, Collection<Graph.Edge<N,E>> edges, boolean directed) {
        return CheckerGraph.check(nodes, edges, directed, name);
    }

    /**
     * Checks if the object is a List and returns a CheckerList for further validation.
     *
     * @return a CheckerList instance
     */
    public CheckerList<?> isList() {
        isInstance(List.class);
        return CheckerList.check((List<?>) this.object, name);
    }


    /**
     * Checks if the object is a List whose elements are all instances of the given class.
     *
     * @param clazz the class of the list elements
     * @param <T>   the element type
     * @return a CheckerList instance
     */
    @SuppressWarnings("unchecked")
    public <T> CheckerList<T> isList(Class<T> clazz) {
        isInstance(List.class);
        is(object -> ((List<?>) this.object).stream().allMatch(elem -> clazz.isInstance(elem)),
                sendMessage(INIT_CHECKER, "is_list.clazz", clazz.getSimpleName()));
        return CheckerList.check((List<T>) this.object, name);
    }



    /**
     * Checks if the object is a Map and returns a CheckerMap for further validation.
     *
     * @return a CheckerMap instance
     */
    public CheckerMap<?, ?> isMap() {
        isInstance(Map.class);
        return CheckerMap.check((Map<?, ?>) this.object, name);
    }

    /**
     * Checks if the object is a Map whose keys and values are instances of the given classes.
     *
     * @param clazzKey   the class of the map keys
     * @param clazzValue the class of the map values
     * @param <K>        key type
     * @param <V>        value type
     * @return a CheckerMap instance
     */
    @SuppressWarnings("unchecked")
    public <K, V> CheckerMap<K, V> isMap(Class<K> clazzKey, Class<V> clazzValue) {
        isInstance(Map.class);
        is(object -> ((Map<?, ?>) object).entrySet().stream()
                .allMatch(entry -> clazzKey.isInstance(entry.getKey()) && clazzValue.isInstance(entry.getValue())),
            sendMessage(INIT_CHECKER, "is_map.clazz", clazzKey.getSimpleName(), clazzValue.getSimpleName()));
        return CheckerMap.check((Map<K, V>) this.object, name);
    }



    /**
     * Checks if the object is a Set and returns a CheckerSet for further validation.
     *
     * @return a CheckerSet instance
     */
    public CheckerSet<?> isSet() {
        isInstance(Set.class);
        return CheckerSet.check((Set<?>) this.object, name);
    }


    /**
     * Checks if the object is a Set whose elements are all instances of the given class.
     *
     * @param clazz the class of the set elements
     * @param <T>   the element type
     * @return a CheckerSet instance
     */
    @SuppressWarnings("unchecked")
    public <T> CheckerSet<T> isSet(Class<T> clazz) {
        isInstance(Set.class);
        is(object -> ((Set<?>) object).stream().allMatch(elem -> clazz.isInstance(elem)),
            sendMessage(INIT_CHECKER, "is_set.clazz", clazz.getSimpleName()));
        return CheckerSet.check((Set<T>) this.object, name);
    }



    /**
     * Creates a CheckerTree for the given root value and children map.
     *
     * @param rootValue   the root node value
     * @param childrenMap the map of children for each node
     * @param <T>         node type
     * @return a CheckerTree instance
     */
    public <T> CheckerTree<T> isTree(T rootValue, Map<T, List<T>> childrenMap) {
        return CheckerTree.check(rootValue, childrenMap, name);
    }


    /**
     * Creates a CheckerTree for the given root value.
     *
     * @param rootValue the root node value
     * @param <T>       node type
     * @return a CheckerTree instance
     */
    public <T> CheckerTree<T> isTree(T rootValue) {
        return CheckerTree.check(rootValue, name);
    }


    /**
     * Checks if the object is a File and returns a CheckerFile for further validation.
     *
     * @return a CheckerFile instance
     */
    public CheckerFile isFile() {
        isInstance(File.class);
        return CheckerFile.check(transformOfNull(this.object, File.class), name);
    }


    /**
     * Checks if the object is a File and returns a CheckerJson for JSON file validation.
     *
     * @return a CheckerJson instance
     * @throws IOException if file reading fails
     */
    public CheckerJson isJson() throws IOException {
        isInstance(File.class);
        return CheckerJson.check(transformOfNull(this.object, File.class), name);
    }


    /**
     * Checks if the object is a URI and returns a CheckerURI for further validation.
     *
     * @return a CheckerURI instance
     * @throws IOException if URI reading fails
     */
    public CheckerURI isURI() throws IOException {
        isInstance(URI.class);
        return CheckerURI.check(transformOfNull(this.object, URI.class), name);
    }


    /**
     * Checks if the object is a Number.
     *
     * @return this Checker
     */
    public Checker isNumber() {
        isInstance(Number.class);
        return this;
    }


    /**
     * Checks if the object is a BigInteger and returns a CheckerBigInteger for further validation.
     *
     * @return a CheckerBigInteger instance
     */
    public CheckerBigInteger isBigInteger() {
        isInstance(BigInteger.class);
        return CheckerBigInteger.check(transformOfNull(this.object, BigInteger.class), name);
    }


    /**
     * Checks if the object is a BigDecimal and returns a CheckerBigDecimal for further validation.
     *
     * @return a CheckerBigDecimal instance
     */
    public CheckerBigDecimal isBigDecimal() {
        isInstance(BigDecimal.class);
        return CheckerBigDecimal.check(transformOfNull(this.object, BigDecimal.class), name);
    }


    /**
     * Checks if the object is an Integer and returns a CheckerInteger for further validation.
     *
     * @return a CheckerInteger instance
     */
    public CheckerInteger isInteger() {
        isInstance(Integer.class);
        return CheckerInteger.check(transformOfNull(this.object, Integer.class), name);
    }


    /**
     * Checks if the object is a Long and returns a CheckerLong for further validation.
     *
     * @return a CheckerLong instance
     */
    public CheckerLong isLong() {
        isInstance(Long.class);
        return CheckerLong.check(transformOfNull(this.object, Long.class), name);
    }


    /**
     * Checks if the object is a Float and returns a CheckerFloat for further validation.
     *
     * @return a CheckerFloat instance
     */
    public CheckerFloat isFloat() {
        isInstance(Float.class);
        return CheckerFloat.check(transformOfNull(this.object, Float.class), name);
    }


    /**
     * Checks if the object is a Double and returns a CheckerDouble for further validation.
     *
     * @return a CheckerDouble instance
     */
    public CheckerDouble isDouble() {
        isInstance(Double.class);
        return CheckerDouble.check(transformOfNull(this.object, Double.class), name);
    }


    /**
     * Checks if the object is an array and returns a CheckerArray for further validation.
     *
     * @return a CheckerArray instance
     */
    public CheckerArray<?> isArray() {
        is(object -> object.getClass().isArray(), sendMessage(INIT_CHECKER, "is_array"));
        return CheckerArray.check(((Collection<?>) this.object).toArray(), this.name);
    }


    /**
     * Checks if the object is an array of the given class and returns a CheckerArray for further validation.
     *
     * @param clazz the class of the array elements
     * @param <T>   the element type
     * @return a CheckerArray instance
     */
    @SuppressWarnings("unchecked")
    public <T> CheckerArray<T> isArray(Class<T> clazz) {
        is(object -> object.getClass().isArray(), sendMessage(INIT_CHECKER, "is_array", clazz.getSimpleName()));
        Collection<T> collection = (Collection<T>) this.object;
        T[] array = collection.toArray(size -> (T[]) Array.newInstance(clazz, size));
        return CheckerArray.check(array, this.name);
    }


    /**
     * Checks if the object is a matrix (2D array) and returns a CheckerMatrix for further validation.
     *
     * @return a CheckerMatrix instance
     */
    public CheckerMatrix<?> isMatrix() {
        is(object ->
            object.getClass().isArray() && object.getClass().getComponentType().isArray(),
            sendMessage(INIT_CHECKER, "is_matrix")
        );

        return CheckerMatrix.check((Number[][]) this.object, this.name);
    }


    /**
     * Checks if the object is a matrix (2D array) of the given class and returns a CheckerMatrix for further validation.
     *
     * @param clazz the class of the matrix elements
     * @param <T>   the element type (extends Number)
     * @return a CheckerMatrix instance
     */
    @SuppressWarnings("unchecked")
    public <T extends Number> CheckerMatrix<T> isMatrix(Class<T> clazz) {
        is(object ->
            object.getClass().isArray() && object.getClass().getComponentType().isArray(),
            sendMessage(INIT_CHECKER, "is_matrix", clazz.getSimpleName())
        );
        return CheckerMatrix.check((T[][]) this.object, this.name);
    }

    /**
     * Checks if the object is a Color and returns a CheckerColor for further validation.
     *
     * @return a CheckerColor instance
     */
    public CheckerColor isColor() {
        isInstance(Color.class);
        return CheckerColor.check(transformOfNull(this.object, Color.class), this.name);
    }


    /**
     * Checks if the object is a Currency and returns a CheckerCurrency for further validation.
     *
     * @return a CheckerCurrency instance
     */
    public CheckerCurrency isCurrency() {
        isInstance(Currency.class);
        return CheckerCurrency.check(transformOfNull(this.object, Currency.class), this.name);
    }


    /**
     * Checks if the object is a String and returns a CheckerString for further validation.
     *
     * @return a CheckerString instance
     */
    public CheckerString isString() {
        isInstance(String.class);
        return CheckerString.check(transformOfNull(this.object, String.class), this.name);
    }
}
