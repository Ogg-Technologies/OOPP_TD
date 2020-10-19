package application.mapreadservice;

import model.game.map.Tile;

import java.io.IOException;

/**
 * @author Erik
 * <p>
 * Interface for defining functionality for reading map files as an object compatible with the model
 */
public interface MapReader {

    /**
     * Tries to read the file at the given path as a Tile matrix that can be used to create TileMap objects
     *
     * @param path The path to the file
     * @return A decoded Tile matrix
     * @throws IOException If the file is not found or otherwise cannot be read
     */
    Tile[][] readMap(String path) throws IOException;
}
