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

public abstract class AbstractChecker<T, C extends AbstractChecker<T,C>> implements InterfaceChecker<AbstractChecker<T,C>, T> {

    protected T object;

    protected String name;

    protected ExceptionTracker exceptionTracker;

    protected boolean saveErrors;

    protected boolean stop;

    protected AbstractChecker<T, C> backObject;

    private static final String INIT_ABSTRACT_CHECKER = "abstract_checker";

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

    public C is(Predicate<T> condition, String message) {
        RuntimeException exception = new RuntimeException(new Error(message));
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

    public C is(Predicate<T> condition) {
        return is(condition, sendMessage(INIT_ABSTRACT_CHECKER, "is"));
    }

    public C isNot(Predicate<T> condition, String message) {
        return is(condition.negate(), message);
    }

    public C isNot(Predicate<T> condition) {
        return is(condition.negate(), sendMessage(INIT_ABSTRACT_CHECKER, "is_not"));
    }

    public C saveErrors() {
        this.saveErrors = true;
        return self();
    }

    public C notSaveErrors() {
        this.saveErrors = false;
        return self();
    }

    public Boolean hasErrors() {
        return this.exceptionTracker.hasErrors();
    }

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

    public static Object getProperty(Object object, Queue<String> properties, Object args)
            throws SecurityException, IllegalArgumentException, IllegalAccessException {
        try {
            return tryGetField(object, properties, args);
        } catch (NoSuchFieldException e) {
            return tryGetMethod(object, properties, args);
        }
    }

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

    public Checker end(){
        ExceptionTracker exceptionTracker = this.exceptionTracker;
        this.backObject.exceptionTracker.merge(exceptionTracker);
        return (Checker) this.backObject;
    }

    public T getObject() {
        return this.object;
    }

}
