package util;

import static util.Message.*;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
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

public class Checker extends AbstractChecker<Object, Checker> {

    private static final String INIT_CHECKER = "checker";

    public Checker(Object object, String name) {
        super(object, name);
    }

    /**
     * @return Checker
     */
    @Override
    protected Checker self() {
        return this;
    }

    /**
     * @param object
     * @param name
     * @return Checker
     */
    public static Checker check(Object object, String name) {
        return new Checker(object, name);
    }

    /**
     * @param object
     * @return Checker
     */
    public static Checker check(Object object) {
        return new Checker(object, "Object");
    }

    /**
     * @param clazz
     * @return Checker
     */
    public <T> Checker isInstance(Class<T> clazz) {
        return is(object -> clazz.isInstance(object), sendMessage(INIT_CHECKER, "is_instance", clazz.getSimpleName()));
    }

    /**
     * @param obj
     * @param clazz
     * @return T
     */
    @SuppressWarnings({ "unchecked" })
    private static <T> T transformOfNull(Object obj, Class<T> clazz) {
        return clazz.isInstance(obj) ? (T) obj : null;
    }

    /**
     * @return Checker
     */
    public Checker isCollection() {
        isInstance(Collection.class);
        return this;
    }

    /**
     * @param edges
     * @param directed
     * @return CheckerGraph<N, E>
     */
    public <N,E extends Number> CheckerGraph<N,E> isGraph(Collection<Graph.Edge<N,E>> edges, boolean directed) {
        return new CheckerGraph<N,E>(edges, directed, name);
    }

    /**
     * @param edges
     * @return CheckerGraph<N, E>
     */
    public <N,E extends Number> CheckerGraph<N,E> isGraph(Collection<Graph.Edge<N,E>> edges) {
        return new CheckerGraph<N,E>(edges, name);
    }

    /**
     * @param edges
     * @return CheckerGraph<N, E>
     */
    public <N,E extends Number> CheckerGraph<N,E> isGraph(Collection<N> nodes, Collection<Graph.Edge<N,E>> edges) {
        return new CheckerGraph<N,E>(nodes, edges, name);
    }

    /**
     * @param edges
     * @param directed
     * @return CheckerGraph<N, E>
     */
    public <N,E extends Number> CheckerGraph<N,E> isGraph(Collection<N> nodes, Collection<Graph.Edge<N,E>> edges, boolean directed) {
        return new CheckerGraph<N,E>(nodes, edges, directed, name);
    }

    /**
     * @return CheckerList<?>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerList<?> isList() {
        isInstance(List.class);
        return new CheckerList((List<?>) this.object, name);
    }

    /**
     * @param clazz
     * @return CheckerList<T>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> CheckerList<T> isList(Class<T> clazz) {
        isInstance(List.class);
        is(object -> ((List<?>) this.object).stream().allMatch(elem -> clazz.isInstance(elem)),
                sendMessage(INIT_CHECKER, "is_list.clazz", clazz.getSimpleName()));
        return new CheckerList((List<T>) this.object, name);
    }


    /**
     * @return CheckerMap<?, ?>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerMap<?, ?> isMap() {
        isInstance(Map.class);
        return new CheckerMap((Map<?, ?>) this.object, name);
    }

    /**
     * @param clazzKey
     * @param clazzValue
     * @return CheckerMap<K, V>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <K, V> CheckerMap<K, V> isMap(Class<K> clazzKey, Class<V> clazzValue) {
        isInstance(Map.class);
        is(object -> ((Map<?, ?>) object).entrySet().stream()
                .allMatch(entry -> clazzKey.isInstance(entry.getKey()) && clazzValue.isInstance(entry.getValue())),
            sendMessage(INIT_CHECKER, "is_map.clazz", clazzKey.getSimpleName(), clazzValue.getSimpleName()));
        return new CheckerMap((Map<K, V>) this.object, name);
    }

    /**
     * @return CheckerSet<?>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerSet<?> isSet() {
        isInstance(Set.class);
        return new CheckerSet((Set<?>) this.object, name);
    }

    /**
     * @param clazz
     * @return CheckerSet<T>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> CheckerSet<T> isSet(Class<T> clazz) {
        isInstance(Set.class);
        is(object -> ((Set<?>) object).stream().allMatch(elem -> clazz.isInstance(elem)),
            sendMessage(INIT_CHECKER, "is_set.clazz", clazz.getSimpleName()));
        return new CheckerSet((Set<T>) this.object, name);
    }

    /**
     * @param childrenMap
     * @return CheckerTree<T>
     */
    public <T> CheckerTree<T> isTree(T rootValue, Map<T, List<T>> childrenMap) {
        return new CheckerTree<T>(rootValue, childrenMap, name);
    }

    /**
     * @param rootValue
     * @return CheckerTree<T>
     */
    public <T> CheckerTree<T> isTree(T rootValue) {
        return new CheckerTree<T>(rootValue, name);
    }

    /**
     * @return CheckerFile
     */
    public CheckerFile isFile() {
        isInstance(File.class);
        return new CheckerFile(transformOfNull(this.object, File.class), name);
    }

    /**
     * @return CheckerJson
     * @throws IOException
     */
    public CheckerJson isJson() throws IOException {
        isInstance(File.class);
        return new CheckerJson(transformOfNull(this.object, File.class), name);
    }

    /**
     * @return CheckerURI
     * @throws IOException
     */
    public CheckerURI isURI() throws IOException {
        isInstance(URI.class);
        return new CheckerURI(transformOfNull(this.object, URI.class), name);
    }

    /**
     * @return Checker
     */
    public Checker isNumber() {
        isInstance(Number.class);
        return this;
    }

    /**
     * @return CheckerBigInteger
     */
    public CheckerBigInteger isBigInteger() {
        isInstance(BigInteger.class);
        return new CheckerBigInteger(transformOfNull(this.object, BigInteger.class), name);
    }

        /**
         * @return CheckerBigDecimal
         */
        public CheckerBigDecimal isBigDecimal() {
        isInstance(BigDecimal.class);
        return new CheckerBigDecimal(transformOfNull(this.object, BigDecimal.class), name);
    }

    /**
     * @return CheckerInteger
     */
    public CheckerInteger isInteger() {
        isInstance(Integer.class);
        return new CheckerInteger(transformOfNull(this.object, Integer.class), name);
    }

    /**
     * @return CheckerLong
     */
    public CheckerLong isLong() {
        isInstance(Long.class);
        return new CheckerLong(transformOfNull(this.object, Long.class), name);
    }

    /**
     * @return CheckerFloat
     */
    public CheckerFloat isFloat() {
        isInstance(Float.class);
        return new CheckerFloat(transformOfNull(this.object, Float.class), name);
    }

    /**
     * @return CheckerDouble
     */
    public CheckerDouble isDouble() {
        isInstance(Double.class);
        return new CheckerDouble(transformOfNull(this.object, Double.class), name);
    }

    /**
     * @return CheckerArray<?>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerArray<?> isArray() {
        is(object -> object.getClass().isArray(), sendMessage(INIT_CHECKER, "is_array"));
        return new CheckerArray(((Collection<?>) this.object).toArray(), this.name);
    }

    /**
     * @param clazz
     * @return CheckerArray<T>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> CheckerArray<T> isArray(Class<T> clazz) {
        is(object -> object.getClass().isArray(), sendMessage(INIT_CHECKER, "is_array", clazz.getSimpleName()));
        return new CheckerArray(((Collection<T>) this.object).toArray(), this.name);
    }

    /**
     * @return CheckerMatrix<?>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerMatrix<?> isMatrix() {
        is(object ->
            object.getClass().isArray() && object.getClass().getComponentType().isArray(),
            sendMessage(INIT_CHECKER, "is_matrix")
        );

        return new CheckerMatrix((Number[][]) this.object, this.name);
    }

    /**
     * @param clazz
     * @return CheckerMatrix<T>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T extends Number> CheckerMatrix<T> isMatrix(Class<T> clazz) {
        is(object ->
            object.getClass().isArray() && object.getClass().getComponentType().isArray(),
            sendMessage(INIT_CHECKER, "is_matrix", clazz.getSimpleName())
        );
        return new CheckerMatrix((T[][]) this.object, this.name);
    }

    /**
     * @return CheckerColor
     */
    public CheckerColor isColor() {
        isInstance(Color.class);
        return new CheckerColor(transformOfNull(this.object, Color.class), this.name);
    }

    /**
     * @return CheckerCurrency
     */
    public CheckerCurrency isCurrency() {
        isInstance(Currency.class);
        return new CheckerCurrency(transformOfNull(this.object, Currency.class), this.name);
    }

    /**
     * @return CheckerString
     */
    public CheckerString isString() {
        isInstance(String.class);
        return new CheckerString(transformOfNull(this.object, String.class), this.name);
    }
}
