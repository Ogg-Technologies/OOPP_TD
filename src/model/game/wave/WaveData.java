package model.game.wave;

import model.game.enemy.EnemyFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oskar
 * Class which defines all waves in the game.
 * <p>
 * The first levels are manually declared in this class. After those levels, there is an infinite number of
 * algorithmically created levels
 */
public class WaveData {
    private List<WaveCreator> definedWaves = new ArrayList<>();
    private EnemyFactory fac;

    public WaveData(EnemyFactory enemyFactory) {
        this.fac = enemyFactory;
        setupDefinedWaves();
    }

    /**
     * Gets the wave for a given level.
     * Level must be 1 or higher
     *
     * @param level the level number for the wave
     * @return the wave for that level
     */
    public Wave getWave(int level) {
        if (level < 1) {
            throw new IllegalArgumentException("Level: " + level + " must be 1 or higher");
        }
        int index = level - 1;
        if (index < definedWaves.size()) {
            return definedWaves.get(index).create();
        }
        return createWaveFromAlgorithm(level);
    }

    public int numberOfDefinedWaves() {
        return definedWaves.size() + 1;
    }

    private void setupDefinedWaves() {
        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishstick, 10, 90)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishstick, 10, 80)
                .delay(50)
                .spawn(fac::createSailfish)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishstick, 5, 90)
                .delay(30)
                .spawnMultipleWithDelay(fac::createSailfish, 5, 90)
                ::toWave);
    }

    private Wave createWaveFromAlgorithm(int level) {
        EnemySequence s = seq();
        int numEnemies = level;
        int separation = Math.max(1, 1000 / level);
        s.spawnMultipleWithDelay(fac::createFishInABoat, numEnemies, separation);
        return s.toWave();
    }

    private EnemySequence seq() {
        return EnemySequenceFactory.create();
    }

    /**
     * A lambda that can create a Wave
     * These are stored instead of actual waves because once a Wave is created it also creates all enemies it will
     * spawn. This saves memory by not creating those Waves until they are fetched.
     */
    @FunctionalInterface
    private interface WaveCreator {
        Wave create();
    }
}
