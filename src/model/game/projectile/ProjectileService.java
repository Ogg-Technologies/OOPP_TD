package model.game.projectile;

import model.game.enemy.Enemy;
import utils.Vector;

import java.util.List;

public interface ProjectileService {

    Vector getMapSize();

    List<? extends Enemy> getEnemies();
}
