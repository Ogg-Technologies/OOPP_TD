package utils;

import java.util.Objects;

/**
 * @author Oskar, Erik
 * A class that represents a 2d vector with an x and a y component.
 */
public class Vector {
    public final double x;
    public final double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector fromPolar(double angle, double magnitude) {
        return new Vector(Math.cos(angle), Math.sin(angle)).setMagnitude(magnitude);
    }

    public double getDist() {
        return Math.hypot(x, y);
    }

    public double getSquaredDistance() {
        return x * x + y * y;
    }

    public double getAngle() {
        if (getDist() == 0) throw new IllegalStateException("Cannot get angle of Zero vector: " + this);
        return Math.atan2(y, x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return x == vector.x &&
                y == vector.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Vector plus(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    public Vector minus(Vector other) {
        return new Vector(x - other.x, y - other.y);
    }

    public Vector times(double scalar) {
        return new Vector(x * scalar, y * scalar);
    }

    public Vector setMagnitude(double scalar) {
        double magnitude = getDist();
        if (magnitude == 0)
            throw new IllegalStateException("Zero vector: " + this + " cannot be projected to another magnitude");
        return times(scalar / magnitude);
    }

    public Vector asUnitVector() {
        return setMagnitude(1);
    }

    public int getIntX() {
        return (int) Math.floor(x);
    }

    public int getIntY() {
        return (int) Math.floor(y);
    }
}
