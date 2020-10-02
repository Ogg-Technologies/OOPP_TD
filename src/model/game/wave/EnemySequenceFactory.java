package model.game.wave;

/**
 * @author Oskar
 * A factory for creating an EnemySequence
 */
class EnemySequenceFactory {
    static EnemySequence create() {
        return new DefaultEnemySequence();
    }
}
