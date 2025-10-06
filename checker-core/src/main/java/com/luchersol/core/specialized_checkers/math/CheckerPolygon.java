package com.luchersol.core.specialized_checkers.math;

import java.awt.Polygon;

import com.luchersol.core.util.AbstractChecker;

/**
 * A specialized checker for {@link java.awt.Polygon} instances, providing fluent API methods
 * to assert properties of 2D polygonal shapes.
 *
 * <p>Typical usage:</p>
 * <pre>{@code
 * int[] xpoints = {0, 50, 100};
 * int[] ypoints = {0, 100, 0};
 *
 * CheckerPolygon checker = CheckerPolygon.check(xpoints, ypoints, 3)
 *     // future validations can be chained here
 *     .self();
 * }</pre>
 *
 * <p>This class supports multiple factory methods to create a checker directly
 * from a {@code Polygon} instance or from arrays of coordinates, making it flexible
 * for geometric validations in a fluent and readable style.</p>
 *
 * @see java.awt.Polygon
 * @see com.luchersol.core.util.AbstractChecker
 */
public class CheckerPolygon extends AbstractChecker<Polygon, CheckerPolygon> {

    // private static final String INIT_POLYGON = "math.polygon";
    private static final String DEFAULT_NAME = "Polygon";


    /**
     * Constructs a new {@code CheckerPolygon} with the specified polygon and name.
     *
     * @param polygon the {@code Polygon} to be used by this checker
     * @param name the name identifying this checker
     */
    protected CheckerPolygon(Polygon polygon, String name) {
        super(polygon, name);
    }


    /**
     * Creates a new {@code CheckerPolygon} for the given {@link Polygon} instance with a custom name.
     *
     * @param polygon the {@code Polygon} instance to be checked
     * @param name      the name to identify this checker instance (useful for error messages)
     * @return a new {@code CheckerPolygon} for the provided {@code Polygon}
     */
    public static CheckerPolygon check(Polygon polygon, String name) {
        return new CheckerPolygon(polygon, name);
    }

    /**
     * Creates a new {@code CheckerPolygon} for the given {@link Polygon} from the specified
     * parameters with a custom name.
     *
     * @param xpoints an array of X coordinates
     * @param ypoints an array of Y coordinates
     * @param npoints the total number of points in the {@code Polygon}
     * @param name      the name to identify this checker instance (useful for error messages)
     * @return a new {@code CheckerPolygon} for the provided {@code Polygon}
     */
    public static CheckerPolygon check(int[] xpoints, int[] ypoints, int npoints, String name) {
        Polygon polygon = new Polygon(xpoints, ypoints, npoints);
        return check(polygon, name);
    }

    /**
     * Creates a new {@code CheckerPolygon} for the given {@link Polygon} instance with a default name.
     *
     * @param polygon the {@code Polygon} instance to be checked
     * @return a new {@code CheckerPolygon} for the provided {@code Polygon}
     */
    public static CheckerPolygon check(Polygon polygon) {
        return check(polygon, DEFAULT_NAME);
    }

    /**
     * Creates a new {@code CheckerPolygon} for the given {@link Polygon} from the specified parameters.
     *
     * @param xpoints an array of X coordinates
     * @param ypoints an array of Y coordinates
     * @param npoints the total number of points in the {@code Polygon}
     * @return a new {@code CheckerPolygon} for the provided {@code Polygon}
     */
    public static CheckerPolygon check(int[] xpoints, int[] ypoints, int npoints) {
        Polygon polygon = new Polygon(xpoints, ypoints, npoints);
        return check(polygon);
    }


    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerPolygon} instance
     */
    @Override
    protected CheckerPolygon self() {
        return this;
    }


}
