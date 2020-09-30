package model.game.wave;

import model.game.enemy.Enemy;

import java.util.Collection;
import java.util.HashSet;

class DefaultWave implements Wave {

    private DefaultEnemySequence sequence;

    public DefaultWave(DefaultEnemySequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void update() {
        if (isFinished()) {
            throw new IllegalStateException("Cannot update a finished Wave");
        }
    }

    @Override
    public Collection<Enemy> getNewEnemies() {
        HashSet<Enemy> enemies = new HashSet<>();
        return enemies;
    }

    @Override
    public boolean isFinished() {
        return sequence.finished;
    }

    @Override
    public int getRemainingHealth() {
        return 0;
    }
}
