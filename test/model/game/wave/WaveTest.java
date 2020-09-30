package model.game.wave;

import model.game.Mock;
import model.game.enemy.Enemy;
import org.junit.jupiter.api.Disabled;
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
        assertTrue(w.isFinished());
    }

    @Test
    void canSpawnMultipleEnemiesAtOnce() {
        Wave w = seq()
                .spawn(this::firstEnemy)
                .spawn(this::firstEnemy)
                .spawn(this::firstEnemy)
                .spawn(this::firstEnemy)
                .toWave();
        w.update();
        Collection<Enemy> enemies = w.getNewEnemies();
        assertEquals(4, enemies.size());
        assertTrue(w.isFinished());
    }

    @Test
    void canSpawnAfterDelay() {
        Wave w = seq()
                .delay(3)
                .spawn(this::firstEnemy)
                .toWave();
        for (int i = 0; i < 3; i++) {
            w.update();
            Collection<Enemy> enemies = w.getNewEnemies();
            assertEquals(0, enemies.size());
        }
        w.update();
        Collection<Enemy> enemies = w.getNewEnemies();
        assertEquals(1, enemies.size());
        assertTrue(w.isFinished());
    }

    @Disabled
    @Test
    void canRepeatAnEnemySequence() {
        EnemySequence e = seq()
                .spawn(this::firstEnemy)
                .delay(2);
        EnemySequence repeated = seq()
                .repeat(e, 4);
        Wave w = repeated.toWave();
        for (int i = 0; i < 4; i++) {
            w.update();
            assertEquals(1, w.getNewEnemies().size());
            w.update();
            assertEquals(0, w.getNewEnemies().size());
            w.update();
            assertEquals(0, w.getNewEnemies().size());
        }
        assertTrue(w.isFinished());
    }
}