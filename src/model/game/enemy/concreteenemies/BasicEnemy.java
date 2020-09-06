package model.game.enemy.concreteenemies;

import javax.swing.DefaultBoundedRangeModel;

import model.game.enemy.DefualtEnemy;
import model.game.enemy.Enemy;
import model.game.enemy.EnemyService;

public class BasicEnemy implements Enemy {
    private final Enemy baseEnemy;

    public BasicEnemy(EnemyService service) {
        baseEnemy = new DefualtEnemy(service);
    }

    @Override
    public EnemyService getEnemyService() {
        return baseEnemy.getEnemyService();
    }

    @Override
    public void update() {

    }

}
