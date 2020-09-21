package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VectorDTest {

    private VectorD vec1;

    @BeforeEach
    void setUp() {
        vec1 = new VectorD(3, 4);
    }

    @Test
    void vectorRemembersItsCoordinate() {
        assertEquals(3, vec1.getX());
        assertEquals(4, vec1.getY());
    }

    @Test
    void vectorHasDistance() {
        assertEquals(5, vec1.getDist());
    }

    @Test
    void vectorHasAngle() {
        assertEquals(0, (new VectorD(1, 0)).getAngle());
        assertEquals(Math.PI, (new VectorD(-1, 0)).getAngle(), 0.001);
        assertEquals(Math.PI / 2, (new VectorD(0, 1)).getAngle(), 0.001);
        assertEquals(-Math.PI / 4, (new VectorD(1, -1)).getAngle(), 0.001);
    }

    @Test
    void throwsExceptionWhenGettingAngleOfConvertingZero() {
        assertThrows(IllegalStateException.class, () -> (new VectorD(0, 0)).getAngle());
    }

    @Test
    void canMultiplyWithScalar() {
        VectorD v = vec1.times(2);
        assertEquals(6, v.getX());
        assertEquals(8, v.getY());
    }

    @Test
    void canSetMagnitude() {
        VectorD v = vec1.setMagnitude(10);
        assertEquals(v.getAngle(), vec1.getAngle());
        assertEquals(10, v.getDist(), 0.001);
    }

    @Test
    void canProjectToUnitCircle() {
        VectorD v = vec1.asUnitVector();
        assertEquals(v.getAngle(), vec1.getAngle());
        assertEquals(1.0, v.getDist(), 0.001);
    }

    @Test
    void throwsExceptionWhenConvertingZeroVectorToUnitVector() {
        assertThrows(IllegalStateException.class, () -> (new VectorD(0, 0)).asUnitVector());
    }

    @Nested
    class WithTwoVectorDs {
        private VectorD vec2;

        @BeforeEach
        void setUp() {
            vec2 = new VectorD(1.1, 1.1);
        }

        @Test
        void canAddVectorD() {
            VectorD vec3 = vec1.plus(vec2);
            assertEquals(vec3.getX(), 4.1, 0.0001);
            assertEquals(vec3.getY(), 5.1, 0.0001);
        }

        @Test
        void canSubtractVectorD() {
            VectorD vec3 = vec1.minus(vec2);
            assertEquals(vec3.getX(), 1.9, 0.0001);
            assertEquals(vec3.getY(), 2.9, 0.0001);
        }
    }
}