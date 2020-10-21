package application;

import application.mapreadservice.MapReader;
import application.mapreadservice.MapReaderServiceFactory;
import model.game.map.Tile;
import model.game.map.TileMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Erik
 * <p>
 * Finds, loads and reads all maps for the game. Works as the control layer between service and model
 */
public final class MapLoader {

    private static final String ROOT_PATH = "src/main/resources/maps/";
    private static final String FILE_FORMAT = ".map";

    /**
     * Loads all maps from the folder at ROOT_PATH. Invalid files or tile layouts are ignored
     * Throws Error if no maps could be loaded or if the ROOT_PATH folder does not exist
     *
     * @return A list of valid TileMaps
     */
    public List<? extends TileMap> loadMaps() {
        MapReader mapReader = MapReaderServiceFactory.createMapReader();

        File[] folderContent = getMapFolderContent();

        List<TileMap> maps = readFilesAsMaps(mapReader, folderContent);
        if (maps.isEmpty()) {
            throw new RuntimeException("Could not load any maps at " + ROOT_PATH);
        }

        return maps;
    }

    /**
     * @return A File array of the contents of the ROOT_PATH folder. Throws Error if folder not found
     */
    private File[] getMapFolderContent() {
        File folder = new File(ROOT_PATH);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            throw new InvalidPathException(ROOT_PATH, "The path does not exist");
        }
        return listOfFiles;
    }

    /**
     * Goes through all files in the given File array and tries to read it as a TileMap object.
     * Invalid maps will throw runtime exceptions. For a TileMap to be valid it has to be of the right format (FILE_FORMAT)
     * and the tile layout must be valid (decided when creating instance of TileMap)
     *
     * @param mapReader   Map reader service used to read the actual file as a Tile matrix
     * @param listOfFiles Folder at which the maps are
     * @return A list containing all valid maps
     */
    private List<TileMap> readFilesAsMaps(MapReader mapReader, File[] listOfFiles) {
        List<TileMap> maps = new ArrayList<>();
        for (File f : listOfFiles) {
            if (!f.getName().endsWith(FILE_FORMAT)) {
                continue;
            }
            try {
                maps.add(tryReadMap(mapReader, f.getName()));
            } catch (IOException e) {
                // IOExceptions are ignored because it is not our fault
                System.out.println("Could not read map \"" + f.getName() + "\" with reason: " + e.getMessage());
            }
        }
        return maps;
    }

    private TileMap tryReadMap(MapReader mapReader, String mapName) throws IOException {
        Tile[][] tileGrid = mapReader.readMap(ROOT_PATH + mapName);

        return TileMap.fromTileGrid(mapName.substring(0, mapName.lastIndexOf('.')), tileGrid);
    }
}
