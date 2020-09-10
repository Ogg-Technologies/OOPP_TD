package model.game.tower;

import java.util.List;

import model.game.enemy.ImmutableEnemy;
import utils.Vector;

public interface Tower extends ImmutableTower {
    TowerService getTowerService();

    Vector getPos();

    void update();

    List<? extends ImmutableEnemy> getEnemiesInRange(float range);

}
