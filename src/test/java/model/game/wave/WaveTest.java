package model.game.wave;

import model.game.Mock;
import model.game.enemy.Enemy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oskar
 */
class WaveTest {
    private EnemySequence seq() {
        return EnemySequenceFactory.create();
    }

    private Wave createEmptyWave() {
        EnemySequence e = seq();
        return e.toWave();
    }

    private Enemy firstEnemy() {
        return Mock.createMockEnemy(null);
    }

    private static void assertNumberOfNewEnemies(int amount, Wave wave) {
        assertEquals(amount, wave.next().size());
    }

    @Test
    void emptyWaveIsFinished() {
        Wave w = createEmptyWave();
        assertFalse(w.hasNext());
    }

    @Test
    void updatingFinishedWaveThrowsException() {
        Wave w = createEmptyWave();
        assertFalse(w.hasNext());
        assertThrows(IllegalStateException.class, () -> w.next());
    }

    @Test
    void waveWithOneEnemyIsNotFinished() {
        Wave w = seq()
                .spawn(this::firstEnemy)
                .toWave();
        assertTrue(w.hasNext());
    }

    @Test
    void waveWith1UpdateDelayIsNotFinished() {
        Wave w = seq()
                .delay(1)
                .toWave();
        assertTrue(w.hasNext());
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
    void waveWith0UpdateDelayIsFinished() {
        Wave w = seq()
                .delay(0)
                .toWave();
        assertFalse(w.hasNext());
    }

    @Test
    void waveWithOneEnemyReturnsThatEnemyOnFirstNext() {
        Wave w = seq()
                .spawn(this::firstEnemy)
                .toWave();
        assertNumberOfNewEnemies(1, w);
        assertFalse(w.hasNext());
    }

    @Test
    void canSpawnMultipleEnemiesAtOnce() {
        Wave w = seq()
                .spawn(this::firstEnemy)
                .spawn(this::firstEnemy)
                .spawn(this::firstEnemy)
                .spawn(this::firstEnemy)
                .toWave();
        assertNumberOfNewEnemies(4, w);
        assertFalse(w.hasNext());
    }

    @Test
    void canSpawnAfterDelay() {
        Wave w = seq()
                .delay(3)
                .spawn(this::firstEnemy)
                .toWave();
        for (int i = 0; i < 3; i++) {
            assertNumberOfNewEnemies(0, w);
        }
        assertNumberOfNewEnemies(1, w);
        assertFalse(w.hasNext());
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

        assertNumberOfNewEnemies(1, w);
        assertNumberOfNewEnemies(1, w);
        assertNumberOfNewEnemies(0, w);
        assertNumberOfNewEnemies(0, w);
        assertNumberOfNewEnemies(1, w);
    }

    @Test
    void canGetHealthOfRemainingEnemies() {
        Wave w = seq()
                .spawn(this::firstEnemy)
                .delay(1)
                .spawn(this::firstEnemy)
                .spawn(this::firstEnemy)
                .delay(1)
                .spawn(this::firstEnemy)
                .toWave();
        assertEquals(4, w.getRemainingHealth());
        w.next();
        assertEquals(3, w.getRemainingHealth());
        w.next();
        assertEquals(1, w.getRemainingHealth());
        w.next();
        assertEquals(0, w.getRemainingHealth());
        assertFalse(w.hasNext());
    }

    @Test
    void canSpawnMultipleEnemiesWithGivenDelay() {
        Wave w = seq()
                .spawnMultipleWithDelay(this::firstEnemy, 4, 2)
                .toWave();
        assertNumberOfNewEnemies(1, w);
        assertNumberOfNewEnemies(0, w);
        assertNumberOfNewEnemies(1, w);
        assertNumberOfNewEnemies(0, w);
        assertNumberOfNewEnemies(1, w);
        assertNumberOfNewEnemies(0, w);
        assertNumberOfNewEnemies(1, w);
    }
}