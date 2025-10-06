package com.luchersol.core.util;

import static com.luchersol.core.util.Message.*;

import java.lang.reflect.Field;
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
 * @param <C> the concrete checker type (must extend {@code AbstractChecker<T, C>}).
 * This ensures the fluent API returns the correct subtype
 *
 *
 * <p>Example:</p>
 * <pre>{@code
 * class MyChecker extends AbstractChecker<MyType, MyChecker> {
 *     // implementation...
 * }
 * }</pre>
 */
public abstract class AbstractChecker<T, C extends AbstractChecker<T,C>> implements InterfaceChecker<AbstractChecker<T,C>, T> {

    private static final String INIT_ABSTRACT_CHECKER = "abstract_checker";
    private static final String REGEX_POINT_PROPERTIES = "(?<!\\([^\\)]*)\\.(?![^\\(]*\\))";
    private static final String REGEX_METHOD_BY_NAME_PARAMS = "^(?<function>[a-zA-Z_][a-zA-Z0-9_]*)\\((?<nameParams>(?:[a-zA-Z_][a-zA-Z0-9_]*)(?:,\\s*[a-zA-Z_][a-zA-Z0-9_])*)?\\)$";
    private static final String REGEX_METHOD_BY_NUMBER_PARAMS = "^(?<function>[a-zA-Z_][a-zA-Z0-9_]*)\\((?<numParams>\\d+)?\\)$";
    private static final String REGEX_PARENTHESIS = "\\((.*?)\\)";

    /**
     * The object being checked.
     */
    protected T object;

    /**
     * The name or label for the checked object (for error messages).
     */
    protected String name;

    /**
     * Tracks exceptions thrown or not thrown during checks.
     */
    protected ExceptionTracker exceptionTracker;

    /**
     * If true, errors are saved in the exception tracker instead of being thrown immediately.
     */
    protected boolean saveErrors;

    /**
     * If true, further checks are stopped (e.g., if the object is null).
     */
    protected boolean stop;

    /**
     * The parent checker of the object being checked.
     */
    protected AbstractChecker<T, C> backObject;

    /**
     * Constructor initializing the checker with a name.
     * @param name Name of the object for reporting purposes
     */
    public AbstractChecker(String name) {
        this(null, name);
    }

    /**
     * Constructor initializing the checker with an object and its name.
     * @param object Object to check
     * @param name Name of the object
     */
    public AbstractChecker(T object, String name) {
        this(object, name, ExceptionTracker.empty(name));
    }

    /**
     * Constructor initializing the checker with a name and an existing exception tracker.
     * @param name Name of the object
     * @param exceptionTracker Tracker for exceptions
     */
    public AbstractChecker(String name, ExceptionTracker exceptionTracker) {
        this(null, name, exceptionTracker);
    }

    /**
     * Constructor initializing the checker with an object, its name, and an exception tracker.
     * @param object Object to check
     * @param name Name of the object
     * @param exceptionTracker Tracker for exceptions
     */
    public AbstractChecker(T object, String name, ExceptionTracker exceptionTracker) {
        this.object = object;
        this.name = name;
        this.exceptionTracker = exceptionTracker;
        this.saveErrors = false;
        this.stop = object == null;
        this.backObject = self();
    }

    /**
     * Sets the object being checked.
     *
     * @param object the object to set
     * @return this checker instance
     */
    public C setObject(T object) {
        this.object = object;
        return self();
    }

    /**
     * Sets the name or label for the checked object.
     *
     * @param name the name to set
     * @return this checker instance
     */
    public C setName(String name) {
        this.name = name;
        return self();
    }

    /**
     * Sets the exception tracker.
     *
     * @param exceptionTracker the tracker to set
     * @return this checker instance
     */
    public C setExceptionTracker(ExceptionTracker exceptionTracker) {
        this.exceptionTracker = exceptionTracker;
        return self();
    }

    /**
     * Sets whether errors should be saved instead of thrown.
     *
     * @param saveErrors true to save errors, false to throw immediately
     * @return this checker instance
     */
    public C setSaveErrors(boolean saveErrors) {
        this.saveErrors = saveErrors;
        return self();
    }

    /**
     * Sets whether further checks should stop.
     *
     * @param stop true to stop further checks, false otherwise
     * @return this checker instance
     */
    public C setStop(boolean stop) {
        this.stop = stop;
        return self();
    }

    /**
     * Sets the parent checker.
     *
     * @param backObject the parent checker to set
     * @return this checker instance
     */
    public C setBackObject(AbstractChecker<T, C> backObject) {
        this.backObject = backObject;
        return self();
    }


    /**
     * Stops further checks in the current checker.
     */
    public void stop() {
        this.stop = true;
    }

    /**
     * Returns the concrete checker instance (for fluent API).
     * @return The concrete checker instance
     */
    protected abstract C self();

    /**
     * Validates the object with a custom condition and message.
     * @param condition Condition to validate
     * @param message Message to use if the check fails
     * @return The current checker instance
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
     * Validates the object with a custom condition using a default message.
     * @param condition Condition to validate
     * @return The current checker instance
     */
    public C is(Predicate<T> condition) {
        return is(condition, sendMessage(INIT_ABSTRACT_CHECKER, "is"));
    }

    /**
     * Validates that the condition is NOT true.
     * @param condition Condition to negate
     * @param message Message to use if the check fails
     * @return The current checker instance
     */
    public C isNot(Predicate<T> condition, String message) {
        return is(condition.negate(), message);
    }

    /**
     * Validates that the condition is NOT true using a default message.
     * @param condition Condition to negate
     * @return The current checker instance
     */
    public C isNot(Predicate<T> condition) {
        return is(condition.negate(), sendMessage(INIT_ABSTRACT_CHECKER, "is_not"));
    }

    /**
     * Checks if the object is null.
     * @return The current checker instance
     */
    public C isNull() {
        return is(object -> object == null, sendMessage(INIT_ABSTRACT_CHECKER, "is_null"));
    }

    /**
     * Checks if the object is not null.
     * @return The current checker instance
     */
    public C isNonNull() {
        return is(object -> object != null, sendMessage(INIT_ABSTRACT_CHECKER, "is_not_null"));
    }

    /**
     * Checks if the object equals another object.
     * @param other Object to compare with
     * @return The current checker instance
     */
    public C isEqual(Object other){
        return is(object -> Utils.equalsContent(other, object), sendMessage(INIT_ABSTRACT_CHECKER, "is_equal"));
    }

    /**
     * Enables saving errors in the exception tracker instead of throwing immediately.
     * @return The current checker instance
     */
    public C saveErrors() {
        this.saveErrors = true;
        return self();
    }

    /**
     * Disables saving errors; exceptions will be thrown immediately.
     * @return The current checker instance
     */
    public C notSaveErrors() {
        this.saveErrors = false;
        return self();
    }

    /**
     * Checks if any errors are recorded in the exception tracker.
     * @return true if errors exist
     */
    public Boolean hasErrors() {
        return this.exceptionTracker.hasErrors();
    }

    /**
     * Checks if there are no errors recorded.
     * @return true if no errors exist
     */
    public Boolean hasNotErrors() {
        return this.exceptionTracker.hasNotErrors();
    }

    /**
     * Displays exceptions that were thrown.
     */
    public void showThrownException() {
        this.exceptionTracker.showThrownException();
    }

    /**
     * Displays exceptions that were not thrown.
     */
    public void showNotThrownException() {
        this.exceptionTracker.showNotThrownException();
    }

    /**
     * Displays all tracked exceptions.
     */
    public void show() {
        this.exceptionTracker.show();
    }

    /**
     * Caution: Be careful with class types when using this method.
     * For example, if a method expects a Map but the object is internally a HashMap, it may fail.
     * This method checks a nested property using reflection and supports method calls with arguments.
     *
     * @param propertyPath Path to the property or method
     * @param args Arguments to pass to the method
     * @return Checker for the property or method, or null if the property/method is not found
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
     * Ensures that the methods are invoked using the desired classes.
     * If not specified, the class of the object obtained via getClass() will be used.
     * This method is useful for checking properties or invoking methods with explicit argument types.
     *
     * @param propertyPath Path to the property or method to check
     * @param args List of argument-value and class pairs
     * @return Checker for the property or method, or null if not found
     * @throws Exception If a reflection-related error occurs
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
     * Checks a property or method using a map of named arguments.
     * @param propertyPath Path to property or method
     * @param args Map of argument names to values
     * @return Checker for the property, or null if not found
     * @throws Exception On reflection failure
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
     * Retrieves a nested property or method result using reflection.
     * @param object Object to inspect
     * @param properties Queue of nested property names or method calls
     * @param args Arguments for methods
     * @return Resulting object
     * @throws Exception In case of failure to obtain field or method
     */
    public static Object getProperty(Object object, Queue<String> properties, Object args) throws Exception {
        try {
            return tryGetField(object, properties, args);
        } catch (NoSuchFieldException e) {
            return tryGetMethod(object, properties, args);
        }
    }

    /**
     * Tries to retrieve a field using different argument types.
     * @param object Object to inspect
     * @param properties Property path queue
     * @param args Arguments
     * @return Retrieved object
     * @throws Exception In case of failure to obtain field
     */
    @SuppressWarnings("unchecked")
    private static Object tryGetField(Object object, Queue<String> properties, Object args) throws Exception {
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
     * Tries to retrieve a method using different argument types.
     * @param object Object to inspect
     * @param properties Property path queue
     * @param args Arguments
     * @return Retrieved object
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
     * Retrieves a field value from an object recursively.
     * @param object Object to inspect
     * @param properties Property path queue
     * @param params Arguments
     * @return Field value
     * @throws Exception In case of failure to obtain field
     */
    private static Object getField(Object object, Queue<String> properties, Object params) throws Exception {
        String propertyField = properties.poll();
        Object currentObject = object;

        Field field = currentObject.getClass().getDeclaredField(propertyField);
        field.setAccessible(true);
        currentObject = field.get(currentObject);
        return currentObject == null ? null
                : properties.isEmpty() ? currentObject : getProperty(currentObject, properties, params);

    }

    /**
     * Retrieves a method result from an object recursively.
     * @param object Object to inspect
     * @param propertyPath Property path queue
     * @param params Arguments
     * @return Method result
     * @throws Exception In case of failure to obtain method
     */
    public static Object getMethod(Object object, Queue<String> propertyPath, Object params) throws Exception {

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
     * Handles invoking a method with name-based or number-based parameters.
     * @param object Object to inspect
     * @param propertyPath Remaining property path queue
     * @param params Arguments
     * @param matcher Regex matcher
     * @param isNameParams True if using named parameters
     * @return Result of method invocation
     * @throws Exception In case of failure to handle method
     */
    @SuppressWarnings("unchecked")
    private static Object handleMethod(Object object, Queue<String> propertyPath, Object params, Matcher matcher,
            boolean isNameParams) throws Exception {

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
     * Ends the current checker and returns the previous checker in the chain.
     * Merges the current exception tracker into the previous checker's tracker.
     * Useful for nested property checks.
     *
     * @return The previous checker in the chain
     */
    public Checker end(){
        ExceptionTracker exceptionTracker = this.exceptionTracker;
        this.backObject.exceptionTracker.merge(exceptionTracker);
        return this.backObject.toChecker();
    }

    /**
     *
     * @return This instance transform to Checker
     */
    @SuppressWarnings("unchecked")
    public Checker toChecker() {
        Checker checker = new Checker(this.object, this.name);
        checker.backObject = (AbstractChecker<Object, Checker>) this.backObject;
        checker.exceptionTracker = this.exceptionTracker;
        checker.saveErrors = this.saveErrors;
        checker.stop = this.stop;
        return checker;
    }

    /**
     * Returns the object being checked by this checker.
     *
     * @return The object under validation
     */
    public T getObject() {
        return this.object;
    }

}
