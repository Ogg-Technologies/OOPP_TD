package model.game.wave;

import model.game.enemy.EnemyFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oskar, Erik
 * Class which defines all waves in the game.
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
        // Introduce fishStick
        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishstick, 10, 90)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishstick, 10, 80)
                .delay(50)
                .spawn(fac::createSwordfish)
                ::toWave);

        // Introduce swordFish
        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishstick, 5, 90)
                .delay(30)
                .spawnMultipleWithDelay(fac::createSwordfish, 5, 90)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createSwordfish, 15, 40)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishstick, 100, 5)
                ::toWave);

        // Introduce FishAndChips
        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishAndChips, 5, 100)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishAndChips, 5, 50)
                ::toWave);

        // Introduce FishInABoat
        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishInABoat, 5, 200)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishInABoat, 3, 20)
                .delay(300)
                .spawnMultipleWithDelay(fac::createFishInABoat, 3, 20)
                ::toWave);

        // Introduce FlyingFish
        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishInABoat, 4, 90)
                .delay(180)
                .spawnMultipleWithDelay(fac::createFlyingFish, 10, 90)
                ::toWave
        );

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFlyingFish, 8, 40)
                .spawnMultipleWithDelay(fac::createFishAndChips, 8, 40)
                .delay(300)
                .spawnMultipleWithDelay(fac::createFlyingFish, 15, 20)
                ::toWave
        );

        // Introduce SailFish
        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishstick, 100, 2)
                .delay(300)
                .spawnMultipleWithDelay(fac::createFishAndChips, 50, 5)
                .delay(300)
                .spawnMultipleWithDelay(fac::createSailfish, 5, 200)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishInABoat, 3, 60)
                .delay(5)
                .spawnMultipleWithDelay(fac::createSailfish, 10, 60)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createSailfish, 10, 50)
                .delay(5)
                .spawnMultipleWithDelay(fac::createFishInABoat, 5, 40)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createSailfish, 10, 20)
                .delay(500)
                .spawnMultipleWithDelay(fac::createSailfish, 10, 20)
                ::toWave);

        // Introduce shark
        definedWaves.add(seq()
                .spawn(fac::createShark)
                .delay(30)
                .spawnMultipleWithDelay(fac::createSailfish, 10, 20)
                .delay(5)
                .spawnMultipleWithDelay(fac::createFishInABoat, 20, 40)
                .delay(30)
                .spawn(fac::createShark)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createShark, 3, 60)
                .delay(400)
                .spawnMultipleWithDelay(fac::createShark, 3, 60)
                .delay(400)
                .spawnMultipleWithDelay(fac::createShark, 8, 40)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createSailfish, 20, 5)
                .delay(100)
                .spawnMultipleWithDelay(fac::createShark, 8, 60)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishstick, 10, 5)
                .spawnMultipleWithDelay(fac::createSwordfish, 10, 8)
                .spawnMultipleWithDelay(fac::createFishAndChips, 10, 9)
                .delay(60)
                .spawnMultipleWithDelay(fac::createFishInABoat, 10, 11)
                .spawnMultipleWithDelay(fac::createFlyingFish, 10, 8)
                .delay(30)
                .spawnMultipleWithDelay(fac::createSailfish, 10, 15)
                ::toWave
        );

        // Introduce fishInAFishTank
        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishInABoat, 40, 10)
                .delay(10)
                .spawn(fac::createFishInFishTank)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createFishInFishTank, 4, 200)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createShark, 20, 60)
                .delay(10)
                .spawnMultipleWithDelay(fac::createFishInFishTank, 10, 200)
                ::toWave);

        definedWaves.add(seq()
                .spawnMultipleWithDelay(fac::createSailfish, 100, 10)
                .delay(10)
                .spawnMultipleWithDelay(fac::createFishInFishTank, 20, 60)
                ::toWave);

        // Introduce Tank Sinatra
        definedWaves.add(seq()
                .spawn(fac::createTankSinatra)
                ::toWave
        );
    }

    private Wave createWaveFromAlgorithm(int level) {
        EnemySequence s = seq();
        int baseNumEnemies = level / 5;
        int baseSeparation = Math.max(1, 1000 / level);
        s.spawnMultipleWithDelay(fac::createFishInFishTank, baseNumEnemies, baseSeparation * 4);
        s.spawnMultipleWithDelay(fac::createShark, baseNumEnemies * 2, baseSeparation);
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
