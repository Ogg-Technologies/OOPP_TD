package model.game.wave.waveimplementation;

import model.game.enemy.Enemy;
import model.game.wave.Wave;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Oskar
 * An implementation of Wave built up by commands in a list
 */
class DefaultWave implements Wave {

    private List<Command> commands;
    private int currentDelay = 0;
    private int index = -1;

    public DefaultWave(List<Command> commands) {
        this.commands = commands;
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
        while (hasNext()) {
            index++;
            Command c = commands.get(index);
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
            if (currentDelay > 0) {
                currentDelay--;
                break;
            }
        }
        return enemies;
    }

    @Override
    public boolean hasNext() {
        return commands.size() > index + 1;
    }

    @Override
    public int getRemainingHealth() {
        if (!hasNext()) {
            return 0;
        }

        List<Command> remainingCommands = commands.subList(index + 1, commands.size());
        return getAllEnemies(remainingCommands)
                .stream()
                .map(enemy -> enemy.getHealth().getCurrent())
                .reduce(0, Integer::sum);
    }

    @Override
    public Set<Class<? extends Enemy>> getEnemyTypes() {
        return getAllEnemies(commands).stream()
                .map(Enemy::getClass)
                .collect(Collectors.toSet());
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
