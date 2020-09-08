package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VectorTest {

    private Vector vec1;

    @BeforeEach
    void setUp() {
        vec1 = new Vector(3, 4);
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
        assertEquals(0, (new Vector(1, 0)).getAngle());
        assertEquals(Math.PI, (new Vector(-1, 0)).getAngle(), 0.001);
        assertEquals(Math.PI / 2, (new Vector(0, 1)).getAngle(), 0.001);
        assertEquals(-Math.PI / 4, (new Vector(1, -1)).getAngle(), 0.001);
    }

    @Test
    void throwsExceptionWhenGettingAngleOfConvertingZero() {
        assertThrows(IllegalStateException.class, () -> (new Vector(0, 0)).getAngle());
    }


    @Nested
    class WithTwoVectors {
        private Vector vec2;

        @BeforeEach
        void setUp() {
            vec2 = new Vector(1, 1);
        }

        @Test
        void canAddVector() {
            Vector vec3 = vec1.plus(vec2);
            assertEquals(vec3.getX(), 4);
            assertEquals(vec3.getY(), 5);
        }

        @Test
        void canSubtractVector() {
            Vector vec3 = vec1.minus(vec2);
            assertEquals(vec3.getX(), 2);
            assertEquals(vec3.getY(), 3);
        }
    }

    @Nested
    class WithFloatVector {
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