package model.game.map;

import utils.ConnectedSequence;
import utils.Vector;

import java.util.ArrayList;
import java.util.List;

import static model.game.map.Tile.*;

public class TileMap {

    private final Tile[][] tileGrid;
    private final List<? extends Vector> deltas;
    private final ConnectedSequence<Vector> path;

    private TileMap(Tile[][] tileGrid) {
        this.tileGrid = tileGrid;
        this.deltas = initDirectionDeltas();
        this.path = calculatePath();
    }

    private List<Vector> initDirectionDeltas() {
        List<Vector> deltas = new ArrayList<>();
        deltas.add(new Vector(1, 0));
        deltas.add(new Vector(-1, 0));
        deltas.add(new Vector(0, 1));
        deltas.add(new Vector(0, -1));
        return deltas;
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
                {START, PATH, GROUND, GROUND, GROUND, PATH, PATH, PATH, PATH, PATH},
                {GROUND, PATH, GROUND, GROUND, GROUND, PATH, GROUND, GROUND, GROUND, PATH},
                {GROUND, PATH, GROUND, GROUND, GROUND, PATH, GROUND, GROUND, GROUND, PATH},
                {GROUND, PATH, GROUND, GROUND, GROUND, PATH, GROUND, GROUND, GROUND, PATH},
                {GROUND, PATH, PATH, PATH, PATH, PATH, GROUND, GROUND, GROUND, PATH},
                {GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, PATH},
                {GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, PATH},
                {GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, PATH},
                {GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, GROUND, BASE, PATH}
        };
    }

    public Vector getStartPosition() {
        return path.first();
    }

    public Vector getNextInPath(Vector previous) {
        return path.next(previous);
    }

    private ConnectedSequence<Vector> calculatePath() {
        ConnectedSequence<Vector> path = new ConnectedSequence<>();

        Vector start = findSingleTilePosition(START);
        Vector base = findSingleTilePosition(BASE);
        path.add(start);

        Vector current = path.first();
        Vector previous = path.first();
        while (true) {
            current = findNext(current, previous);
            if (current == null) {
                // Path is finished
                break;
            }
            path.add(current);
            if (path.size() > 2) {
                previous = path.next(previous);
            }
        }

        // Check if the base tile is within one tile of the current last path tile
        if (path.last().minus(base).getDist() == 1) {
            path.add(base);
            return path;
        }
        throw new IllegalTileMapException("The base tile (" + base
                + ") is not connected to the last path (" + path.last() + ") tile");
    }

    /**
     * Goes through all deltas (unit vectors in each direction) and finds out which one is the next in the path
     * @param current The current grid position as starting point
     * @param previous The previous grid position to stop backtracking
     * @return The next grid position in the path as a vector or null if its the end of the path
     */
    private Vector findNext(Vector current, Vector previous) {
        for (Vector direction : this.deltas) {
            Vector potentialNextPath = current.plus(direction);

            if (!potentialNextPath.equals(previous) && isPath(potentialNextPath)) {
                return potentialNextPath;
            }
        }
        return null;
    }

    private boolean isPath(Vector position) {
        // Check if outside grid
        if (position.getX() < 0 || position.getX() >= tileGrid[0].length
                || position.getY() < 0 || position.getY() >= tileGrid.length) {
            return false;
        }
        return tileGrid[position.getY()][position.getX()] == PATH;
    }


    /**
     * Goes through every tile in the map to find the position with the tile given as argument
     * If none or multiple were found it throws an IllegalTileMapException
     * @param tile The tile to search for
     * @return A vector defining the position of the tile
     */
    private Vector findSingleTilePosition(Tile tile) {
        Vector position = null;
        for (int y = 0; y < tileGrid.length; y++) {
            for (int x = 0; x < tileGrid[0].length; x++) {
                if (getTile(x, y) != tile) {
                    continue;
                }
                if (position != null) {
                    throw new IllegalTileMapException("Found multiple " + tile + " in map when only expecting one");
                }
                position = new Vector(x, y);
            }
        }
        if (position == null) {
            throw new IllegalTileMapException("Could not find a " + tile + " tile in map");
        }
        return position;
    }

    public Tile[][] getTileGrid() {
        return tileGrid;
    }

    private int getWidth() {
        return tileGrid[0].length;
    }

    private int getHeight() {
        return tileGrid.length;
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || x >= getWidth())
            throw new IllegalArgumentException("x: " + x + " is not within 0 to " + (getWidth() - 1));
        if (y < 0 || y >= getHeight())
            throw new IllegalArgumentException("y: " + y + " is not within 0 to " + (getHeight() - 1));
        return tileGrid[y][x];
    }

    public Vector getSize() {
        return new Vector(getWidth(), getHeight());
    }
}
