package model.game.wave;

import model.game.enemy.Enemy;

import java.util.Collection;
import java.util.HashSet;

class DefaultWave implements Wave {

    private DefaultEnemySequence sequence;
    private int index = -1;
    private Wave currentWaveSegment = null;

    public DefaultWave(DefaultEnemySequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void update() {
        if (isFinished()) {
            throw new IllegalStateException("Cannot update a finished Wave");
        }
        if (currentWaveSegment == null || currentWaveSegment.isFinished()) {
            nextWaveSegment();
        } else {
            currentWaveSegment.update();
        }
    }

    @Override
    public Collection<Enemy> getNewEnemies() {
        HashSet<Enemy> enemies = new HashSet<>();
        if (currentWaveSegment == null) {
            return enemies;
        }
        enemies.addAll(currentWaveSegment.getNewEnemies());
        while (!isFinished() && currentWaveSegment.isFinished()) {
            nextWaveSegment();
            enemies.addAll(currentWaveSegment.getNewEnemies());
        }
        return enemies;
    }

    private void nextWaveSegment() {
        index += 1;
        currentWaveSegment = sequence.waveSegments.get(index);
        currentWaveSegment.update();
    }

    @Override
    public boolean isFinished() {
        if (sequence.waveSegments.isEmpty()) {
            return true; // This wave is empty so it is always finished
        }
        if (sequence.waveSegments.size() > index + 1) {
            return false; // There are still waveSegments left in the enemySequence
        }
        if (currentWaveSegment == null) {
            return false; // Update has never been called yet
        }
        return currentWaveSegment.isFinished();
    }

    @Override
    public int getRemainingHealth() {
        return 0;
    }
}
