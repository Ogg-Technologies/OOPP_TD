package model.game.wave;

import model.game.Mock;
import model.game.enemy.EnemyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaveDataTest {

    private WaveData waveData;

    @BeforeEach
    void setUp() {
        EnemyFactory enemyFactory = Mock.createMockEnemyFactory();
        waveData = new WaveData(enemyFactory);
    }

    @Test
    void gettingLevelUnder1ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> waveData.getWave(0));
        assertThrows(IllegalArgumentException.class, () -> waveData.getWave(-1));
    }

    @Test
    void noWavesAreNullOrEmpty() {
        for (int level = 0; level < waveData.numberOfDefinedWaves() + 1000; level++) {
            Wave w = waveData.getWave(1);
            assertNotNull(w);
            assertTrue(w.hasNext());
            assertTrue(w.getRemainingHealth() > 0);
        }
    }

}