package model.game.enemy;

import model.event.Event;
import model.event.EventSender;
import utils.Vector;

import java.util.*;

public class EnemyHandler {
    private final EnemyFactory enemyFactory;
    private final Collection<Enemy> enemies;
    private int clock = 0;
    private final EventSender eventSender;

    public EnemyHandler(BaseDamager baseDamager, List<? extends Vector> path, EventSender eventSender) {
        enemyFactory = new EnemyFactory(baseDamager, path);
        enemies = new ArrayList<>();
        this.eventSender = eventSender;
    }

    public void update() {
        clock++;
        if (clock % 100 == 0) {
            enemies.add(enemyFactory.createFishstick());
        }

        for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext(); ) {
            Enemy e = enemyIterator.next();
            if (e.getHealth().isDead()) { //When enemy dies from tower
                onEnemyDeath(e);
                enemyIterator.remove();
                continue;
            }

            e.update();

            if (e.getHealth().isDead()) { //When enemy dies from reaching the base
                enemyIterator.remove();
            }
        }
    }

    private void onEnemyDeath(Enemy e) {
        this.eventSender.sendEvent(new Event(Event.Type.ENEMY_DEATH, e.getClass(), e.getPos()));
    }

    public Collection<? extends Enemy> getEnemies() {
        return Collections.unmodifiableCollection(new ArrayList<>(enemies));
    }
}
