package model.game.projectile;

import model.game.enemy.Enemy;
import utils.Vector;

import java.util.Collection;

/**
 * @author Oskar, Erik
 * A class with all outside functionality that a Projectile needs.
 */
public interface ProjectileService {
    // TODO: Figure out if ProjectileService should extend EventSender and thereby removing the need to send EventSender separately

    Vector getMapSize();

    Collection<? extends Enemy> getEnemies();
}
