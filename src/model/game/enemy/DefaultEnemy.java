package model.game.enemy;

import utils.VectorF;

public class DefaultEnemy implements Enemy {
    private final EnemyService enemyService;
    private VectorF pos;

    public DefaultEnemy(EnemyService service, VectorF pos) {
        enemyService = service;
        this.pos = pos;
    }

    @Override
    public EnemyService getEnemyService() {
        return enemyService;
    }

    @Override
    public void update() {
    }

    @Override
    public VectorF getPos() {
        return pos;
    }
}
