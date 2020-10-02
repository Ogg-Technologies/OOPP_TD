package model.game.wave;

import model.game.enemy.Enemy;

import java.util.Collection;
import java.util.HashSet;

class DefaultWave implements Wave {

    private DefaultEnemySequence sequence;
    private int delay = 0;
    private int index = -1;

    public DefaultWave(DefaultEnemySequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public Collection<Enemy> next() {
        if (!hasNext()) {
            throw new IllegalStateException("Cannot update a finished Wave");
        }
        Collection<Enemy> enemies = new HashSet<>();
        if (delay > 0) {
            delay--;
            return enemies;
        }
        while (hasNext() && delay <= 0) {
            index++;
            Command c = sequence.commands.get(index);
            if (c instanceof Spawn) {
                for (EnemySequence.Spawner s : ((Spawn) c).spawners) {
                    enemies.add(s.spawn());
                }
            } else if (c instanceof Delay) {
                delay += ((Delay) c).updates;
            }
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
