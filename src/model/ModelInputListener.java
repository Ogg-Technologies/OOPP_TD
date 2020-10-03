package model;

import utils.Vector;

/**
 * @author Sebastian, Samuel, Erik
 * An interface for sending user input to the Model from the controller
 */
public interface ModelInputListener {
    /**
     * Gives a call to model/game that a tile has been clicked on
     * @param v the tile pos of the clicked tile
     */
    void onTileClick(Vector v);

    /**
     * Gives a call to model/game that the user want a new wave
     */
    void onStartNewWave();
}
