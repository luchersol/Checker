package util.math;


/**
 * Point3D represents a point in 3D space, extending {@link Point2D} with a z-coordinate.
 */
public class Point3D extends Point2D {


    /**
     * Constructs a Point3D with the specified x, y, and z coordinates.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param z the z-coordinate
     */
    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }


    /**
     * Constructs a Point3D with the specified x and y coordinates, and z set to 0.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Point3D(double x, double y) {
        this(x, y, 0);
    }


    /**
     * The z-coordinate of the point.
     */
    protected double z;


    /**
     * Returns the z-coordinate of the point.
     *
     * @return the z-coordinate
     */
    public double getZ() {
        return this.z;
    }
}
