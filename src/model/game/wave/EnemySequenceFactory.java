package model.game.wave;

import model.game.wave.waveimplementation.DefaultEnemySequence;

/**
 * @author Oskar
 * A factory for creating an EnemySequence
 */
class EnemySequenceFactory {
    static EnemySequence create() {
        return new DefaultEnemySequence();
    }
}
