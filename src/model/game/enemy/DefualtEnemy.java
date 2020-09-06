package model.game.enemy;

public class DefualtEnemy implements Enemy {
    private final EnemyService enemyService;

    public DefualtEnemy(EnemyService service) {
        enemyService = service;
    }

    @Override
    public EnemyService getEnemyService() {
        return enemyService;
    }

    @Override
    public void update() {

    }
}
