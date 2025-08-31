package util.math;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Polygon2D represents a polygon in 2D space defined by a list of points (vertices).
 * Provides methods for geometric properties and checks such as area, perimeter, convexity, and validity.
 */
public class Polygon2D {

    /**
     * The list of points (vertices) that define the polygon.
     */
    private List<Point2D> points;


    /**
     * Constructs a Polygon2D from a list of Point2D objects.
     *
     * @param points the list of points (vertices) defining the polygon
     */
    public Polygon2D(List<Point2D> points) {
        this.points = points;
    }


    /**
     * Constructs a Polygon2D from a 2D array of coordinates.
     * Each sub-array should have two elements: [x, y].
     *
     * @param points a 2D array where each sub-array is a point [x, y]
     */
    public Polygon2D(double[][] points) {
        this.points = Arrays.stream(points).map(Point2D::new).collect(Collectors.toList());
    }


    /**
     * Returns the list of points (vertices) of the polygon.
     *
     * @return the list of points
     */
    public List<Point2D> getPoints() {
        return this.points;
    }


    /**
     * Returns the number of vertices in the polygon (excluding the closing point if present).
     *
     * @return the number of vertices
     */
    public int countVertex() {
        return this.points.size() - 1;
    }


    /**
     * Checks if the polygon is valid (has at least 3 non-null points).
     *
     * @return true if the polygon is valid, false otherwise
     */
    public boolean isValid() {
        if (points == null || points.size() < 3) return false;
        return points.stream().allMatch(p -> p != null);
    }


    /**
     * Checks if the polygon is closed (the first and last points are equal).
     *
     * @return true if the polygon is closed, false otherwise
     */
    public boolean isClosed() {
        if (!isValid()) return false;
        Point2D first = points.get(0);
        Point2D last = points.get(points.size() - 1);
        return first.equals(last);
    }


    /**
     * Checks if the polygon has any repeated points (excluding the closing point).
     *
     * @return true if there are repeated points, false otherwise
     */
    public boolean hasRepeatedPoints() {
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                if (points.get(i).equals(points.get(j)) && !(i == 0 && j == points.size() - 1)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Calculates the perimeter of the polygon (sum of edge lengths).
     *
     * @return the perimeter of the polygon, or 0 if invalid
     */
    public double perimeter() {
        if (!isValid()) return 0;
        double sum = 0;
        for (int i = 0; i < points.size(); i++) {
            Point2D p1 = points.get(i);
            Point2D p2 = points.get((i + 1) % points.size()); // connects last to first
            sum += p1.distance(p2);
        }
        return sum;
    }


    /**
     * Calculates the area of the polygon using the shoelace formula.
     *
     * @return the area of the polygon, or 0 if invalid
     */
    public double area() {
        if (!isValid()) return 0;
        double sum = 0;
        for (int i = 0; i < points.size(); i++) {
            Point2D p1 = points.get(i);
            Point2D p2 = points.get((i + 1) % points.size());
            sum += (p1.getX() * p2.getY()) - (p2.getX() * p1.getY());
        }
        return Math.abs(sum) / 2.0;
    }


    /**
     * Checks if the polygon is convex (all internal angles are less than 180 degrees).
     *
     * @return true if the polygon is convex, false otherwise
     */
    public boolean isConvex() {
        if (!isValid()) return false;
        int n = points.size();
        boolean sign = false;
        for (int i = 0; i < n; i++) {
            double dx1 = points.get((i + 2) % n).getX() - points.get((i + 1) % n).getX();
            double dy1 = points.get((i + 2) % n).getY() - points.get((i + 1) % n).getY();
            double dx2 = points.get(i).getX() - points.get((i + 1) % n).getX();
            double dy2 = points.get(i).getY() - points.get((i + 1) % n).getY();
            double cross = dx1 * dy2 - dy1 * dx2;
            if (i == 0) {
                sign = cross > 0;
            } else {
                if ((cross > 0) != sign) {
                    return false;
                }
            }
        }
        return true;
    }

}
