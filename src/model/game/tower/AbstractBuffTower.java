package model.game.tower;

import model.game.tower.towerutils.TowerFinder;
import model.game.tower.towerutils.chargestrategy.ChargeStrategy;
import utils.Vector;

import java.util.Collection;

/**
 * @author Oskar
 * A common subtype for all towers that buff other towers within its range
 */
public abstract class AbstractBuffTower extends AbstractTower {
    private TowerFinder towerFinder;

    /**
     * Creates an AbstractBuffTower
     *
     * @param pos            The position of the tower
     * @param range          The range within which towers will get buffed
     * @param chargeStrategy An object describing when this tower fires
     * @param towerFinder    A way of finding towers on the map
     */
    public AbstractBuffTower(Vector pos, double range, ChargeStrategy chargeStrategy, TowerFinder towerFinder) {
        super(pos, range, chargeStrategy);
        this.towerFinder = towerFinder;
    }

    @Override
    protected boolean tryFire() {
        Collection<? extends Tower> towersInRange = towerFinder.getTowersInRange(getPos(), getRange());
        towersInRange.remove(this);
        if (towersInRange.size() == 0) {
            return false;
        }
        buffTowers(towersInRange);
        return true;
    }

    /**
     * Template method for buffing towers. The subtype should apply its buff to all towers in the collection. This
     * method will never be called with an empty collection
     *
     * @param towers The towers that was within the range and should be buffed
     */
    protected abstract void buffTowers(Collection<? extends Tower> towers);
}
