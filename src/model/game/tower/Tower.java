package model.game.tower;

import model.game.enemy.Enemy;
import utils.Vector;

import java.util.List;

public interface Tower extends ImmutableTower {
    TowerService getTowerService();

    Vector getPos();

    void update();

    List<? extends Enemy> getEnemiesInRange(float range);

}
