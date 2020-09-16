package model.game.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utils.Vector;

import static model.game.map.Tile.*;
import static org.junit.jupiter.api.Assertions.*;

class TileMapTest {

    @Test
    void mapWithNoStartPosition() {
        assertThrows(IllegalTileMapException.class, () ->
                TileMap.fromTileGrid(new Tile[][]{
                        {GROUND, GROUND, GROUND},
                        {PATH, PATH, PATH},
                        {GROUND, GROUND, BASE}
                }));
    }

    @Test
    void mapWithMultipleStartPositions() {
        assertThrows(IllegalTileMapException.class, () ->
                TileMap.fromTileGrid(new Tile[][]{
                        {BASE, GROUND, START},
                        {PATH, PATH, PATH},
                        {GROUND, START, GROUND}
                }));
    }

    @Test
    void mapWithNoBasePosition() {
        assertThrows(IllegalTileMapException.class, () ->
                TileMap.fromTileGrid(new Tile[][]{
                        {GROUND, GROUND, START},
                        {PATH, PATH, PATH},
                        {GROUND, GROUND, GROUND}
                }));
    }

    @Test
    void mapWithMultipleBasePositions() {
        assertThrows(IllegalTileMapException.class, () ->
                TileMap.fromTileGrid(new Tile[][]{
                        {BASE, GROUND, START},
                        {PATH, PATH, PATH},
                        {BASE, GROUND, GROUND}
                }));
    }

    @Test
    void startPositionIsCorrect() {
        assertEquals(TileMap.fromTileGrid(new Tile[][]{
                {GROUND, GROUND, GROUND},
                {GROUND, START, GROUND},
                {BASE, PATH, GROUND}
        }).getStartPosition(), new Vector(1, 1));
        assertEquals(TileMap.fromTileGrid(new Tile[][]{
                {PATH, PATH, BASE},
                {PATH, GROUND, GROUND},
                {PATH, PATH, GROUND},
                {GROUND, PATH, START}
        }).getStartPosition(), new Vector(2, 3));
    }

    @Test
    void mapWithIllegalBasePosition() {
        assertThrows(IllegalTileMapException.class, () ->
                TileMap.fromTileGrid(new Tile[][]{
                        {BASE, GROUND, START},
                        {PATH, PATH, PATH},
                        {PATH, GROUND, GROUND}
                }));
    }

    @Nested
    class WithCorrectTileMap {
        private TileMap tileMap;

        @BeforeEach
        void setUp() {
            tileMap = TileMap.fromTileGrid(new Tile[][]{
                    {PATH, PATH, PATH, GROUND},
                    {PATH, GROUND, PATH, PATH},
                    {PATH, PATH, BASE, START}
            });
        }

        @Test
        void isCreatedPathCorrect() {
            Vector current = tileMap.getStartPosition();

            assertEquals(new Vector(3, 2), current);
            current = next(current);
            assertEquals(new Vector(3, 1), current);
            current = next(current);
            assertEquals(new Vector(2, 1), current);
            current = next(current);
            assertEquals(new Vector(2, 0), current);
            current = next(current);
            assertEquals(new Vector(1, 0), current);
            current = next(current);
            assertEquals(new Vector(0, 0), current);
            current = next(current);
            assertEquals(new Vector(0, 1), current);
            current = next(current);
            assertEquals(new Vector(0, 2), current);
            current = next(current);
            assertEquals(new Vector(1, 2), current);
            current = next(current);
            assertEquals(new Vector(2, 2), current);
            current = next(current);
            assertNull(current);
        }

        private Vector next(Vector current) { // Convenience method
            return tileMap.getNextInPath(current);
        }

        @Test
        void createdMapHasCorrectSize() {
            assertEquals(tileMap.getSize().getX(), 4);
            assertEquals(tileMap.getSize().getY(), 3);
        }

        @Test
        void getTileGivesCorrectTile() {
            assertEquals(tileMap.getTile(0, 0), PATH);
            assertEquals(tileMap.getTile(1, 1), GROUND);
            assertEquals(tileMap.getTile(3, 2), START);
            assertEquals(tileMap.getTile(2, 2 ), BASE);
        }

        @Test
        void getTileOutsideMapThrowsIllegalArgument() {
            assertThrows(IllegalArgumentException.class, () -> tileMap.getTile(-1, 0));
            assertThrows(IllegalArgumentException.class, () -> tileMap.getTile(0, -1));
            assertThrows(IllegalArgumentException.class, () -> tileMap.getTile(4, 0));
            assertThrows(IllegalArgumentException.class, () -> tileMap.getTile(0, 3));
        }
    }
}