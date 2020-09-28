package model.game.enemy;

import utils.Vector;

import java.util.*;

public class EnemyHandler {
    private final EnemyFactory enemyFactory;
    private final Collection<Enemy> enemies;
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

    public Collection<? extends Enemy> getEnemies() {
        return Collections.unmodifiableCollection(enemies);
    }
}
