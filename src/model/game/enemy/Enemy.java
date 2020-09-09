package model.game.enemy;

public interface Enemy extends ImmutableEnemy {
    EnemyService getEnemyService();

    void update();
}
