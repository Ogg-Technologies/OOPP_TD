package model.game.wave;

import model.game.enemy.Enemy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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
                    enemies.addAll(spawnCommand.enemies);
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
        if (!hasNext()) {
            return 0;
        }

        List<Command> remainingCommands = sequence.commands.subList(index + 1, sequence.commands.size());
        return getAllEnemies(remainingCommands)
                .stream()
                .map(enemy -> enemy.getHealth().getCurrent())
                .reduce(0, Integer::sum);
    }

    private Collection<Enemy> getAllEnemies(Collection<Command> commands) {
        Collection<Enemy> enemies = new ArrayList<>();
        for (Command c : commands) {
            c.accept(new CommandVisitor() {
                @Override
                public void visit(Delay delayCommand) {
                }

                @Override
                public void visit(Spawn spawnCommand) {
                    enemies.addAll(spawnCommand.enemies);
                }
            });
        }
        return enemies;
    }
}
