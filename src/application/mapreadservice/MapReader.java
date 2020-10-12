package application.mapreadservice;

import model.game.map.Tile;

import java.io.IOException;

public interface MapReader {

    Tile[][] readMap(String path) throws IOException, Exception;
}
