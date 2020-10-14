package application.mapreadservice;

import model.game.map.Tile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Erik
 * <p>
 * Concrete implementation of the MapReader interface. Reads a map at the given path as a Tile matrix
 * Uses MapDecoder to decode String content to Tiles
 */
class MapFileReader implements MapReader {

    @Override
    public Tile[][] readMap(String path) throws IOException {
        List<String> content = readFileAsString(path);

        Tile[][] tileGrid = new Tile[content.size()][content.get(0).length()];

        for (int row = 0; row < content.size(); row++) {
            for (int i = 0; i < content.get(row).length(); i++) {
                tileGrid[row][i] = MapDecoder.convertToTile(content.get(row).charAt(i));
            }
        }

        return tileGrid;
    }

    private List<String> readFileAsString(String path) throws IOException {
        return Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
    }


    /**
     * @author Erik
     * <p>
     * Helper class for decoding chars to Tiles
     */
    private final static class MapDecoder {

        private static final char GROUND = '.';
        private static final char PATH = 'o';
        private static final char START = 'S';
        private static final char BASE = 'X';

        static Tile convertToTile(char character) throws IOException {
            switch (character) {
                case GROUND:
                    return Tile.GROUND;
                case PATH:
                    return Tile.PATH;
                case START:
                    return Tile.START;
                case BASE:
                    return Tile.BASE;
                default:
                    throw new IOException("The map could not be decoded as it uses the wrong format. " +
                            "(Cannot have character " + character + ")");
            }
        }
    }
}
