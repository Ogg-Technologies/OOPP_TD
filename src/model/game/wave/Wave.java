package model.game.wave;

import model.game.enemy.Enemy;

import java.util.Collection;

public interface Wave {
    void update();

    Collection<Enemy> getNewEnemies();

    boolean isFinished();

    int getRemainingHealth();
}
