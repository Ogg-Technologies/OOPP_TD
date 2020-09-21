package utils;

import java.util.Objects;

public class VectorD {
    private final double x;
    private final double y;

    public VectorD(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDist() {
        return Math.hypot(x, y);
    }

    public double getAngle() {
        if (getDist() == 0) throw new IllegalStateException("Cannot get angle of Zero vector: " + this);
        return Math.atan2(y, x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VectorD vector = (VectorD) o;
        return x == vector.x &&
                y == vector.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "VectorD{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public VectorD plus(VectorD other) {
        return new VectorD(x + other.x, y + other.y);
    }

    public VectorD minus(VectorD other) {
        return new VectorD(x - other.x, y - other.y);
    }

    public VectorD times(double scalar) {
        return new VectorD(x * scalar, y * scalar);
    }

    public VectorD setMagnitude(double scalar) {
        double magnitude = getDist();
        if (magnitude == 0)
            throw new IllegalStateException("Zero vector: " + this + " cannot be projected to another magnitude");
        return times(scalar / magnitude);
    }

    public VectorD asUnitVector() {
        return setMagnitude(1);
    }
}
