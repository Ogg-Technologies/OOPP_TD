package model.game.enemy.concreteenemies;

import model.game.enemy.Enemy;
import model.game.enemy.EnemyService;
import utils.VectorF;

public class BasicEnemy implements Enemy {
    private final Enemy baseEnemy;

    public BasicEnemy(Enemy baseEnemy) {
        this.baseEnemy = baseEnemy;
    }

    @Override
    public EnemyService getEnemyService() {
        return baseEnemy.getEnemyService();
    }

    @Override
    public void update() {
        baseEnemy.update();
    }

    @Override
    public VectorF getPos() {
        return baseEnemy.getPos();
    }
}
