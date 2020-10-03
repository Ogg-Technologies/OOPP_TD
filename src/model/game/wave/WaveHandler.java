package model.game.wave;

import model.game.enemy.Enemy;
import model.game.enemy.EnemyAdder;
import model.game.enemy.EnemyFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Oskar
 * Handler for starting Waves and spawning the enemies from them.
 * <p>
 * It is possible to start multiple waves at the same time.
 */
public class WaveHandler {
    EnemyAdder enemyAdder;
    WaveData waveData;
    Collection<Wave> activeWaves = new ArrayList<>();
    private int currentLevel = 0;

    public WaveHandler(EnemyFactory enemyFactory, EnemyAdder enemyAdder) {
        this.enemyAdder = enemyAdder;
        waveData = new WaveData(enemyFactory);
        startNextWave();
    }

    /**
     * Starts the next Wave. Can be done while another one is active
     */
    public void startNextWave() {
        currentLevel++;
        Wave wave = waveData.getWave(currentLevel);
        activeWaves.add(wave);
        System.out.println("Starting Level " + currentLevel);
    }

    public void update() {
        for (Iterator<Wave> waveIterator = activeWaves.iterator(); waveIterator.hasNext(); ) {
            Wave w = waveIterator.next();
            if (w.hasNext()) {
                Collection<Enemy> newEnemies = w.next();
                newEnemies.forEach(enemyAdder::addEnemy);
            } else {
                waveIterator.remove();
            }
        }
    }

    /**
     * Gets the level (wave) that the game is currently on
     *
     * @return the current level
     */
    public int getCurrentLevel() {
        return currentLevel;
    }
}
