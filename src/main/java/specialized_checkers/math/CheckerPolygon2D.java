package specialized_checkers.math;

import util.AbstractChecker;
import util.math.Polygon2D;

public class CheckerPolygon2D extends AbstractChecker<Polygon2D, CheckerPolygon2D> {

    private static final String INIT_POLYGON_2D = "math.Polygon2D_2d";
    private static final String DEFAULT_NAME = "Polygon2D";

    protected CheckerPolygon2D(Polygon2D Polygon2D, String name) {
        super(Polygon2D, name);
    }

    public static CheckerPolygon2D check(Polygon2D Polygon2D, String name) {
        return new CheckerPolygon2D(Polygon2D, DEFAULT_NAME);
    }

    public static CheckerPolygon2D check(Polygon2D Polygon2D) {
        return check(Polygon2D, DEFAULT_NAME);
    }

    @Override
    protected CheckerPolygon2D self() {
        return this;
    }


}
