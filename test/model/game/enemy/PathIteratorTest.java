package model.game.enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class PathIteratorTest {

    private PathIterator p;

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