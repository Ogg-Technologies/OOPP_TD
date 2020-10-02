package model.game.tower;

import utils.Vector;

/**
 * @author Oskar, Sebastian, Behroz, Erik
 * Super type of all towers
 */
public interface Tower {

    /** @return The position of the tower */
    Vector getPos();

    /** Updates the tower */
    void update();

    /** @return The range of the tower in tiles */
    double getRange();

    /**
     * Method used to make visitor pattern work for towers
     * @param visitor TowerVisitor containing overloaded methods for the towers to "visit"
     */
    void accept(TowerVisitor visitor);
}
