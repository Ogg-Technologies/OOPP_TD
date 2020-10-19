package model.game.wave.waveimplementation;

import model.game.enemy.Enemy;

import java.util.Collection;

/**
 * @author Oskar
 * A class describing something to do in an EnemySequence
 */
interface Command {
    void accept(CommandVisitor visitor);
}

/**
 * @author Oskar
 * Visitor for Delay and Spawn commands
 */
interface CommandVisitor {
    void visit(Delay delayCommand);

    void visit(Spawn spawnCommand);
}

/**
 * @author Oskar
 * The action of waiting a number of updates
 */
class Delay implements Command {
    final int updates;

    Delay(int updates) {
        this.updates = updates;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }

}

/**
 * @author Oskar
 * The action of spawning enemies
 */
class Spawn implements Command {
    final Collection<Enemy> enemies;

    Spawn(Collection<Enemy> enemies) {
        this.enemies = enemies;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }

}
