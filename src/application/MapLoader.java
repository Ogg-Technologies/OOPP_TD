package application;

import application.mapreadservice.MapReader;
import application.mapreadservice.MapReaderServiceFactory;
import model.game.map.IllegalTileMapException;
import model.game.map.Tile;
import model.game.map.TileMap;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Erik
 * <p>
 * Finds, loads and reads all maps for the game. Works as the control layer between service and model
 */
public final class MapLoader {

    // TODO: Constructor arguments, add in config file or leave as it is?
    private static final String ROOT_PATH = "resource/maps/";
    private static final String FILE_FORMAT = ".map";

    /**
     * Loads all maps from the folder at ROOT_PATH. Invalid files or tile layouts are ignored
     * Throws Error if no maps could be loaded or if the ROOT_PATH folder does not exist
     *
     * @return A list of valid TileMaps
     */
    public List<? extends TileMap> loadMaps() {
        MapReader mapReader = MapReaderServiceFactory.createMapReader();

        File[] folder = getMapFolder();

        List<TileMap> maps = readFilesAsMaps(mapReader, folder);
        if (maps.isEmpty()) {
            throw new IOError(new Throwable("Could not load any maps"));
        }

        String mapOrMaps = maps.size() == 1 ? "map" : "maps";
        System.out.println("Succeeded in loading " + maps.size() + " " + mapOrMaps);
        return maps;
    }

    /**
     * @return A File array of the contents of the ROOT_PATH folder. Throws Error if folder not found
     */
    private File[] getMapFolder() {
        File folder = new File(ROOT_PATH);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            throw new IOError(new Throwable("Could not find map folder " + folder.getPath()));
        }
        return listOfFiles;
    }

    /**
     * Goes through all files in the given File array and tries to read it as a TileMap object, invalid files are ignored.
     * For a TileMap to be valid it has to be of the right format (FILE_FORMAT) and the tile layout must be valid
     * (decided when creating instance of TileMap)
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
                System.out.println("Could not read map \"" + f.getName() + "\" with reason: " + e.getMessage());
            } catch (IllegalTileMapException e) {
                System.out.println("Illegal tile map layout in \"" + f.getName() + "\" with reason: " + e.getMessage());
            }
        }
        return maps;
    }

    private TileMap tryReadMap(MapReader mapReader, String mapName) throws IOException, IllegalTileMapException {
        Tile[][] tileGrid = mapReader.readMap(ROOT_PATH + mapName);

        return TileMap.fromTileGrid(mapName.substring(0, mapName.lastIndexOf('.')), tileGrid);
    }
}
