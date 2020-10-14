package model.game.map;

import utils.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static model.game.map.Tile.*;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * Represents the a map in the game with the path for the enemies, the spawn and the base.
 * <p>
 * The tileGrid never changes
 */
public class TileMap {

    private final Tile[][] tileGrid;
    private final List<? extends Vector> deltas;
    private final List<Vector> path;

    /**
     * Tries to create a TileMap with the given tile grid information
     *
     * @param tileGrid The layout of the map as a Tile matrix
     * @throws IllegalTileMapException Thrown during the creation process if:
     *                                 There is not exactly one START and BASE tile in the matrix
     *                                 There is not a continuous connection of PATH tiles from the START tile to the BASE tile
     */
    private TileMap(Tile[][] tileGrid) throws IllegalTileMapException {
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

    public static TileMap fromDefaultTileGrid() throws IllegalTileMapException {
        return new TileMap(createBasicTileGrid());
    }

    public static TileMap fromTileGrid(Tile[][] tileGrid) throws IllegalTileMapException {
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

    private List<Vector> calculatePath() throws IllegalTileMapException {
        List<Vector> path = new ArrayList<>();

        Vector start = findSingleTilePosition(START);
        Vector base = findSingleTilePosition(BASE);

        path.add(calculateSpawnTile(start));

        Vector current = start;
        do {
            path.add(current);
            current = findNext(path, current);

        } while (current != null);

        // Check if the base tile is within one tile of the current last path tile
        if (path.get(path.size() - 1).minus(base).getDist() == 1) {
            path.add(base);
            return path;
        }
        throw new IllegalTileMapException("The base tile (" + base
                + ") is not connected to the last path (" + path.get(path.size() - 1) + ") tile");
    }

    /**
     * Calculates the tile at which enemies appear. This position is off-screen (provided that START tile is on the border).
     * The reason this exists is to not have enemies just appear on the start tile - instead having them enter the map.
     *
     * @param startPosition The marked start position of the map
     * @return The position one tile backwards from where the next tile after the start tile is
     */
    private Vector calculateSpawnTile(Vector startPosition) throws IllegalTileMapException {
        Vector next = findNext(new ArrayList<>(), startPosition);
        if (next == null) {
            throw new IllegalTileMapException("The marked path is not connected to the START tile");
        }
        Vector delta = next.minus(startPosition);
        return startPosition.minus(delta);
    }

    /**
     * Goes through all deltas (unit vectors in each direction) and finds out which one is the next in the path
     *
     * @param path    The so-far-calculated path, used to make sure only one position is added at most once to avoid loops or backtracking
     * @param current The current grid position as starting point
     * @return The next grid position in the path as a vector or null if its the end of the path
     */
    private Vector findNext(Collection<? extends Vector> path, Vector current) {
        for (Vector direction : this.deltas) {
            Vector potentialNextPath = current.plus(direction);

            if (path.contains(potentialNextPath)) {
                continue;
            }

            if (isPath(potentialNextPath)) {
                return potentialNextPath;
            }
        }
        return null;
    }

    private boolean isPath(Vector position) {
        // Check if outside grid
        if (position.x < 0 || position.x >= tileGrid[0].length
                || position.y < 0 || position.y >= tileGrid.length) {
            return false;
        }
        return tileGrid[position.getIntY()][position.getIntX()] == PATH;
    }


    /**
     * Goes through every tile in the map to find the position with the tile given as argument
     * If none or multiple were found it throws an IllegalTileMapException
     *
     * @param tile The tile to search for
     * @return A vector defining the position of the tile
     */
    private Vector findSingleTilePosition(Tile tile) throws IllegalTileMapException {
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

    private int getWidth() {
        return tileGrid[0].length;
    }

    private int getHeight() {
        return tileGrid.length;
    }

    /**
     * Returns the Tile at the given position, or throws an exception if the given position is not within map bounds
     *
     * @param x The x-coordinate, must be within (0..width-1)
     * @param y The x-coordinate, must be within (0..height-1)
     * @return The Tile at that position
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || x >= getWidth()) {
            throw new IllegalArgumentException("x: " + x + " is not within 0 to " + (getWidth() - 1));
        }
        if (y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException("y: " + y + " is not within 0 to " + (getHeight() - 1));
        }
        return tileGrid[y][x];
    }

    /**
     * @return Returns the size of the map in tiles as a Vector
     */
    public Vector getSize() {
        return new Vector(getWidth(), getHeight());
    }

    /**
     * Getter for the calculated path, wrapped to make it unmodifiable
     *
     * @return The list of calculated path tiles from the start to the base
     */
    public List<? extends Vector> getPath() {
        return Collections.unmodifiableList(path);
    }
}
