package model.game.wave;

import model.event.Event;
import model.event.EventSender;
import model.game.Health;
import model.game.MutableHealth;
import model.game.enemy.Enemy;
import model.game.enemy.EnemyFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * Handler for starting Waves and spawning the enemies from them.
 * It is possible to start multiple waves at the same time.
 */
public class WaveHandler {
    private final WaveData waveData;
    private final Collection<Wave> activeWaves = new ArrayList<>();
    private int currentLevel = 0;
    private final Collection<Enemy> enemies = new ArrayList<>();
    private final EventSender eventSender;

    /**
     * The sum of all the starting health of every wave that gets activated.
     * Gets reset when ALL active waves are cleared
     */
    private int originalEnemyAttackHealth = 0; //TODO remove this by keeping finished waves to calculate enemyAttackHealth. Remove all Waves when all are finished

    public WaveHandler(EnemyFactory enemyFactory, EventSender eventSender) {
        waveData = new WaveData(enemyFactory);
        this.eventSender = eventSender;
    }

    public void update() {
        updateWaves();
        updateEnemies();

        if (activeWaves.isEmpty() && enemies.isEmpty()) {
            originalEnemyAttackHealth = 0;
        }

    }

    private void updateEnemies() {
        synchronized (enemies) {
            for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext(); ) {
                Enemy e = enemyIterator.next();
                if (e.getHealth().isDead()) { //When enemy dies from tower
                    sendEnemyDeathEvent(e);
                    enemyIterator.remove();
                    continue;
                }

                e.update();

                if (e.getHealth().isDead()) { //When enemy dies from reaching the base
                    enemyIterator.remove();
                }
            }
        }
    }

    private void updateWaves() {
        synchronized (activeWaves) {
            for (Iterator<Wave> waveIterator = activeWaves.iterator(); waveIterator.hasNext(); ) {
                Wave w = waveIterator.next();
                if (w.hasNext()) {
                    Collection<Enemy> newEnemies = w.next();
                    synchronized (enemies) {
                        enemies.addAll(newEnemies);
                    }
                } else {
                    waveIterator.remove();
                }
            }
        }
    }

    /**
     * Starts the next Wave. Can be done while another one is active
     */
    public void startNewWave() {
        currentLevel++;
        Wave wave = waveData.getWave(currentLevel);
        synchronized (activeWaves) {
            activeWaves.add(wave);
        }
        originalEnemyAttackHealth += wave.getRemainingHealth();
    }

    /**
     * Gets the level (wave) that the game is currently on
     *
     * @return the current level
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    public Collection<? extends Enemy> getSpawnedEnemies() {
        synchronized (enemies) {
            return new ArrayList<>(enemies);
        }
    }

    /**
     * Gets the health object for "the enemies attack".
     * Max = total health of all waves activated since last time no waves were active
     * Current = the health of spawned enemies + to be spawned enemies
     *
     * @return the health of the enemy teams attack
     */
    public Health getEnemyAttackHealth() {
        int healthOfSpawnedEnemies;
        synchronized (enemies) {
            healthOfSpawnedEnemies = enemies
                    .stream()
                    .map(e -> e.getHealth().getCurrent())
                    .reduce(0, Integer::sum);
        }

        int healthOfToBeSpawnedEnemies;
        synchronized (activeWaves) {
            healthOfToBeSpawnedEnemies = activeWaves
                    .stream()
                    .map(Wave::getRemainingHealth)
                    .reduce(0, Integer::sum);
        }

        int current = healthOfSpawnedEnemies + healthOfToBeSpawnedEnemies;
        return new MutableHealth(originalEnemyAttackHealth, current);
    }

    private void sendEnemyDeathEvent(Enemy e) {
        this.eventSender.sendEvent(new Event(Event.Type.ENEMY_DEATH, e.getClass(), e.getPos()));
    }
}
