package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectedSequenceTest {

    @Test
    void newSequenceIsEmpty() {
        ConnectedSequence<Object> connectedSequence = new ConnectedSequence<>();
        assertTrue(connectedSequence.isEmpty());
        connectedSequence.add(new Object());
        assertFalse(connectedSequence.isEmpty());
    }

    @Nested
    class AsIntegerSequence {
        private ConnectedSequence<Integer> sequence;

        @BeforeEach
        void setup() {
            sequence = new ConnectedSequence<>();
        }

        @Test
        void resetResets() {
            sequence.add(-1);
            assertNotNull(sequence.first());
            assertFalse(sequence.isEmpty());
            assertNotEquals(0, sequence.size());
            sequence.reset();
            assertNull(sequence.first());
            assertTrue(sequence.isEmpty());
            assertEquals(0, sequence.size());
        }

        @Test
        void sequenceSizeGetsUpdatedCorrectly() {
            assertEquals(0, sequence.size());
            sequence.add(5);
            assertEquals(1, sequence.size());
            sequence.add(6);
            assertEquals(2, sequence.size());

            sequence.reset();
            assertEquals(0, sequence.size());
        }

        @Test
        void addingIllegalElementThrowsException() {
            sequence.add(0);
            sequence.add(1);
            assertThrows(ConnectedSequence.IllegalSequenceException.class, () -> sequence.add(1));
            assertThrows(ConnectedSequence.IllegalSequenceException.class, () -> sequence.add(0));
            assertThrows(ConnectedSequence.IllegalSequenceException.class, () -> sequence.add(null));
        }

        @Test
        void firstReturnsFirstElement() {
            sequence.add(1);
            assertEquals(1, sequence.first());
            sequence.reset();
            sequence.add(444);
            sequence.add(33);
            assertEquals(444, sequence.first());
            sequence.add(4);
            assertEquals(444, sequence.first());
        }

        @Test
        void lastReturnsLastElement() {
            sequence.add(1);
            assertEquals(1, sequence.last());
            sequence.reset();
            sequence.add(444);
            sequence.add(33);
            assertEquals(33, sequence.last());
            sequence.add(4);
            assertEquals(4, sequence.last());
        }

        @Test
        void nextReturnsCorrectElement() {
            sequence.add(0);
            sequence.add(10);
            sequence.add(20);
            sequence.add(30);

            Integer current = sequence.first();
            assertEquals(0, current);
            current = sequence.next(current);
            assertEquals(10, current);
            current = sequence.next(current);
            assertEquals(20, current);
            current = sequence.next(current);
            assertEquals(30, current);
            assertNull(sequence.next(current));
        }
    }

    @Nested
    class AsStringSequence {
        private ConnectedSequence<String> sequence;

        @BeforeEach
        void setup() {
            sequence = new ConnectedSequence<>();
        }

        @Test
        void resetResets() {
            sequence.add("");
            assertNotNull(sequence.first());
            assertFalse(sequence.isEmpty());
            assertNotEquals(sequence.size(), 0);
            sequence.reset();
            assertNull(sequence.first());
            assertTrue(sequence.isEmpty());
            assertEquals(sequence.size(), 0);
        }

        @Test
        void sequenceSizeGetsUpdatedCorrectly() {
            assertEquals(0, sequence.size());
            sequence.add("aaa");
            assertEquals(1, sequence.size());
            sequence.add("bbb");
            assertEquals(2, sequence.size());

            sequence.reset();
            assertEquals(0, sequence.size());
        }

        @Test
        void addingIllegalElementThrowsException() {
            sequence.add("");
            sequence.add("lemon");
            assertThrows(ConnectedSequence.IllegalSequenceException.class, () -> sequence.add("lemon"));
            assertThrows(ConnectedSequence.IllegalSequenceException.class, () -> sequence.add(""));
            assertThrows(ConnectedSequence.IllegalSequenceException.class, () -> sequence.add(null));
        }

        @Test
        void firstReturnsFirstElement() {
            sequence.add("first");
            assertEquals("first", sequence.first());
            sequence.reset();
            sequence.add("1234");
            sequence.add("wow");
            assertEquals("1234", sequence.first());
            sequence.add("more");
            assertEquals("1234", sequence.first());
        }

        @Test
        void lastReturnsLastElement() {
            sequence.add("last");
            assertEquals("last", sequence.last());
            sequence.reset();
            sequence.add("1234");
            sequence.add("wow");
            assertEquals("wow", sequence.last());
            sequence.add("more");
            assertEquals("more", sequence.last());
        }

        @Test
        void nextReturnsCorrectElement() {
            sequence.add("Super");
            sequence.add("duper");
            sequence.add("Omega");
            sequence.add("gruppen");

            String current = sequence.first();
            assertEquals("Super", current);
            current = sequence.next(current);
            assertEquals("duper", current);
            current = sequence.next(current);
            assertEquals("Omega", current);
            current = sequence.next(current);
            assertEquals("gruppen", current);
            assertNull(sequence.next(current));
        }
    }
}
