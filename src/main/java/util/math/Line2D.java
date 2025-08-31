package util.math;

/**
 * Line2D represents a line segment in 2D space defined by two endpoints.
 */
public class Line2D {

    /**
     * The first endpoint of the line segment.
     */
    private Point2D p1;

    /**
     * The second endpoint of the line segment.
     */
    private Point2D p2;

    /**
     * Constructs a Line2D from two Point2D.
     *
     * @param p1 the first point
     * @param p2 the second point
     */
    public Line2D(Point2D p1, Point2D p2){
        this.p1 = p1;
        this.p2 = p2;
    }
}

