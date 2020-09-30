package model.game.wave;

import model.game.enemy.Enemy;

public interface EnemySequence {
    EnemySequence spawn(Spawner enemySpawner);

    EnemySequence delay(int numberOfUpdates);

    EnemySequence repeat(EnemySequence sequence, int times);

    Wave toWave();

    @FunctionalInterface
    interface Spawner {
        Enemy spawn();
    }

    class Factory {
        static EnemySequence create() {
            return new DefaultEnemySequence();
        }
    }
}
