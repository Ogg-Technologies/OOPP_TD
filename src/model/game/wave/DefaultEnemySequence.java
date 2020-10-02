package model.game.wave;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class DefaultEnemySequence implements EnemySequence {

    List<Command> commands = new ArrayList<>();
    int currentDelay = 0;
    Collection<Spawner> currentSpawners = new ArrayList<>();

    @Override
    public EnemySequence spawn(Spawner spawner) {
        if (spawner == null) {
            throw new IllegalArgumentException("spawner can not be null");
        }
        flushDelay();
        currentSpawners.add(spawner);
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

    private void flushSpawners() {
        if (currentSpawners.size() > 0) {
            commands.add(new Spawn(currentSpawners));
            currentSpawners = new ArrayList<>();
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
        return new DefaultWave(this);
    }
}
