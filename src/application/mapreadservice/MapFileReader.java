package application.mapreadservice;

import model.game.map.Tile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

class MapFileReader implements MapReader {

    @Override
    public Tile[][] readMap(String path) throws IOException, Exception {
        List<String> content = readFileAsString(path);

        for (String s : content) {
            System.out.println(s);
        }
        Tile[][] tileGrid = new Tile[content.size()][content.get(0).length()];

        for (int row = 0; row < content.size(); row++) {
            for (int i = 0; i < content.get(row).length(); i++) {
                tileGrid[row][i] = MapDecoder.convertToTile(content.get(row).charAt(i));
            }
        }

        System.out.println(Arrays.deepToString(tileGrid));

        return tileGrid;
    }

    private List<String> readFileAsString(String path) throws IOException {
        return Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
    }
}
