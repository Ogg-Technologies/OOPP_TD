package model.game.projectile;

import utils.Vector;

/**
 * @author Oskar, Erik
 * A subtype of Projectile which contains common functionality for all projectiles
 */
public abstract class AbstractProjectile implements Projectile {
    protected Vector velocity;
    private Vector position;
    protected boolean consumed;

    public AbstractProjectile(Vector position, Vector velocity) {
        this.position = position;
        this.velocity = velocity;
        consumed = false;
    }

    @Override
    public void update() {
        position = position.plus(velocity);
    }

    @Override
    public Vector getPosition() {
        return position;
    }

    @Override
    public Vector getVelocity() {
        return velocity;
    }

    @Override
    public boolean isConsumed() {
        return consumed;
    }
}
