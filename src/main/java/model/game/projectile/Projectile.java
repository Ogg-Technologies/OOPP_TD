package model.game.projectile;

import model.game.enemy.Enemy;
import utils.Vector;

/**
 * @author Oskar, Erik
 * The super type for all projectiles
 */
public interface Projectile {
    Vector getPosition();

    Vector getVelocity();

    void update();

    boolean isConsumed();

    void onEnemyHit(Enemy enemy);
}
