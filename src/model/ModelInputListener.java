package model;

import model.game.tower.Tower;
import utils.Vector;

/**
 * @author Sebastian, Samuel, Erik
 * An interface for sending user input to the Model from the controller
 */
public interface ModelInputListener {
    /**
     * Gives a call to model/game that a tile has been clicked on
     * The parameters cannot be null.
     * @param v the tile pos of the clicked tile
     * @param towerClass the class of wanted tower to place
     */
    void onTileClick(Vector v, Class<? extends Tower> towerClass);

    /**
     * Gives a call to model/game that the user want a new wave
     */
    void onStartNewWave();
}
