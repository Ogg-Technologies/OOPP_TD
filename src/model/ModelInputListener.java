package model;

import utils.Vector;

/**
 * @author Sebastian, Samuel, Erik
 * An interface for sending user input to the Model from the controller
 */
public interface ModelInputListener {
    void onTileClick(Vector v);
}
