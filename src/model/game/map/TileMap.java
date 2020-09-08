package model.game.map;

import utils.Vector;

import java.util.ArrayList;
import java.util.List;

import static model.game.map.Tile.GROUND;
import static model.game.map.Tile.PATH;

public class TileMap {

    private final Tile[][] tileGrid;
    private final List<Vector> path;

    private TileMap(Tile[][] tileGrid) {
        this.tileGrid = tileGrid;
        this.path = calculatePath();
    }

    public static TileMap fromDefaultTileGrid() {
        return new TileMap(createBasicTileGrid());
    }

    public static TileMap fromTileGrid(Tile[][] tileGrid) {
        return new TileMap(tileGrid);
    }

    private static Tile[][] createBasicTileGrid() {
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

    private List<Vector> calculatePath() {
        List<Vector> path = new ArrayList<>();

        Vector start = findStartPosition();
        path.add(start);
        return path;
    }

    // TODO: Come up with a more suitable way to find start position

    /**
     * Goes through the border and finds the first PATH tile for start position
     * @return A vector defining the start position
     */
    private Vector findStartPosition() {
        for (int y = 0; y < tileGrid.length; y++) {
            if (tileGrid[y][0] == PATH) {   // Check leftmost
                return new Vector(0, y);
            } else if (tileGrid[y][tileGrid[y].length - 1] == PATH) {   // Check rightmost
                return new Vector(tileGrid[y].length - 1, y);
            }
        }
        for (int x = 0; x < tileGrid[0].length; x++) {
            if (tileGrid[0][x] == PATH) {   // Check topmost
                return new Vector(x, 0);
            } else if (tileGrid[tileGrid.length - 1][x] == PATH) {   // Check bottommost
                return new Vector(x, tileGrid[tileGrid.length - 1].length);
            }
        }
        throw new IllegalTileMapException("Could not find a start position for the map");
    }

    public int getWidth() {
        if (getHeight() == 0)
            return 0;
        return tileGrid[0].length;
    }

    public Tile[][] getTileGrid() {
        return tileGrid;
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
