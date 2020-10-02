package model;

import model.event.EventListener;
import model.game.Health;
import model.game.enemy.Enemy;
import model.game.map.Tile;
import model.game.projectile.Projectile;
import model.game.tower.Tower;
import utils.Vector;

import java.util.Collection;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * An interface for fetching information about the program state or adding eventListeners without being able to modify it.
 * Implemented by the Model. Used by View.
 */
public interface ModelData {

    void addOnModelEventListener(EventListener eventListener);

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

    Collection<? extends Tower> getTowers();

    Collection<? extends Enemy> getEnemies();

    Collection<? extends Projectile> getProjectiles();

    Health getBaseHealth();

    /**
     * @param tilePos the tileMap position
     * @return a boolean that describes whether the game thinks it is a valid tile or not
     */
    boolean isValidTile(Vector tilePos);

    /**
     * @return amount of money the player has
     */
    int getMoney();
}
