package utils;

import java.util.Objects;

public class VectorF {
    private final float x;
    private final float y;

    public VectorF(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDist() {
        return (float) Math.hypot(x, y);
    }

    public float getAngle() {
        if (getDist() == 0) throw new IllegalStateException("Cannot get angle of Zero vector: " + this);
        return (float) Math.atan2(y, x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VectorF vector = (VectorF) o;
        return x == vector.x &&
                y == vector.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "VectorF{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public VectorF plus(VectorF other) {
        return new VectorF(x + other.x, y + other.y);
    }

    public VectorF minus(VectorF other) {
        return new VectorF(x - other.x, y - other.y);
    }

    public VectorF times(float scalar) {
        return new VectorF(x * scalar, y * scalar);
    }

    public VectorF setMagnitude(float scalar) {
        float magnitude = getDist();
        if (magnitude == 0)
            throw new IllegalStateException("Zero vector: " + this + " cannot be projected to another magnitude");
        return times(scalar / magnitude);
    }

    public VectorF asUnitVector() {
        return setMagnitude(1);
    }
}
