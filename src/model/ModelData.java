package model;

import model.game.Health;
import model.game.enemy.Enemy;
import model.game.map.Tile;
import model.game.tower.Tower;
import utils.Vector;

import java.util.List;

public interface ModelData {

    void addOnModelUpdateObserver(OnModelUpdateObserver observer);

    /**
     * @return A Vector defining the size of the tile map in tiles
     */
    Vector getMapSize();

    /**
     * Retrieves the Tile at the given position or throws an exception if they are not within getMapSize() bounds
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The Tile at that position in the map
     */
    Tile getTile(int x, int y);

    List<? extends Tower> getTowers();

    List<? extends Enemy> getEnemies();

    Health getBaseHealth();
}
