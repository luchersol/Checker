package specialized_checkers.math;

import util.AbstractChecker;
import util.math.Polygon2D;

/**
 * Checker for {@link util.math.Polygon2D} instances, providing fluent validation methods for 2D polygon objects.
 * <p>
 * This class allows you to validate and assert properties of {@code Polygon2D} objects in a fluent and readable way.
 * </p>
 */
public class CheckerPolygon2D extends AbstractChecker<Polygon2D, CheckerPolygon2D> {

    private static final String INIT_POLYGON_2D = "math.polygon2d";
    private static final String DEFAULT_NAME = "Polygon2D";

    protected CheckerPolygon2D(Polygon2D polygon2d, String name) {
        super(polygon2d, name);
    }


    /**
     * Creates a new {@code CheckerPolygon2D} for the given {@link Polygon2D} instance with a custom name.
     *
     * @param polygon2d the {@code Polygon2D} instance to be checked
     * @param name      the name to identify this checker instance (useful for error messages)
     * @return a new {@code CheckerPolygon2D} for the provided {@code Polygon2D}
     */
    public static CheckerPolygon2D check(Polygon2D polygon2d, String name) {
        return new CheckerPolygon2D(polygon2d, name);
    }


    /**
     * Creates a new {@code CheckerPolygon2D} for the given {@link Polygon2D} instance with a default name.
     *
     * @param polygon2d the {@code Polygon2D} instance to be checked
     * @return a new {@code CheckerPolygon2D} for the provided {@code Polygon2D}
     */
    public static CheckerPolygon2D check(Polygon2D polygon2d) {
        return check(polygon2d, DEFAULT_NAME);
    }


    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerPolygon2D} instance
     */
    @Override
    protected CheckerPolygon2D self() {
        return this;
    }


}
