package model.game.enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utils.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oskar
 */
class PathIteratorTest {

    private PathIterator p;

    @Nested
    class StandardConstructor {

        @BeforeEach
        void setUp() {
            List<Vector> list = new ArrayList<>();
            list.add(new Vector(1, 1));
            list.add(new Vector(2, 1));
            list.add(new Vector(2, 2));
            list.add(new Vector(2, 3));
            p = new PathIterator(list);
        }

        @Test
        void canIterateForwards() {
            assertTrue(p.hasNext());
            assertEquals(new Vector(1, 1), p.next());
            assertTrue(p.hasNext());
            assertEquals(new Vector(2, 1), p.next());
            assertTrue(p.hasNext());
            assertEquals(new Vector(2, 2), p.next());
            assertTrue(p.hasNext());
            assertEquals(new Vector(2, 3), p.next());
            assertFalse(p.hasNext());
            assertThrows(NoSuchElementException.class, () -> p.next());
        }

        @Test
        void canStepBackwards() {
            assertFalse(p.hasPrevious());
            assertTrue(p.hasNext());
            assertEquals(new Vector(1, 1), p.next());

            assertFalse(p.hasPrevious());
            assertTrue(p.hasNext());
            assertEquals(new Vector(2, 1), p.next());

            assertTrue(p.hasPrevious());
            assertTrue(p.hasNext());
            assertEquals(new Vector(1, 1), p.previous());

            assertFalse(p.hasPrevious());
            assertTrue(p.hasNext());
            assertThrows(NoSuchElementException.class, () -> p.previous());
        }
    }

    @Nested
    class FlyingPath {

        @BeforeEach
        void setUp() {
            List<Vector> list = new ArrayList<>();
            list.add(new Vector(1, 1));
            list.add(new Vector(2, 1));
            list.add(new Vector(2, 2));
            list.add(new Vector(2, 3));
            list.add(new Vector(3, 3));
            p = new PathIterator(list, 3);
        }

        @Test
        void flyingPathIsCorrect() {
            assertEquals(new Vector(1, 1), p.next());
            assertEquals(new Vector(2, 3), p.next());
            assertEquals(new Vector(3, 3), p.next());
            assertThrows(NoSuchElementException.class, () -> p.next());
        }
    }
}