package application;

import application.mapreadservice.MapReader;
import application.mapreadservice.MapReaderServiceFactory;
import model.Model;
import model.game.map.Tile;

import java.io.IOException;

public class MapController {

    private Model model;

    public MapController(Model model) {
        this.model = model;
    }

    public void startGame(int mapId) {
        MapReader mapReader = MapReaderServiceFactory.createMapReader();

        try {
            Tile[][] tileGrid = mapReader.readMap("resource/maps/super" + mapId + ".map");

            model.startGame(tileGrid);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
