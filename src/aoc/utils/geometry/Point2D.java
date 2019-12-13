package aoc.utils.geometry;

import java.util.Objects;

public class Point2D {
    private final int x;
    private final int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int distanceFrom(Point2D other) {
        int deltaX = Math.abs(x - other.x);
        int deltaY = Math.abs(y - other.y);
        return deltaX + deltaY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point = (Point2D) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public Point2D moveBy(int x, int y) {
        return new Point2D(this.x + x, this.y + y);
    }
}
