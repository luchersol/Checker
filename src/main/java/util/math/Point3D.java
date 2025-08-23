package util.math;

public class Point3D extends Point2D {
    
    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public Point3D(double x, double y) {
        this(x, y, 0);
    }

    protected double z;

    public double getZ() {
        return this.z;
    }
}
