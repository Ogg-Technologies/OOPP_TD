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
        if (currentWaveSegment == null) {
            nextWaveSegment();
            return;
        }
        if (currentWaveSegment.isFinished() && !isFinished()) {
            nextWaveSegment();
        }
        currentWaveSegment.update();
    }

    @Override
    public Collection<Enemy> getNewEnemies() {
        HashSet<Enemy> enemies = new HashSet<>();
        if (currentWaveSegment == null) {
            return enemies;
        }
        enemies.addAll(currentWaveSegment.getNewEnemies());
        return enemies;
    }

    private void nextWaveSegment() {
        index++;
        currentWaveSegment = sequence.getWaveSegment(index);
    }

    @Override
    public boolean isFinished() {
        if (sequence.segments.isEmpty()) {
            return true; // This wave is empty so it is always finished
        }
        if (currentWaveSegment == null) {
            return false; // Update has never been called yet
        }
        if (sequence.segments.size() > index + 1) {
            return false; // There are still waveSegments left in the enemySequence
        }
        return currentWaveSegment.isFinished();
    }

    @Override
    public int getRemainingHealth() {
        return 0;
    }
}
