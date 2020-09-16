package model.game.enemy;

import utils.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnemyHandler {
    private final EnemyFactory enemyFactory;
    private final List<Enemy> enemies;
    private int clock = 0;

    public EnemyHandler(BaseDamager baseDamager, List<? extends Vector> path) {
        enemyFactory = new EnemyFactory(baseDamager, path);
        enemies = new ArrayList<>();
        enemies.add(enemyFactory.createFishstick());
    }

    public void update() {
        clock++;
        if (clock % 100 == 0) {
            enemies.add(enemyFactory.createFishstick());
        }

        for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext(); ) {
            Enemy e = enemyIterator.next();
            if (e.getHealth().isDead()) {
                enemyIterator.remove();
                continue;
            }

            e.update();

            if (e.getHealth().isDead()) {
                enemyIterator.remove();
            }
        }
    }

    public List<? extends Enemy> getEnemies() {
        return enemies;
    }
}
