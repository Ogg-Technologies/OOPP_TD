package model.game.enemy;

import utils.VectorF;

import java.util.ArrayList;
import java.util.List;

public class EnemyHandler {
    private final EnemyFactory enemyFactory;
    private final List<Enemy> enemies;

    public EnemyHandler(EnemyService service) {
        enemyFactory = new EnemyFactory(service);
        enemies = new ArrayList<>();
        enemies.add(enemyFactory.createBasicEnemy(new VectorF(0, 101)));
    }

    public List<? extends ImmutableEnemy> getEnemies() {
        return enemies;
    }
}
