package model.game.enemy;

import utils.VectorF;

public interface Enemy {
    EnemyService getEnemyService();

    void update();

    VectorF getPos();
}
