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
        enemies.add(enemyFactory.createBasicEnemy(new VectorF(1, 1)));
    }

    public void update() {
        for (Enemy e : enemies) {
            e.update();
        }
    }

    public List<? extends ImmutableEnemy> getEnemies() {
        return enemies;
    }
}
