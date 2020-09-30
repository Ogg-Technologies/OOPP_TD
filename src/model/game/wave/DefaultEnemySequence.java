package model.game.wave;

import model.game.enemy.Enemy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

class DefaultEnemySequence implements EnemySequence {
    List<Segment> segments = new ArrayList<>();
    int currentDelay = 0;
    Collection<Spawner> currentSpawners = new ArrayList<>();

    public Wave getWaveSegment(int index) {
        return segments.get(index).toWave();
    }

    @Override
    public EnemySequence spawn(Spawner spawner) {
        if (spawner == null) {
            throw new IllegalArgumentException("spawner can not be null");
        }
        flushDelay();
        currentSpawners.add(spawner);
        return this;
    }

    private void flushDelay() {
        if (currentDelay != 0) {
            segments.add(createDelayWaveSegment(currentDelay));
            currentDelay = 0;
        }
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
            segments.add(createSpawnWaveSegment(currentSpawners));
            currentSpawners = new ArrayList<>();
        }
    }

    @Override
    public Wave toWave() {
        flushDelay();
        flushSpawners();
        return new DefaultWave(this);
    }

    private Segment createSpawnWaveSegment(Collection<Spawner> spawners) {
        return () -> new Wave() {
            @Override
            public void update() {
            }

            @Override
            public Collection<Enemy> getNewEnemies() {
                HashSet<Enemy> enemies = new HashSet<>();
                for (Spawner s : spawners) {
                    enemies.add(s.spawn());
                }
                return enemies;
            }

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public int getRemainingHealth() {
                return 0;
            }
        };
    }

    private Segment createDelayWaveSegment(int numberOfUpdates) {
        return () -> new Wave() {
            int i = 0;

            @Override
            public void update() {
                i++;
            }

            @Override
            public Collection<Enemy> getNewEnemies() {
                return new HashSet<>();
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

    private interface Segment {
        Wave toWave();
    }
}
