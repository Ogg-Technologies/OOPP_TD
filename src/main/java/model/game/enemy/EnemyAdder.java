package model.game.enemy;

/**
 * @author Oskar
 * Interface for adding enemies to the game.
 * Implemented by EnemyHandler and used by WaveHandler
 */
public interface EnemyAdder {
    void addEnemy(Enemy enemy);
}
