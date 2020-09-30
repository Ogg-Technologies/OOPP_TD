package model.game.wave;

import model.game.Mock;
import model.game.enemy.Enemy;
import org.junit.jupiter.api.Test;

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

    private static void assertNumberOfNewEnemies(int amount, Wave wave) {
        assertEquals(amount, wave.getNewEnemies().size());
    }

    @Test
    void getNewEnemiesOnNonUpdatedWaveReturnsEmptyCollection() {
        Wave w = seq()
                .spawn(this::firstEnemy)
                .toWave();
        assertNumberOfNewEnemies(0, w);
    }

    @Test
    void waveWithOneEnemyReturnsThatEnemyOnFirstUpdate() {
        Wave w = seq()
                .spawn(this::firstEnemy)
                .toWave();
        w.update();
        assertNumberOfNewEnemies(1, w);
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
        assertNumberOfNewEnemies(4, w);
        assertTrue(w.isFinished());
    }

    @Test
    void canSpawnAfterDelay() {
        Wave w = seq()
                .delay(3)
                .spawn(this::firstEnemy)
                .toWave();
        for (int i = 0; i < 4; i++) {
            w.update();
            assertNumberOfNewEnemies(0, w);
        }
        w.update();
        assertNumberOfNewEnemies(1, w);
        assertTrue(w.isFinished());
    }

    @Test
    void canAlternateSpawningAndDelaying() {
        Wave w = seq()
                .spawn(this::firstEnemy)
                .delay(1)
                .spawn(this::firstEnemy)
                .delay(3)
                .spawn(this::firstEnemy)
                .delay(1)
                .toWave();

        w.update();
        assertNumberOfNewEnemies(1, w);
        w.update();
        assertNumberOfNewEnemies(0, w);
        w.update();
        assertNumberOfNewEnemies(1, w);
        w.update();
        assertNumberOfNewEnemies(0, w);
        w.update();
        assertNumberOfNewEnemies(0, w);
        w.update();
        assertNumberOfNewEnemies(0, w);
        w.update();
        assertNumberOfNewEnemies(1, w);
        w.update();
        assertNumberOfNewEnemies(0, w);
        assertTrue(w.isFinished());
    }
}