package com.luchersol.core.specialized_checkers;

import static com.luchersol.core.util.MessageService.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.function.Predicate;

import com.luchersol.core.util.AbstractChecker;
import com.luchersol.core.util.Utils;

/**
 * A specialized checker for {@link Enum} instances, providing a fluent API
 * to assert enum properties such as equality, name, ordinal, inclusion,
 * ordering and reflective attributes.
 *
 * <p>Typical usage:</p>
 * <pre>{@code
 * CheckerEnum.check(status)
 *     .is(Status.ACTIVE)
 *     .isNot(Status.CANCELLED)
 *     .hasName("ACTIVE")
 *     .ordinalGreaterThan(0)
 *     .isIn(Status.ACTIVE, Status.PENDING);
 * }</pre>
 *
 * <p>Custom rule example:</p>
 * <pre>{@code
 * CheckerEnum.check(status)
 *     .matches(s -> s.ordinal() >= Status.ACTIVE.ordinal(),
 *              "status must be ACTIVE or higher");
 * }</pre>
 *
 * <p>Reflective property example:</p>
 * <pre>{@code
 * CheckerEnum.check(status)
 *     .hasProperty("enabled", true);
 * }</pre>
 *
 * <p>This class supports chaining multiple assertions in a fluent style and
 * integrates with {@link AbstractChecker} for generalized validation handling.</p>
 *
 * @param <T> Enum type being validated
 *
 * @see AbstractChecker
 */
public class CheckerEnum<T extends Enum<T>> extends AbstractChecker<T, CheckerEnum<T>> {

    private static final String INIT_ENUM = "enum";
    private static final String DEFAULT_NAME = "Enum";

    /**
     * Constructs a new {@code CheckerEnum} with the specified enum and name.
     *
     * @param enumerate the enum instance associated with this checker
     * @param name the logical name of the enum
     */
    protected CheckerEnum(T enumerate, String name) {
        super(enumerate, name);
    }

    /**
     * Creates a new CheckerEnum instance for the given enum and name.
     *
     * @param enumerate the enum value to be checked
     * @param name the logical name used in error messages
     * @param <T> Enum type
     * @return a CheckerEnum instance for further validations
     */
    public static <T extends Enum<T>> CheckerEnum<T> check(T enumerate, String name) {
        return new CheckerEnum<>(enumerate, name);
    }

    /**
     * Creates a new CheckerEnum instance with a default name.
     *
     * @param enumerate the enum value to be checked
     * @param <T> Enum type
     * @return a CheckerEnum instance for further validations
     */
    public static <T extends Enum<T>> CheckerEnum<T> check(T enumerate) {
        return check(enumerate, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API usage).
     *
     * @return this CheckerEnum instance
     */
    @Override
    protected CheckerEnum<T> self() {
        return this;
    }

    /**
     * Checks if the enum equals the expected value.
     *
     * <p>Uses identity comparison (==).</p>
     *
     * @param expected expected enum value
     * @return this CheckerEnum instance for chaining
     */
    public CheckerEnum<T> isEqual(T expected) {
        return is(enumerate -> enumerate == expected, sendMessage(INIT_ENUM, "is_equal", expected));
    }

    /**
     * Checks if the enum is different from the given value.
     *
     * @param value enum value that must not match
     * @return this CheckerEnum instance for chaining
     */
    public CheckerEnum<T> isNotEqual(T value) {
        return is(enumerate -> enumerate != value, sendMessage(INIT_ENUM, "is_not_equal", value));
    }

    /**
     * Checks if the enum belongs to the specified values.
     *
     * @param values allowed enum values
     * @return this CheckerEnum instance for chaining
     */
    @SafeVarargs
    public final CheckerEnum<T> isIn(T... values) {
        return is(
            enumerate -> {
                if (values == null || values.length == 0) return false;
                for (T value : values) if(value == enumerate) return true;
                return false;
            },
            sendMessage(INIT_ENUM, "is_in.array", Arrays.toString(values))
        );
    }


    /**
     * Checks inclusion against a collection.
     *
     * @param values allowed enum values
     * @return this CheckerEnum instance for chaining
     */
    public CheckerEnum<T> isIn(Collection<T> values) {
        return is(values::contains, sendMessage(INIT_ENUM, "is_in.collection", values));
    }

    /**
     * Checks inclusion against a collection.
     *
     * @param values allowed enum values
     * @return this CheckerEnum instance for chaining
     */
    public CheckerEnum<T> isIn(EnumSet<T> values) {
        return is(values::contains, sendMessage(INIT_ENUM, "is_in.enumset", values));
    }

    /**
     * Checks if the enum does NOT belong to the specified values.
     *
     * @param values disallowed enum values
     * @return this CheckerEnum instance for chaining
     */
    @SafeVarargs
    public final CheckerEnum<T> isNotIn(T... values) {
        return is(
            enumerate -> {
                if (values == null || values.length == 0) return true;
                for (T value : values) if(value != enumerate) return false;
                return false;
            },
            sendMessage(INIT_ENUM, "is_not_in.array", Arrays.toString(values))
        );
    }

    /**
     * Checks if the enum does NOT belong to the specified values.
     *
     * @param values disallowed enum values
     * @return this CheckerEnum instance for chaining
     */
    public final CheckerEnum<T> isNotIn(Collection<T> values) {
        return is(Predicate.not(values::contains), sendMessage(INIT_ENUM, "is_not_in.collection", values));
    }

    /**
     * Checks if the enum does NOT belong to the specified values.
     *
     * @param values disallowed enum values
     * @return this CheckerEnum instance for chaining
     */
    public final CheckerEnum<T> isNotIn(EnumSet<T> values) {
        return is(Predicate.not(values::contains), sendMessage(INIT_ENUM, "is_not_in.enumset", values));
    }


    /**
     * Checks if enum name matches the expected name.
     *
     * @param name expected enum name (case-insensitive)
     * @return this CheckerEnum instance for chaining
     */
    public CheckerEnum<T> hasName(String name) {
        return is(
            enumerate -> enumerate.name().equalsIgnoreCase(name),
            sendMessage(INIT_ENUM, "has_name", name)
        );
    }

    /**
     * Checks if enum ordinal matches the expected value.
     *
     * @param ordinal expected ordinal
     * @return this CheckerEnum instance for chaining
     */
    public CheckerEnum<T> hasOrdinal(int ordinal) {
        return is(
            enumerate -> enumerate.ordinal() == ordinal,
            sendMessage(INIT_ENUM, "has_ordinal", ordinal)
        );
    }

    /**
     * Checks if ordinal is greater than specified value.
     *
     * @param ordinal lower bound
     * @return this CheckerEnum instance for chaining
     */
    public CheckerEnum<T> ordinalGreaterThan(int ordinal) {
        return is(
            enumerate -> enumerate.ordinal() > ordinal,
            sendMessage(INIT_ENUM, "ordinal_greater_than", ordinal)
        );
    }

    /**
     * Checks if ordinal is less than specified value.
     *
     * @param ordinal upper bound
     * @return this CheckerEnum instance for chaining
     */
    public CheckerEnum<T> ordinalLessThan(int ordinal) {
        return is(
            enumerate -> enumerate.ordinal() < ordinal,
            sendMessage(INIT_ENUM, "ordinal_less_than", ordinal)
        );
    }

    /**
     * Checks if enum appears after another enum.
     *
     * @param other reference enum
     * @return this CheckerEnum instance for chaining
     */
    public CheckerEnum<T> isAfter(T other) {
        return is(
            enumerate -> enumerate.ordinal() > other.ordinal(),
            sendMessage(INIT_ENUM, "is_after", other)
        );
    }

    /**
     * Checks if enum appears before another enum.
     *
     * @param other reference enum
     * @return this CheckerEnum instance for chaining
     */
    public CheckerEnum<T> isBefore(T other) {
        return is(
            enumerate -> enumerate.ordinal() < other.ordinal(),
            sendMessage(INIT_ENUM, "is_before", other)
        );
    }

    /**
     * Checks enum internal property using reflection.
     *
     * @param fieldName enum field name
     * @param expected expected value
     * @return this CheckerEnum instance for chaining
     */
    public CheckerEnum<T> hasProperty(String fieldName, Object expected) {
        return is(
            enumerate -> {
                try {
                    Field field = enumerate.getDeclaringClass()
                                          .getDeclaredField(fieldName);
                    field.setAccessible(true);
                    Object value = field.get(enumerate);
                    return Utils.equalsContent(value, expected);
                } catch (Exception e) {
                    return false;
                }
            },
            sendMessage(INIT_ENUM, "has_property", fieldName, expected)
        );
    }

    /**
     * Checks if enums belong to the same declaring type.
     *
     * @param other enum to compare
     * @return this CheckerEnum instance for chaining
     */
    public CheckerEnum<T> isSameType(Enum<?> other) {
        return is(
            enumerate -> enumerate.getDeclaringClass() == other.getDeclaringClass(),
            sendMessage(INIT_ENUM, "is_same_type", other)
        );
    }
}
