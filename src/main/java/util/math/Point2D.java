package util.math;

/**
 * Point2D represents a point in 2D space with x and y coordinates.
 */
public class Point2D {

    /**
     * The x-coordinate of the point.
     */
    protected double x;

    /**
     * The y-coordinate of the point.
     */
    protected double y;


    /**
     * Constructs a Point2D from an array of two doubles.
     *
     * @param point an array where point[0] is x and point[1] is y
     */
    public Point2D(double[] point) {
        this.x = point[0];
        this.y = point[1];
    }


    /**
     * Constructs a Point2D with the specified x and y coordinates.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Returns the x-coordinate of the point.
     *
     * @return the x-coordinate
     */
    public double getX() {
        return this.x;
    }


    /**
     * Returns the y-coordinate of the point.
     *
     * @return the y-coordinate
     */
    public double getY() {
        return this.y;
    }


    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between the two points
     */
    public double distance(Point2D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }


    /**
     * Returns a new Point2D that is the result of subtracting another point from this point.
     *
     * @param other the point to subtract
     * @return the resulting Point2D
     */
    public Point2D subtract(Point2D other) {
        return new Point2D(this.x - other.x, this.y - other.y);
    }


    /**
     * Returns a new Point2D that is the result of scaling this point by a factor.
     *
     * @param factor the scale factor
     * @return the resulting Point2D
     */
    public Point2D scale(double factor) {
        return new Point2D(this.x * factor, this.y * factor);
    }


    /**
     * Checks if this point is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the object is a Point2D with the same coordinates, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point2D)) return false;
        Point2D other = (Point2D) obj;
        return Double.compare(this.x, other.x) == 0 &&
               Double.compare(this.y, other.y) == 0;
    }


    /**
     * Returns the magnitude (length) of the vector from the origin to this point.
     *
     * @return the magnitude of the point
     */
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }


    /**
     * Returns a normalized (unit length) version of this point as a vector.
     *
     * @return the normalized Point2D, or (0,0) if the point is at the origin
     */
    public Point2D normalize() {
        double mag = magnitude();
        if (this.x == 0 && this.y == 0) return new Point2D(0, 0);
        return new Point2D(x / mag, y / mag);
    }

}
