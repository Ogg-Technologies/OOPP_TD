package model.game.map;

import static model.game.map.Tile.GROUND;
import static model.game.map.Tile.PATH;

public class Map {
    private final Tile[][] tileGrid;

    public Map() {
        tileGrid = createBasicTileGrid();
    }

    private Map(Tile[][] tileGrid) {
        this.tileGrid = tileGrid;
    }

    public static Map fromTileGrid(Tile[][] tileGrid) {
        return new Map(tileGrid);
    }

    private Tile[][] createBasicTileGrid() {
        return new Tile[][]{
                {GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND},
                {PATH, PATH, GROUND, GROUND, GROUND, PATH, PATH, PATH, PATH, PATH},
                {GROUND, PATH, GROUND, GROUND, GROUND, PATH, GROUND, GROUND, GROUND, PATH},
                {GROUND, PATH, GROUND, GROUND, GROUND, PATH, GROUND, GROUND, GROUND, PATH},
                {GROUND, PATH, GROUND, GROUND, GROUND, PATH, GROUND, GROUND, GROUND, PATH},
                {GROUND, PATH, PATH, PATH, PATH, PATH, GROUND, GROUND, GROUND, PATH},
                {GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, PATH},
                {GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, PATH},
                {GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, PATH},
                {GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, PATH}
        };
    }

    public int getWidth() {
        if (getHeight() == 0)
            return 0;
        return tileGrid[0].length;
    }

    public int getHeight() {
        return tileGrid.length;
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || x >= getWidth())
            throw new IllegalArgumentException("x: " + x + " is not within 0 to " + (getWidth() - 1));
        if (y < 0 || y >= getHeight())
            throw new IllegalArgumentException("y: " + y + " is not within 0 to " + (getHeight() - 1));
        return tileGrid[y][x];
    }
}
