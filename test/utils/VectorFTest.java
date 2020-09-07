package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VectorFTest {

    private VectorF vec1;

    @BeforeEach
    void setUp() {
        vec1 = new VectorF(3, 4);
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
        assertEquals(0, (new VectorF(1, 0)).getAngle());
        assertEquals(Math.PI, (new VectorF(-1, 0)).getAngle(), 0.001);
        assertEquals(Math.PI / 2, (new VectorF(0, 1)).getAngle(), 0.001);
        assertEquals(-Math.PI / 4, (new VectorF(1, -1)).getAngle(), 0.001);
    }

    @Test
    void throwsExceptionWhenGettingAngleOfConvertingZero() {
        assertThrows(IllegalStateException.class, () -> (new VectorF(0, 0)).getAngle());
    }

    @Test
    void canMultiplyWithScalar() {
        VectorF v = vec1.times(2);
        assertEquals(6, v.getX());
        assertEquals(8, v.getY());
    }

    @Test
    void canProjectToUnitCircle() {
        VectorF v = vec1.asUnitVector();
        assertEquals(v.getAngle(), vec1.getAngle());
        assertEquals(1.0f, v.getDist(), 0.001);
    }

    @Test
    void throwsExceptionWhenConvertingZeroVectorToUnitVector() {
        assertThrows(IllegalStateException.class, () -> (new VectorF(0, 0)).asUnitVector());
    }

    @Nested
    class WithTwoVectorFs {
        private VectorF vec2;

        @BeforeEach
        void setUp() {
            vec2 = new VectorF(1.1f, 1.1f);
        }

        @Test
        void canAddVectorF() {
            VectorF vec3 = vec1.plus(vec2);
            assertEquals(vec3.getX(), 4.1f);
            assertEquals(vec3.getY(), 5.1f);
        }

        @Test
        void canSubtractVectorF() {
            VectorF vec3 = vec1.minus(vec2);
            assertEquals(vec3.getX(), 1.9f);
            assertEquals(vec3.getY(), 2.9f);
        }
    }
}