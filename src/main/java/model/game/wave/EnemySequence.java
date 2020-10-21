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
     * Spawns a series of enemies spaced out by a given delay
     *
     * @param spawner         a method for which enemy to spawn
     * @param numberOfEnemies how many enemies to spawn in total
     * @param delayInBetween  the number of updates to delay between each enemy spawning
     * @return
     */
    EnemySequence spawnMultipleWithDelay(Spawner spawner, int numberOfEnemies, int delayInBetween);

    /**
     * Finalizes the sequence to a Wave.
     * <p>
     * You are not allowed to change the EnemySequence after calling this function. Doing so will result in undefined
     * behavior for the created wave.
     *
     * @return a Wave of this sequence
     */
    Wave toWave();

    @FunctionalInterface
    interface Spawner {
        Enemy spawn();
    }

}
