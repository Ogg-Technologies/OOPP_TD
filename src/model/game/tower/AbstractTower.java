package model.game.tower;

import utils.Vector;

public abstract class AbstractTower implements Tower {
    private final Vector pos;
    private final double range;
    private final ChargeStrategy chargeStrategy;

    public AbstractTower(Vector pos, double range, ChargeStrategy chargeStrategy) {
        this.pos = pos;
        this.range = range;
        this.chargeStrategy = chargeStrategy;
    }

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
            }
        }
    }

    @Override
    public double getRange() {
        return range;
    }

    protected abstract boolean tryFire();
}
