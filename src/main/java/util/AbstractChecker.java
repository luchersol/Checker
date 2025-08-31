package util;

import static util.Message.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract base class for implementing checkers that validate objects of type T.
 * Provides a fluent API for chaining validation methods and tracking exceptions.
 *
 * @param <T> the type of object to check
 * @param <C> the type of the concrete checker (for fluent API)
 */
public abstract class AbstractChecker<T, C extends AbstractChecker<T,C>> implements InterfaceChecker<AbstractChecker<T,C>, T> {

    protected T object;

     /**
      * The object being checked.
      */
    protected String name;

     /**
      * The name or label for the checked object (for error messages).
      */
    protected ExceptionTracker exceptionTracker;

     /**
      * Tracks exceptions thrown or not thrown during checks.
      */
    protected boolean saveErrors;

     /**
      * If true, errors are saved in the exception tracker instead of being thrown immediately.
      */
    protected boolean stop;

     /**
      * If true, further checks are stopped (e.g., if the object is null).
      */
    protected AbstractChecker<T, C> backObject;

    private static final String INIT_ABSTRACT_CHECKER = "abstract_checker";

     /**
      * Message key for this checker type.
      */
    private static final String REGEX_POINT_PROPERTIES = "(?<!\\([^\\)]*)\\.(?![^\\(]*\\))";
    private static final String REGEX_METHOD_BY_NAME_PARAMS = "^(?<function>[a-zA-Z_][a-zA-Z0-9_]*)\\((?<nameParams>(?:[a-zA-Z_][a-zA-Z0-9_]*)(?:,\\s*[a-zA-Z_][a-zA-Z0-9_])*)?\\)$";
    private static final String REGEX_METHOD_BY_NUMBER_PARAMS = "^(?<function>[a-zA-Z_][a-zA-Z0-9_]*)\\((?<numParams>\\d+)?\\)$";
    private static final String REGEX_PARENTHESIS = "\\((.*?)\\)";

    public AbstractChecker(String name) {
        this.object = null;
        this.name = name;
        this.exceptionTracker = ExceptionTracker.empty(name);
        this.saveErrors = false;
        this.stop = object == null;
    }

    public AbstractChecker(T object, String name) {
        this.object = object;
        this.name = name;
        this.exceptionTracker = ExceptionTracker.empty(name);
        this.saveErrors = false;
        this.stop = object == null;
    }

    public AbstractChecker(String name, ExceptionTracker exceptionTracker) {
        this.name = name;
        this.exceptionTracker = exceptionTracker;
        this.saveErrors = false;
        this.stop = object == null;
    }

    public AbstractChecker(T object, String name, ExceptionTracker exceptionTracker) {
        this.object = object;
        this.name = name;
        this.exceptionTracker = exceptionTracker;
        this.saveErrors = false;
        this.stop = object == null;
    }

    public void stop() {
        this.stop = true;
    }

    protected abstract C self();

    /**
     * @param condition
     * @param message
     * @return C
     */
    public C is(Predicate<T> condition, String message) {
        CheckerException exception = new CheckerException(message);
        if (stop) {
            this.exceptionTracker.addNotCheckedException(exception);
            return self();
        }

        if (!condition.test(this.object)) {
            if (saveErrors) {
                this.exceptionTracker.addThrownException(exception);
            } else {
                throw exception;
            }
        } else {
            if (saveErrors) {
                this.exceptionTracker.addNotThrownException(exception);
            }
        }

        return self();
    }

    /**
     * @param condition
     * @return C
     */
    public C is(Predicate<T> condition) {
        return is(condition, sendMessage(INIT_ABSTRACT_CHECKER, "is"));
    }

    /**
     * @param condition
     * @param message
     * @return C
     */
    public C isNot(Predicate<T> condition, String message) {
        return is(condition.negate(), message);
    }

    /**
     * @param condition
     * @return C
     */
    public C isNot(Predicate<T> condition) {
        return is(condition.negate(), sendMessage(INIT_ABSTRACT_CHECKER, "is_not"));
    }

        /**
     * @return C
     */
    public C isNull() {
        return is(object -> object == null, sendMessage(INIT_ABSTRACT_CHECKER, "is_null"));
    }

    /**
     * @return C
     */
    public C isNonNull() {
        return is(object -> object != null, sendMessage(INIT_ABSTRACT_CHECKER, "is_not_null"));
    }

    /**
     * @param other
     * @return C
     */
    public C isEqual(Object other){
        return is(object -> Utils.equalsContent(other, object), sendMessage(INIT_ABSTRACT_CHECKER, "is_equal"));
    }

    /**
     * @return C
     */
    public C saveErrors() {
        this.saveErrors = true;
        return self();
    }

    /**
     * @return C
     */
    public C notSaveErrors() {
        this.saveErrors = false;
        return self();
    }

    /**
     * @return Boolean
     */
    public Boolean hasErrors() {
        return this.exceptionTracker.hasErrors();
    }

    /**
     * @return Boolean
     */
    public Boolean hasNotErrors() {
        return this.exceptionTracker.hasNotErrors();
    }

    public void showThrownException() {
        this.exceptionTracker.showThrownException();
    }

    public void showNotThrownException() {
        this.exceptionTracker.showNotThrownException();
    }

    public void show() {
        this.exceptionTracker.show();
    }

    /**
     * Hay que tener cuidado con las clases si se usa este método.
     * Ej: Si el método usa Map y el objeto es un HashMap internamente, fallará
     *
     *
     * @param propertyPath
     * @param args
     * @return
     */
    public Checker checkProperty(String propertyPath, Object... args) {
        int numArgs = 0;
        Pattern pattern = Pattern.compile(REGEX_PARENTHESIS);
        Matcher matcher = pattern.matcher(propertyPath);

        while (matcher.find()){
            String str = matcher.group(1);
            if(str != null && !str.isBlank()){
                numArgs += Integer.valueOf(str);
            }
        }

        if (numArgs != args.length)
            return null;

        try {
            String[] split = propertyPath.split(REGEX_POINT_PROPERTIES);
            Queue<String> properties = new LinkedList<>(Arrays.asList(split));
            Queue<Object> argsQueue = new LinkedList<>(Arrays.asList(args));
            Object obj = getProperty(this.object, properties, argsQueue);
            Checker checker = new Checker(obj, name + "." + propertyPath);
            checker.saveErrors = this.saveErrors;
            checker.stop = this.stop;
            checker.backObject = (Checker) self();
            return checker;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Para asegurarse de que se utilizando los métodos con las clases queridas, aplicar este método.
     * En los otros casos, se aplicará la clase del objeto que se obtenga con el método getClass()
     *
     * @param propertyPath
     * @param args
     * @return
     * @throws Exception
     */
    public Checker checkProperty(String propertyPath, List<Entry<Object, Class<?>>> args) throws Exception {
        int numArgs = 0;
        Pattern pattern = Pattern.compile(REGEX_PARENTHESIS);
        Matcher matcher = pattern.matcher(propertyPath);

        while (matcher.find()){
            String str = matcher.group(1);
            if(!str.isBlank())
                numArgs += Integer.valueOf(str);
        }

        if (numArgs != args.size())
            return null;

        try {
            String[] split = propertyPath.split(REGEX_POINT_PROPERTIES);
            Queue<String> properties = new LinkedList<>(Arrays.asList(split));
            Object obj = getProperty(this.object, properties, args);
            Checker checker = new Checker(obj, name + "." + propertyPath);
            checker.backObject = (Checker) self();
            return checker;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param propertyPath
     * @param args
     * @return Checker
     * @throws Exception
     */
    public Checker checkProperty(String propertyPath, Map<String, Object> args) throws Exception {
        int numArgs = 0;
        Pattern pattern = Pattern.compile(REGEX_PARENTHESIS);
        Matcher matcher = pattern.matcher(propertyPath);

        while (matcher.find()){
            String group = matcher.group(1);
            if(group != null && !group.isBlank()){
                numArgs += group.split(",").length;
            }
        }
        if (numArgs != args.size())
            return null;

        try {
            String[] split = propertyPath.split(REGEX_POINT_PROPERTIES);
            Queue<String> properties = new LinkedList<>(Arrays.asList(split));
            Object obj = getProperty(this.object, properties, args);
            Checker checker = new Checker(obj, name + "." + propertyPath);
            checker.backObject = (Checker) self();
            return checker;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param object
     * @param properties
     * @param args
     * @return Object
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object getProperty(Object object, Queue<String> properties, Object args)
            throws SecurityException, IllegalArgumentException, IllegalAccessException {
        try {
            return tryGetField(object, properties, args);
        } catch (NoSuchFieldException e) {
            return tryGetMethod(object, properties, args);
        }
    }

    /**
     * @param object
     * @param properties
     * @param args
     * @return Object
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    private static Object tryGetField(Object object, Queue<String> properties, Object args)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Queue<String> propCopy = new LinkedList<>(properties);

        if (args instanceof Queue) {
            return getField(object, propCopy, (Queue<Object>) args);
        } else if (args instanceof List) {
            return getField(object, propCopy, (List<Entry<Object, Class<?>>>) args);
        } else if (args instanceof Map) {
            return getField(object, propCopy, (Map<String, Object>) args);
        } else {
            throw new IllegalArgumentException("Unsupported argument type");
        }
    }

    /**
     * @param object
     * @param properties
     * @param args
     * @return Object
     */
    @SuppressWarnings("unchecked")
    private static Object tryGetMethod(Object object, Queue<String> properties, Object args) {
        Queue<String> propCopy = new LinkedList<>(properties);

        try {
            if (args instanceof Queue) {
                return getMethod(object, propCopy, (Queue<Object>) args);
            } else if (args instanceof List) {
                return getMethod(object, propCopy, (List<Entry<Object, Class<?>>>) args);
            } else if (args instanceof Map) {
                return getMethod(object, propCopy, (Map<String, Object>) args);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param object
     * @param properties
     * @param params
     * @return Object
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static Object getField(Object object, Queue<String> properties, Object params)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String propertyField = properties.poll();
        Object currentObject = object;

        Field field = currentObject.getClass().getDeclaredField(propertyField);
        field.setAccessible(true);
        currentObject = field.get(currentObject);

        return currentObject == null ? null
                : properties.isEmpty() ? currentObject : getProperty(currentObject, properties, params);

    }

    /**
     * @param object
     * @param propertyPath
     * @param params
     * @return Object
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Object getMethod(Object object, Queue<String> propertyPath, Object params)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        String property = propertyPath.poll();
        Pattern namePattern = Pattern.compile(REGEX_METHOD_BY_NAME_PARAMS);
        Matcher nameMatcher = namePattern.matcher(property);

        if (nameMatcher.find()) {
            return handleMethod(object, propertyPath, params, nameMatcher, true);
        }

        Pattern numberPattern = Pattern.compile(REGEX_METHOD_BY_NUMBER_PARAMS);
        Matcher numberMatcher = numberPattern.matcher(property);

        if (numberMatcher.find()) {
            return handleMethod(object, propertyPath, params, numberMatcher, false);
        }

        return null;
    }

    /**
     * @param object
     * @param propertyPath
     * @param params
     * @param matcher
     * @param isNameParams
     * @return Object
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("unchecked")
    private static Object handleMethod(Object object, Queue<String> propertyPath, Object params, Matcher matcher,
            boolean isNameParams)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        String function = matcher.group("function");
        Object[] args;
        Class<?>[] parameterTypes;

        if (isNameParams) {
            String groupNumParams = matcher.group("nameParams");
            String[] nameParams = groupNumParams == null ? new String[]{} : groupNumParams.split(",");
            args = new Object[nameParams.length];
            parameterTypes = new Class[nameParams.length];

            if(nameParams.length > 0){
                if (params instanceof Map) {
                    Map<String, Object> paramMap = (Map<String, Object>) params;
                    for (int i = 0; i < nameParams.length; i++) {
                        args[i] = paramMap.get(nameParams[i]);
                        parameterTypes[i] = args[i] == null ? Object.class : args[i].getClass();
                    }
                } else {
                    throw new IllegalArgumentException("Unsupported parameter type for name-based method");
                }
            }

        } else {
            String groupNumParams = matcher.group("numParams");
            Integer numParams = groupNumParams == null ? 0 : Integer.valueOf(groupNumParams);
            args = new Object[numParams];
            parameterTypes = new Class[numParams];

            if(numParams > 0){
                if (params instanceof Queue) {
                    Queue<?> paramQueue = (Queue<?>) params;
                    for (int i = 0; i < numParams; i++) {
                        args[i] = paramQueue.poll();
                        parameterTypes[i] = args[i] == null ? Object.class : args[i].getClass();
                    }
                } else if (params instanceof List) {
                    List<Entry<Object, Class<?>>> paramList = (List<Entry<Object, Class<?>>>) params;
                    for (int i = 0; i < numParams; i++) {
                        Entry<Object, Class<?>> entry = paramList.get(i);
                        args[i] = entry.getKey();
                        parameterTypes[i] = entry.getValue();
                    }
                } else {
                    throw new IllegalArgumentException("Unsupported parameter type for number-based method");
                }
            }

        }

        Method method = object.getClass().getDeclaredMethod(function, parameterTypes);
        method.setAccessible(true);
        Object result = method.invoke(object, args);

        return result == null ? null : propertyPath.isEmpty() ? result : getProperty(result, propertyPath, params);
    }

    /**
     * @return Checker
     */
    public Checker end(){
        ExceptionTracker exceptionTracker = this.exceptionTracker;
        this.backObject.exceptionTracker.merge(exceptionTracker);
        return (Checker) this.backObject;
    }

    /**
     * @return T
     */
    public T getObject() {
        return this.object;
    }

}
