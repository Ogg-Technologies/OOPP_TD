package application.mapreadservice;

import model.game.map.Tile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Erik
 * <p>
 * Concrete implementation of the MapReader interface. Reads a map at the given path as a Tile matrix
 * Uses MapDecoder to decode String content to Tiles
 */
class MapFileReader implements MapReader {

    @Override
    public Tile[][] readMap(String path) throws IOException {
        List<String> contentWithComments = readFileAsString(path);

        // Remove commented lines
        List<String> content = contentWithComments.stream()
                .filter(e -> !e.startsWith(MapDecoder.COMMENT_PREFIX))
                .collect(Collectors.toList());

        if (content.isEmpty()) {
            throw new RuntimeException("Map could not be loaded because it is empty");
        }

        return convertContentToTiles(content, content.get(0).length());
    }

    /**
     * Converts the list of Strings into a Tile matrix
     *
     * @param content   The list of Strings read from the file where every element is one row
     * @param rowLength The expected row length of all uncommented rows
     * @return A Tile matrix decoded from @param content
     */
    private Tile[][] convertContentToTiles(List<String> content, int rowLength) {
        Tile[][] tileGrid = new Tile[content.size()][rowLength];

        for (int row = 0; row < content.size(); row++) {
            String currentRow = content.get(row);

            // Invalid content if uneven row size
            if (currentRow.length() != rowLength) {
                throw new IllegalArgumentException("Map could not be loaded because row "
                        + (row + 1) + " (not included commented lines) is not of correct length (" + rowLength + ")");
            }

            // Convert row to tiles
            for (int i = 0; i < currentRow.length(); i++) {
                tileGrid[row][i] = MapDecoder.convertToTile(currentRow.charAt(i));
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

        private static final String COMMENT_PREFIX = "//";
        private static final char GROUND = '.';
        private static final char PATH = 'o';
        private static final char START = 'S';
        private static final char BASE = 'X';

        static Tile convertToTile(char character) {
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
                    throw new IllegalArgumentException("The map could not be decoded as it uses the wrong format. " +
                            "(Illegal character " + character + ")");
            }
        }
    }
}
