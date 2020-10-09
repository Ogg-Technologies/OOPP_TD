package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Oskar, Erik
 */
class VectorTest {

    private Vector vec1;

    @BeforeEach
    void setUp() {
        vec1 = new Vector(3, 4);
    }

    @Test
    void canCreateFromPolar() {
        Vector v = Vector.fromPolar(2, 3);
        assertEquals(2, v.getAngle(), 0.0001);
        assertEquals(3, v.getDist(), 0.0001);
    }

    @Test
    void vectorRemembersItsCoordinate() {
        assertEquals(3, vec1.x);
        assertEquals(4, vec1.y);
    }

    @Test
    void vectorHasDistance() {
        assertEquals(5, vec1.getDist());
    }

    @Test
    void vectorHasSquaredDistance() {
        assertEquals(25, vec1.getSquaredDistance());
    }

    @Test
    void vectorHasAngle() {
        assertEquals(0, (new Vector(1, 0)).getAngle());
        assertEquals(Math.PI, (new Vector(-1, 0)).getAngle(), 0.001);
        assertEquals(Math.PI / 2, (new Vector(0, 1)).getAngle(), 0.001);
        assertEquals(-Math.PI / 4, (new Vector(1, -1)).getAngle(), 0.001);
    }

    @Test
    void throwsExceptionWhenGettingAngleOfConvertingZero() {
        assertThrows(IllegalStateException.class, () -> (new Vector(0, 0)).getAngle());
    }

    @Test
    void canMultiplyWithScalar() {
        Vector v = vec1.times(2);
        assertEquals(6, v.x);
        assertEquals(8, v.y);
    }

    @Test
    void canSetMagnitude() {
        Vector v = vec1.setMagnitude(10);
        assertEquals(v.getAngle(), vec1.getAngle());
        assertEquals(10, v.getDist(), 0.001);
    }

    @Test
    void canProjectToUnitCircle() {
        Vector v = vec1.asUnitVector();
        assertEquals(v.getAngle(), vec1.getAngle());
        assertEquals(1.0, v.getDist(), 0.001);
    }

    @Test
    void throwsExceptionWhenConvertingZeroVectorToUnitVector() {
        assertThrows(IllegalStateException.class, () -> (new Vector(0, 0)).asUnitVector());
    }

    @Nested
    class WithTwoVectors {
        private Vector vec2;

        @BeforeEach
        void setUp() {
            vec2 = new Vector(1.1, 1.1);
        }

        @Test
        void canAddVector() {
            Vector vec3 = vec1.plus(vec2);
            assertEquals(vec3.x, 4.1, 0.0001);
            assertEquals(vec3.y, 5.1, 0.0001);
        }

        @Test
        void canSubtractVector() {
            Vector vec3 = vec1.minus(vec2);
            assertEquals(vec3.x, 1.9, 0.0001);
            assertEquals(vec3.y, 2.9, 0.0001);
        }
    }
}