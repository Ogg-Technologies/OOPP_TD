package model.game.wave;

import model.game.enemy.Enemy;

import java.util.Collection;

interface CommandVisitor {
    void visit(Delay delayCommand);

    void visit(Spawn spawnCommand);
}

interface Command {
    void accept(CommandVisitor visitor);
}

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
