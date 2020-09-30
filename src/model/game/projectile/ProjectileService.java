package model.game.projectile;

import model.game.enemy.Enemy;
import utils.Vector;

import java.util.Collection;

public interface ProjectileService {
    // TODO: Figure out if ProjectileService should extend EventSender and thereby removing the need to send EventSender separately

    Vector getMapSize();

    Collection<? extends Enemy> getEnemies();
}
