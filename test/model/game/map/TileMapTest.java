package model.game.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static model.game.map.Tile.GROUND;
import static model.game.map.Tile.PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TileMapTest {

    @Test
    void emptyMapHas0HeightWidth() {
        TileMap tileMap = TileMap.fromTileGrid(new Tile[][]{});
        assertEquals(tileMap.getWidth(), 0);
        assertEquals(tileMap.getHeight(), 0);
    }

    @Nested
    class WithMockTileMap {
        private TileMap tileMap;

        @BeforeEach
        void setUp() {
            tileMap = TileMap.fromTileGrid(new Tile[][]{
                    {GROUND, PATH, GROUND},
                    {GROUND, PATH, PATH}
            });
        }

        @Test
        void createdMapHasCorrectSize() {
            assertEquals(tileMap.getWidth(), 3);
            assertEquals(tileMap.getHeight(), 2);
        }

        @Test
        void getTileGivesCorrectTile() {
            assertEquals(tileMap.getTile(0, 0), GROUND);
            assertEquals(tileMap.getTile(1, 1), PATH);
        }

        @Test
        void getTileOutsideMapThrowsIllegalArgument() {
            assertThrows(IllegalArgumentException.class, () -> tileMap.getTile(-1, 0));
            assertThrows(IllegalArgumentException.class, () -> tileMap.getTile(0, -1));
            assertThrows(IllegalArgumentException.class, () -> tileMap.getTile(3, 0));
            assertThrows(IllegalArgumentException.class, () -> tileMap.getTile(0, 2));
        }

    }
}