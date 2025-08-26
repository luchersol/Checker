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
import java.util.function.Predicate;

import specialized_checkers.CheckerColor;
import specialized_checkers.CheckerCurrency;
import specialized_checkers.CheckerString;
import specialized_checkers.collection.CheckerGraph;
import specialized_checkers.collection.CheckerList;
import specialized_checkers.collection.CheckerMap;
import specialized_checkers.collection.CheckerSet;
import specialized_checkers.collection.CheckerTree;
import specialized_checkers.file.CheckerFile;
import specialized_checkers.file.CheckerJson;
import specialized_checkers.file.CheckerURI;
import specialized_checkers.math.CheckerArray;
import specialized_checkers.math.CheckerMatrix;
import specialized_checkers.math.numbers.bigTypes.CheckerBigDecimal;
import specialized_checkers.math.numbers.bigTypes.CheckerBigInteger;
import specialized_checkers.math.numbers.decimalTypes.CheckerDouble;
import specialized_checkers.math.numbers.decimalTypes.CheckerFloat;
import specialized_checkers.math.numbers.integerTypes.CheckerInteger;
import specialized_checkers.math.numbers.integerTypes.CheckerLong;
import util.collection.Graph;
import util.collection.Tree;

public class Checker extends AbstractChecker<Object, Checker> {

    private static final String INIT_CHECKER = "checker";

    public Checker(Object object, String name) {
        super(object, name);
    }

    @Override
    protected Checker self() {
        return this;
    }

    public static Checker check(Object object, String name) {
        return new Checker(object, name);
    }

    public static Checker check(Object object) {
        return new Checker(object, "Object");
    }

    public Checker isNull() {
        return is(object -> object == null, sendMessage(INIT_CHECKER, "is_null"));
    }

    public Checker isNonNull() {
        return is(object -> object != null, sendMessage(INIT_CHECKER, "is_not_null"));
    }

    public Checker isEqual(Object other){
        return is(object -> object.equals(other), sendMessage(INIT_CHECKER, "is_equal"));
    }

    public <T> Checker isInstance(Class<T> clazz) {
        return is(object -> clazz.isInstance(object), sendMessage(INIT_CHECKER, "is_instance", clazz.getSimpleName()));
    }

    @SuppressWarnings({ "unchecked" })
    private static <T> T transformOfNull(Object obj, Class<T> clazz) {
        return clazz.isInstance(obj) ? (T) obj : null;
    }

    public Checker isCollection() {
        isInstance(Collection.class);
        return this;
    }

    public <N,E extends Number> CheckerGraph<N,E> isGraph(Collection<Graph.Edge<N,E>> edges, boolean directed) {
        Graph<N,E> graph = new Graph<N,E>(edges, directed);
        return new CheckerGraph<N,E>(graph, name);
    }

    public <N,E extends Number> CheckerGraph<N,E> isGraph(Collection<Graph.Edge<N,E>> edges) {
        Graph<N,E> graph = new Graph<N,E>(edges);
        return new CheckerGraph<N,E>(graph, name);
    }

    public <N,E extends Number> CheckerGraph<N,E> isGraph(Collection<N> nodes, Collection<Graph.Edge<N,E>> edges) {
        Graph<N,E> graph = new Graph<N,E>(nodes, edges);
        return new CheckerGraph<N,E>(graph, name);
    }

    public <N,E extends Number> CheckerGraph<N,E> isGraph(Collection<N> nodes, Collection<Graph.Edge<N,E>> edges, boolean directed) {
        Graph<N,E> graph = new Graph<N,E>(nodes, edges, directed);
        return new CheckerGraph<N,E>(graph, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerList<?> isList() {
        isInstance(List.class);
        return new CheckerList((List<?>) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> CheckerList<T> isList(Class<T> clazz) {
        isInstance(List.class);
        Predicate<Object> conditionClazz = object -> ((List<?>) this.object).stream()
                .allMatch(elem -> clazz.isInstance(elem));
        is(conditionClazz, sendMessage(INIT_CHECKER, "is_list.clazz", clazz.getSimpleName()));
        return new CheckerList((List<T>) this.object, name);
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerMap<?, ?> isMap() {
        isInstance(Map.class);
        return new CheckerMap((Map<?, ?>) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <K, V> CheckerMap<K, V> isMap(Class<K> clazzKey, Class<V> clazzValue) {
        isInstance(Map.class);
        Predicate<Object> conditionClazz = object -> ((Map<?, ?>) object).entrySet().stream()
                .allMatch(entry -> clazzKey.isInstance(entry.getKey()) &&
                        clazzValue.isInstance(entry.getValue()));
        is(conditionClazz,
                sendMessage(INIT_CHECKER, "is_map.clazz", clazzKey.getSimpleName(), clazzValue.getSimpleName()));
        return new CheckerMap((Map<K, V>) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerSet<?> isSet() {
        isInstance(Set.class);
        return new CheckerSet((Set<?>) this.object, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> CheckerSet<T> isSet(Class<T> clazz) {
        isInstance(Set.class);
        Predicate<Object> conditionClazz = object -> ((Set<?>) object).stream()
                .allMatch(elem -> clazz.isInstance(elem));
        is(conditionClazz, sendMessage(INIT_CHECKER, "is_set.clazz", clazz.getSimpleName()));
        return new CheckerSet((Set<T>) this.object, name);
    }

    public <T> CheckerTree<T> isTree(T rootValue, Map<T, List<T>> childrenMap) {
        Tree<T> tree = new Tree<T>(rootValue, childrenMap);
        return new CheckerTree<T>(tree, name);
    }

    public <T> CheckerTree<T> isTree(T rootValue) {
        Tree<T> tree = new Tree<T>(rootValue);
        return new CheckerTree<T>(tree, name);
    }

    public CheckerFile isFile() {
        isInstance(File.class);
        return new CheckerFile(transformOfNull(this.object, File.class), name);
    }

    public CheckerJson isJson() throws IOException {
        isInstance(File.class);
        return new CheckerJson(transformOfNull(this.object, File.class), name);
    }

    public CheckerURI isURI() throws IOException {
        isInstance(URI.class);
        return new CheckerURI(transformOfNull(this.object, URI.class), name);
    }

    public Checker isNumber() {
        isInstance(Number.class);
        return this;
    }

    public CheckerBigInteger isBigInteger() {
        isInstance(BigInteger.class);
        return new CheckerBigInteger(transformOfNull(this.object, BigInteger.class), name);
    }

        public CheckerBigDecimal isBigDecimal() {
        isInstance(BigDecimal.class);
        return new CheckerBigDecimal(transformOfNull(this.object, BigDecimal.class), name);
    }

    public CheckerInteger isInteger() {
        isInstance(Integer.class);
        return new CheckerInteger(transformOfNull(this.object, Integer.class), name);
    }

    public CheckerLong isLong() {
        isInstance(Long.class);
        return new CheckerLong(transformOfNull(this.object, Long.class), name);
    }

    public CheckerFloat isFloat() {
        isInstance(Float.class);
        return new CheckerFloat(transformOfNull(this.object, Float.class), name);
    }

    public CheckerDouble isDouble() {
        isInstance(Double.class);
        return new CheckerDouble(transformOfNull(this.object, Double.class), name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerArray<?> isArray() {
        is(object -> object.getClass().isArray(), sendMessage(INIT_CHECKER, "is_array"));
        return new CheckerArray(((Collection<?>) this.object).toArray(), this.name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> CheckerArray<T> isArray(Class<T> clazz) {
        is(object -> object.getClass().isArray(), sendMessage(INIT_CHECKER, "is_array", clazz.getSimpleName()));
        return new CheckerArray(((Collection<T>) this.object).toArray(), this.name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CheckerMatrix<?> isMatrix() {
        is(object ->
            object.getClass().isArray() && object.getClass().getComponentType().isArray(),
            sendMessage(INIT_CHECKER, "is_matrix")
        );

        return new CheckerMatrix((Number[][]) this.object, this.name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T extends Number> CheckerMatrix<T> isMatrix(Class<T> clazz) {
        is(object ->
            object.getClass().isArray() && object.getClass().getComponentType().isArray(),
            sendMessage(INIT_CHECKER, "is_matrix", clazz.getSimpleName())
        );
        return new CheckerMatrix((T[][]) this.object, this.name);
    }

    public CheckerColor isColor() {
        isInstance(Color.class);
        return new CheckerColor(transformOfNull(this.object, Color.class), this.name);
    }

    public CheckerCurrency isCurrency() {
        isInstance(Currency.class);
        return new CheckerCurrency(transformOfNull(this.object, Currency.class), this.name);
    }

    public CheckerString isString() {
        isInstance(String.class);
        return new CheckerString(transformOfNull(this.object, String.class), this.name);
    }
}
