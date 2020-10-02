package model.game.projectile;

import utils.Vector;

/**
 * @author Oskar, Erik
 * The super type for all projectiles
 */
public interface Projectile {
    Vector getPosition();

    void update();

    boolean isConsumed();
}
