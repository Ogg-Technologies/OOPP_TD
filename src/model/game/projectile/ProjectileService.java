package model.game.projectile;

import model.game.enemy.Enemy;
import utils.Vector;

import java.util.Collection;

public interface ProjectileService {

    Vector getMapSize();

    Collection<? extends Enemy> getEnemies();
}
