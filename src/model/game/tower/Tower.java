package model.game.tower;

import model.game.tower.towerutils.buff.TowerModifier;
import utils.Vector;

/**
 * @author Oskar, Sebastian, Behroz, Erik
 * Super type of all towers
 */
public interface Tower {

    /**
     * @return The position of the tower
     */
    Vector getPos();

    /**
     * Updates the tower
     */
    void update();

    /**
     * @return The range of the tower in tiles
     */
    double getRange();

    /**
     * Applies a buff to a tower for the given duration.
     *
     * @param towerModifier The way that the buff changes the tower
     * @param duration      The duration that the buff will be active for
     */
    void applyBuff(TowerModifier towerModifier, int duration);

    /**
     * Method used to make visitor pattern work for towers
     *
     * @param visitor TowerVisitor containing overloaded methods for the towers to "visit"
     */
    void accept(TowerVisitor visitor);
}
