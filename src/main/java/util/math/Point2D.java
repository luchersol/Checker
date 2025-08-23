package util.math;

public class Point2D {
    protected double x, y;

    public Point2D(double[] point) {
        this.x = point[0];
        this.y = point[0];
    }

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double distance(Point2D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Point2D subtract(Point2D other) {
        return new Point2D(this.x - other.x, this.y - other.y);
    }

    public Point2D scale(double factor) {
        return new Point2D(this.x * factor, this.y * factor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point2D)) return false;
        Point2D other = (Point2D) obj;
        return Double.compare(this.x, other.x) == 0 &&
               Double.compare(this.y, other.y) == 0;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Point2D normalize() {
        double mag = magnitude();
        if (this.x == 0 && this.y == 0) return new Point2D(0, 0);
        return new Point2D(x / mag, y / mag);
    }
}
