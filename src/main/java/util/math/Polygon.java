package util.math;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Polygon {

    private List<Point2D> points;

    public Polygon(List<Point2D> points) {
        this.points = points;
    }

    public Polygon(double[][] points) {
        this.points = Arrays.stream(points).map(Point2D::new).collect(Collectors.toList());
    }

    public List<Point2D> getPoints() {
        return this.points;
    }

    public int countVertex() {
        return this.points.size() - 1;
    }

    public boolean isValid() {
        if (points == null || points.size() < 3) return false;
        return points.stream().allMatch(p -> p != null);
    }

    public boolean isClosed() {
        if (!isValid()) return false;
        Point2D first = points.get(0);
        Point2D last = points.get(points.size() - 1);
        return first.equals(last);
    }

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

    public double perimeter() {
        if (!isValid()) return 0;
        double sum = 0;
        for (int i = 0; i < points.size(); i++) {
            Point2D p1 = points.get(i);
            Point2D p2 = points.get((i + 1) % points.size()); // conecta último con primero
            sum += p1.distance(p2);
        }
        return sum;
    }

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
