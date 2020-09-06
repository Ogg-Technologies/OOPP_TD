package model.game.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static model.game.map.Tile.GROUND;
import static model.game.map.Tile.PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MapTest {

    @Test
    void emptyMapHas0HeightWidth() {
        Map map = Map.fromTileGrid(new Tile[][]{});
        assertEquals(map.getWidth(), 0);
        assertEquals(map.getHeight(), 0);
    }

    @Nested
    class WithMockMap {
        private Map map;

        @BeforeEach
        void setUp() {
            map = Map.fromTileGrid(new Tile[][]{
                    {GROUND, PATH, GROUND},
                    {GROUND, PATH, PATH}
            });
        }

        @Test
        void createdMapHasCorrectSize() {
            assertEquals(map.getWidth(), 3);
            assertEquals(map.getHeight(), 2);
        }

        @Test
        void getTileGivesCorrectTile() {
            assertEquals(map.getTile(0, 0), GROUND);
            assertEquals(map.getTile(1, 1), PATH);
        }

        @Test
        void getTileOutsideMapThrowsIllegalArgument() {
            assertThrows(IllegalArgumentException.class, () -> map.getTile(-1, 0));
            assertThrows(IllegalArgumentException.class, () -> map.getTile(0, -1));
            assertThrows(IllegalArgumentException.class, () -> map.getTile(3, 0));
            assertThrows(IllegalArgumentException.class, () -> map.getTile(0, 2));
        }

    }
}