package model.game.wave;

import model.game.enemy.Enemy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Oskar
 * An implementation of EnemySequence which uses a list of Commands.
 * It also groups directly following spawn() or delay() calls into a single command.
 */
class DefaultEnemySequence implements EnemySequence {

    List<Command> commands = new ArrayList<>();
    int currentDelay = 0;
    Collection<Enemy> currentEnemies = new ArrayList<>();

    @Override
    public EnemySequence spawn(Spawner spawner) {
        if (spawner == null) {
            throw new IllegalArgumentException("spawner can not be null");
        }
        flushDelay();
        currentEnemies.add(spawner.spawn());
        return this;
    }

    @Override
    public EnemySequence delay(int numberOfUpdates) {
        if (numberOfUpdates < 0) {
            throw new IllegalArgumentException("numberOfUpdates: " + numberOfUpdates + " can not be a negative number");
        }
        if (numberOfUpdates == 0) {
            return this;
        }
        flushSpawners();
        currentDelay += numberOfUpdates;
        return this;
    }

    @Override
    public EnemySequence spawnMultipleWithDelay(Spawner spawner, int numberOfEnemies, int delayInBetween) {
        if (numberOfEnemies < 0) {
            throw new IllegalArgumentException("numberOfEnemies: " + numberOfEnemies + " can not be a negative number");
        }
        spawn(spawner);
        for (int i = 0; i < numberOfEnemies - 1; i++) {
            delay(delayInBetween);
            spawn(spawner);
        }
        return this;
    }

    private void flushSpawners() {
        if (currentEnemies.size() > 0) {
            commands.add(new Spawn(currentEnemies));
            currentEnemies = new ArrayList<>();
        }
    }

    private void flushDelay() {
        if (currentDelay != 0) {
            commands.add(new Delay(currentDelay));
            currentDelay = 0;
        }
    }

    @Override
    public Wave toWave() {
        flushDelay();
        flushSpawners();
        return new DefaultWave(commands);
    }

}
