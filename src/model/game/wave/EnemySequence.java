package model.game.wave;

import model.game.enemy.Enemy;

/**
 * @author Oskar
 * A builder for Waves
 * Defines the wave by calling different methods on object and then finalizing it by calling {@code toWave()}
 * <p>
 * Example:
 * <pre>
 * {@code
 * Wave w = createSequence()
 *          .spawn(this::createWeakEnemy)
 *          .delay(10)
 *          .spawn(this::createWeakEnemy)
 *          .delay(5)
 *          .spawn(this::createWeakEnemy)
 *          .toWave()
 * }
 * </pre>
 */
public interface EnemySequence {
    /**
     * Adds the instruction to spawn an enemy
     *
     * @param enemySpawner a lambda which can spawn the enemy
     * @return the object the method was called on (this)
     */
    EnemySequence spawn(Spawner enemySpawner);

    /**
     * Adds the instruction to wait a given number of updates
     *
     * @param numberOfUpdates the number of updates to wait
     * @return the object the method was called on (this)
     */
    EnemySequence delay(int numberOfUpdates);

    /**
     * Finalizes the sequence to a Wave.
     *
     * @return a Wave of this sequence
     */
    Wave toWave();

    @FunctionalInterface
    interface Spawner {
        Enemy spawn();
    }

}
