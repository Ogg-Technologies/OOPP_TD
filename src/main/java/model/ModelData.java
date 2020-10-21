package model;

import model.event.EventListener;
import model.game.enemy.Enemy;
import model.game.health.Health;
import model.game.map.TileMap;
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
     * Getter for the tileMap game contains, for knowing how to paint that map
     *
     * @return the tileMap
     */
    TileMap getActiveMap();

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

    /**
     * Gets the health object for "the enemies attack".
     *
     * @return the health of the enemy teams attack
     */
    Health getEnemyAttackHealth();

    /**
     * Getter for current wave number
     *
     * @return the wave number
     */
    int getWaveNumber();
}
