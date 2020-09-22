package utils;

import java.util.Objects;

public class Vector {
    private final int x;
    private final int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
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

    public VectorD plus(VectorD other) {
        return new VectorD(x + other.x, y + other.y);
    }

    public VectorD minus(VectorD other) {
        return new VectorD(x - other.x, y - other.y);
    }

    public VectorD asVectorD() {
        return new VectorD(x, y);
    }
}
