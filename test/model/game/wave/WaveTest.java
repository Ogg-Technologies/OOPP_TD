package model.game.wave;

import model.game.Mock;
import model.game.enemy.Enemy;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class WaveTest {
    private EnemySequence seq() {
        return EnemySequence.Factory.create();
    }

    private Wave createEmptyWave() {
        EnemySequence e = seq();
        return e.toWave();
    }

    private Enemy firstEnemy() {
        return Mock.createMockEnemy(null);
    }

    @Test
    void emptyWaveIsFinished() {
        Wave v = createEmptyWave();
        assertTrue(v.isFinished());
    }

    @Test
    void updatingFinishedWaveThrowsException() {
        Wave v = createEmptyWave();
        assertTrue(v.isFinished());
        assertThrows(IllegalStateException.class, () -> v.update());
    }

    @Test
    void waveWithOneEnemyIsNotFinished() {
        Wave w = seq()
                .spawn(this::firstEnemy)
                .toWave();
        assertFalse(w.isFinished());
    }

    @Test
    void waveWith1UpdateDelayIsNotFinished() {
        Wave w = seq()
                .delay(1)
                .toWave();
        assertFalse(w.isFinished());
    }

    @Test
    void waveWith0UpdateDelayIsFinished() {
        Wave w = seq()
                .delay(0)
                .toWave();
        assertTrue(w.isFinished());
    }

    @Test
    void addingNegativeDelayThrowsIllegalArgumentException() {
        EnemySequence e = seq();
        assertThrows(IllegalArgumentException.class, () -> e.delay(-1));
    }

    @Test
    void spawningWithNullSpawnerThrowsIllegalArgumentException() {
        EnemySequence e = seq();
        assertThrows(IllegalArgumentException.class, () -> e.spawn(null));
    }

    @Test
    void getNewEnemiesOnNonUpdatedWaveReturnsEmptyCollection() {
        Wave w = seq()
                .spawn(this::firstEnemy)
                .toWave();
        Collection<Enemy> enemies = w.getNewEnemies();
        assertEquals(0, enemies.size());
    }

    @Test
    void waveWithOneEnemyReturnsThatEnemyOnFirstUpdate() {
        Wave w = seq()
                .spawn(this::firstEnemy)
                .toWave();
        w.update();
        Collection<Enemy> enemies = w.getNewEnemies();
        assertEquals(1, enemies.size());
    }
}