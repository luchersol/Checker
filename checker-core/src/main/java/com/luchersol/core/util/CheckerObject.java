package com.luchersol.core.util;

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
public class CheckerObject<T> extends AbstractChecker<T, CheckerObject<T>> {

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
    protected CheckerObject(T object, String name) {
        super(object, name);
    }

    /**
     * Returns this Checker instance (for fluent API).
     *
     * @return this Checker
     */
    @Override
    protected CheckerObject<T> self() {
        return this;
    }

    /**
     * Creates a Checker for the given object and name.
     *
     * @param object the object to check
     * @param name   the name or label for the object
     * @return a new Checker instance
     */
    public static <T> CheckerObject<T> check(T object, String name) {
        return new CheckerObject<T>(object, name);
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

}
