package model.game.wave;

import model.game.enemy.Enemy;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Author Oskar
 * An interface representing a Wave of enemies in the Game.
 * Each element in the iterator represents the enemies that should spawn that update in the game.
 * When hasNext() == false, the wave has finished
 * <p>
 * Example:
 * <pre>
 * {@code
 * void gameUpdate() {
 *     if (wave.hasNext) {
 *         enemies.add(wave.next());
 *     }
 * }
 * }
 * </pre>
 */
public interface Wave extends Iterator<Collection<Enemy>> {
    /**
     * Looks at all the remaining enemies and returns the sum of their health.
     *
     * @return the total health of all remaining enemies
     */
    int getRemainingHealth();
}
