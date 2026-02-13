package com.luchersol.core.util;

import static com.luchersol.core.util.MessageService.*;

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

import com.luchersol.core.specialized_checkers.CheckerColor;
import com.luchersol.core.specialized_checkers.CheckerCurrency;
import com.luchersol.core.specialized_checkers.CheckerString;
import com.luchersol.core.specialized_checkers.collection.CheckerGraph;
import com.luchersol.core.specialized_checkers.collection.CheckerList;
import com.luchersol.core.specialized_checkers.collection.CheckerMap;
import com.luchersol.core.specialized_checkers.collection.CheckerSet;
import com.luchersol.core.specialized_checkers.collection.CheckerTree;
import com.luchersol.core.specialized_checkers.io.CheckerFile;
import com.luchersol.core.specialized_checkers.io.CheckerJson;
import com.luchersol.core.specialized_checkers.io.CheckerURI;
import com.luchersol.core.specialized_checkers.math.CheckerArray;
import com.luchersol.core.specialized_checkers.math.CheckerMatrix;
import com.luchersol.core.specialized_checkers.math.numbers.bigTypes.CheckerBigDecimal;
import com.luchersol.core.specialized_checkers.math.numbers.bigTypes.CheckerBigInteger;
import com.luchersol.core.specialized_checkers.math.numbers.decimalTypes.CheckerDouble;
import com.luchersol.core.specialized_checkers.math.numbers.decimalTypes.CheckerFloat;
import com.luchersol.core.specialized_checkers.math.numbers.integerTypes.CheckerInteger;
import com.luchersol.core.specialized_checkers.math.numbers.integerTypes.CheckerLong;
import com.luchersol.core.util.collection.Graph;


/**
 * Main entry point for object validation and type checking.
 *
 * <p>
 * {@code Checker<T>} provides a fluent API for validating and inspecting objects of any type.
 * It supports type checks, structural validations, and content assertions (e.g., collections,
 * files, URIs, numbers, matrices). When applicable, specialized checker instances are returned
 * to enable type-specific validations.
 *
 *
 * <p><strong>Example usage:</strong>
 * <pre>{@code
 * Checker<List<String>> checker = Checker.check(myList)
 *     .isList(String.class)
 *     .isNotEmpty();
 * }</pre>
 *
 * <p>
 * This class extends {@code AbstractChecker<T, Checker<T>>} and serves as the primary facade
 * for validation workflows.
 *
 *
 * @param <T> the type of the object being validated
 */
public class Checker<T> extends AbstractChecker<T, Checker<T>> {


    /**
     * Message key for this checker type.
     */
    private static final String INIT_CHECKER = "checker";

    /**
     * Default name for checked objects when none is provided.
     */
    private static final String DEFAULT_NAME = "Object";


    /**
     * Constructs a {@code Checker<T>} for the given object and name.
     *
     * @param object the object to check
     * @param name   the name or label for the object (used in error messages)
     */
    protected Checker(T object, String name) {
        super(object, name);
    }


    /**
     * Returns this {@code Checker<T>} instance (for fluent API).
     *
     * @return this {@code Checker<T>}
     */
    @Override
    protected Checker<T> self() {
        return this;
    }


    /**
     * Creates a new {@link Checker} for the given object and name.
     *
     * @param <T>   the type of the object being checked
     * @param object the object to check
     * @param name   the name or label for the object
     * @return a new {@code Checker<T>} instance
     */
    public static <T> Checker<T> check(T object, String name) {
        return new Checker<T>(object, name);
    }


    /**
     * Creates a new {@link Checker} for the given object and name.
     *
     * @param <T>   the type of the object being checked
     * @param object the object to check
     * @return a new {@code Checker<T>} instance
     */
    public static <T> Checker<T> check(T object) {
        return new Checker<T>(object, DEFAULT_NAME);
    }


    /**
     * Checks if the object is an instance of the given class.
     *
     * @param clazz the class to check against
     * @param <C>   the type of the class
     * @return this {@code Checker<T>}
     */
    @SuppressWarnings("unchecked")
    public <C> Checker<C> isInstance(Class<C> clazz) {
        return (Checker<C>) is(object -> clazz.isInstance(object), sendMessage(INIT_CHECKER, "is_instance", clazz.getSimpleName()));
    }


    /**
     * Casts the object to the given class if possible, or returns null if not compatible.
     *
     * @param obj   the object to cast
     * @param clazz the target class
     * @param <T>   the type of the class
     * @return the casted object or null
     */
    private static <T> T transformOfNull(Object obj, Class<T> clazz) {
        return clazz.isInstance(obj) ? clazz.cast(obj) : null;
    }


    /**
     * Checks if the object is a Collection.
     *
     * @return this {@code Checker<T>}
     */
    public Checker<T> isCollection() {
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
        return CheckerGraph.check(edges, directed, name)
            .updateChecker(this);
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
        return CheckerGraph.check(edges, name)
            .updateChecker(this);
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
        return CheckerGraph.check(nodes, edges, name)
            .updateChecker(this);
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
        return CheckerGraph.check(nodes, edges, directed, name)
            .updateChecker(this);
    }

    /**
     * Checks if the object is a List and returns a CheckerList for further validation.
     *
     * @return a CheckerList instance
     */
    public CheckerList<?> isList() {
        isInstance(List.class);
        return CheckerList.check((List<?>) this.object, name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is a List whose elements are all instances of the given class.
     *
     * @param clazz the class of the list elements
     * @param <C>   the element type
     * @return a CheckerList instance
     */
    @SuppressWarnings("unchecked")
    public <C> CheckerList<C> isList(Class<C> clazz) {
        isInstance(List.class);
        is(object -> ((List<?>) this.object).stream().allMatch(elem -> clazz.isInstance(elem)),
                sendMessage(INIT_CHECKER, "is_list.clazz", clazz.getSimpleName()));
        return CheckerList.check((List<C>) this.object, name)
            .updateChecker(this);
    }



    /**
     * Checks if the object is a Map and returns a CheckerMap for further validation.
     *
     * @return a CheckerMap instance
     */
    public CheckerMap<?, ?> isMap() {
        isInstance(Map.class);
        return CheckerMap.check((Map<?, ?>) this.object, name)
            .updateChecker(this);
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
        return CheckerMap.check((Map<K, V>) this.object, name)
            .updateChecker(this);
    }

    /**
     * Checks if the object is a Set and returns a CheckerSet for further validation.
     *
     * @return a CheckerSet instance
     */
    public CheckerSet<?> isSet() {
        isInstance(Set.class);
        return CheckerSet.check((Set<?>) this.object, name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is a Set whose elements are all instances of the given class.
     *
     * @param clazz the class of the set elements
     * @param <C>   the element type
     * @return a CheckerSet instance
     */
    @SuppressWarnings("unchecked")
    public <C> CheckerSet<C> isSet(Class<C> clazz) {
        isInstance(Set.class);
        is(object -> ((Set<?>) object).stream().allMatch(elem -> clazz.isInstance(elem)),
            sendMessage(INIT_CHECKER, "is_set.clazz", clazz.getSimpleName()));
        return CheckerSet.check((Set<C>) this.object, name)
            .updateChecker(this);
    }



    /**
     * Creates a CheckerTree for the given root value and children map.
     *
     * @param rootValue   the root node value
     * @param childrenMap the map of children for each node
     * @return a CheckerTree instance
     */
    public <C> CheckerTree<C> isTree(C rootValue, Map<C, List<C>> childrenMap) {
        return CheckerTree.check(rootValue, childrenMap, name)
            .updateChecker(this);
    }


    /**
     * Creates a CheckerTree for the given root value.
     *
     * @param rootValue the root node value
     * @param <C>       node type
     * @return a CheckerTree instance
     */
    public <C> CheckerTree<C> isTree(C rootValue) {
        return CheckerTree.check(rootValue, name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is a File and returns a CheckerFile for further validation.
     *
     * @return a CheckerFile instance
     */
    public CheckerFile isFile() {
        isInstance(File.class);
        return CheckerFile.check(transformOfNull(this.object, File.class), name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is a File and returns a CheckerJson for JSON file validation.
     *
     * @return a CheckerJson instance
     * @throws IOException if file reading fails
     */
    public CheckerJson isJson() throws IOException {
        isInstance(File.class);
        return CheckerJson.check(transformOfNull(this.object, File.class), name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is a URI and returns a CheckerURI for further validation.
     *
     * @return a CheckerURI instance
     * @throws IOException if URI reading fails
     */
    public CheckerURI isURI() throws IOException {
        isInstance(URI.class);
        return CheckerURI.check(transformOfNull(this.object, URI.class), name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is a Number.
     *
     * @return this {@code Checker<T>}
     */
    public Checker<T> isNumber() {
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
        return CheckerBigInteger.check(transformOfNull(this.object, BigInteger.class), name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is a BigDecimal and returns a CheckerBigDecimal for further validation.
     *
     * @return a CheckerBigDecimal instance
     */
    public CheckerBigDecimal isBigDecimal() {
        isInstance(BigDecimal.class);
        return CheckerBigDecimal.check(transformOfNull(this.object, BigDecimal.class), name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is an Integer and returns a CheckerInteger for further validation.
     *
     * @return a CheckerInteger instance
     */
    public CheckerInteger isInteger() {
        isInstance(Integer.class);
        return CheckerInteger.check(transformOfNull(this.object, Integer.class), name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is a Long and returns a CheckerLong for further validation.
     *
     * @return a CheckerLong instance
     */
    public CheckerLong isLong() {
        isInstance(Long.class);
        return CheckerLong.check(transformOfNull(this.object, Long.class), name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is a Float and returns a CheckerFloat for further validation.
     *
     * @return a CheckerFloat instance
     */
    public CheckerFloat isFloat() {
        isInstance(Float.class);
        return CheckerFloat.check(transformOfNull(this.object, Float.class), name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is a Double and returns a CheckerDouble for further validation.
     *
     * @return a CheckerDouble instance
     */
    public CheckerDouble isDouble() {
        isInstance(Double.class);
        return CheckerDouble.check(transformOfNull(this.object, Double.class), name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is an array and returns a CheckerArray for further validation.
     *
     * @return a CheckerArray instance
     */
    public CheckerArray<?> isArray() {
        is(object -> object.getClass().isArray(), sendMessage(INIT_CHECKER, "is_array"));
        return CheckerArray.check(((Collection<?>) this.object).toArray(), this.name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is an array of the given class and returns a CheckerArray for further validation.
     *
     * @param clazz the class of the array elements
     * @param <C>   the element type
     * @return a CheckerArray instance
     */
    @SuppressWarnings("unchecked")
    public <C> CheckerArray<C> isArray(Class<C> clazz) {
        is(object -> object.getClass().isArray(), sendMessage(INIT_CHECKER, "is_array", clazz.getSimpleName()));
        Collection<C> collection = (Collection<C>) this.object;
        C[] array = collection.toArray(size -> (C[]) Array.newInstance(clazz, size));
        return CheckerArray.check(array, this.name)
            .updateChecker(this);
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

        return CheckerMatrix.check((Number[][]) this.object, this.name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is a matrix (2D array) of the given class and returns a CheckerMatrix for further validation.
     *
     * @param clazz the class of the matrix elements
     * @param <C>   the element type (extends Number)
     * @return a CheckerMatrix instance
     */
    @SuppressWarnings("unchecked")
    public <C extends Number> CheckerMatrix<C> isMatrix(Class<C> clazz) {
        is(object ->
            object.getClass().isArray() && object.getClass().getComponentType().isArray(),
            sendMessage(INIT_CHECKER, "is_matrix", clazz.getSimpleName())
        );
        return CheckerMatrix.check((C[][]) this.object, this.name)
            .updateChecker(this);
    }

    /**
     * Checks if the object is a Color and returns a CheckerColor for further validation.
     *
     * @return a CheckerColor instance
     */
    public CheckerColor isColor() {
        isInstance(Color.class);
        return CheckerColor.check(transformOfNull(this.object, Color.class), this.name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is a Currency and returns a CheckerCurrency for further validation.
     *
     * @return a CheckerCurrency instance
     */
    public CheckerCurrency isCurrency() {
        isInstance(Currency.class);
        return CheckerCurrency.check(transformOfNull(this.object, Currency.class), this.name)
            .updateChecker(this);
    }


    /**
     * Checks if the object is a String and returns a CheckerString for further validation.
     *
     * @return a CheckerString instance
     */
    public CheckerString isString() {
        isInstance(String.class);
        return CheckerString.check(transformOfNull(this.object, String.class), this.name)
            .updateChecker(this);
    }
}
