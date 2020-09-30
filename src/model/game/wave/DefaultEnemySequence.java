package model.game.wave;

class DefaultEnemySequence implements EnemySequence {
    boolean finished = true;
    Spawner spawner = null;

    @Override
    public EnemySequence spawn(Spawner spawner) {
        if (spawner == null) {
            throw new IllegalArgumentException("spawner can not be null");
        }
        this.spawner = spawner;
        finished = false;
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
        finished = false;
        return this;
    }

    @Override
    public Wave toWave() {
        return new DefaultWave(this);
    }
}
