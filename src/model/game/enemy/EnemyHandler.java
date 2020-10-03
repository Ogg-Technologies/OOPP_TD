package model.game.enemy;

import model.event.Event;
import model.event.EventSender;
import model.game.wave.WaveHandler;
import utils.Vector;

import java.util.*;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * Class for handling everything to do with enemies
 * Used and created by Game
 */
public class EnemyHandler implements EnemyAdder {
    private final EnemyFactory enemyFactory;
    private final Collection<Enemy> enemies;
    private int clock = 0;
    private final EventSender eventSender;
    private final WaveHandler waveHandler;

    public EnemyHandler(BaseDamager baseDamager, List<? extends Vector> path, EventSender eventSender) {
        enemyFactory = new EnemyFactory(baseDamager, path);
        enemies = new ArrayList<>();
        this.eventSender = eventSender;
        waveHandler = new WaveHandler(enemyFactory, this);
    }

    public void update() {
        waveHandler.update();

        if (enemies.size() == 0) {
            waveHandler.startNextWave();
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

    @Override
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }
}
