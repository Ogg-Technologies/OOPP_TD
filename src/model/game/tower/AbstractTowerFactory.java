package model.game.tower;

import utils.Vector;

/**
 * @author Sebastian
 * Interface for creating towers
 * It is used by controller and towerHandler
 */
public interface AbstractTowerFactory {
    /**
     * Method that actually creates the tower
     *
     * @param pos the tilePosition of the created tower
     * @return the newly created tower
     */
    Tower createTower(Vector pos);
}
