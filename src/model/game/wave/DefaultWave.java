package model.game.wave;

import model.game.enemy.Enemy;

import java.util.Collection;
import java.util.HashSet;

class DefaultWave implements Wave {

    private DefaultEnemySequence sequence;
    private int currentDelay = 0;
    private int index = -1;

    public DefaultWave(DefaultEnemySequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public Collection<Enemy> next() {
        if (!hasNext()) {
            throw new IllegalStateException("Cannot update a finished Wave");
        }

        if (currentDelay > 0) {
            currentDelay--;
            return new HashSet<>();
        }

        return getEnemiesUntilNextDelay();
    }

    private Collection<Enemy> getEnemiesUntilNextDelay() {
        Collection<Enemy> enemies = new HashSet<>();
        while (hasNext() && currentDelay <= 0) {
            index++;
            Command c = sequence.commands.get(index);
            c.accept(new CommandVisitor() {
                @Override
                public void visit(Delay delayCommand) {
                    currentDelay += delayCommand.updates;
                }

                @Override
                public void visit(Spawn spawnCommand) {
                    for (EnemySequence.Spawner s : spawnCommand.spawners) {
                        enemies.add(s.spawn());
                    }
                }
            });
        }
        return enemies;
    }

    @Override
    public boolean hasNext() {
        return sequence.commands.size() > index + 1;
    }

    @Override
    public int getRemainingHealth() {
        return 0;
    }
}
