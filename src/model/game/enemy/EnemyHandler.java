package model.game.enemy;

import utils.VectorF;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnemyHandler {
    private final EnemyFactory enemyFactory;
    private final List<Enemy> enemies;
    private int clock = 0;

    public EnemyHandler(EnemyService service) {
        enemyFactory = new EnemyFactory(service);
        enemies = new ArrayList<>();
        enemies.add(enemyFactory.createFishstick(new VectorF(0, 1)));
    }

    public void update() {
        clock++;
        if (clock % 100 == 0) {
            enemies.add(enemyFactory.createFishstick(new VectorF(0, 1)));
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
