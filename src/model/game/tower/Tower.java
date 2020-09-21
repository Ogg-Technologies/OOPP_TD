package model.game.tower;

import model.game.enemy.Enemy;
import utils.Vector;

import java.util.List;

public interface Tower {
    TowerService getTowerService();

    Vector getPos();

    void update();

    List<? extends Enemy> getEnemiesInRange(double range);

    double getRange();

    void accept(TowerVisitor visitor);
}
