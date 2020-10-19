package application;

import model.game.map.TileMap;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Erik
 * The agreed only test for map loading IO
 */
class MapLoaderTest {

    @Test
    void loadsMapsWithoutExceptions() {
        List<? extends TileMap> maps = new MapLoader().loadMaps();
        assertFalse(maps.isEmpty());
    }
}