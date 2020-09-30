package model.game.tower;

import model.game.tower.towerutils.chargestrategy.ChargeStrategy;
import utils.Vector;

/**
 * Common super type for all towers
 * Every tower in the game needs everything in this class
 */
public abstract class AbstractTower implements Tower {
    private final Vector pos;
    private final double range;
    private final ChargeStrategy chargeStrategy;

    /**
     * @param pos The tile position of the tower
     * @param range The range of the tower in tiles
     * @param chargeStrategy Strategy of how the tower charges between fires
     */
    public AbstractTower(Vector pos, double range, ChargeStrategy chargeStrategy) {
        this.pos = pos;
        this.range = range;
        this.chargeStrategy = chargeStrategy;
    }

    /**
     * Template method pattern - all towers fires differently
     * @return True if it succeeded or false if it didn't
     */
    protected abstract boolean tryFire();

    @Override
    public Vector getPos() {
        return pos;
    }

    @Override
    public void update() {
        chargeStrategy.update();
        while (chargeStrategy.isReadyToFire()) {
            boolean success = tryFire();
            if (success) {
                chargeStrategy.didFire();
            } else {
                break;
            }
        }
    }

    @Override
    public double getRange() {
        return range;
    }
}
