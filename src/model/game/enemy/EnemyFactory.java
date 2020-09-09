package model.game.enemy;

import model.game.enemy.concreteenemies.BasicEnemy;
import utils.VectorF;

public class EnemyFactory {
    private final EnemyService service;

    public EnemyFactory(EnemyService service) {
        this.service = service;
    }

    public Enemy createBasicEnemy(VectorF pos) {
        return new BasicEnemy(new DefaultEnemy(service, pos));
    }
}
