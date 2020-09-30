package model.game.wave;

import model.game.enemy.Enemy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

class DefaultEnemySequence implements EnemySequence {
    List<Wave> waveSegments = new ArrayList<>();

    @Override
    public EnemySequence spawn(Spawner spawner) {
        if (spawner == null) {
            throw new IllegalArgumentException("spawner can not be null");
        }
        waveSegments.add(createSpawnWaveSegment(spawner));
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
        waveSegments.add(createDelayWaveSegment(numberOfUpdates));
        return this;
    }

    private Wave createSpawnWaveSegment(Spawner spawner) {
        return new Wave() {
            @Override
            public void update() {
            }

            @Override
            public Collection<Enemy> getNewEnemies() {
                HashSet<Enemy> enemies = new HashSet<>();
                enemies.add(spawner.spawn());
                return enemies;
            }

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public int getRemainingHealth() {
                return spawner.spawn().getHealth().getCurrent();
            }
        };
    }

    private Wave createDelayWaveSegment(int numberOfUpdates) {
        return new Wave() {
            int i = 0;

            @Override
            public void update() {
                i++;
            }

            @Override
            public Collection<Enemy> getNewEnemies() {
                HashSet<Enemy> enemies = new HashSet<>();
                return enemies;
            }

            @Override
            public boolean isFinished() {
                return i >= numberOfUpdates;
            }

            @Override
            public int getRemainingHealth() {
                return 0;
            }
        };
    }

    @Override
    public Wave toWave() {
        return new DefaultWave(this);
    }
}
